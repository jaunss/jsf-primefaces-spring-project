package com.joaogcm.jsf.primefaces.spring.project.interfaces.hibernate.impl;

import java.io.Serializable;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SimpleJdbcTemplateImpl extends SimpleJdbcTemplate implements Serializable {

	private static final long serialVersionUID = -7026682349633200662L;

	public SimpleJdbcTemplateImpl(DataSource dataSource) {
		super(dataSource);
	}
}