package com.netposa.ips.ga1400;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
@EnableElasticsearchRepositories(basePackages = "com.netposa.ips.ga1400.modules.es")
@EnableScheduling
public class StorageApplication {

    public static void main(String[] args) {
        /**
         * Springboot整合Elasticsearch 在项目启动前设置一下的属性，防止报错
         * 解决netty冲突后初始化client时还会抛出异常
         */
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(StorageApplication.class, args);
    }

}
