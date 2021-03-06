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

public class ArticleDAOJdbcImpl implements ArticleDAO 
{
	
	private static final String SELECT_ARTICLES = "SELECT nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, ARTICLES_VENDUS.no_utilisateur, no_categorie, pseudo \r\n"
												+ "FROM ARTICLES_VENDUS \r\n"
												+ "INNER JOIN UTILISATEURS \r\n"
												+ "ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur \r\n"
												+ "WHERE date_debut_encheres <= GETDATE() AND date_fin_encheres > GETDATE();";
	
	private static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, lien_image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
	
	private static final String SELECT_ARTICLE = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_categorie, no_utilisateur, lien_image \r\n"
												+ "FROM ARTICLES_VENDUS \r\n"
												+ "WHERE nom_article = ?;";
	
	private static final String SEARCH_ARTICLE = "SELECT * FROM ARTICLES_VENDUS WHERE nom_article LIKE ? AND date_debut_encheres <= GETDATE() AND date_fin_encheres > GETDATE();";
;
	
	private static final String SEARCH_ARTICLE_NAME_CAT = "SELECT * FROM ARTICLES_VENDUS WHERE no_categorie = ? AND nom_article LIKE ? AND date_debut_encheres <= GETDATE() AND date_fin_encheres > GETDATE();";
	
	private static final String SELECT_ALL_MY_ARTICLES = " SELECT nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente,  ARTICLES_VENDUS.no_utilisateur, no_categorie, pseudo \r\n"
														+ "FROM ARTICLES_VENDUS \r\n" 
														+ "INNER JOIN UTILISATEURS \r\n"
														+ "ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur \r\n"
														+ "WHERE pseudo = ?;";
	
	private static final String	SELECT_MY_ARTICLES = " SELECT nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente,  ARTICLES_VENDUS.no_utilisateur, no_categorie, pseudo \r\n"
													+ "FROM ARTICLES_VENDUS \r\n" 
													+ "INNER JOIN UTILISATEURS \r\n"
													+ "ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur \r\n"
													+ "WHERE date_debut_encheres <= GETDATE() AND date_fin_encheres > GETDATE() AND pseudo = ?;";
	
	private static final String	SELECT_MY_SOLD_ARTICLES = " SELECT nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente,  ARTICLES_VENDUS.no_utilisateur, no_categorie, pseudo \r\n"
															+ "FROM ARTICLES_VENDUS \r\n"
															+ "INNER JOIN UTILISATEURS \r\n"
															+ "ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur \r\n"
															+ "WHERE date_fin_encheres < GETDATE() AND pseudo = ?;";
	
	private static final String	SELECT_MY_FUTURE_ARTICLES = " SELECT nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, ARTICLES_VENDUS.no_utilisateur, no_categorie, pseudo \r\n"
														+ "FROM ARTICLES_VENDUS \r\n"
														+ "INNER JOIN UTILISATEURS \r\n"
														+ "ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur \r\n"
														+ "WHERE date_debut_encheres > GETDATE() AND pseudo = ?;";
	

	private static final String	DELETE_ARTICLE = " DELETE ARTICLES_VENDUS WHERE no_article=?;";

	private static final String UPDATE_ARTICLE_BY_ID = 	"UPDATE ARTICLES_VENDUS\r\n"
														+ "SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, prix_vente = ? \r\n"
														+ "WHERE no_article =?;";
	private static String END_SELL_ARTICLE = "SELECT no_article WHERE date_fin_encheres = GETDATE()";

	private static String SELECT_BOUGHT_ARTICLES = "SELECT nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, ARTICLES_VENDUS.no_utilisateur, no_categorie, pseudo \r\n"
													+ "FROM ENCHERES \r\n"
													+ "INNER JOIN ARTICLES_VENDUS \r\n"
													+ "ON ENCHERES.no_article = ARTICLES_VENDUS.no_article \r\n"
													+ "INNER JOIN UTILISATEURS\r\n"
													+ "ON ENCHERES.no_utilisateur = UTILISATEURS.no_utilisateur\r\n"
													+ "WHERE ENCHERES.no_utilisateur = ? AND date_fin_encheres < GETDATE();";
	
