package fr.eni.encheres.dal;

import java.time.LocalDate;
import java.util.List;

import fr.eni.encheres.bo.Article;

public interface ArticleDAO {
	
	/**
	 * @return la liste des articles en cours d'enchère
	 * @throws DALException
	 */
	List<Article> getArticles() throws DALException;
	
	/**
	 * Permet ajouter un article en base
	 * @param article
	 * @throws DALException
	 */
	void insertArticle(Article article) throws DALException;
	
	/**
	 * @param nom
	 * @return un article de la BDD qui a pour nom, la chaine entrée en paramètre
	 * @throws DALException
	 */
	Article getArticle( String nom ) throws DALException;
	
	/**
	 * 
	 * @param mot
	 * @return une liste d'articles qui contiennent dans leurs nom
	 * la chaine de caractère en paramètre
	 * @throws DALException
	 */
	List<Article> getArticlesByName( String mot ) throws DALException;
	
	/**
	 * @param mot
	 * @param categorie
	 * @return une liste d'article de la BDD ayant pour catégorie celle rentrée en paramètre,
	 * et leurs noms contiennent la chaine en de caracètere en paramètre
	 * @throws DALException
	 */
	List<Article> getArticlesByCat(String mot, int categorie) throws DALException ;

	/**
	 * @param pseudo
	 * @return Une liste d'article en ventes dont le pseudo du vendeur est en paramètre
	 * @throws DALException
	 */
	public List<Article> getMyArticles(String pseudo) throws DALException;
	
	/**
	 * @param pseudo
	 * @return ne liste d'article qui seront à vendre dont le pseudo du vendeur est en paramètre
	 * @throws DALException
	 */
	public List<Article> getMyFuturArticles(String pseudo) throws DALException;	

	/**
	 * @param pseudo
	 * @return ne liste d'article vendu dont le pseudo du vendeur est en paramètre
	 * @throws DALException
	 */
	public List<Article> getMySoldArticles(String pseudo) throws DALException;
	
	
	/**
	 * @param pseudo
	 * @returnne liste d'article dont le pseudo du vendeur est en paramètre
	 * @throws DALException
	 */
	public List<Article> getAllMyArticles(String pseudo) throws DALException;
	
	/**
	 * Methode permettant de supprimer un article en BDD en fonction du numéro de cet article
	 * @param noArticle
	 * @throws DALException
	 */
	public void deleteArticle( int noArticle) throws DALException;

	/***
	 * Une methode permettant de modifier certains paramètres d'un article (nom, description, date de début et fin d'enchères, prix intitial)
	 * @param nomArticle
	 * @param Description
	 * @param dateDebutEnchere
	 * @param dateFinEnchere
	 * @param prixInitial
	 * @throws DALException
	 */
	public void updateArticleById(String nomArticle, String description, LocalDate dateDebutEnchere, LocalDate dateFinEnchere, int prixInitial, int noArticle ) throws DALException;

	List<Article> getMyOnBuyArticles(int no_utilisateur) throws DALException;
	
	List<Article> getMyBoughtArticles(int no_utilisateur) throws DALException;
	
	void updateSellPrice(int prixVente, int noArticle) throws DALException;
	
}
