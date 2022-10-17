package com.francis.platform.log;

import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.francis.platform.common.constans.AppConstants;
import com.francis.platform.entity.Log;
import com.francis.platform.service.LogService;
import com.francis.platform.util.RequestHolder;
import com.francis.platform.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * @Author Francis
 * @Date 2022-09-05 17:21
 **/
@Aspect
@Component
@Slf4j

public class OperationLogAspect {

    ThreadLocal<Long> currentTime = new ThreadLocal<>();
    private final LogService logService;

    public OperationLogAspect(LogService logService) {
        this.logService = logService;
    }


    @Pointcut("@annotation(OperationLog)")
    public void pointCut() {
    }


    @Around("pointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        currentTime.set(System.currentTimeMillis());
        result = joinPoint.proceed();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        OperationLog annotation = method.getAnnotation(OperationLog.class);
        // 方法路径
        String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        Log info = Log.builder()
                .browser(StringUtils.getBrowser(request))
                .address(StringUtils.getCityInfo(StringUtils.getIp(request)))
                .requestIp(StringUtils.getIp(request))
                .description(annotation.value())
                .createTime(new Date())
                .method(methodName)
                .logType("INFO")
                .time(System.currentTimeMillis() - currentTime.get())
                .params(getParameter(method, joinPoint.getArgs()))
                .username(SecurityContextHolder.getContext().getAuthentication().getName())
                .build();
        // 记录登录用户，隐藏密码信息
        if (AppConstants.LOGIN_URI.equals(signature.getName()) && StringUtils.isNotEmpty(info.getParams())) {
            JSONObject obj = JSONUtil.parseObj(info.getParams());
            info.setUsername(obj.getStr("username", ""));
            info.setParams(JSONUtil.toJsonStr(Dict.create().set("username", info.getUsername())));
        }
        currentTime.remove();
        logService.save(info);
        return result;
    }

    /**
     * 配置异常通知
     *
     * @param joinPoint join point for advice
     * @param e         exception
     */
    @AfterThrowing(pointcut = "pointCut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        OperationLog annotation = method.getAnnotation(OperationLog.class);
        // 方法路径
        String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        Log info = Log.builder()
                .browser(StringUtils.getBrowser(request))
                .address(StringUtils.getCityInfo(StringUtils.getIp(request)))
                .requestIp(StringUtils.getIp(request))
                .description(annotation.value())
                .createTime(new Date())
                .method(methodName)
                .exceptionDetail(e.getMessage())
                .logType("ERROR")
                .time(System.currentTimeMillis() - currentTime.get())
                .params(getParameter(method, joinPoint.getArgs()))
                .username(SecurityContextHolder.getContext().getAuthentication().getName())
                .build();
        // 记录登录用户，隐藏密码信息
        if (AppConstants.LOGIN_URI.equals(signature.getName()) && StringUtils.isNotEmpty(info.getParams())) {
            JSONObject obj = JSONUtil.parseObj(info.getParams());
            info.setUsername(obj.getStr("username", ""));
            info.setParams(JSONUtil.toJsonStr(Dict.create().set("username", info.getUsername())));
        }
        currentTime.remove();
        logService.save(info);

    }




    /**
     * 根据方法和传入的参数获取请求参数
     */
    private String getParameter(Method method, Object[] args) {
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            //将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                argList.add(args[i]);
            }
            //将RequestParam注解修饰的参数作为请求参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam != null) {
                Map<String, Object> map = new HashMap<>(2);
                String key = parameters[i].getName();
                if (!StringUtils.isEmpty(requestParam.value())) {
                    key = requestParam.value();
                }
                map.put(key, args[i]);
                argList.add(map);
            }
        }
        if (argList.isEmpty()) {
            return "";
        }
        return argList.size() == 1 ? JSONUtil.toJsonStr(argList.get(0)) : JSONUtil.toJsonStr(argList);
    }


}
