package com.project1.page;

import org.springframework.stereotype.Component;

@Component
public class Page {
    private int pageNum;
    private int pageSize;
    private int offset;

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getOffset() {
        this.offset = (pageNum -1)*pageSize;
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = (pageNum-1)*pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

}
