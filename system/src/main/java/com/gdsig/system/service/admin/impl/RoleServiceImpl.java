package com.gdsig.system.service.admin.impl;

import com.alibaba.fastjson.JSONObject;
import com.gdsig.common.dto.system.FunctionListParam;
import com.gdsig.common.dto.system.MenuListParam;
import com.gdsig.common.dto.system.RoleDto;
import com.gdsig.common.dto.system.RoleListParam;
import com.gdsig.common.result.RespCodeEnum;
import com.gdsig.common.result.Result;
import com.gdsig.common.util.TypeUtil;
import com.gdsig.common.vo.RoleVO;
import com.gdsig.mybatis.mapper.*;
import com.gdsig.mybatis.model.*;
import com.gdsig.system.dao.*;
import com.gdsig.system.service.admin.RoleService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author xs
 * @date 2022/12/1上午 10:26
 */

@Service("admin/RoleService")
public class RoleServiceImpl implements RoleService {

    @Resource
    BdAccountMapper accountMapper;

    @Resource
    BdRoleMapper objMapper;
    @Resource
    BdRoleDao objDao;

    @Resource
    BdRoleMappingMapper roleMappingMapper;

    @Resource
    BdMenuMapper menuMapper;
    @Resource
    BdMenuDao menuDao;

    @Resource
    BdMenuMappingMapper menuMappingMapper;
    @Resource
    BdMenuMappingDao menuMappingDao;

    @Resource
    BdRoleMenuDao roleMenuDao;
    @Resource
    BdRoleMenuMapper roleMenuMapper;

    @Resource
    BdRoleMenuMappingDao roleMenuMappingDao;
    @Resource
    BdRoleMenuMappingMapper roleMenuMappingMapper;

    /**
     * 查询记录
     *
     * @param roleListParam 过滤条件
     * @return {@code Result<List<RoleVO>>}
     */
    @Override
    public List<RoleVO> searchRecord(RoleListParam roleListParam) {
        PageHelper.startPage(roleListParam.getPage(), roleListParam.getLimit());

        List<BdRole> roles = objDao.searchRecord(roleListParam);

        List<RoleVO> roleVos = new ArrayList<>();
        roles.forEach(obj -> {
            RoleVO roleVO = new RoleVO();
            roleVO.setId(obj.getId());
            roleVO.setName(obj.getName());
            roleVos.add(roleVO);
        });

        return roleVos;
    }

    /**
     * 查询详情
     *
     * @param accountId 账号id
     * @param id        角色id
     * @return {@link RoleVO}
     */
    @Override
    public Result<RoleVO> findById(String accountId, Integer id) {
        RoleVO roleVO = new RoleVO();
        BdRole role;
        if (id == null || (role = objMapper.selectByPrimaryKey(id)) == null) {
            return new Result<>(false, "role is null");
        }

        roleVO.setName(role.getName());
        roleVO.setId(role.getId());

        return new Result<>(roleVO);
    }

    /**
     * 修改
     *
     * @param accountId 用户id
     * @param id        角色id
     * @param dto       参数
     * @return {@link Result<Void>}
     */
    @Override
    public Result<Void> saveOrUpdate(String accountId, Integer id, RoleDto dto) throws Exception {
        BdAccount account = accountMapper.selectByPrimaryKey(accountId);
        if (account == null) {
            return new Result<>(false, RespCodeEnum.AccountInvalid.getMsg());
        }

        BdRole currAcRole = objMapper.selectByPrimaryKey(account.getRoleId());
        boolean isSa = false;
        boolean currentSa = currAcRole.getSuperadmin();

        BdRole parentObj = objMapper.selectByPrimaryKey(dto.getParentId());
        if (parentObj == null || !parentObj.getStatus()) {
            // 超管角色
            if (currentSa) {
                isSa = true;
                parentObj = currAcRole;
            } else {
                return new Result<>(false, "父类角色无效");
            }
        }

        if (Objects.equals(id, dto.getParentId()) && !isSa) {
            return new Result<>(false, "上级角色不能为当前角色");
        }

        BdRole obj = objMapper.selectByPrimaryKey(id);
        if (obj == null) {
            obj = new BdRole();
            obj.setCreatorId(accountId);
            obj.setCreateTime(new Date());
            obj.setStatus(true);
            obj.setOrgunitId(account.getOrgunitId());
        }

        BeanUtils.copyProperties(dto, obj);
        obj.setCreatorId(account.getId());
        obj.setParentId(isSa ? null : parentObj.getId());

        if (Objects.isNull(obj.getId())) {
            objMapper.insert(obj);
        } else {
            objMapper.updateByPrimaryKey(obj);
        }

        this.refreshPermission(obj, currAcRole, dto.getMenus());
        return new Result<>();
    }

