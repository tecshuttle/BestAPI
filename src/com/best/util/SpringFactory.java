package com.best.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringFactory implements ApplicationContextAware{

	private static ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		 this.context = context;
	}
	
	 /**
     * 从applicationContext中获取指定id的bean
     * @param id要获取的springBean的id
     * @return
     */
    public static Object getObject(String id) {
        Object object = null;
        object = context.getBean(id);
        return object;
     }

}
