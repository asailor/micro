package com.gdsig.auth.feign;

import com.gdsig.auth.config.FeignClientsInterceptor;
import com.gdsig.common.dto.system.RoleDto;
import com.gdsig.common.result.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author xs
 * @date 2023/1/10下午 4:54
 */
@Component
@FeignClient(value = "micro-system", contextId = "micro-role", configuration = FeignClientsInterceptor.class)
public interface RoleApi {

    @PostMapping("/system/admin/roles/add")
    CommonResult<Void> add(@RequestBody RoleDto roleDto);
}
