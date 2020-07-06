package com.zero.dreamland.api;

import com.zero.dreamland.common.utils.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Slf4j
@ServletComponentScan   //扫描Servlet
@EnableTransactionManagement(proxyTargetClass = true)//开启事务管理的注解
@MapperScan({"com.zero.dreamland.biz.*.dao","com.zero.dreamland.quartz.dao"})//这里mapper是你的mybatis的mapper目录。
@SpringBootApplication
@ComponentScan({"com.zero.dreamland"})
public class DreamlandApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DreamlandApiApplication.class, args);
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }


}
