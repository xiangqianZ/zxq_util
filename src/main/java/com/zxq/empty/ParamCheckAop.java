package com.zxq.empty;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class ParamCheckAop {

    @Before("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void invoke(JoinPoint joinPoint) {

        Object[] params = joinPoint.getArgs();

        log.info("{},请求参数为:{}",getRequestUrl(), Arrays.toString(params));

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if(!method.isAnnotationPresent(ParamCheck.class))
            return;

        ParamCheck paramCheck = method.getAnnotation(ParamCheck.class);

        String[] checkColumns = paramCheck.value();

        Object obj = params[0];

        Param.paramIsNull(obj,checkColumns);

    }

    private String getRequestUrl() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        String url = request.getRequestURI();
        return url;
    }
}
