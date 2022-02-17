package fr.eni.encheres.dal;


import fr.eni.encheres.dal.jdbc.ArticleDAOJdbcImpl;
import fr.eni.encheres.dal.jdbc.CategorieDAOJdbcImpl;
import fr.eni.encheres.dal.jdbc.UserDAOJdbcImpl;
import fr.eni.encheres.dal.nosql.UserDAONoSQLImpl;

public class DAOFactory {
	public static UserDAO createUserDAO(String type) throws DALException {
		switch(type) {
			case "JDBC":
				return new UserDAOJdbcImpl();
			case "NOSQL" :
				return new UserDAONoSQLImpl();
			default : 
				throw new DALException("Source de données inconnue");
		}
	}
	
	public static ArticleDAO createArticleDAO(String type) throws DALException {
		switch(type) {
			case "JDBC":
				return new ArticleDAOJdbcImpl();
			case "NOSQL" :
				return new ArticleDAOJdbcImpl();
			default : 
				throw new DALException("Source de données inconnue");
		}
	}
	
	public static CategorieDAO createCategorieDAO(String type) throws DALException {
		switch(type) {
			case "JDBC":
				return new CategorieDAOJdbcImpl();
			case "NOSQL" :
				return new CategorieDAOJdbcImpl();
			default : 
				throw new DALException("Source de données inconnue");
		}
	}

}