    public void refreshPermission(BdRole obj, BdRole currAcRole, List<RoleDto.Menu> menus) throws Exception {
        if (menus == null) {
            return;
        }

        boolean currentSa = currAcRole.getSuperadmin();

        // 是否当前角色
        boolean isCurrRole = Objects.equals(currAcRole.getId(), obj.getId());

        // 判断当前用户角色是否属于该角色的上级
        boolean isParentRole = false;
        int curRoleId = currAcRole.getId();

        BdRole roleTemp = objMapper.selectByPrimaryKey(obj.getId());
        while (roleTemp != null) {
            if (Objects.equals(curRoleId, roleTemp.getId())) {
                isParentRole = true;
                break;
            }
            roleTemp = objMapper.selectByPrimaryKey(roleTemp.getId());
        }

        // 解析权限JSON数据，提取id和选中状态
        List<Integer> menuIds = new ArrayList<>();
        List<Integer> mappingIds = new ArrayList<>();
        this.recurseMenuMappingId(menuIds, mappingIds, true, menus);

        List<BdRoleMenu> oldRoleMenus = roleMenuDao.findByRoleId(obj.getId());

        if (isCurrRole && !currentSa) {

            for (BdRoleMenu oldRoleMenu : oldRoleMenus) {
                oldRoleMenu.setChecked(menuIds.contains(oldRoleMenu.getMenuId()));

                List<BdRoleMenuMapping> roleMenuMappings = roleMenuMappingDao.findByRoleMenuId(oldRoleMenu.getId());
                for (BdRoleMenuMapping roleMenuMapping : roleMenuMappings) {
                    roleMenuMapping.setChecked(mappingIds.contains(roleMenuMapping.getMenuMappingId()));
                    roleMenuMappingMapper.updateByPrimaryKey(roleMenuMapping);
                }

                roleMenuMapper.updateByPrimaryKey(oldRoleMenu);
            }
        } else if (currentSa || isParentRole) {

            for (BdRoleMenu roleMenu : oldRoleMenus) {
                BdRoleMenuMappingExample rmme = new BdRoleMenuMappingExample();
                rmme.createCriteria().andRoleMenuIdEqualTo(roleMenu.getId());
                roleMenuMappingMapper.deleteByExample(rmme);
            }

            BdRoleMenuExample rme = new BdRoleMenuExample();
            rme.createCriteria().andRoleIdEqualTo(obj.getId());
            roleMenuMapper.deleteByExample(rme);

            if (!menus.isEmpty()) {

                // 获取数据库全部存量菜单，避免重复查询
                Map<Integer, BdMenu> idMenuMap = new HashMap<>(16);
                menuDao.searchRecord(new MenuListParam()).forEach(bdMenu -> idMenuMap.put(bdMenu.getId(), bdMenu));

                // 获取数据库全部存量菜单按钮，避免重复查询
                Map<Integer, BdMenuMapping> mapIdMenuMapping = new HashMap<>(16);
                menuMappingDao.searchRecord(new FunctionListParam()).forEach(mapping -> mapIdMenuMapping.put(mapping.getId(), mapping));

                this.recurseSaveRoleMenu(obj, idMenuMap, mapIdMenuMapping, menus);

            }
        }

        roleMappingMapper.deleteByExample(new BdRoleMappingExample());

        BdRole superAdminRole = objDao.getSuperAdminRole();

        List<BdRole> superadminRoles = objDao.findByParentId(superAdminRole.getId());
        this.saveRoleAndSonsMapping(superAdminRole, superadminRoles);
    }

    void recurseMenuMappingId(List<Integer> menuIds, List<Integer> mappingIds, boolean isMenu, List<RoleDto.Menu> list) {
        if (list == null || list.isEmpty()) {
            return;
        }

        for (RoleDto.Menu obj : list) {
            if (!BooleanUtils.isTrue(obj.getChecked())) {
                continue;
            }
            if (!isMenu) {
                mappingIds.add(obj.getId());
                continue;
            }
            menuIds.add(obj.getId());

            List<RoleDto.Menu> menus = obj.getMenus();
            recurseMenuMappingId(menuIds, mappingIds, true, menus);

            List<RoleDto.Menu> functions = obj.getFunctions();
            recurseMenuMappingId(menuIds, mappingIds, false, functions);
        }
    }

