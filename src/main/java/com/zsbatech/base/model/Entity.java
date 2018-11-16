package com.zsbatech.base.model;

import java.io.Serializable;

/**
 * description:
 *
 * @author wxcsdb88
 * @since 2017/11/7 15:29
 */
public interface Entity extends Serializable {
    /**
     * 获取数据唯一主键 id
     *
     * @return {@link Long}
     */
    Long getId();

    /**
     * 是否是新建对象, 使用此方法，请确保实体model含有id(Long) 字段
     *
     * @return <p><code>true<code/>, if the real Entity contains the id(<code>Long<code/>)</p>
     * <p><code>false<code/>,if the real Entity not contains the id <code>Long<code/></p>
     */
    boolean newCreate();
}
