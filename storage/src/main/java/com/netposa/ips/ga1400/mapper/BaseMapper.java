package com.netposa.ips.ga1400.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 继承自己的BaseMapper
 * 特别注意，该接口不能被扫描到，否则会出错
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}