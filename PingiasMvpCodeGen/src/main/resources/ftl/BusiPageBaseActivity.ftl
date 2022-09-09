// this file is created by freemarker template file!!!

 <#assign busiName = pageApi.busiName>   <#-- 注意声明变量的assign语句块内，引用模板变量不需要用${} -->
 <#assign methodList = pageApi.methods>
  <#assign classNamePrefix = busiName?cap_first >

package ${pageApi.viewPackageName};

import android.app.Activity;
import android.content.Intent;
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
public class ${classNamePrefix}Activity extends com.github.pingia.ui.framework.base.BaseToolBarActivity
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