	private static String SELECT_ON_BUY_ARTICLES = "SELECT nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, ARTICLES_VENDUS.no_utilisateur, no_categorie, pseudo \r\n"
													+ "FROM ENCHERES \r\n"
													+ "INNER JOIN ARTICLES_VENDUS \r\n"
													+ "ON ENCHERES.no_article = ARTICLES_VENDUS.no_article \r\n"
													+ "INNER JOIN UTILISATEURS\r\n"
													+ "ON ENCHERES.no_utilisateur = UTILISATEURS.no_utilisateur\r\n"
													+ "WHERE ENCHERES.no_utilisateur = ? AND date_fin_encheres > GETDATE();";
																						
	private static String UPDATE_SELL_PRICE = "UPDATE ARTICLES_VENDUS SET prix_vente = ? WHERE no_article = ?;";
	
	/**
	 * M??thodes de cr??ation d'une liste d'article ?? partir d'une requ??te
	 * @param rs
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	private List<Article> listerArticles( ResultSet rs, boolean user) throws SQLException 
	{
		Article articleCourant;
		List<Article> searchList= new ArrayList<Article>();
		
		//Pour chaque ligne on cr??er un objet article correspondant, que l'on rajoute ?? la liste
		while(rs.next())
		{
		
			{
				//On cr??er un article avec les valeurs trouv??es en base
				articleCourant = new Article();
				articleCourant.setNomArticle(rs.getString("nom_article"));
				articleCourant.setDescription(rs.getString("description"));
				articleCourant.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
				articleCourant.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				articleCourant.setPrixInitial(rs.getInt("prix_initial"));
				articleCourant.setPrixVente(rs.getInt("prix_vente"));
				articleCourant.setNoCategorie(rs.getInt("no_categorie"));
				
				if( user )
				{
					articleCourant.setPseudo(rs.getString("pseudo"));		
				}
				
				searchList.add(articleCourant);
			}
		}
		
		return searchList;
	}
	
	private Article map( ResultSet rs, boolean user) throws SQLException 
	{
		Article articleCourant = new Article();
				articleCourant.setNomArticle(rs.getString("nom_article"));
				articleCourant.setDescription(rs.getString("description"));
				articleCourant.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
				articleCourant.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				articleCourant.setPrixInitial(rs.getInt("prix_initial"));
				articleCourant.setPrixVente(rs.getInt("prix_vente"));
				articleCourant.setNoCategorie(rs.getInt("no_categorie"));
				
				if( user )
				{
					articleCourant.setPseudo(rs.getString("pseudo"));		
				}

		
		return articleCourant;
	}
	
	
	/**
	 * M??thodes qui r??cup??re tout les articles de la base de donn??es
	 * @param sql_rqt
	 * @return
	 * @throws DALException
	 */
	private List<Article> getAllArticles(String sql_rqt) throws DALException 
	{
		List<Article> lesArticlesExtraits = new ArrayList<Article>() ;
		
		try (Connection cnx = ConnectionProvider.getConnection())
		{
		
			Statement rqt = cnx.createStatement();
			
			ResultSet rs = rqt.executeQuery(sql_rqt);
			Article articleCourant = null;
			
			lesArticlesExtraits  = listerArticles(rs, true);
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new DALException("Probl??me d'extraction des articles de la base. Cause : " + e.getMessage());
		}
		
		return lesArticlesExtraits ;
	}
	
	
	/**
	 * R??cup??re tout le articles qui ont pour cr??ateur : le pseudo rentrer en param??tre
	 * @param sql_rqt
	 * @param pseudo
	 * @return
	 * @throws DALException
	 */
	private List<Article> getAllMyArticles( String sql_rqt, String pseudo) throws DALException
	{
		
		List<Article> lesArticlesExtraits = new ArrayList<Article>() ;
		
		try(Connection cnx = ConnectionProvider.getConnection()) 
		{
			
			PreparedStatement rqt = cnx.prepareStatement(sql_rqt);
			
			//WHERE pseudo = this.pseudo
			rqt.setString(1, pseudo);
			
			ResultSet rs = rqt.executeQuery();
			
			lesArticlesExtraits  = listerArticles(rs, true);
							
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new DALException("Probl??me d'extraction des articles de la base. Cause : " + e.getMessage());

		} 
		catch (DALException e) 
		{
			e.printStackTrace();
			throw new DALException("Probl??me d'extraction des articles de la base. Cause : " + e.getMessage());
		}
		
		return lesArticlesExtraits;
		
	}
	
