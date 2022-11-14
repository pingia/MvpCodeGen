package com.github.pingia.mvpcodegen;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import javax.annotation.processing.Filer;
import javax.tools.JavaFileObject;

import static com.github.pingia.mvpcodegen.Utils.getFirstLetterUpper;

/**
 * Mvp 框架模式，涉及到的相关代码文件生成帮助类
 */
public class MvpApiGenerateHelper {


    private String outputJavaSrcDirPath;
    private String outputJScriptSrcDirPath;
    private boolean isAptSourcePath;
    private boolean isAptResourcePath;

    private Filer mFiler;

    /**
     * 构造函数 注解处理器使用
     * 
     * @param filer
     * @param outputJavaSrcDirPath
     * @param outputJScriptSrcDirPath
     */
    public MvpApiGenerateHelper(Filer filer, String outputJavaSrcDirPath, String outputJScriptSrcDirPath){
        this.mFiler = filer;
        this.outputJavaSrcDirPath = outputJavaSrcDirPath;
        this.outputJScriptSrcDirPath = outputJScriptSrcDirPath;
        
        String aptSrcDirPath = Utils.getAptSourceDirPath(filer) ;
        File aptSrcDirFile = new File(aptSrcDirPath);
        boolean isValidAptSrcDirPath = new File(aptSrcDirPath).isDirectory();
        
        this.isAptSourcePath = isValidAptSrcDirPath 
                && Utils.isDescendantFile(new File(outputJavaSrcDirPath), aptSrcDirFile);
        this.isAptResourcePath = isValidAptSrcDirPath
                && Utils.isDescendantFile(new File(outputJScriptSrcDirPath), aptSrcDirFile);
    }

    /**
     * 构造函数，可不在注解处理器中使用，方便测试
     * 
     * @param outputJavaSrcDirPath
     * @param outputJScriptSrcDirPath
     */
    public MvpApiGenerateHelper(String outputJavaSrcDirPath, String outputJScriptSrcDirPath){
        this.outputJavaSrcDirPath = outputJavaSrcDirPath;
        this.outputJScriptSrcDirPath = outputJScriptSrcDirPath;
        this.isAptSourcePath = false;
        this.isAptResourcePath = false;
    }
    
    private Writer getJavaFileWriter(String fileNameNoSuffix, BusiContractTemplateBean api) throws IOException{
        return getJavaFileWriter(fileNameNoSuffix, api.getPackageName());
    }

    private Writer getJavaFileWriter(String fileNameNoSuffix, String pkgName) throws IOException{
        if(!isAptSourcePath) {
            String finalSourceFileDir = outputJavaSrcDirPath + '/' + pkgName.replaceAll("\\.","/");
            File file = Utils.createFileNotExists(finalSourceFileDir, fileNameNoSuffix+".java");
            return new BufferedWriter (new OutputStreamWriter(new FileOutputStream(file,false), StandardCharsets.UTF_8));
        }else{
            JavaFileObject jfo = mFiler.createSourceFile(pkgName+"." + fileNameNoSuffix);
            boolean deleteSuc;
            if(jfo != null){
                deleteSuc = jfo.delete();
                if(!deleteSuc){
                    return jfo.openWriter();
                }
            }

            return mFiler.createSourceFile(pkgName + "." + fileNameNoSuffix).openWriter();
        }
    }

