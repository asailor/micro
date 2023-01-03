package com.gdsig.common.vo;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author xs
 * @date 2022/12/1上午 10:12
 */

@Data
@ApiModel(value = "角色VO")
public class RoleVO {

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "ID")
    private String name;

    @ApiModelProperty(value = "菜单")
    private List<Menu> menus;

    @ApiModelProperty(value = "功能按钮权限")
    private JSONObject function;


    @Data
    public static class Menu {

        private String title;

        private QueryParams queryParams;

        private Boolean expanded;

        private Icon icon;

        private String link;

        private List<Menu> children;
    }

    @Data
    public static class QueryParams {
        private Integer mid;
    }

    @Data
    public static class Icon {
        private String pack;
        private String icon;
    }

}
