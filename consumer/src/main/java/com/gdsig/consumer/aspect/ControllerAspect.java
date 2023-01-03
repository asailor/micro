package com.gdsig.consumer.aspect;

import com.gdsig.common.util.DateUtil;
import com.gdsig.common.util.IpUtil;
import com.gdsig.common.util.JwtTokenUtil;
import com.gdsig.common.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Controller层 AOP拦截
 *
 * @author huihu
 * @date 2021-09-01
 */
@Component
@Aspect
public class ControllerAspect {
    private final Logger logger = LoggerFactory.getLogger("ACCESS_LOG");

    @Value("${project.debug.enabled}")
    boolean debug;

    @Value("${project.debug.resp-print}")
    boolean respPrint;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.secret}")
    private String secret;

    /**
     * 拦截所有 controller 方法参数及响应
     * execution 扫描及解析controller路径下0级 || 1级类
     * 表达式解析：
     * 1、* 任意返回类型
     * 2、com.*.controller 包路径
     * 3、* 任意Controller类 或 下一级目录
     * 4、*(..) 任意方法 + 任意参数
     *
     * @param pjp 切点对象
     * @return Object对象
     * @throws Throwable 未知异常
     */
    @Around("execution (* com.gdsig.consumer.controller..*.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        Object object = null;

        if (debug) {
            // 原始的HTTP请求和响应的信息
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes == null) {
                return null;
            }
            HttpServletRequest request = attributes.getRequest();
            Signature signature = pjp.getSignature();

            String token = StringUtil.defIfBlank(request.getHeader(this.tokenHeader), "");

            String username = "";
            if (StringUtils.isNotBlank(token)){
                username = JwtTokenUtil.getUserNameFromToken(token, secret);
            }

            StringBuilder requestUri = new StringBuilder();
            requestUri.append(DateUtil.formatMonthTimes(new Date())).append("\t");
            requestUri.append(username).append("\t");
            requestUri.append(IpUtil.getIpAddr(request)).append("\t");
            requestUri.append(request.getRequestURI());
            // 获取当前执行的类
            MethodSignature methodSignature = (MethodSignature) signature;
            requestUri.append(" >>> ").append(signature.getDeclaringTypeName());

            // 获取当前执行的方法
            Method targetMethod = methodSignature.getMethod();
            requestUri.append(" >>> ").append(targetMethod.getName());

            // 获取当前执行的方法参数集合
            requestUri.append("(");
            requestUri.append(StringUtils.join(pjp.getArgs(), ","));
            requestUri.append(")");

            // 获取返回对象
            if (respPrint) {
                object = pjp.proceed();
                requestUri.append(" >>> ").append(object);
            }

            // 打印请求地址
            System.out.println(requestUri);
            logger.info(requestUri.toString());
        }

        // 代理方法的返回值
        return object != null ? object : pjp.proceed();
    }

    /**
     * tryCatch异常时进入
     *
     * @param pjp 切点对象
     */
    @AfterThrowing("execution (* com.gdsig.consumer.controller..*.*(..))")
    public void afterThrowing(JoinPoint pjp) {

    }

    /**
     * tryCatch正常时进入
     *
     * @param pjp 切点对象
     */
    @AfterReturning("execution (* com.gdsig.consumer.controller..*.*(..))")
    public void afterReturning(JoinPoint pjp) {

    }

    /**
     * finally 后执行
     *
     * @param pjp 切点对象
     */
    @After("execution (* com.gdsig.consumer.controller..*.*(..))")
    public void after(JoinPoint pjp) {

    }

}
