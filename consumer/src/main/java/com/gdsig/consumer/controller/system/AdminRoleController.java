package com.gdsig.consumer.controller.system;

import com.gdsig.common.dto.system.RoleDto;
import com.gdsig.common.result.CommonResult;
import com.gdsig.common.vo.RoleVO;
import com.gdsig.consumer.service.system.AdminRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author xs
 * @date 2022/11/24下午 5:51
 */

@Api(tags = "后台角色管理")
@RequestMapping("/system/admin/roles")
@RestController
public class AdminRoleController {

    @Resource
    private AdminRoleService objService;

    @ApiOperation(value = "列表")
    @GetMapping
    public CommonResult<List<RoleVO>> adminRecords(@RequestParam(required = false) int page,
                                                   @RequestParam(required = false) int limit,
                                                   @RequestParam(required = false) String name){
        return objService.list(page, limit, name);
    }

    @ApiOperation(value = "详情")
    @GetMapping("/{id}")
    public CommonResult<RoleVO> get(@PathVariable Integer id){
        return objService.get(id);
    }

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public CommonResult<Void> add(@Valid @RequestBody RoleDto roleDto){
        return objService.add(roleDto);
    }

    @ApiOperation(value = "修改")
    @PostMapping("/{id}/update")
    public CommonResult<Void> update(@PathVariable Integer id, @Valid @RequestBody RoleDto roleDto){
        return objService.update(id, roleDto);
    }

    @ApiOperation(value = "删除")
    @PostMapping("/{id}/delete")
    public CommonResult<Void> delete(@PathVariable Integer id){
        return objService.delete(id);
    }

    @ApiOperation(value = "菜单权限")
    @PostMapping("/menus")
    public CommonResult<RoleVO> menus(){
        return objService.menus();
    }


}
