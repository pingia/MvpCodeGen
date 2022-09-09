package com.github.pingia.mvpcodegen;

public final class Constants {
    //使用到的contract模板和最终生成的contract接口类名称
    public static final String TEMPLATE_FILE_NAME_CONTRACT_INTERFACE = "IBusiContract.ftl";
    public static final String TEMPLATE_FILE_NAME_PRESENTER_DELEGATE_CLASS = "BusiWrapper.ftl";
    public static final String TEMPLATE_FILE_NAME_PRESENTER_CLASS = "BusiPresenter.ftl";

    public static final String TEMPLATE_FILE_NAME_VIEW_BLOCK_LOADING_FRAGMENT_CLASS = "BusiPageBlockLoadingFragment.ftl";
    public static final String TEMPLATE_FILE_NAME_VIEW_NOT_BLOCK_LOADING_FRAGMENT_CLASS = "BusiPageNoBlockLoadingFragment.ftl";

    public static final String TEMPLATE_FILE_NAME_VIEW_BLOCK_LOADING_ACTIVITY_CLASS = "BusiPageBlockLoadingActivity.ftl";

    public static final String TEMPLATE_FILE_NAME_VIEW_BASE_FRAGMENT_CLASS = "BusiPageBaseFragment.ftl";
    public static final String TEMPLATE_FILE_NAME_VIEW_BASE_ACTIVITY_CLASS = "BusiPageBaseActivity.ftl";

    public static final String TEMPLATE_FILE_NAME_VIEW_SINGLE_FRAGMENT_ACTIVITY_CLASS = "BusiPageSingleFragmentActivity.ftl";
    public static final String TEMPLATE_FILE_NAME_VIEW_NO_PAGE_CLASS = "BusiPageNoPage.ftl";



    public static final String CLASS_FILE_NAME_CONRACT_INTERFACE_NOSUFFIX = "I%sContract";  //%s是用业务名替换的
    public static final String CLASS_FILE_NAME_PRESENTER_NOSUFFIX = "%sPresenter";  //%s是用业务名替换的
    public static final String CLASS_FILE_NAME_DELEGATE_NOSUFFIX = "%sWrapper";  //%s是用业务名替换的
    public static final String CLASS_FILE_NAME_VIEW_FRAGMENT_NOSUFFIX = "%sFragment";        //%s是用业务名替换的
    public static final String CLASS_FILE_NAME_VIEW_ACTIVITY_NOSUFFIX = "%sActivity";        //%s是用业务名替换的
    public static final String CLASS_FILE_NAME_VIEW_VIEW_NOSUFFIX = "%sView";        //%s是用业务名替换的


}
