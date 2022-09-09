package com.github.pingia.mvpcodegen;

import java.io.IOException;
import java.io.Writer;

public abstract class AbstractApiCodeGenerator {
    protected BusiContractTemplateBean mApi;
    protected Writer mWriter;
    protected String template_name;
    public AbstractApiCodeGenerator(BusiContractTemplateBean api, Writer writer, String templateName){
        this.mApi = api;
        this.mWriter  = writer;
        this.template_name = templateName;
    }

    public abstract void generate() throws IOException;
}
