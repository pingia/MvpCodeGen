package com.github.pingia.mvpcodegen;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Description: 方法的映射
 * Created by zenglulin@youxiang.com
 * <p>
 * Date: 2022/8/1
 */
public class BusiPageMethod {
    private String methodName;  //方法名
    private String methodDesc;  //方法描述，业务描述
    private int methodDescResId;    //方法描述的字符串资源id
    private String returnType;  //返回类型，包名+类名的全名称,目前仅支持一个
    private String returnTypeArgument;  //返回类型中包括的泛型参数，可能为null
    private boolean isAsync;    //是否异步，同步和异步在处理网络响应时有区别
    private boolean isGetModelMethod;   //是否是获取model方法，若是，则会生成mvp相关类并进行调用，

    private BusiPageMethodUIConfig config;
    private List<BusiPageMethodParam> params;

    public boolean isAsync() {
        return isAsync;
    }

    public void setAsync(boolean async) {
        isAsync = async;
    }

    private List<String> modifierStrs;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public void setModifiers(Set<String> modifier_strs) {
        this.modifierStrs = new ArrayList<>(modifier_strs);
    }

    public List<String> getModifierStrs() {
        return modifierStrs;
    }

    public List<BusiPageMethodParam> getParams() {
        return params;
    }

    public void setParams(List<BusiPageMethodParam> params) {
        this.params = params;
    }

    public String getReturnTypeArgument() {
        return returnTypeArgument;
    }

    public void setReturnTypeArgument(String returnTypeArgument) {
        this.returnTypeArgument = returnTypeArgument;
    }

    public BusiPageMethodUIConfig getConfig() {
        return config;
    }

    public void setConfig(BusiPageMethodUIConfig config) {
        this.config = config;
    }

    public String getMethodDesc() {
        return methodDesc;
    }

    public void setMethodDesc(String methodDesc) {
        this.methodDesc = methodDesc;
    }

    public int getMethodDescResId() {
        return methodDescResId;
    }

    public void setMethodDescResId(int methodDescResId) {
        this.methodDescResId = methodDescResId;
    }

    public boolean isGetModelMethod() {
        return isGetModelMethod;
    }

    public void setGetModelMethod(boolean getModelMethod) {
        isGetModelMethod = getModelMethod;
    }
}
