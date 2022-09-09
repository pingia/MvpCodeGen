package com.github.pingia.mvpcodegen;

import java.util.List;

/**
 * Description: 业务contract类需要的
 * Created by zenglulin@youxiang.com
 * <p>
 * Date: 2022/8/1
 */
public class BusiContractTemplateBean {
    private String packageName;
    private String viewPackageName;
    private String busiName;
    private String baseLoadPresenterQualifiedClassName;

    private int pageLayoutResId;
    private int pageTitleLayoutResId;

    private List<BusiPageMethod> methods;
    private List<BusiPageListMethod> listMethods;

    private int pageViewIntType;

    private String singleFragmentClassQualifiedClassName;



    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getBaseLoadPresenterQualifiedClassName() {
        return baseLoadPresenterQualifiedClassName;
    }

    public void setBaseLoadPresenterQualifiedClassName(String baseLoadPresenterQualifiedClassName) {
        this.baseLoadPresenterQualifiedClassName = baseLoadPresenterQualifiedClassName;
    }


    public List<BusiPageMethod> getMethods() {
        return methods;
    }

    public void setMethods(List<BusiPageMethod> methods) {
        this.methods = methods;
    }

    public List<BusiPageListMethod> getListMethods() {
        return listMethods;
    }

    public void setListMethods(List<BusiPageListMethod> listMethods) {
        this.listMethods = listMethods;
    }

    public String getBusiName() {
        return busiName;
    }

    public void setBusiName(String busiName) {
        this.busiName = busiName;
    }

    public int getPageLayoutResId() {
        return pageLayoutResId;
    }

    public void setPageLayoutResId(int pageLayoutResId) {
        this.pageLayoutResId = pageLayoutResId;
    }

    public int getPageTitleLayoutResId() {
        return pageTitleLayoutResId;
    }

    public void setPageTitleLayoutResId(int pageTitleLayoutResId) {
        this.pageTitleLayoutResId = pageTitleLayoutResId;
    }

    public int getPageViewIntType() {
        return pageViewIntType;
    }

    public void setPageViewIntType(int pageViewIntType) {
        this.pageViewIntType = pageViewIntType;
    }

    public String getSingleFragmentClassQualifiedClassName() {
        return singleFragmentClassQualifiedClassName;
    }

    public void setSingleFragmentClassQualifiedClassName(String singleFragmentClassQualifiedClassName) {
        this.singleFragmentClassQualifiedClassName = singleFragmentClassQualifiedClassName;
    }

    public String getViewPackageName() {
        return viewPackageName;
    }

    public void setViewPackageName(String viewPackageName) {
        this.viewPackageName = viewPackageName;
    }
}