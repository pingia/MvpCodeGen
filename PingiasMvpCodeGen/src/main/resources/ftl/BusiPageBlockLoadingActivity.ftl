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

import ${pageApi.packageName}.contract.I${busiName}Contract;
import ${pageApi.packageName}.presenter.${classNamePrefix}Presenter;

/**
 * Description:
 * Created by zenglulin@youxiang.com
 * <p>
 * Date: ${.now}
 */
public class ${classNamePrefix}Activity extends com.github.pingia.ui.framework.base.BaseLoadingToolBarActivity<I${busiName}Contract.Presenter> implements I${busiName}Contract.View
{
    public static void startMe(Activity fromActivity) {
        Intent intent = new Intent(fromActivity, ${classNamePrefix}Activity.class);
        fromActivity.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //do things on activity create lifecycle.
    }


    @Override
    public void onDestroy() {
        //do things on activity destroy lifecycle.

        super.onDestroy();
    }


    @Override
    public void onResume() {
        super.onResume();

        //do things on activity resume lifecycle. usually request data again.
    }

    @Override
    public void onStop() {
        super.onStop();

        //do things on activity stop lifecycle, usually save tiny data, or stop anim.
    }

    @Override
    public void onActivityInit(Bundle savedInstanceState) {
        super.onActivityInit(savedInstanceState);
        initView();
    }

    private void initView(){
        //do viewIds init.
    }

    @Override
    protected int getPageToolbarResId() {
        return ${pageApi.pageTitleLayoutResId?c};
    }

    @Override
    protected int getLayoutId() {
        return ${pageApi.pageLayoutResId?c};
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
        getPresenter().${method.methodName}(
            <#assign paramList = method.params>
                   <#list paramList as methodParam>
                       ${methodParam.paramName} <#if methodParam_index != paramList?size-1 >,</#if>  <#-- 方法参数中间加逗号分隔 -->
                   </#list>
        );
        </#if>
    }

    </#list>

    @NonNull
    @Override
    public I${busiName}Contract.Presenter onCreatePresenter() {
        return new ${classNamePrefix}Presenter();
    }

    public void showError(Throwable e){
        super.showError(e);

        //do your things after error occur.
    }

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
            BizToastUtil.showBizResultToast(this, methodBiz, success, msg);
       </#if>
           //TODO do your success logic.
        }else{
      <#if (method.config)?? && (method.config.showResultFail)> <#-- 如果需要显示失败toast -->
            String methodBiz = <#if method.methodDescResId != -1 && method.methodDescResId != 0>getString(${method.methodDescResId})<#else>"${method.methodDesc}"</#if>;
            BizToastUtil.showBizResultToast(this, methodBiz, success, msg);
      </#if>
          //TODO do your fail logic.
        }
    }
    </#if>
    </#list>
}
