package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.User;

public class ArticleDAOJdbcImpl {
	private static final String SELECT_ARTICLE = "SELECT nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS;";
	
	public List<Article> selectAll() throws Exception{
		List<Article> lesArticlesExtraits = new ArrayList<Article>();
		try (Connection cnx = ConnectionProvider.getConnection(); Statement rqt = cnx.createStatement();){
			ResultSet rs = rqt.executeQuery(SELECT_ARTICLE);
			int idCurrentArticle = 0;
			Article articleCourant = null;
			while(rs.next())
			{
				if (idCurrentArticle != rs.getInt("no_article")) 
				{
					articleCourant = new Article();
					articleCourant.setNo_article(rs.getInt("no_article"));
					articleCourant.setNom ;
					articleCourant. ;
					idCurrentArticle = rs.getInt("no_article");
				}
			}
		} catch (SQLException e) {
			//propager une exception personnalisée
			throw new Exception("Problème d'extraction des articles de la base. Cause : " + e.getMessage());
		}
		return lesArticlesExtraits;
	}
}
