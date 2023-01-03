package com.gdsig.system.controller.admin;

import com.gdsig.common.dto.system.OrgunitDto;
import com.gdsig.common.dto.system.OrgunitListParam;
import com.gdsig.common.result.CommonResult;
import com.gdsig.common.vo.OrgunitVo;
import com.gdsig.system.controller.BaseController;
import com.gdsig.system.service.admin.OrgunitService;
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

@Api(tags = "组织管理")
@RequestMapping("/system/admin/orgunits")
@RestController("admin/OrgunitController")
public class OrgunitController extends BaseController {

    @Resource(name = "admin/OrgunitService")
    OrgunitService objService;

    @ApiOperation(value = "列表")
    @GetMapping
    CommonResult<List<OrgunitVo>> list(OrgunitListParam listParam) {
        listParam.setStatus(true);
        return CommonResult.ok(objService.searchRecord(listParam));
    }

    @ApiOperation(value = "详情")
    @GetMapping("/{id}")
    CommonResult<OrgunitVo> detail(@PathVariable Integer id) {
        return CommonResult.get(objService.findById(getAccountId(), id));
    }

    @ApiOperation(value = "新增")
    @PostMapping(value = "/add")
    CommonResult<Void> add(@Valid @RequestBody OrgunitDto dto) {
        return CommonResult.get(objService.add(getAccountId(), dto));
    }

    @ApiOperation(value = "修改")
    @PostMapping(value = "/{id}/update")
    CommonResult<Void> update(@PathVariable Integer id, @Valid @RequestBody OrgunitDto dto) {
        return CommonResult.get(objService.update(getAccountId(), id, dto));
    }

    @ApiOperation(value = "删除")
    @PostMapping(value = "/{id}/delete")
    CommonResult<Void> delete(@PathVariable Integer id) {
        return CommonResult.get(objService.delete(getAccountId(), id));
    }

    @ApiOperation(value = "下级组织")
    @GetMapping("/trees")
    CommonResult<List<OrgunitVo>> trees() {
        return CommonResult.get(objService.trees(getAccountId(), getOrgunitId()));
    }


}
