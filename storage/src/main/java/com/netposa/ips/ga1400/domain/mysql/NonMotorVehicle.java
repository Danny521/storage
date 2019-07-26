package com.netposa.ips.ga1400.domain.mysql;

import com.netposa.ips.ga1400.domain.NonMotorVehicleBase;
import lombok.ToString;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * Author: zhangtao@netposa.com on 2019/3/27
 */
@ToString
@Table(name = "md_ac_data_nonmotorvehicle")
public class NonMotorVehicle extends NonMotorVehicleBase implements Serializable {
    private static final long serialVersionUID = 5326210443964287701L;
    private String gbId;
}
