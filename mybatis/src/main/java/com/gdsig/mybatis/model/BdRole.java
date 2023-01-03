package com.gdsig.mybatis.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class BdRole implements Serializable {
    private Integer id;

    private String creatorId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "0无效；1有效")
    private Boolean status;

    @ApiModelProperty(value = "上级角色")
    private Integer parentId;

    private Integer orgunitId;

    @ApiModelProperty(value = "是否超管角色")
    private Boolean superadmin;

    @ApiModelProperty(value = "类型")
    private Integer type;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getOrgunitId() {
        return orgunitId;
    }

    public void setOrgunitId(Integer orgunitId) {
        this.orgunitId = orgunitId;
    }

    public Boolean getSuperadmin() {
        return superadmin;
    }

    public void setSuperadmin(Boolean superadmin) {
        this.superadmin = superadmin;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", creatorId=").append(creatorId);
        sb.append(", createTime=").append(createTime);
        sb.append(", name=").append(name);
        sb.append(", status=").append(status);
        sb.append(", parentId=").append(parentId);
        sb.append(", orgunitId=").append(orgunitId);
        sb.append(", superadmin=").append(superadmin);
        sb.append(", type=").append(type);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
