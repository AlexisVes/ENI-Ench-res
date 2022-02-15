package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import fr.eni.encheres.dal.DALException;

public class ConnectionProvider {

	/**
	 * Obtenir une connexion ouverte vers la base de données
	 * @return
	 * @throws DALException
	 */
	public static Connection getConnection() throws DALException {
		Connection cnx = null;
		try {
			Context context = new InitialContext();
			DataSource datasource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx_eniencheres");
			cnx = datasource.getConnection();
		} catch (NamingException e) {
			DALException exception = new DALException("Le pool de connexions est introuvable !");
			throw exception;
		} catch (SQLException e) {
			DALException exception = new DALException("Problème de connexion à la base de données ! " + e.getMessage());
			throw exception;
		}
		return cnx;
	}

}
