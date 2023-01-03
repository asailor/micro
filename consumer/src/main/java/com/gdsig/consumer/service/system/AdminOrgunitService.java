package com.gdsig.consumer.service.system;

import com.gdsig.common.dto.system.OrgunitDto;
import com.gdsig.common.result.CommonResult;
import com.gdsig.common.vo.OrgunitVo;
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
@FeignClient(value = "micro-system", contextId = "micro-orgunit",
        configuration = FeignClientsInterceptor.class)
public interface AdminOrgunitService {

    @GetMapping("/system/admin/orgunits")
    CommonResult<List<OrgunitVo>> list(@RequestParam(required = false, name = "page") int page,
                                    @RequestParam(required = false, name = "limit") int limit,
                                    @RequestParam(required = false, name = "name") String name);

    @GetMapping("/system/admin/orgunits/{id}")
    CommonResult<OrgunitVo> get(@PathVariable("id") Integer id);

    @PostMapping("/system/admin/orgunits/add")
    CommonResult<Void> add(@RequestBody OrgunitDto dto);

    @PostMapping("/system/admin/orgunits/{id}/update")
    CommonResult<Void> update(@PathVariable("id") Integer id, @RequestBody OrgunitDto dto);

    @PostMapping("/system/admin/orgunits/{id}/delete")
    CommonResult<Void> delete(@PathVariable("id") Integer id);

    @GetMapping("/system/admin/orgunits/trees")
    CommonResult<List<OrgunitVo>> trees();
}
