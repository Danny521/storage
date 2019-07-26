package com.netposa.ips.ga1400.domain.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * Author: zhangtao@netposa.com on 2019/3/26
 */
@Document(indexName = "test-role", type = "role", replicas = 0, shards = 3)
@Data
public class Role {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String name;

    @Field(type = FieldType.Date)
    private Date createTime;

    @Field(type = FieldType.Keyword)
    private String description;

    public Role() {
    }

    public Role(String name, String description, Date createTime) {
        this.name = name;
        this.description = description;
        this.createTime = createTime;
    }
}
