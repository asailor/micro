package com.gdsig.system.filter;

import cn.hutool.json.JSONUtil;
import com.gdsig.common.result.CommonResult;
import com.gdsig.common.util.JwtTokenUtil;
import com.gdsig.mybatis.model.BdAccount;
import com.gdsig.system.service.AccountCacheService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 *  HTTP请求过滤器
 * @author xs
 * @date 2022/12/21下午 2:37
 */

@Component
@WebFilter(urlPatterns = {"/**"})
public class RequestFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger("ACCESS_LOG");

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.secret}")
    private String secret;

    @Resource
    AccountCacheService accountCacheService;

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String authToken = request.getHeader(this.tokenHeader);
        if (StringUtils.isNotBlank(authToken)){
            String username = JwtTokenUtil.getUserNameFromToken(authToken, secret);
            BdAccount account = accountCacheService.getAccount(username);

            if (!Objects.equals(authToken, account.getJwt())){
                HttpServletResponse response = ((HttpServletResponse) servletResponse);
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Cache-Control","no-cache");
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                response.getWriter().println(JSONUtil.parse(CommonResult.unauthorized()));
                response.getWriter().flush();
                return;
            }

        }
        chain.doFilter(request, servletResponse);
    }
}
