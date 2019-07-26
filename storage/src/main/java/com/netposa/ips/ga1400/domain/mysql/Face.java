package com.netposa.ips.ga1400.domain.mysql;

import com.netposa.ips.ga1400.domain.FaceBase;
import lombok.ToString;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * Author: zhangtao@netposa.com on 2019/3/27
 */
@ToString
@Table(name = "md_storage_face_data")
public class Face extends FaceBase implements Serializable {
    private static final long serialVersionUID = 6538792709957676880L;
    private String gbId;
}
