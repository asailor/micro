package com.gdsig.consumer.service.system;

import com.gdsig.common.dto.system.RoleDto;
import com.gdsig.common.result.CommonResult;
import com.gdsig.common.vo.RoleVO;
import com.gdsig.consumer.config.FeignClientsInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xs
 * @date 2022/11/24下午 5:20
 */

@Component
@FeignClient(value = "micro-system", contextId = "micro-role",
        configuration = FeignClientsInterceptor.class)
public interface AdminRoleService {

    @GetMapping("/system/admin/roles")
    CommonResult<List<RoleVO>> list(@RequestParam(required = false, name = "page") int page,
                                    @RequestParam(required = false, name = "limit") int limit,
                                    @RequestParam(required = false, name = "name") String name);

    @GetMapping("/system/admin/roles/{id}")
    CommonResult<RoleVO> get(@PathVariable("id") Integer id);

    @PostMapping("/system/admin/roles/add")
    CommonResult<Void> add(@RequestBody RoleDto roleDto);

    @PostMapping("/system/admin/roles/{id}/update")
    CommonResult<Void> update(@PathVariable("id") Integer id, @RequestBody RoleDto roleDto);

    @PostMapping("/system/admin/roles/{id}/delete")
    CommonResult<Void> delete(@PathVariable("id") Integer id);

    @PostMapping("/system/admin/roles/menus")
    CommonResult<RoleVO> menus();
}
