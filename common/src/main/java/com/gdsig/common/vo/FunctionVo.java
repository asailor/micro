package com.gdsig.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xs
 * @date 2022/12/8上午 9:40
 */

@Data
@ApiModel(value = "菜单按钮VO")
public class FunctionVo {

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "等级")
    private Integer level;

    @ApiModelProperty(value = "排序")
    private Integer seq;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "路径")
    private String path;
}
