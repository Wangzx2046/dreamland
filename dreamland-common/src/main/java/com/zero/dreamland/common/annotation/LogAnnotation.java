package com.zero.dreamland.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)     // 这个注解是用来规定注解的作用范围的,这里定义为method方法级别.
@Retention(RetentionPolicy.RUNTIME)    // 这个注解可以理解为定义注解的生命周期,这里标识一直存在(编译和运行之后)
public @interface LogAnnotation {
    // 定义注解参数
    public String operateContent() default "";

    public String operateType() default "";
}


/***
 @Target 用来取值 注解使用范围:
 1.METHOD  可用于方法上
 2.TYPE    可用于类或者接口上
 3.ANNOTATION_TYPE 可用于注解类型上（被@interface修饰的类型）
 4.CONSTRUCTOR 可用于构造方法上
 5.FIELD   可用于域上
 6.LOCAL_VARIABLE  可用于局部变量上
 7.PACKAGE 用于记录java文件的package信息
 8.PARAMETER   可用于参数上

 @Retention
 1、RetentionPolicy.SOURCE：注解只保留在源文件，当Java文件编译成class文件的时候，注解被遗弃；
 2、RetentionPolicy.CLASS：注解被保留到class文件，但jvm加载class文件时候被遗弃，这是默认的生命周期；
 3、RetentionPolicy.RUNTIME：注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在；
 */