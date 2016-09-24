package com.letcode.view;

import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class JsonViewResolver extends AbstractTemplateViewResolver {

	public JsonViewResolver() {
		setViewClass(requiredViewClass());
	}

	/**
	 * Requires {@link MappingJackson2JsonView}.
	 */
	@Override
	protected Class<?> requiredViewClass() {
		return MappingJackson2JsonView.class;
	}

}
