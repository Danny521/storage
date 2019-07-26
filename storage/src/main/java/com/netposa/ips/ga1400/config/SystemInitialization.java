package com.netposa.ips.ga1400.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SystemInitialization implements ApplicationRunner {


    @Override
    public void run(ApplicationArguments args) {
        log.info("------>系统初始化加载...");
        //TODO 系统启动后自执行代码
    }


//    @Bean
//    public ServletWebServerFactory servletContainer() {
//        UndertowServletWebServerFactory tomcat = new UndertowServletWebServerFactory();
//        return tomcat;
//    }

}
