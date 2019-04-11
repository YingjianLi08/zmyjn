package com.zmyjn.core.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 页
 * @author liyj
 *
 * @param <T>
 */
public class Page<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static int DEFAULT_SIZE = 10;

    //当前页数
    private long page = 1L;

    //总页数
    private long pageCount;

    // 每页显示的条数
    private long limit = DEFAULT_SIZE;

    //总记录数
    private long total;



    public static int getDefaultSize() {
        return DEFAULT_SIZE;
    }

    public static void setDefaultSize(int defaultSize) {
        DEFAULT_SIZE = defaultSize;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
