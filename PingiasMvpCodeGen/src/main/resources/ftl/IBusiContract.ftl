// this file is created by freemarker template file!!!

package ${pageApi.packageName}.contract;

import com.github.pingia.ui.framework.base.ILoadView;
import java.util.List;

/**
 * Description:
 * Created by zenglulin@youxiang.com
 * <p>
 * Date: ${.now}
 */

<#assign busiName = pageApi.busiName>   <#-- 注意声明变量的assign语句块内，引用模板变量不需要用${} -->
<#assign methodList = pageApi.methods>


public interface I${busiName}Contract {
    public interface View extends ILoadView {
            <#list methodList as method>
                <#if (method.getModelMethod)>
                    <#assign resultMethodName = "show${method.methodName?cap_first}Result"> <#-- 方法名转大写 -->
                    void ${resultMethodName}(
                        <#assign resultMethodParamType = method.returnType>

                        <#if (method.returnTypeArgument)??>
                            ${resultMethodParamType} result, boolean success, String msg, ${method.returnTypeArgument} entity
                        <#else>
                            ${resultMethodParamType} result, boolean success , String msg
                        </#if>
                    );
                </#if>
            </#list>
    }

    public abstract static class Presenter extends ${pageApi.baseLoadPresenterQualifiedClassName}<I${busiName}Contract.View> {

        <#assign methodList = pageApi.methods>
        <#list methodList as method>
            <#if (method.getModelMethod)>
                abstract public void ${method.methodName}(
                    <#assign paramList = method.params>
                    <#list paramList as methodParam>
                        ${methodParam.paramType} ${methodParam.paramName} <#if methodParam_index != paramList?size-1 >,</#if>  <#-- 方法参数中间加逗号分隔 -->
                    </#list>
                );
            </#if>
        </#list>
    }
}
