package com.netposa.ips.ga1400.domain.test;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * @Author: zhangtao@netposa.com
 * @Description:
 * @Date: 2019/7/1
 */
@Table(name = "users")
@Data
public class User {
    private String id;
    private String name;
    private String idCardNumber;
    private Integer gender;
    private String phoneNo;
    private String email;
    private Integer status;
    private String department;
    private Date createTime;
}
