package com.netposa.ips.ga1400.domain.es;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

/**
 * Author: zhangtao@netposa.com on 2019/3/26
 */
@Document(indexName = "test-user", type = "user", replicas = 0, shards = 3, refreshInterval = "-1")
@Data
@NoArgsConstructor
@ToString
public class User {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String userName;

    @Field(type = FieldType.Integer)
    private Integer age;

    @Field(type = FieldType.Date)
    private Date birthday;

    @Field(type = FieldType.Keyword)
    private String description;

    /**
     * 1对多在spring-data-elasticsearch 统一为nested类型
     **/
    @Field(type = FieldType.Nested, includeInParent = true)
    private List<Role> roles;

    public User(String userName, Integer age, Date birthday, String description) {
        this.userName = userName;
        this.age = age;
        this.birthday = birthday;
        this.description = description;
    }
}
