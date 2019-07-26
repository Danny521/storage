package com.netposa.ips.ga1400.domain.mysql;

import com.netposa.ips.ga1400.domain.BodyBase;
import lombok.ToString;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * Author: zhangtao@netposa.com on 2019/3/27
 */
@ToString
@Table(name = "md_ac_data_body")
public class Body extends BodyBase implements Serializable {
    private static final long serialVersionUID = -4805856441225453134L;
    private String gbId;
}
