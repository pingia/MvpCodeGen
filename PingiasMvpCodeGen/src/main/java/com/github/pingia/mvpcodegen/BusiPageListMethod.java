package com.github.pingia.mvpcodegen;

/**
 * Description:
 * Created by zenglulin@youxiang.com
 * <p>
 * Date: 2022/8/4
 */
public class BusiPageListMethod {
    private boolean isFenyeQuery;   //是否分页

    private int pageSize;   //分页查询大小


    public boolean isFenyeQuery() {
        return isFenyeQuery;
    }

    public void setFenyeQuery(boolean fenyeQuery) {
        isFenyeQuery = fenyeQuery;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
