package fr.eni.encheres.dal;

import fr.eni.encheres.dal.jdbc.UserDAOJdbcImpl;
import fr.eni.encheres.dal.nosql.UserDAONoSQLImpl;

public class DAOFactory {
	public static UserDAO createDAO(String type) throws DALException {
		switch(type) {
			case "JDBC":
				return new UserDAOJdbcImpl();
			case "NOSQL" :
				return new UserDAONoSQLImpl();
			default : 
				throw new DALException("Source de données inconnue");
		}
	}

}
