package com.joaogcm.jsf.primefaces.spring.project.interfaces.hibernate.impl;

import java.io.Serializable;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SimpleJdbcCallImpl extends SimpleJdbcCall implements Serializable {

	private static final long serialVersionUID = -4349867100281701704L;

	public SimpleJdbcCallImpl(DataSource dataSource) {
		super(dataSource);
	}
}