    public void generateAll(BusiContractTemplateBean api){
        if(null == api) return;

        System.out.println("gentrating mvp code generator");

        if(!Utils.isEmptyOrWhitespace(api.getBusiName())){    //写入tml api相关类
            try {
                String busiName = api.getBusiName();

                String contractPkgName = api.getPackageName() + ".contract";
                String presenterPkgName = api.getPackageName() + ".presenter";
                String viewPkgName = api.getPackageName() + ".view";
                String modelPkgName = api.getPackageName() + ".model";
                String delegatePkgName = api.getPackageName() + ".delegate";

                int pageViewType = api.getPageViewIntType();

                boolean generateMvpAssociate = true;    //是否产生mvp相关类（presenter \ wrapper \contract，依据是页面的业务类型）
                if(pageViewType == 1  //baseactivity
                        || pageViewType == 2  //basefragment
                        || pageViewType == 5    //singlefragmentactivity
                        || pageViewType == 10){ //webview
                    generateMvpAssociate = false;
                }

                if(generateMvpAssociate){

                    //创建contract接口类
                    String contractFileName = String.format(Constants.CLASS_FILE_NAME_CONRACT_INTERFACE_NOSUFFIX, busiName);
                    MvpJavaCodeGenerator contractGenerator = new MvpJavaCodeGenerator(api, getJavaFileWriter(contractFileName, contractPkgName), Constants.TEMPLATE_FILE_NAME_CONTRACT_INTERFACE);
                    contractGenerator.generate();

                    //创建代理类
                    String delegateFileName = getFirstLetterUpper(String.format(Constants.CLASS_FILE_NAME_DELEGATE_NOSUFFIX, busiName));
                    MvpJavaCodeGenerator presenterDelegateGenerator = new MvpJavaCodeGenerator(api, getJavaFileWriter(delegateFileName, delegatePkgName), Constants.TEMPLATE_FILE_NAME_PRESENTER_DELEGATE_CLASS);
                    presenterDelegateGenerator.generate();

                    //创建presenter类
                    String presenterFileName = getFirstLetterUpper(String.format(Constants.CLASS_FILE_NAME_PRESENTER_NOSUFFIX, busiName));
                    MvpJavaCodeGenerator presenterGenerator = new MvpJavaCodeGenerator(api, getJavaFileWriter(presenterFileName, presenterPkgName), Constants.TEMPLATE_FILE_NAME_PRESENTER_CLASS);
                    presenterGenerator.generate();
                }else{
                    viewPkgName = api.getPackageName();
                }

                api.setViewPackageName(viewPkgName);

                //创建fragment，阻塞对话框式的
                String viewFileName = null;
                String viewTemplateFileName = null;
                if(pageViewType >0) {
                    if (pageViewType % 2 == 0) {  //偶数作为fragment
                        viewFileName = getFirstLetterUpper(String.format(Constants.CLASS_FILE_NAME_VIEW_FRAGMENT_NOSUFFIX, busiName));
                    } else {      //奇数作为activity
                        viewFileName = getFirstLetterUpper(String.format(Constants.CLASS_FILE_NAME_VIEW_ACTIVITY_NOSUFFIX, busiName));
                    }
                }else{
                    viewFileName = getFirstLetterUpper(String.format(Constants.CLASS_FILE_NAME_VIEW_VIEW_NOSUFFIX, busiName));
                }

                switch (pageViewType) {
                    //BASE_ACTIVITY(1),
                    //    BLOCKING_ACTIVITY(3),
                    //    SINGLE_FRAGMENT_ACTIVITY(5),
                    //
                    //    BASE_FRAGMENT(2),
                    //    BLOCKING_FRAGMENT(4),
                    //    NOT_BLOCKING_FRAGMENT(6),
                    //    LOADING_LIST_FRAGMENT(8),
                    //    WEB_FRAGMENT(10);
                    case 1:
                        viewTemplateFileName = Constants.TEMPLATE_FILE_NAME_VIEW_BASE_ACTIVITY_CLASS;
                        break;
                    case 3:
                        viewTemplateFileName = Constants.TEMPLATE_FILE_NAME_VIEW_BLOCK_LOADING_ACTIVITY_CLASS;
                        break;
                    case 5:
                        viewTemplateFileName = Constants.TEMPLATE_FILE_NAME_VIEW_SINGLE_FRAGMENT_ACTIVITY_CLASS;
                        break;
                    case 7:
                        break;
                    case 2:
                        viewTemplateFileName = Constants.TEMPLATE_FILE_NAME_VIEW_BASE_FRAGMENT_CLASS;
                        break;
                    case 4:
                        viewTemplateFileName = Constants.TEMPLATE_FILE_NAME_VIEW_BLOCK_LOADING_FRAGMENT_CLASS;
                        break;
                    case 6:
                        viewTemplateFileName = Constants.TEMPLATE_FILE_NAME_VIEW_NOT_BLOCK_LOADING_FRAGMENT_CLASS;
                        break;
                    case 8:
                        //list page
                        break;
                    case 10:
                        //web page
                        break;
                    default:
                        // no page or other etc.
                        viewTemplateFileName = Constants.TEMPLATE_FILE_NAME_VIEW_NO_PAGE_CLASS;
                        break;
                }

                if(null != viewTemplateFileName) {
                    MvpJavaCodeGenerator viewGenerator = new MvpJavaCodeGenerator(api, getJavaFileWriter(viewFileName, viewPkgName), viewTemplateFileName);
                    viewGenerator.generate();
                }

            }catch (Exception e){
                e.printStackTrace();

            }

        }

    }
}
