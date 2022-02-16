package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.dal.ArticleDAO;
import fr.eni.encheres.dal.DALException;

public class ArticleDAOJdbcImpl implements ArticleDAO {
	private static final String SELECT_ARTICLE = "SELECT nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS;";
	
	
	public List<Article> getArticles() throws DALException{
		
		
		List<Article> lesArticlesExtraits = new ArrayList<Article>();
		
		Connection cnx = null;
		
		try {
				
			
			cnx = ConnectionProvider.getConnection();		
				
			Statement rqt = cnx.createStatement();
			
			ResultSet rs = rqt.executeQuery(SELECT_ARTICLE);
			int idCurrentArticle = 0;
			Article articleCourant = null;
			
			
			while(rs.next())
			{
				System.out.println("are");
				if (idCurrentArticle != rs.getInt("no_utilisateur")) 
					
				{
					articleCourant = new Article();
//					articleCourant.setNoArticle(rs.getInt("no_article"));
					articleCourant.setNomArticle(rs.getString("nom_article"));
					articleCourant.setDescription(rs.getString("description"));
					articleCourant.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
					articleCourant.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
					articleCourant.setPrixInitial(rs.getInt("prix_initial"));
					articleCourant.setPrixVente(rs.getInt("prix_vente"));
					articleCourant.setNoUtilisateur(rs.getInt("no_utilisateur"));
					articleCourant.setNoCategorie(rs.getInt("no_categorie"));
//					idCurrentArticle = rs.getInt("no_article");
					lesArticlesExtraits.add(articleCourant);
				}
			}
			
		} catch (SQLException e) {
			//propager une exception personnalisée
			throw new DALException("Problème d'extraction des articles de la base. Cause : " + e.getMessage());
		}
		
		try {
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lesArticlesExtraits;
	}
}
