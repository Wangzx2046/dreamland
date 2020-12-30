package com.zero.dreamland.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description：用于标记匿名访问方法
 * @Method_Name：
 *
 * @Param :
 * @return           :
 * @Creation Date    ：2020/3/1713:32
 * @Author           ：wzx
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnonymousAccess {

}
