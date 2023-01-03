package com.gdsig.security.pojo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author xs
 * @date 2022/11/28上午 11:00
 */
public class AccountDetails implements UserDetails {

    private Account bdAccount;

    private static final long serialVersionUID = 1L;

    public AccountDetails() {
    }

    public AccountDetails(Account bdAccount) {
        this.bdAccount = bdAccount;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return Arrays.asList(new SimpleGrantedAuthority("ADD"));
    }

    @Override
    public String getPassword() {
        return bdAccount.getPassword();
    }

    @Override
    public String getUsername() {
        return bdAccount.getNumber();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Account getBdAccount() {
        return bdAccount;
    }
}
