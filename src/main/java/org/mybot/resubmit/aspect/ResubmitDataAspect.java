/**
 * @Project Name: alasga-app
 * File Name:${FILE_NAME}
 * Package Name:com.cn.alasga.app.gateway.aspect
 * @Date 2018/7/10/010 17:05
 * Copyright (c) 2017, http://www.alasga.cn All Rights Reserved.
 **/
package org.mybot.resubmit.aspect;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.mybot.resubmit.annotation.Resubmit;
import org.mybot.resubmit.ex.ResubmitException;
import org.mybot.resubmit.lock.ResubmitLock;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.StringJoiner;

/**
 * @ClassName RequestDataAspect
 * @Description 数据重复提交校验
 * @Author lijing
 * @Date 2019/05/16 17:05
 **/
@Aspect
@Component
@Order(0)
public class ResubmitDataAspect {

    private final static Object PRESENT = new Object();

    @Around("@annotation(org.mybot.resubmit.annotation.Resubmit)")
    public Object handleResubmit(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        //获取注解信息
        Resubmit annotation = method.getAnnotation(Resubmit.class);
        int delaySeconds = annotation.delaySeconds();
        Object[] pointArgs = joinPoint.getArgs();
        String key = "";

        StringJoiner sj = new StringJoiner(":");

        Arrays.stream(pointArgs).forEach(x -> sj.add(JSONObject.toJSONString(x)));
        System.out.println("sj========:"+sj);
        key = ResubmitLock.handleKey(sj.toString());
        System.out.println("key========:"+key);

        //执行锁
        boolean lock = false;
        try {
            //设置解锁key
            lock = ResubmitLock.getInstance().lock(key, PRESENT);
            if (lock) {
                //放行
                return joinPoint.proceed();
            } else {
                //响应重复提交异常
                throw new ResubmitException(annotation.msg());
            }
        } finally {
            //设置解锁key和解锁时间
            ResubmitLock.getInstance().unLock(lock, key, delaySeconds);
        }
    }
}