    void recurseSaveRoleMenu(BdRole obj, Map<Integer, BdMenu> idMenuMap, Map<Integer, BdMenuMapping> idMenuMappingMap,
                             List<RoleDto.Menu> menus) {

        if (menus == null || menus.isEmpty()) {
            return;
        }
        for (RoleDto.Menu menu : menus) {
            if (!BooleanUtils.isTrue(menu.getChecked())) {
                continue;
            }
            BdMenu menuObj = idMenuMap.get(menu.getId());
            if (menuObj != null) {
                // 保存新的菜单配置
                BdRoleMenu roleMenu = new BdRoleMenu();
                roleMenu.setMenuId(menuObj.getId());
                roleMenu.setRoleId(obj.getId());
                // 父级/自己 配置菜单，只要节点勾选，都认为是选中状态
                roleMenu.setChecked(true);
                roleMenuMapper.insert(roleMenu);

                this.recurseSaveRoleMenu(obj, idMenuMap, idMenuMappingMap, menu.getMenus());

                List<RoleDto.Menu> functions = menu.getFunctions();
                if (functions == null || functions.isEmpty()) {
                    continue;
                }
                for (RoleDto.Menu function : functions) {
                    int funcId = function.getId();
                    BdMenuMapping funcObj = idMenuMappingMap.get(funcId);
                    if (funcObj != null) {
                        BdRoleMenuMapping mapping = new BdRoleMenuMapping();
                        mapping.setMenuMappingId(funcObj.getId());
                        mapping.setRoleMenuId(roleMenu.getId());
                        // 父级/自己 配置菜单，只要节点勾选，都认为是选中状态
                        mapping.setChecked(true);
                        roleMenuMappingMapper.insert(mapping);
                    }
                }
            }
        }
    }

    public void saveRoleAndSonsMapping(BdRole obj, List<BdRole> roles) {
        // 保存当前角色与自己的关系
        BdRoleMapping mapping = new BdRoleMapping();
        mapping.setParentId(obj.getId());
        mapping.setParentName(obj.getName());
        mapping.setRoleId(obj.getId());
        mapping.setRoleName(obj.getName());
        roleMappingMapper.insert(mapping);

        for (BdRole sonObj : roles) {
            if (sonObj.getSuperadmin()) {
                continue;
            }
            BdRoleMapping objMapping = new BdRoleMapping();
            objMapping.setParentId(obj.getId());
            objMapping.setParentName(obj.getName());
            objMapping.setRoleId(sonObj.getId());
            objMapping.setRoleName(sonObj.getName());
            roleMappingMapper.insert(objMapping);

            // 循环角色与子级关系
            List<BdRole> sonObjRoles = objDao.findByParentId(sonObj.getId());
            this.saveRoleAndSonsMapping(sonObj, sonObjRoles);

            // 循环子级角色与当前角色的所有上级关系
            this.saveRoleAndParentMapping(sonObj, obj);

        }
    }

    public void saveRoleAndParentMapping(BdRole obj, BdRole objParent){
        BdRole objGrandParent;

        if (objParent == null || (objGrandParent = objMapper.selectByPrimaryKey(objParent.getParentId())) == null) {
            return;
        }
        BdRoleMapping objCurrent = new BdRoleMapping();
        objCurrent.setParentId(objGrandParent.getId());
        objCurrent.setParentName(objGrandParent.getName());
        objCurrent.setRoleId(obj.getId());
        objCurrent.setRoleName(obj.getName());
        roleMappingMapper.insert(objCurrent);

        // 继续循环保存与上级角色的关系
        if (!objGrandParent.getSuperadmin()) {
            this.saveRoleAndParentMapping(obj, objGrandParent);
        }
    }

    /**
     * 删除
     *
     * @param accountId 用户id
     * @param id        角色id
     * @return {@link Result<Void>}
     */
    @Override
    public Result<Void> delete(String accountId, Integer id) {
        BdRole role;
        if (id == null || (role = objMapper.selectByPrimaryKey(id)) == null) {
            return new Result<>(false, "role is null");
        }
        role.setStatus(false);
        objMapper.updateByPrimaryKey(role);

        return new Result<>();
    }

