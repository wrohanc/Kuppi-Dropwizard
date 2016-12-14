package com.ro.learn.api;

import com.ro.learn.model.Order;

import java.util.List;

/**
 * Created by rohan on 2016-12-13.
 */
public class SearchResponseAPI {
    private long offset;
    private int limit;
    private long recordCount;
    private List<Order> order;

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }
}
