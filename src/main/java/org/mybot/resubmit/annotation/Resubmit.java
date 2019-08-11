package org.mybot.resubmit.annotation;


import java.lang.annotation.*;


/**
 * @author lijing
 * @className RequestDataAspect
 * @description 数据重复提交校验
 * @date 2019/08/11 12:00
 **/

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Resubmit {

    /**
     * 延时时间 在延时多久后可以再次提交
     *
     * @return Time unit is one second
     */
    int delaySeconds() default 20;

    String msg() default "请勿重复提交";
}
