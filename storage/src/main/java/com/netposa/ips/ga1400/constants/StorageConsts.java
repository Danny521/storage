package com.netposa.ips.ga1400.constants;

/**
 * Author: zhangtao@netposa.com on 2019/3/7
 */
public class StorageConsts {

    /**
     * 计算设备数据合理参考值时，取最近若干天的数据进行计算，默认为7天
     */
    public final static String CONCURRENCY = "20";

    /**
     * 重试间隔时间
     */
    public final static int RETRY_INTERVAL_SECONDS = 5;
    /**
     * ES 相关属性
     */
    public final static String ES_INDEX_NAME = "dgep_metadata";
    public final static String ES_INDEX_TYPE_FACE = "face";
    public static final String ES_INDEX_TYPE_BODY = "body";
    public static final String ES_INDEX_TYPE_VEHICLE = "vehicle";
    public static final String ES_INDEX_TYPE_NON_MOTOR_VEHICLE = "non_motor_vehicle";

}
