package com.joaogcm.jsf.primefaces.spring.project.util.framework;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class UtilFramework implements Serializable {

	private static final long serialVersionUID = -7844829297846111351L;

	private static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();

	public synchronized static ThreadLocal<Long> getThreadLocal() {
		return threadLocal;
	}
}