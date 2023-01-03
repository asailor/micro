package com.gdsig.common.dto.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author xs
 * @date 2022/12/8上午 9:23
 */

@Data
@ApiModel(value = "菜单按钮DTO")
public class FunctionDto {

    @ApiModelProperty(value = "名称", required = true)
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "编号", required = true)
    @NotNull(message = "编号不能为空")
    private String number;

    @ApiModelProperty(value = "菜单id")
    @NotNull(message = "菜单不能为空")

    private Integer menuId;
    @ApiModelProperty(value = "css", required = true)
    @NotNull(message = "css不能为空")
    private String css;

}
