package com.zero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author zero
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class ZeroActApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ZeroActApplication.class, args);

    }
}
