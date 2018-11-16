package com.zsbatech.base.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by wxcsdb88 on 2017/9/5 18:12.
 */
public interface BaseService<ID, T extends Serializable> {
    /**
     * 添加记录
     *
     * @param record
     * @return int
     */
    int insert(T record);

    /**
     * 根据主键删除记录
     *
     * @param primaryKey
     * @return int
     */
    int deleteByPrimaryKey(ID primaryKey);

    /**
     * 根据主键进行更新, 具体字段根据具体方法设置
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKey(T record);

    /**
     * 根据主键进行查询,必须保证结果唯一
     *
     * @param primaryKey
     * @return T
     */
    T selectByPrimaryKey(ID primaryKey);

    /**
     * 根据实体类不为null的字段进行查询,条件全部使用=号and条件
     *
     * @param record
     * @return List<T>
     */
    
    List<T> select(T record);
   

    /**
     * 根据实体类不为null的字段进行查询并删除, 条件全部使用=号and条件
     *
     * @param conditionRecord
     * @return int
     */
    int deleteByCondition(T conditionRecord);

    /**
     * 根据实体类不为null的字段进行查询并更新数据
     *
     * @param conditionRecord
     * @param record
     * @return
     */
    int updateByCondition(T conditionRecord, T record);

    /**
     * 判断数据是否能删除, 简单判断存在与否
     *
     * @param id 主键
     * @return <p><code>true</code>&nbsp;&nbsp;如果数据可以删除,默认可以删除</p><p><code>false</code>&nbsp;如果数据不可以删除</p>
     */
    default boolean isDeletable(Long id) {
        return true;
    }

    /**
     * 判断数据是否能删除, 复杂条件判断
     *
     * @param map 用于判断是否可以删除数据的条件
     * @return <p><code>true</code>&nbsp;&nbsp;如果数据可以删除,默认可以删除</p><p><code>false</code>&nbsp;如果数据不可以删除</p>
     */
    default boolean isDeletable(Map<String, Object> map) {
        return true;
    }

}
