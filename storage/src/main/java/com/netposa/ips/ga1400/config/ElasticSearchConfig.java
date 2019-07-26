package com.netposa.ips.ga1400.config;

import com.netposa.ips.ga1400.constants.StorageConsts;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ApiException;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Author: zhangtao@netposa.com on 2019/3/26
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.netposa.dgep.modules.es")
@Slf4j
public class ElasticSearchConfig {
    /**
     * 主机
     */
    @Value("${storage.dist-source.elasticsearch.hosts}")
    private String esHosts;

    /**
     * 传输层端口，注意和ES的Restful API默认9200端口有区分
     */
    @Value("${storage.dist-source.elasticsearch.port}")
    private int esPort;

    /**
     * 集群名称
     */
    @Value("${storage.dist-source.elasticsearch.cluster-name}")
    private String esClusterName;

    /**
     * 连接池
     */
    @Value("${storage.dist-source.elasticsearch.pool}")
    private String poolSize;

    @Value("${storage.dist-source.elasticsearch.enable}")
    private boolean enableEs;

    private TransportClient client;

    @PostConstruct
    public void initialize() throws Exception {
        log.info("开始初始化Elasticsearch");
        Settings esSettings = Settings.builder()
                .put("cluster.name", esClusterName)
                .put("thread_pool.search.size", Integer.parseInt(poolSize))//增加线程池个数，暂时设为5
                .put("client.transport.sniff", true)//增加嗅探机制，找到ES集群
                .build();
        client = new PreBuiltTransportClient(esSettings);

        if (enableEs) {
            String[] ips = esHosts.trim().split(",");
            for (String ip : ips) {
                try {
                    client.addTransportAddress(new TransportAddress(InetAddress.getByName(ip), esPort));
                } catch (UnknownHostException e) {
                    //TODO
                    log.info("--------------es exception, ip={}", ip);
                }
            }
        }
    }

    @Bean
    public Client client() {
        return client;
    }

    @Bean
    public ElasticsearchOperations esTemplate() {
        Client client = client();
        if (client != null) {
            ElasticsearchTemplate elasticsearchTemplate = new ElasticsearchTemplate(client);
            //判断索引是否存在
            if (enableEs && !elasticsearchTemplate.indexExists(StorageConsts.ES_INDEX_NAME)) {
                elasticsearchTemplate.createIndex(StorageConsts.ES_INDEX_NAME);
            }
            return elasticsearchTemplate;
        } else {
            //弹出自定义异常对象
            throw new ApiException("初始化Elasticsearch失败！");
        }
    }

    //Embedded Elasticsearch Server
    /*@Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(nodeBuilder().local(true).node().client());
    }*/

    @PreDestroy
    public void destroy() {
        if (client != null) {
            client.close();
        }
    }
}
