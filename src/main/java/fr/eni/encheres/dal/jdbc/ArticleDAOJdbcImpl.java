package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.dal.ArticleDAO;
import fr.eni.encheres.dal.DALException;

public class ArticleDAOJdbcImpl implements ArticleDAO {
	private static final String SELECT_ARTICLES = "SELECT nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, ARTICLES_VENDUS.no_utilisateur, no_categorie, pseudo \r\n"
												+ "FROM ARTICLES_VENDUS \r\n"
												+ "INNER JOIN UTILISATEURS \r\n"
												+ "ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur \r\n"
												+ "WHERE date_debut_encheres <= GETDATE() AND date_fin_encheres > GETDATE();";
	
	private static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) VALUES (?, ?, ?, ?, ?, ?, ?);";
	private static final String SELECT_ARTICLE = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_categorie, no_utilisateur \r\n"
												+ "FROM ARTICLES_VENDUS \r\n"
												+ "WHERE nom_article = ?;";
	
	
	public List<Article> getArticles() throws DALException{
		
		
		List<Article> lesArticlesExtraits = new ArrayList<Article>();
		
		Connection cnx = null;
		
		try {
				
			cnx = ConnectionProvider.getConnection();		
				
			Statement rqt = cnx.createStatement();
			
			ResultSet rs = rqt.executeQuery(SELECT_ARTICLES);
			int idCurrentArticle = 0;
			Article articleCourant = null;
			
			while(rs.next())
			{
				if (idCurrentArticle != rs.getInt("no_utilisateur")) 
					
				{
					articleCourant = new Article();
					articleCourant.setNomArticle(rs.getString("nom_article"));
					articleCourant.setDescription(rs.getString("description"));
					articleCourant.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
					articleCourant.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
					articleCourant.setPrixInitial(rs.getInt("prix_initial"));
					articleCourant.setPrixVente(rs.getInt("prix_vente"));
					articleCourant.setPseudo(rs.getString("pseudo"));
					articleCourant.setNoCategorie(rs.getInt("no_categorie"));
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


	@Override
	public void insertArticle(Article article) throws DALException {
		
		//on prepare la requête pour ajouter l'article en BDD		
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement rqt = cnx.prepareStatement(INSERT_ARTICLE);
			
			//on valorise les paramètres de la requête 
			rqt.setString(1, article.getNomArticle());
			rqt.setString(2, article.getDescription());
			rqt.setDate(3, java.sql.Date.valueOf(article.getDateDebutEncheres()));
			rqt.setDate(4, java.sql.Date.valueOf(article.getDateFinEncheres()));
			rqt.setInt(5, article.getPrixInitial());
			rqt.setInt(6, article.getNoUtilisateur());
			rqt.setInt(7, article.getNoCategorie());
			
			//exécuter la requête vers la BDD
			rqt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Article getArticle( String nom) throws DALException {
		
Connection cnx = null;

Article articleBDD = null;
		
		try {
				
			cnx = ConnectionProvider.getConnection();		
				
			PreparedStatement rqt = cnx.prepareStatement(SELECT_ARTICLE);

			rqt.setString(1, nom);
			
			
			ResultSet rs = rqt.executeQuery();
			
			
			if(rs != null) {
				rs.next();
				
				int no_article = rs.getInt(1);
				String nom_article = rs.getString(2);
				String description = rs.getString(3);
				LocalDate debut_encheres = rs.getDate(4).toLocalDate();
				LocalDate fin_encheres = rs.getDate(5).toLocalDate();
				int prixIni = rs.getInt(6);
				int prixVente = rs.getInt(7);
				int no_categorie = rs.getInt(8);
				int no_utilisateur = rs.getInt(9);
				
				articleBDD = new Article(no_article, nom_article, description, debut_encheres, fin_encheres, prixIni, prixVente, no_utilisateur ,no_categorie);
				
				
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
		return articleBDD;
		
		
	}
	
}
