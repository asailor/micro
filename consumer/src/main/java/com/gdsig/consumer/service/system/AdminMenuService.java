package com.gdsig.consumer.service.system;

import com.gdsig.common.dto.system.MenuDto;
import com.gdsig.common.result.CommonResult;
import com.gdsig.common.vo.MenuVo;
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
@FeignClient(value = "micro-system", contextId = "micro-menu",
        configuration = FeignClientsInterceptor.class)
public interface AdminMenuService {

    @GetMapping("/system/admin/menus")
    CommonResult<List<MenuVo>> list(@RequestParam(required = false, name = "page") int page,
                                    @RequestParam(required = false, name = "limit") int limit,
                                    @RequestParam(required = false, name = "name") String name);

    @GetMapping("/system/admin/menus/{id}")
    CommonResult<MenuVo> get(@PathVariable("id") Integer id);

    @PostMapping("/system/admin/menus/add")
    CommonResult<Void> add(@RequestBody MenuDto dto);

    @PostMapping("/system/admin/menus/{id}/update")
    CommonResult<Void> update(@PathVariable("id") Integer id, @RequestBody MenuDto dto);

    @PostMapping("/system/admin/menus/{id}/delete")
    CommonResult<Void> delete(@PathVariable("id") Integer id);
}
