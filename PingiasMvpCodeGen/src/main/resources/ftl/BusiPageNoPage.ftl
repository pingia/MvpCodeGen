 // this file is created by freemarker template file!!!

 <#assign busiName = pageApi.busiName>   <#-- 注意声明变量的assign语句块内，引用模板变量不需要用${} -->
 <#assign methodList = pageApi.methods>
  <#assign classNamePrefix = busiName?cap_first >

package ${pageApi.viewPackageName};

import android.app.Activity;
import android.content.Intent;
import cn.sunline.uicommonlib.utils.ToastUtil;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import com.github.pingia.ui.common.utils.BizToastUtil;
import java.lang.String;
import androidx.annotation.NonNull;

import ${pageApi.packageName}.contract.I${busiName}Contract;
import ${pageApi.packageName}.presenter.${classNamePrefix}Presenter;

/**
 * Description:
 * Created by zenglulin@youxiang.com
 * <p>
 * Date: ${.now}
 */
public class ${classNamePrefix}View implements I${busiName}Contract.View
{
    private ${pageApi.packageName}.presenter.${classNamePrefix}Presenter presenter;
    private Context mContext;

    public ${classNamePrefix}View(@NonNull Context context){
        mContext = context;
    }

    <#-- 生成对presenter进行调用的所有私有方法 -->
    <#list methodList as method>
    private void do${method.methodName?cap_first}(
       <#assign paramList = method.params>
       <#list paramList as methodParam>
           ${methodParam.paramType} ${methodParam.paramName} <#if methodParam_index != paramList?size-1 >,</#if>  <#-- 方法参数中间加逗号分隔 -->
       </#list>){
       //TODO  parse presenter method if need.

       <#if (method.getModelMethod)>
       if(null != presenter){
            presenter.${method.methodName}(
                <#assign paramList = method.params>
                       <#list paramList as methodParam>
                           ${methodParam.paramName} <#if methodParam_index != paramList?size-1 >,</#if>  <#-- 方法参数中间加逗号分隔 -->
                       </#list>
            );
        }
        </#if>
    }

    </#list>

    <#list methodList as method>
    <#if (method.getModelMethod)>
    <#assign resultMethodName = "show${method.methodName?cap_first}Result"> <#-- 方法名转大写 -->
    @Override
    public void ${resultMethodName}(
        <#assign resultMethodParamType = method.returnType>
        <#if (method.returnTypeArgument)??>
            ${resultMethodParamType} result, boolean success, String msg, ${method.returnTypeArgument} entity
        <#else>
            ${resultMethodParamType} result, boolean success , String msg
        </#if>){
        //TODO handler your user interface logic after result!

        if(success){
       <#if (method.config)?? && (method.config.showResultSuccess)> <#-- 如果需要显示成功toast -->
            String methodBiz = <#if method.methodDescResId != -1 && method.methodDescResId != 0>getString(${method.methodDescResId})<#else>"${method.methodDesc}"</#if>;
            BizToastUtil.showBizResultToast(mContext, methodBiz, success, msg);
       </#if>
           //TODO do your success logic.
        }else{
      <#if (method.config)?? && (method.config.showResultFail)> <#-- 如果需要显示失败toast -->
            String methodBiz = <#if method.methodDescResId != -1 && method.methodDescResId != 0>getString(${method.methodDescResId})<#else>"${method.methodDesc}"</#if>;
            BizToastUtil.showBizResultToast(mContext, methodBiz, success, msg);
      </#if>
          //TODO do your fail logic.
        }
    }
    </#if>
    </#list>

    @Override
    public void showLoading() {
        //do nothing
    }

    @Override
    public void hideLoading() {
        //do nothing
    }

    public void showError(Throwable e){
        //do your things after error occur.
    }

    private void attach(){
        this.presenter = new ${classNamePrefix}Presenter();
        this.presenter.attachView(this);
    }

    private void detach() {
       // TODO do your detach logic before view is disposed.
       if(null != this.presenter){
           this.presenter.detachView();
       }
    }
}