	//retourne les articles qui arriveront, sont, ou qui ??taient en ench??res
	public List<Article> getOnDateArticles() throws DALException
	{			
		return getAllArticles(END_SELL_ARTICLE);		
	}
	
	//retourne les articles qui arriveront, sont, ou qui ??taient en ench??res
	public List<Article> getArticles() throws DALException
	{			
		return getAllArticles(SELECT_ARTICLES);		
	}
	
	
	//retourne mes articles qui arriveront, sont, ou qui ??taient en ench??res
	public List<Article> getMyArticles(String pseudo) throws DALException
	{		
		return getAllMyArticles(SELECT_MY_ARTICLES, pseudo);		
	}
	
	
	//retourne tout mes articles
	public List<Article> getAllMyArticles(String pseudo) throws DALException
	{			
		return getAllMyArticles(SELECT_ALL_MY_ARTICLES, pseudo);		
	}
	
	
	//Retourne mes articles qui seront en ventes
	public List<Article> getMyFuturArticles(String pseudo) throws DALException
	{		
		return getAllMyArticles(SELECT_MY_FUTURE_ARTICLES, pseudo );	
	}
	
	
	//Retourne mes articles d??j?? vendus
	public List<Article> getMySoldArticles(String pseudo) throws DALException
	{	
		return getAllMyArticles(SELECT_MY_SOLD_ARTICLES, pseudo);		
	}


	/**
	 * Ajoute en base un nouvel article
	 */
	public void insertArticle(Article article) throws DALException {
		//on prepare la requ??te pour ajouter l'article en BDD		
		try (Connection cnx = ConnectionProvider.getConnection();)
		{
			PreparedStatement rqt = cnx.prepareStatement(INSERT_ARTICLE);
			
			//on valorise les param??tres de la requ??te 
			rqt.setString(1, article.getNomArticle());
			rqt.setString(2, article.getDescription());
			rqt.setDate(3, java.sql.Date.valueOf(article.getDateDebutEncheres()));
			rqt.setDate(4, java.sql.Date.valueOf(article.getDateFinEncheres()));
			rqt.setInt(5, article.getPrixInitial());
			rqt.setInt(6, article.getPrixInitial());
			rqt.setInt(7, article.getNoUtilisateur());
			rqt.setInt(8, article.getNoCategorie());
			rqt.setString(9, article.getPhotoArticle());
			
			//ex??cuter la requ??te vers la BDD
			rqt.executeUpdate();
			
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new DALException("Probl??me d'extraction des articles de la base. Cause : " + e.getMessage());
		}
	}
	
	/**
	 * On r??cup??re un article trouv?? en base qui a pour nom,
	 * le nom rentr?? en param??tre
	 */
	public Article getArticle( String nom) throws DALException 
	{
	
		Article articleBDD = null;
		
		try (Connection cnx = ConnectionProvider.getConnection())
		{	
				
			PreparedStatement rqt = cnx.prepareStatement(SELECT_ARTICLE);

			//WHERE nom = this.nom
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
				String photoArticle = rs.getString(10);
				
				//On cr??er un article avec les valeurs trouv??es en base
				articleBDD = new Article(no_article, nom_article, description, debut_encheres, fin_encheres, prixIni, prixVente, no_utilisateur ,no_categorie, photoArticle);
				
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new DALException("Probl??me d'extraction des articles de la base. Cause : " + e.getMessage());
		}
		
		return articleBDD;
	
	}


	/**
	 * On retourne une liste d'article qui contient dans leurs noms
	 * la chaine de caract??res entr??e en param??tre
	 */
	@Override
	public List<Article> getArticlesByName(String mot) throws DALException 
	{
		
		Article articleBDD = null;
		List<Article> searchList=null;
						
		try (Connection cnx = ConnectionProvider.getConnection())
		{
			
			PreparedStatement rqt = cnx.prepareStatement(SEARCH_ARTICLE);
			rqt.setString(1, "%" + mot + "%");
			ResultSet rs = rqt.executeQuery();
			boolean user = false;
			
			//on v??rifie le nombre de lignes r??cup??r??es dans le result set pour alimenter la liste
			searchList = listerArticles( rs, false );
			
		} 
		catch (DALException | SQLException e) 
		{
			e.printStackTrace();
			throw new DALException("Probl??me d'extraction des articles de la base. Cause : " + e.getMessage());
		}				

		return searchList;
	}

	
	
