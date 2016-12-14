package com.ro.learn.api;

/**
 * Created by rohanw on 2/20/2016.
 */
public class SearchRequestAPI {
    private String productCode;
    ;
    private int offset;
    private short limit;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public short getLimit() {
        return limit;
    }

    public void setLimit(short limit) {
        this.limit = limit;
    }
}
