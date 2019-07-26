package com.netposa.ips.ga1400.domain.es;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Author: zhangtao@netposa.com on 2019/3/27
 */
//@ConditionalOnExpression("#{'${storage.dist-source.kafka.enable}.'.equals('true')}")
@Document(indexName = "dgep-face", type = "face", replicas = 0, shards = 3, refreshInterval = "-1")
@Data
@NoArgsConstructor
@ToString
public class Face4Es {
    @Id
    private String gbId;

    /**
     * 设备ID
     */
    @Field(type = FieldType.Keyword)
    private String deviceId;
    /**
     * 推送时间
     */
    @Field(type = FieldType.Integer)
    private Integer pushTime;
    /**
     * 来源类型
     */
    @Field(type = FieldType.Integer)
    private Integer sourceType;
}
