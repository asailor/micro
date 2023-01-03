package com.gdsig.common.dto.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author xs
 * @date 2022/12/7下午 4:20
 */

@Data
@ApiModel(value = "组织DTO")
public class OrgunitDto {

    @ApiModelProperty(value = "名称", required = true)
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "上级组织", required = true)
    private Integer parentId;

    @ApiModelProperty(value = "上级组织", required = true)
    @NotNull(message = "等级不能为空")
    private Integer level;
}
