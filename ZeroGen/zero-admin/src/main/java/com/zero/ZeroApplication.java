package com.zero;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

/**
 * 启动程序
 *
 * @author zero
 */
@Slf4j
@SpringBootApplication
public class ZeroApplication {
    public static void main(String[] args) throws UnknownHostException {
        // System.setProperty("spring.devtools.restart.enabled", "false");

        ConfigurableApplicationContext application = SpringApplication.run(ZeroApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = Objects.toString(env.getProperty("server.servlet.context-path"), "");
        log.info("\n----------------------------------------------------------\n\t"
                + "Application  is running! Access URLs:\n\t"
                + "login Index    : \t\thttp://" + ip + ":" + port + path + "/index.html\n\t"
                + "----------------------------------------------------------");

    }
}