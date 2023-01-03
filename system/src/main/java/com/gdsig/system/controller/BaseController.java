package com.gdsig.system.controller;

import com.gdsig.security.pojo.AccountDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

/**
 * @author xs
 * @date 2022/12/7上午 11:15
 */

public class BaseController  {

    public AccountDetails getAccount(){
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        return (AccountDetails) auth.getPrincipal();
    }

    public String getAccountNumber(){
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        AccountDetails accountDetails = (AccountDetails) auth.getPrincipal();
        if (Objects.isNull(accountDetails)){
            return null;
        }
        return accountDetails.getUsername();
    }

    public String getAccountId(){
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        AccountDetails accountDetails = (AccountDetails) auth.getPrincipal();
        if (Objects.isNull(accountDetails)){
            return null;
        }
        return accountDetails.getBdAccount().getId();
    }

    public Integer getOrgunitId(){
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        AccountDetails accountDetails = (AccountDetails) auth.getPrincipal();
        if (Objects.isNull(accountDetails)){
            return null;
        }
        return accountDetails.getBdAccount().getOrgunitId();
    }

    public Integer getRoleId(){
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        AccountDetails accountDetails = (AccountDetails) auth.getPrincipal();
        if (Objects.isNull(accountDetails)){
            return null;
        }
        return accountDetails.getBdAccount().getRoleId();
    }

}
