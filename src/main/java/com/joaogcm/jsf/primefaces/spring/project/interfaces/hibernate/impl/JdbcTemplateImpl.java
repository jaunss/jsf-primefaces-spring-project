package com.joaogcm.jsf.primefaces.spring.project.interfaces.hibernate.impl;

import java.io.Serializable;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class JdbcTemplateImpl extends JdbcTemplate implements Serializable {

	private static final long serialVersionUID = 5853777058172710151L;

	public JdbcTemplateImpl(DataSource dataSource) {
		super(dataSource);
	}
}