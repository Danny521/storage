package com.netposa.ips.ga1400.service.impl;

import com.netposa.ips.ga1400.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author chensujian on 2019/3/2.
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private Mapper<T> mapper;

    @Override
    public T queryById(int id) {
        // TODO Auto-generated method stub
        return this.mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<T> queryAll() {
        // TODO Auto-generated method stub
        return this.mapper.select(null);
    }

    @Override
    public T queryOne(T record) {
        // TODO Auto-generated method stub
        return this.mapper.selectOne(record);
    }

    @Override
    public List<T> queryListByWhere(T record) {
        // TODO Auto-generated method stub
        return this.mapper.select(record);
    }

    @Override
    public Integer save(T t) {
        // TODO Auto-generated method stub
        return this.mapper.insert(t);
    }

    @Override
    public Integer saveSelective(T t) {
        // TODO Auto-generated method stub
        return this.mapper.insertSelective(t);
    }

    @Override
    public Integer update(T t) {
        // TODO Auto-generated method stub
        return this.mapper.updateByPrimaryKey(t);
    }

    @Override
    public Integer updateSelective(T t) {
        // TODO Auto-generated method stub
        return this.mapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public Integer deleteById(Integer id) {
        // TODO Auto-generated method stub
        return this.mapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer deleteByIds(Class<T> clazz, String property,
                               List<Object> values) {
        // TODO Auto-generated method stub
        Example example = new Example(clazz);
        example.createCriteria().andIn(property, values);
        return this.mapper.deleteByExample(example);
    }

    @Override
    public Integer deleteByWhere(T record) {
        // TODO Auto-generated method stub
        return this.mapper.delete(record);
    }
}
