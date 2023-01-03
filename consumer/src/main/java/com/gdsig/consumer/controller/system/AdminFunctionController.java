package com.gdsig.consumer.controller.system;

import com.gdsig.common.dto.system.FunctionDto;
import com.gdsig.common.result.CommonResult;
import com.gdsig.common.vo.FunctionVo;
import com.gdsig.consumer.service.system.AdminFunctionService;
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

@Api(tags = "后台组织管理")
@RequestMapping("/system/admin/functions")
@RestController
public class AdminFunctionController {

    @Resource
    private AdminFunctionService objService;

    @ApiOperation(value = "列表")
    @GetMapping
    public CommonResult<List<FunctionVo>> adminRecords(@RequestParam(required = false) int page,
                                                      @RequestParam(required = false) int limit,
                                                      @RequestParam(required = false) String name){
        return objService.list(page, limit, name);
    }

    @ApiOperation(value = "详情")
    @GetMapping("/{id}")
    public CommonResult<FunctionVo> get(@PathVariable Integer id){
        return objService.get(id);
    }

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public CommonResult<Void> add(@Valid @RequestBody FunctionDto dto){
        return objService.add(dto);
    }

    @ApiOperation(value = "修改")
    @PostMapping("/{id}/update")
    public CommonResult<Void> update(@PathVariable Integer id, @Valid @RequestBody FunctionDto dto){
        return objService.update(id, dto);
    }

    @ApiOperation(value = "删除")
    @PostMapping("/{id}/delete")
    public CommonResult<Void> delete(@PathVariable Integer id){
        return objService.delete(id);
    }

}
