package com.netposa.ips.ga1400.config;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: zhangtao@netposa.com on 2019/3/25
 */
@Component
public class KafkaTopicConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

//    @Value("${spring.kafka.topic.name}")
//    private String topicName;

//    @Value("${spring.kafka.topic.numPartitions}")
//    private int numPartitions;

//    @Value("${spring.kafka.topic.replicationFactor}")
//    private int replicationFactor;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        return new KafkaAdmin(configs);
    }

    @Bean
    public AdminClient adminClient() {
        return AdminClient.create(kafkaAdmin().getConfig());
    }


//    @Bean
//    public NewTopic myTopic() {
//        // 指定主题名称，分区数量，和复制因子
//        //第三个参数是副本数量，确保集群中配置的数目大于等于副本数量
//        return new NewTopic(topicName, numPartitions, (short) replicationFactor);
//    }


}
