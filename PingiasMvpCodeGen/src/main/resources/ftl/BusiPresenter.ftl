 // this file is created by freemarker template file!!!

 <#assign busiName = pageApi.busiName>   <#-- 注意声明变量的assign语句块内，引用模板变量不需要用${} -->
 <#assign methodList = pageApi.methods>
  <#assign classNamePrefix = busiName?cap_first >

package ${pageApi.packageName}.presenter;

import ${pageApi.packageName}.contract.I${busiName}Contract;
import ${pageApi.packageName}.delegate.${classNamePrefix}Wrapper;


import cn.sunline.basenetworklib2.net.BaseJsonCallback;
import cn.sunline.basenetworklib2.net.ICallback;

/**
 * Description:
 * Created by zenglulin@youxiang.com
 * <p>
 * Date: ${.now}
 */



public class ${classNamePrefix}Presenter extends I${busiName}Contract.Presenter {
    private ${classNamePrefix}Wrapper wrapper;

    public ${classNamePrefix}Presenter(){
        wrapper = new ${classNamePrefix}Wrapper();
    }

    <#list methodList as method>
     <#if (method.getModelMethod)>
        @Override
        public void ${method.methodName}(
            <#assign paramList = method.params>
            <#list paramList as methodParam>
                ${methodParam.paramType} ${methodParam.paramName} <#if methodParam_index != paramList?size-1 >,</#if>  <#-- 方法参数中间加逗号分隔 -->
            </#list>){
                <#if (method.config)?? && (method.config.showRequestLoading)> <#-- 如果配置了需要弹出请求对话框 -->
                    getMvpView().showLoading();
                </#if>

                <#assign resultMethodName = "show${method.methodName?cap_first}Result"> <#-- 方法名转大写 -->

                <#if method.returnTypeArgument??>
                    BaseJsonCallback<${method.returnType}<${method.returnTypeArgument}>> callback = new BaseJsonCallback<${method.returnType}<${method.returnTypeArgument}>>(this) {
                            @Override
                            public void onSuccess(${method.returnType}<${method.returnTypeArgument}> result) {
                                getMvpView().hideLoading();
                                if(null == result) return;

                                getMvpView().${resultMethodName}(result, result.isSuccess(), result.getDesc(), result.getData());
                            }
                        };

                    wrapper.${method.methodName}(
                        <#assign paramList = method.params>
                        <#list paramList as methodParam>
                                ${methodParam.paramName},
                        </#list>
                        callback
                    );

                <#else>
                    BaseJsonCallback<${method.returnType}> callback = new BaseJsonCallback<${method.returnType}>(this) {
                        @Override
                        public void onSuccess(${method.returnType} result) {
                            getMvpView().hideLoading();
                            if(null == result) return;

                            getMvpView().${resultMethodName}(result, result.isSuccess(), result.getDesc());
                        }
                    };

                    wrapper.${method.methodName}(
                        <#assign paramList = method.params>
                        <#list paramList as methodParam>
                                ${methodParam.paramName},
                        </#list>
                        callback
                    );
                </#if>            

           

            
        }

        </#if>
    </#list>
}