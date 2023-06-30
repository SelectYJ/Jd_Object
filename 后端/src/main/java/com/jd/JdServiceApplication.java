package com.jd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan // 开启Servlet组件支持，spring本身没有Servlet
@SpringBootApplication
public class JdServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(JdServiceApplication.class, args);
    }

}
