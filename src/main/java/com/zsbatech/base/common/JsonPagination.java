package com.zsbatech.base.common;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * description:
 *
 * @author wxcsdb88
 * @since 2017/11/7 10:03
 */
public class JsonPagination<T> implements Serializable {
    //当前页
    private int pageNum;
    //每页的数量
    private int pageSize;
    //当前页的数量
    @JSONField(serialize = false)
    private int size;
    //总记录数
    private long total;
    //总页数
    @JSONField(serialize = false)
    private int pages;
    //结果集
    private T data;

    public JsonPagination() {
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("JsonPagination{");
        sb.append("pageNum=").append(pageNum);
        sb.append(", pageSize=").append(pageSize);
        sb.append(", size=").append(size);
        sb.append(", total=").append(total);
        sb.append(", pages=").append(pages);
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
