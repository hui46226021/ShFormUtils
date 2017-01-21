package com.sh.zsh.code.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhush on 2016/10/8.
 * E-mail zhush@jerei.com
 * 表单映射注解
 *
 * name 需要映射的字段
 * isNull  该字段是否可为空
 * message 提示信息
 * 需要调用  FormInit.injection(this); 初始化
 * 初始化后 会将所有的控件 以当前页面的名字为key缓存起来  通过对 实体对象反射 赋值 或读取 操作
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormInjection {
    String name();
    boolean isNull() default false;
    String message() default "参数";
}
