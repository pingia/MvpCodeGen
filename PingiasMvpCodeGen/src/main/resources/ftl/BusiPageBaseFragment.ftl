// this file is created by freemarker template file!!!

 <#assign busiName = pageApi.busiName>   <#-- 注意声明变量的assign语句块内，引用模板变量不需要用${} -->
 <#assign methodList = pageApi.methods>
  <#assign classNamePrefix = busiName?cap_first >

package ${pageApi.viewPackageName};

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;

/**
 * Description:
 * Created by zenglulin@youxiang.com
 * <p>
 * Date: ${.now}
 */
public class ${classNamePrefix}Fragment extends com.github.pingia.ui.framework.base.BaseToolbarFragment
{
    public ${classNamePrefix}Fragment() {
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        //do things on fragment on attach lifecycle. usually do something attach from activity
    }

    public void onDetach(){
        super.onDetach();

        //do things on fragment on detach lifecycle. usually do something detach from activity
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //do things on fragment create lifecycle.
    }


    @Override
    public void onDestroy() {
        //do things on fragment destroy lifecycle.

        super.onDestroy();
    }


    @Override
    public void onResume() {
        super.onResume();

        //do things on fragment resume lifecycle. usually request data again.
    }

    @Override
    public void onStop() {
        super.onStop();

        //do things on fragment stop lifecycle, usually save tiny data, or stop anim.
    }

    @Override
    protected void initView(View view) {

        //init your viewIds, init load data,etc...
    }

    @Override
    protected int getPageToolbarResId() {
        return ${pageApi.pageTitleLayoutResId?c};
    }

    @Override
    protected View getLayoutView(LayoutInflater inflater) {
        return inflater.inflate(${pageApi.pageLayoutResId?c}, null);
    }

    <#list methodList as method>
    private void do${method.methodName?cap_first}(
       <#assign paramList = method.params>
       <#list paramList as methodParam>
           ${methodParam.paramType} ${methodParam.paramName} <#if methodParam_index != paramList?size-1 >,</#if>  <#-- 方法参数中间加逗号分隔 -->
       </#list>){
       //TODO

    }

    </#list>
}
