package com.netposa.ips.ga1400.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 加载设备统计信息到缓存
 *
 * @author zhangtao@netposa.com on 2019/3/6.
 */
@Configuration
@Slf4j
public class CacheConfig {

    /**
     * 缓存管理配置
     *
     * @return
     */
    public static LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();

    void test() {
//        linkedBlockingQueue.
    }

}
