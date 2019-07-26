package com.netposa.ips.ga1400.domain.mysql;

import com.netposa.ips.ga1400.domain.MotorVehicleBase;
import lombok.ToString;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * Author: zhangtao@netposa.com on 2019/3/27
 */
@ToString
@Table(name = "md_ac_data_motorvehicle")
public class MotorVehicle extends MotorVehicleBase implements Serializable {
    private static final long serialVersionUID = -7508971527370726116L;
    private String gbId;
}
