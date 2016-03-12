package com.letcode.freemarker;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class HtmlGenerator {
    public String generate(String template, Map<String,Object> variables) throws IOException, TemplateException{     
        BufferedWriter writer = null;   
        String htmlContent = "";
        try{
        	Configuration config = FreemarkerConfiguration.getConfiguation();    
        	config.setDefaultEncoding("UTF-8");
        	Template tp = config.getTemplate(template);     
        	StringWriter stringWriter = new StringWriter();       
        	writer = new BufferedWriter(stringWriter);  
        	
        	tp.setEncoding("UTF-8");       
        	tp.process(variables, writer);       
        	htmlContent = stringWriter.toString();     
        	writer.flush();       
        	
        }finally{
        	if(writer!=null)
        		writer.close();     
        }
        return htmlContent;     
    }     

}
