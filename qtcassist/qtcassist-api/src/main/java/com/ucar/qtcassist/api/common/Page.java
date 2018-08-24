package com.ucar.qtcassist.api.common;

import java.util.List;

/**
 * @Description: (分页返回前端页面数据)
 *
 * @auther: cong.zhou01
 * @date: 2018/5/31 15:55
 * @param:
 * @return:
 */
public class Page<T> {

    public static final Integer PAGE_NO = 1;
    public static final Integer PAGE_SIZE = 10;

    private int pageNo = 1;
    private int pageSize = 10;
    private int total;
    private int page;
    private List<T> rows;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
        int totalPage = total%pageSize==0 ? total/pageSize : total/pageSize + 1;
        this.setPage(totalPage);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Page [pageNo=").append(pageNo).append(", pageSize=")
                .append(pageSize).append(", rows=").append(rows).append(
                ", page=").append(page).append(
                ", total=").append(total).append("]");
        return builder.toString();
    }

}

