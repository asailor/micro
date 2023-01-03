package com.gdsig.system.controller.admin;

import com.gdsig.common.dto.system.RoleDto;
import com.gdsig.common.dto.system.RoleListParam;
import com.gdsig.common.result.CommonResult;
import com.gdsig.common.vo.RoleVO;
import com.gdsig.system.controller.BaseController;
import com.gdsig.system.service.admin.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author xs
 * @date 2022/12/1上午 9:28
 */

@Api(tags = "角色管理")
@RequestMapping("/system/admin/roles")
@RestController("admin/RoleController")
public class RoleController extends BaseController {

    @Resource(name = "admin/RoleService")
    RoleService objService;

    @ApiOperation(value = "列表")
    @GetMapping
    CommonResult<List<RoleVO>> list(RoleListParam listParam) {
        listParam.setStatus(true);
        return CommonResult.ok(objService.searchRecord(listParam));
    }

    @ApiOperation(value = "详情")
    @GetMapping("/{id}")
    CommonResult<RoleVO> detail(@PathVariable Integer id) {
        return CommonResult.get(objService.findById(getAccountId(), id));
    }

    @ApiOperation(value = "新增")
    @PostMapping(value = "/add")
    CommonResult<Void> add(@Valid @RequestBody RoleDto dto) {
        try {
            return CommonResult.get(objService.saveOrUpdate(getAccountId(), null, dto));
        } catch (Exception e) {
            return CommonResult.fail(e.getMessage());
        }    }

    @ApiOperation(value = "修改")
    @PostMapping(value = "/{id}/update")
    CommonResult<Void> update(@PathVariable Integer id, @Valid @RequestBody RoleDto dto) {
        try {
            return CommonResult.get(objService.saveOrUpdate(getAccountId(), id, dto));
        } catch (Exception e) {
            return CommonResult.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "删除")
    @PostMapping(value = "/{id}/delete")
    CommonResult<Void> delete(@PathVariable Integer id) {
        return CommonResult.get(objService.delete(getAccountId(), id));
    }

    @ApiOperation(value = "菜单权限")
    @PostMapping(value = "/menus")
    CommonResult<RoleVO> menus() {
        return CommonResult.get(objService.menus(getRoleId(), 1));
    }


}
