package com.github.pingia.mvpcodegen;


import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * mvp java相关代码构建器，调用freemarker来生成java文件
 */
public class MvpJavaCodeGenerator extends AbstractApiCodeGenerator{

    public MvpJavaCodeGenerator(BusiContractTemplateBean api, Writer writer, String templateName) {
        super(api, writer,templateName);
    }

    @Override
    public void generate() throws IOException{
        try {

            Configuration config = new Configuration(Configuration.getVersion());
            config.setClassLoaderForTemplateLoading(getClass().getClassLoader(), "ftl");
            config.setDefaultEncoding("utf-8");
//            config.setDirectoryForTemplateLoading(new File("/ftl"));
            Template template = config.getTemplate(template_name);

            Map<String, Object> map = new HashMap<>();
            map.put("pageApi", mApi);
            template.process(map, mWriter);
            mWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
