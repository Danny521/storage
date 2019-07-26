package com.netposa.ips.ga1400.mapper.mysql;

import com.netposa.ips.ga1400.domain.mysql.Face;
import com.netposa.ips.ga1400.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author: zhangtao@netposa.com on 2019/3/27
 */
public interface MysqlFaceMapper extends BaseMapper<Face> {

    void batchInsert(@Param("list") List<Face> list);
}