	@Override
	public List<Article> getArticlesByCat(String mot, int categorie) throws DALException 
	{
		Article articleBDD = null;
		List<Article> searchList=null;
						
		try (Connection cnx = ConnectionProvider.getConnection())
		{
			
			PreparedStatement rqt = cnx.prepareStatement(SEARCH_ARTICLE_NAME_CAT);
			rqt.setInt(1, categorie);
			rqt.setString(2, "%" + mot + "%");
			ResultSet rs = rqt.executeQuery();
			
			//on v??rifie le nombre de lignes r??cup??r??es dans le result set pour alimenter la liste
			searchList = listerArticles( rs, false);
			
		} 
		catch (DALException | SQLException e) 
		{
			e.printStackTrace();
			throw new DALException("Probl??me d'extraction des articles de la base. Cause : " + e.getMessage());
		} 	
	
		return searchList;
	}


	@Override
	public void deleteArticle(int noArticle) throws DALException 
	{
		try (Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement rqt = cnx.prepareStatement(DELETE_ARTICLE);
			
			rqt.setInt(1, noArticle);
			
			int rs = rqt.executeUpdate();
					
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new DALException("Probl??me d'extraction des articles de la base. Cause : " + e.getMessage());
		} 	

	}
	
	
	public void updateArticleById(String nomArticle, String description, LocalDate dateDebutEnchere, LocalDate dateFinEnchere, int prixInitial, int noArticle) throws DALException {
		
		try (Connection cnx = ConnectionProvider.getConnection();)
		{
			
			PreparedStatement rqt = cnx.prepareStatement(UPDATE_ARTICLE_BY_ID);
			
			//on valorise les param??tres de la requ??te 
			rqt.setString(1, nomArticle);
			rqt.setString(2, description);
			rqt.setDate(3, java.sql.Date.valueOf(dateDebutEnchere));
			rqt.setDate(4, java.sql.Date.valueOf(dateFinEnchere));
			rqt.setInt(5, prixInitial);
			rqt.setInt(6, prixInitial);
			rqt.setInt(7, noArticle);
			
			//ex??cuter la requ??te vers la BDD
			rqt.executeUpdate();
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new DALException("Probl??me d'extraction des articles de la base. Cause : " + e.getMessage());
		}
		
	}


	public List<Article> getMyBoughtArticles(int no_utilisateur) throws DALException
	{
		return myArticleOnView(no_utilisateur, SELECT_BOUGHT_ARTICLES);
	}
	
	public List<Article> getMyOnBuyArticles(int no_utilisateur) throws DALException
	{
		return myArticleOnView(no_utilisateur,  SELECT_ON_BUY_ARTICLES );
	}


	private List<Article> myArticleOnView(int no_utilisateur, String rqtSql) throws DALException {
		Article articleBDD = null;
		List<Article> searchList=null;
						
		try (Connection cnx = ConnectionProvider.getConnection())
		{
			
			PreparedStatement rqt = cnx.prepareStatement(rqtSql);
			
			rqt.setInt(1, no_utilisateur);
			
			ResultSet rs = rqt.executeQuery();
			
			//on v??rifie le nombre de lignes r??cup??r??es dans le result set pour alimenter la liste
			searchList = listerArticles( rs, true);
			
		} 
		catch (DALException | SQLException e) 
		{
			e.printStackTrace();
			throw new DALException("Probl??me d'extraction des articles de la base. Cause : " + e.getMessage());
		} 	
	
		return searchList;
	}

	@Override
	public void updateSellPrice(int prixVente, int noArticle) throws DALException {
		
		try(Connection cnx = ConnectionProvider.getConnection()){

			PreparedStatement rqt = cnx.prepareStatement(UPDATE_SELL_PRICE);
			
			//on valorise le param??tre avec le num??ro de l'article
			rqt.setInt(1, prixVente);
			rqt.setInt(2, noArticle);
			
			//on ex??cute l'update du prix de vente 
			rqt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}

