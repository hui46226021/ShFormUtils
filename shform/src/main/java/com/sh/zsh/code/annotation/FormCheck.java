package com.sh.zsh.code.annotation;

import com.sh.zsh.code.check.CheckType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhush on 2016/10/8.
 * E-mail zhush@jerei.com
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormCheck {
    //表单检查类型
    CheckType type() default CheckType.CUSTOM;
}
