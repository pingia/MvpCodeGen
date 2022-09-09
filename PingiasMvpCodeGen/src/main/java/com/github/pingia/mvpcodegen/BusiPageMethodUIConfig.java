package com.github.pingia.mvpcodegen;

/**
 * Description:
 * Created by zenglulin@youxiang.com
 * <p>
 * Date: 2022/8/3
 */
public class BusiPageMethodUIConfig {
    private boolean showRequestLoading;
    private boolean showResultSuccess;
    private boolean showResultFail;

    public boolean isShowRequestLoading() {
        return showRequestLoading;
    }

    public void setShowRequestLoading(boolean showRequestLoading) {
        this.showRequestLoading = showRequestLoading;
    }

    public boolean isShowResultSuccess() {
        return showResultSuccess;
    }

    public void setShowResultSuccess(boolean showResultSuccess) {
        this.showResultSuccess = showResultSuccess;
    }

    public boolean isShowResultFail() {
        return showResultFail;
    }

    public void setShowResultFail(boolean showResultFail) {
        this.showResultFail = showResultFail;
    }
}
