package com.gdsig.common.dto.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author xs
 * @date 2022/12/1上午 10:08
 */

@Data
@ApiModel(value = "角色DTO")
public class RoleDto {

    @ApiModelProperty(value = "名称", required = true)
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "组织", required = true)
    @NotBlank(message = "组织不能为空")
    private String orgunitId;

    @ApiModelProperty(value = "上级角色")
    private Integer parentId;

    @ApiModelProperty(value = "超管")
    private Boolean superadmin;

    @ApiModelProperty(value = "类型", required = true)
    @NotNull(message = "类型不能为空")
    private Integer typeId;

    @ApiModelProperty(value = "菜单权限")
    private List<Menu> menus;

    @Data
    public static class Menu {

        private Integer id;
        private Boolean checked;
        private List<Menu> menus;
        private List<Menu> functions;
    }

}
