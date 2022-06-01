package com.joaogcm.jsf.primefaces.spring.project.interfaces.hibernate.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.joaogcm.jsf.primefaces.spring.project.interfaces.hibernate.HibernateCrud;
import com.joaogcm.jsf.primefaces.spring.project.session.HibernateUtil;

public class HibernateCrudImpl<T> implements HibernateCrud<T> {

	private static final long serialVersionUID = 8218251724829425056L;

	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Autowired
	private JdbcTemplateImpl jdbcTemplateImpl;

	@Autowired
	private SimpleJdbcTemplateImpl simpleJdbcTemplateImpl;

	@Autowired
	private SimpleJdbcInsertImpl simpleJdbcInsertImpl;

	@Autowired
	private SimpleJdbcCallImpl simpleJdbcCallImpl;

	@Override
	public void save(T obj) throws Exception {
		validateSessionFactory();
		sessionFactory.getCurrentSession().save(obj);
		executeFlushSession();
	}

	@Override
	public void persist(T obj) throws Exception {
		validateSessionFactory();
		sessionFactory.getCurrentSession().persist(obj);
		executeFlushSession();
	}

	@Override
	public void saveOrUpdate(T obj) throws Exception {
		validateSessionFactory();
		sessionFactory.getCurrentSession().saveOrUpdate(obj);
		executeFlushSession();
	}

	@Override
	public void update(T obj) throws Exception {
		validateSessionFactory();
		sessionFactory.getCurrentSession().update(obj);
		executeFlushSession();
	}

	@Override
	public void delete(T obj) throws Exception {
		validateSessionFactory();
		sessionFactory.getCurrentSession().delete(obj);
		executeFlushSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T merge(T obj) throws Exception {
		validateSessionFactory();
		T t = (T) sessionFactory.getCurrentSession().merge(obj);
		executeFlushSession();

		return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findList(Class<T> objs) throws Exception {
		validateSessionFactory();

		StringBuilder query = new StringBuilder();
		query.append("SELECT DISTINCT(entity) FROM ").append(objs.getSimpleName()).append(" entity");

		List<T> ts = sessionFactory.getCurrentSession().createQuery(query.toString()).list();

		return ts;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findListByQueryDinamic(String sql) throws Exception {
		validateSessionFactory();

		List<T> ts = new ArrayList<T>();
		ts = sessionFactory.getCurrentSession().createQuery(sql).list();

		return ts;
	}

	@Override
	public Object findById(Class<T> obj, Long id) throws Exception {
		validateSessionFactory();

		Object ob = sessionFactory.getCurrentSession().load(getClass(), id);

		return ob;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findByIdGeneric(Class<T> obj, Long id) throws Exception {
		validateSessionFactory();

		T t = (T) sessionFactory.getCurrentSession().load(getClass(), id);

		return t;
	}

	@Override
	public void executeQueryDinamic(String sql) throws Exception {
		validateSessionFactory();
		sessionFactory.getCurrentSession().createQuery(sql).executeUpdate();
		executeFlushSession();
	}

	@Override
	public void executeUpdateSQLDinamic(String sql) throws Exception {
		validateSessionFactory();
		sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
		executeFlushSession();
	}

	@Override
	public void clearSession() throws Exception {
		sessionFactory.getCurrentSession().clear();
	}

	@Override
	public void evict(Object objs) throws Exception {
		validateSessionFactory();
		sessionFactory.getCurrentSession().evict(objs);
	}

	@Override
	public Session getSession() throws Exception {
		validateSessionFactory();
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<?> getListSQLDinamic(String sql) throws Exception {
		validateSessionFactory();

		List<?> ts = sessionFactory.getCurrentSession().createSQLQuery(sql).list();

		return ts;
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplateImpl;
	}

	@Override
	public SimpleJdbcTemplate getSimpleJdbcTemplate() {
		return simpleJdbcTemplateImpl;
	}

	@Override
	public SimpleJdbcInsert getSimpleJdbcInsert() {
		return simpleJdbcInsertImpl;
	}

	@Override
	public Long totalRegister(String sql) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT COUNT(1) FROM ").append(sql);

		return jdbcTemplateImpl.queryForLong(sql.toString());
	}

	@Override
	public Query getQuery(String sql) throws Exception {
		validateSessionFactory();
		Query query = sessionFactory.getCurrentSession().createQuery(sql.toString());

		return query;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findListByQueryDinamic(String sql, int minRegister, int maxRegister) throws Exception {
		validateSessionFactory();

		List<T> ts = new ArrayList<T>();
		ts = sessionFactory.getCurrentSession().createQuery(sql).setFirstResult(minRegister).setMaxResults(maxRegister)
				.list();

		return ts;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getListSQLDinamicArray(String sql) throws Exception {
		validateSessionFactory();

		List<Object[]> objs = (List<Object[]>) sessionFactory.getCurrentSession().createSQLQuery(sql).list();

		return objs;
	}

	/**
	 * Roda instantaneamente o SQL no base de dados
	 */
	private void executeFlushSession() {
		sessionFactory.getCurrentSession().flush();
	}

	public SimpleJdbcCallImpl getSimpleJdbcCallImpl() {
		return simpleJdbcCallImpl;
	}

	private void validateTransaction() {
		if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
	}

	@SuppressWarnings("unused")
	private void commitProccessAjax() {
		sessionFactory.getCurrentSession().beginTransaction().commit();
	}

	@SuppressWarnings("unused")
	private void rollbackProccessAjax() {
		sessionFactory.getCurrentSession().beginTransaction().rollback();
	}

	private void validateSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}

		validateTransaction();
	}
}