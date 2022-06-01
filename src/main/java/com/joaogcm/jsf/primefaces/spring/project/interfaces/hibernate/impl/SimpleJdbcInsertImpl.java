package com.joaogcm.jsf.primefaces.spring.project.interfaces.hibernate.impl;

import java.io.Serializable;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)

public class SimpleJdbcInsertImpl extends SimpleJdbcInsert implements Serializable {

	private static final long serialVersionUID = -8347151235051770119L;

	public SimpleJdbcInsertImpl(DataSource dataSource) {
		super(dataSource);
	}
}