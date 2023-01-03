package com.gdsig.mybatis.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class BdOrgunitMapping implements Serializable {
    private Integer id;

    private Integer orgunitId;

    private String orgunitName;

    private Integer parentId;

    private String parentName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrgunitId() {
        return orgunitId;
    }

    public void setOrgunitId(Integer orgunitId) {
        this.orgunitId = orgunitId;
    }

    public String getOrgunitName() {
        return orgunitName;
    }

    public void setOrgunitName(String orgunitName) {
        this.orgunitName = orgunitName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orgunitId=").append(orgunitId);
        sb.append(", orgunitName=").append(orgunitName);
        sb.append(", parentId=").append(parentId);
        sb.append(", parentName=").append(parentName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}