    /**
     * 菜单
     *
     * @param id    角色id
     * @param types 顶级菜单类型：1管理平台；2App
     * @return {@link RoleVO}
     */
    @Override
    public Result<RoleVO> menus(Integer id, Integer... types) {
        BdRole role;
        if (id == null || (role = objMapper.selectByPrimaryKey(id)) == null) {
            return new Result<>(false, "role is null");
        }
        BdRoleMenuExample example = new BdRoleMenuExample();
        example.createCriteria().andRoleIdEqualTo(role.getId());
        List<BdRoleMenu> roleMenus = roleMenuMapper.selectByExample(example);

        List<Integer> checkedMenuIds = roleMenus.stream()
                .filter(BdRoleMenu::getChecked)
                .map(BdRoleMenu::getId).collect(Collectors.toList());

        Function<Integer, Boolean> containRootType = Arrays.asList(types)::contains;

        List<RoleVO.Menu> menus = new ArrayList<>();
        JSONObject funcMap = new JSONObject();

        for (BdRoleMenu roleMenu : roleMenus) {
            BdMenu rootMenu = menuMapper.selectByPrimaryKey(roleMenu.getMenuId());
            if (rootMenu.getLevel() == 1 && containRootType.apply(rootMenu.getType())) {

                List<BdMenu> roleMenuChildrens = menuDao.findByParentId(rootMenu.getId());

                loadMenuSonLoop(menus, checkedMenuIds, roleMenuChildrens);
            }

            BdRoleMenuMappingExample roleMenuExample = new BdRoleMenuMappingExample();
            roleMenuExample.createCriteria().andRoleMenuIdEqualTo(roleMenu.getId());
            List<BdRoleMenuMapping> roleMenuMappings = roleMenuMappingMapper.selectByExample(roleMenuExample);

            roleMenuMappings.stream()
                    .filter(BdRoleMenuMapping::getChecked)
//                    .map(BdRoleMenuMapping::getBdMenuMapping)
//                    .filter(bdMenuMapping -> StringUtils.isNotBlank(bdMenuMapping.getNumber()))
                    .sorted(Comparator.comparingInt(BdRoleMenuMapping::getMenuMappingId))
                    .forEach(roleMenuMapping -> {

                        BdMenuMapping menuMapping = menuMappingMapper.selectByPrimaryKey(roleMenuMapping.getMenuMappingId());

                        if (StringUtils.isBlank(menuMapping.getNumber())) {
                            return;
                        }
                        String menuId = menuMapping.getMenuId() + "";
                        String number = menuMapping.getNumber();
                        String name = menuMapping.getName();
                        String css = menuMapping.getCss();
                        List<JSONObject> list;

                        if (funcMap.containsKey(menuId)) {
                            list = funcMap.getObject(menuId, TypeUtil.type(List.class, JSONObject.class));
                        } else {
                            list = new ArrayList<>();
                        }
                        funcMap.put(menuId, list);

                        JSONObject jsonFn = new JSONObject();
                        jsonFn.put("type", number);
                        jsonFn.put("name", name);
                        jsonFn.put("css", css);
                        jsonFn.put("menuId", menuId);
                        list.add(jsonFn);
                    });
        }
        RoleVO vo = new RoleVO();
        vo.setFunction(funcMap);
        vo.setMenus(menus);
        return new Result<>(vo);
    }

    private void loadMenuSonLoop(List<RoleVO.Menu> dataList, List<Integer> checkedMenuIds, List<BdMenu> menus) {

        if (menus == null) {
            return;
        }

        List<BdMenu> menuList = menus.stream().sorted(Comparator.comparingInt(BdMenu::getSequence)).collect(Collectors.toList());
        RoleVO.Menu roleMenu;
        List<RoleVO.Menu> childList;

        for (BdMenu menu : menuList) {
            if (!checkedMenuIds.contains(menu.getId())) {
                continue;
            }
            childList = new ArrayList<>();
            roleMenu = new RoleVO.Menu();

            roleMenu.setTitle(menu.getName());
            if (menu.getLevel() == 1) {
                roleMenu.setExpanded(true);
            }

            if (menu.getLevel() >= 2) {
                RoleVO.QueryParams queryParams = new RoleVO.QueryParams();
                queryParams.setMid(menu.getId());
                roleMenu.setQueryParams(queryParams);
            }

            if (menu.getLevel() == 2) {
                roleMenu.setExpanded(false);
                if (StringUtils.isNotBlank(menu.getIcon())) {
                    RoleVO.Icon icon = new RoleVO.Icon();
                    icon.setPack("kn-menu");
                    icon.setIcon(StringUtils.defaultIfBlank(menu.getIcon(), "layout-outline"));
                    roleMenu.setIcon(icon);
                }
            }

            if (menu.getLevel() > 2) {
                roleMenu.setLink(menu.getPath());
            }

            List<BdMenu> roleMenuChildrens = menuDao.findByParentId(menu.getId());
            if (!roleMenuChildrens.isEmpty()) {
                roleMenu.setChildren(childList);
            }

            dataList.add(roleMenu);
            loadMenuSonLoop(childList, checkedMenuIds, roleMenuChildrens);

        }

    }

}
