package com.joaogcm.jsf.primefaces.spring.project.interfaces.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public interface HibernateCrud<T> extends Serializable {

	public void save(T obj) throws Exception;

	public void persist(T obj) throws Exception;

	public void saveOrUpdate(T obj) throws Exception;

	public void update(T obj) throws Exception;

	public void delete(T obj) throws Exception;

	T merge(T obj) throws Exception;

	public List<T> findList(Class<T> objs) throws Exception;

	public List<T> findListByQueryDinamic(String sql) throws Exception;

	Object findById(Class<T> obj, Long id) throws Exception;

	T findByIdGeneric(Class<T> obj, Long id) throws Exception;

	public void executeQueryDinamic(String sql) throws Exception;

	public void executeUpdateSQLDinamic(String sql) throws Exception;

	// Limpar a sessão do Hibernate
	public void clearSession() throws Exception;

	// Retira um objeto da sessão do Hibernate
	public void evict(Object objs) throws Exception;

	public Session getSession() throws Exception;

	public List<?> getListSQLDinamic(String sql) throws Exception;

	// Métodos para trabalhar com JDBC do Spring Framework
	public JdbcTemplate getJdbcTemplate();

	public SimpleJdbcTemplate getSimpleJdbcTemplate();

	public SimpleJdbcInsert getSimpleJdbcInsert();

	public Long totalRegister(String sql) throws Exception;

	public Query getQuery(String sql) throws Exception;

	// Carregamento dinâmico com JSF e Primefaces
	public List<T> findListByQueryDinamic(String sql, int minRegister, int maxRegister) throws Exception;

	public List<Object[]> getListSQLDinamicArray(String sql) throws Exception;
}