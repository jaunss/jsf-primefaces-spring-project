package com.joaogcm.jsf.primefaces.spring.project.session;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.SessionFactoryImplementor;

import com.joaogcm.jsf.primefaces.spring.project.util.ConnectionUtil;

/**
 * Responsável por estabelecer a conexão com o hibernate
 * 
 * @author joaog
 */
public class HibernateUtil implements Serializable {

	private static final long serialVersionUID = -4908382223682649553L;

	public static String JAVA_COMP_ENV_JDBC_DATASOURCE = "java:comp/env/jdbc/datasource";
	private static SessionFactory sessionFactory = buildSessionFactory();

	/**
	 * Responsável por ler o arquivo de configuração hibernate.cfg.xml
	 * 
	 * @return SessionFactory
	 * @author joaog
	 */
	@SuppressWarnings("deprecation")
	private static SessionFactory buildSessionFactory() {
		try {
			if (sessionFactory == null) {
				sessionFactory = new Configuration().configure().buildSessionFactory();
			}

			return sessionFactory;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("Erro ao criar conexão SessionFactory.");
		}
	}

	/**
	 * Retorna o SessionFactory corrente
	 * 
	 * @return SessionFactory
	 * @author joaog
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Retorna a sessão do SessionFactory
	 * 
	 * @return Session
	 * @author joaog
	 */
	public static Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}

	/**
	 * Abre uma nova sessão no SessionFactory
	 * 
	 * @return Session
	 * @author joaog
	 */
	public static Session openSession() {
		if (sessionFactory == null) {
			buildSessionFactory();
		}

		return sessionFactory.openSession();
	}

	/**
	 * Obtém a conexão do provedor de conexões configurado
	 * 
	 * @return Connection SQL
	 * @author joaog
	 * @throws SQLException
	 */
	public static Connection getConnectionProvider() throws SQLException {

		return ((SessionFactoryImplementor) sessionFactory).getConnectionProvider().getConnection();
	}

	/**
	 * 
	 * @return Connection no InitialContext java:comp/env/jdbc/datasource
	 * @author joaog
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		InitialContext context = new InitialContext();
		DataSource ds = (DataSource) context.lookup(JAVA_COMP_ENV_JDBC_DATASOURCE);

		return ds.getConnection();
	}

	/**
	 * @return DataSource JNDI Tomcat 9
	 * @author joaog
	 */
	public DataSource getDataSourceJndi() throws NamingException {
		InitialContext context = new InitialContext();

		return (DataSource) context.lookup(ConnectionUtil.JAVA_COMP_ENV_JDBC_DATASOURCE);
	}
}