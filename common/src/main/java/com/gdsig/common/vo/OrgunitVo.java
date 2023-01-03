package com.gdsig.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author xs
 * @date 2022/12/7下午 4:18
 */

@Data
@ApiModel(value = "组织VO")
public class OrgunitVo {

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "等级")
    private Integer level;

    @ApiModelProperty(value = "icon")
    private String icon;

    @ApiModelProperty(value = "liAttr")
    private LiAttr liAttr;

    @ApiModelProperty(value = "state")
    private State state;

    @ApiModelProperty(value = "下级组织")
    private List<OrgunitVo> orgunitVos;

    @Data
    public static class LiAttr {

        @ApiModelProperty(value = "名称")
        private String name;

        @ApiModelProperty(value = "地址")
        private String address;

        @ApiModelProperty(value = "联系人")
        private String contact;
    }

    @Data
    public static class State {

        @ApiModelProperty(value = "名称")
        private Boolean opened;

        @ApiModelProperty(value = "名称")
        private Boolean selected;
    }
}
