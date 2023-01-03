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
@ApiModel(value = "菜单DTO")
public class MenuDto {

    @ApiModelProperty(value = "名称", required = true)
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "上级组织")
    private Integer parentId;

    @ApiModelProperty(value = "上级组织", required = true)
    @NotNull(message = "等级不能为空")
    private Integer level;

    @ApiModelProperty(value = "排序", required = true)
    @NotNull(message = "排序不能为空")
    private Integer sequence;

    @ApiModelProperty(value = "上级组织")
    private String icon;

    @ApiModelProperty(value = "上级组织")
    private String path;

}
