// this file is created by freemarker template file!!!

package ${pageApi.packageName}.delegate;


import cn.sunline.basenetworklib2.net.ICallback;
import cn.sunline.basenetworklib2.net.BaseJsonCallback;

/**
 * Description:
 * Created by zenglulin@youxiang.com
 * <p>
 * Date: ${.now}
 */
 <#assign busiName = pageApi.busiName>   <#-- 注意声明变量的assign语句块内，引用模板变量不需要用${} -->
 <#assign methodList = pageApi.methods>

public class ${busiName?cap_first}Wrapper {
        <#list methodList as method>
             <#if (method.getModelMethod)>
            public void ${method.methodName}(
                <#assign paramList = method.params>
                <#list paramList as methodParam>
                    ${methodParam.paramType} ${methodParam.paramName},
                </#list>
                <#if method.returnTypeArgument??>
                BaseJsonCallback<${method.returnType}<${method.returnTypeArgument}>> callback
                <#else>
                BaseJsonCallback<${method.returnType}> callback
                </#if>
            ){
                //invoke retrofit api,then convert observable to ICallback.
            }

            </#if>
        </#list>
}
