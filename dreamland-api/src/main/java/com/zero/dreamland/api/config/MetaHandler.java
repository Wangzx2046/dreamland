package com.zero.dreamland.api.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zero.dreamland.auth.springSecurity.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


/**
 * 处理新增和更新的基础数据填充，配合BaseEntity和MyBatisPlusConfig使用
 */
@Slf4j
@Component
public class MetaHandler implements MetaObjectHandler {


    /**
     * 新增数据执行
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {

        Object ud = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AuthUser userDetails = (AuthUser) ud;

        this.setFieldValByName("createDate", LocalDateTime.now(), metaObject);
        this.setFieldValByName("createBy", userDetails.getUser().getNickName(), metaObject);
        this.setFieldValByName("updateDate", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateBy", userDetails.getUser().getNickName(), metaObject);
    }

    /**
     * 更新数据执行
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        Object ud = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AuthUser userDetails = (AuthUser) ud;

        this.setFieldValByName("updateDate", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateBy", userDetails.getUser().getNickName(), metaObject);
    }

}