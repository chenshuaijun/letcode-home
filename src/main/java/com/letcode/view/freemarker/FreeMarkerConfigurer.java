package com.letcode.view.freemarker;

public class FreeMarkerConfigurer extends
		org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer {
	private String[] templatePaths;

	public void setTemplatePaths(String[] templatePaths) {
		this.templatePaths = templatePaths;
		setTemplateLoaderPaths(templatePaths);

	}

	public String[] getTemplatePaths() {
		return templatePaths;
	}
}
