package com.github.pingia.mvpcodegen;

/**
 * Description:
 * Created by zenglulin@youxiang.com
 * <p>
 * Date: 2022/8/1
 */
public class BusiPageMethodParam {

    private String paramName;       //参数名称
    private String paramType;       //参数类型

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String toString(){
        return "paramName:" + paramName + ",paramType:" +paramType;
    }
}
