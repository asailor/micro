package com.gdsig.mybatis.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class BdRoleMenuMapping implements Serializable {
    private Integer id;

    private Integer roleMenuId;

    private Integer menuMappingId;

    @ApiModelProperty(value = "是否选中：0否1是")
    private Boolean checked;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleMenuId() {
        return roleMenuId;
    }

    public void setRoleMenuId(Integer roleMenuId) {
        this.roleMenuId = roleMenuId;
    }

    public Integer getMenuMappingId() {
        return menuMappingId;
    }

    public void setMenuMappingId(Integer menuMappingId) {
        this.menuMappingId = menuMappingId;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleMenuId=").append(roleMenuId);
        sb.append(", menuMappingId=").append(menuMappingId);
        sb.append(", checked=").append(checked);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}