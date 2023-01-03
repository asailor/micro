package com.gdsig.security.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xs
 * @date 2022/11/29下午 4:41
 */

@Getter
@Setter
public class Account {

    private String id;

    private String number;

    private String password;

    private Integer roleId;

    private Integer orgunitId;

    public Account() {
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", password='" + password + '\'' +
                ", roleId=" + roleId +
                ", orgunitId=" + orgunitId +
                '}';
    }
}
