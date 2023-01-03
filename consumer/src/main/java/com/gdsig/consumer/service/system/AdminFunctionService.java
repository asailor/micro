package com.gdsig.consumer.service.system;

import com.gdsig.common.dto.system.FunctionDto;
import com.gdsig.common.result.CommonResult;
import com.gdsig.common.vo.FunctionVo;
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
@FeignClient(value = "micro-system", contextId = "micro-function",
        configuration = FeignClientsInterceptor.class)
public interface AdminFunctionService {

    @GetMapping("/system/admin/functions")
    CommonResult<List<FunctionVo>> list(@RequestParam(required = false, name = "page") int page,
                                    @RequestParam(required = false, name = "limit") int limit,
                                    @RequestParam(required = false, name = "name") String name);

    @GetMapping("/system/admin/functions/{id}")
    CommonResult<FunctionVo> get(@PathVariable("id") Integer id);

    @PostMapping("/system/admin/functions/add")
    CommonResult<Void> add(@RequestBody FunctionDto dto);

    @PostMapping("/system/admin/functions/{id}/update")
    CommonResult<Void> update(@PathVariable("id") Integer id, @RequestBody FunctionDto dto);

    @PostMapping("/system/admin/functions/{id}/delete")
    CommonResult<Void> delete(@PathVariable("id") Integer id);
}
