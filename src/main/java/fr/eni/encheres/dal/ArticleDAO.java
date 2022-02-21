package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Article;

public interface ArticleDAO {
	List<Article> getArticles() throws DALException;
	
	void insertArticle(Article article) throws DALException;
	
	Article getArticle( String nom ) throws DALException;
	
	List<Article> getArticlesByName( String mot );
	
	List<Article> getArticlesByCat(String mot, int categorie);

	public List<Article> getMyArticles(String pseudo) throws DALException;
	
	public List<Article> getMyFuturArticles(String pseudo) throws DALException;	

	public List<Article> getMySoldArticles(String pseudo) throws DALException;
	
	public List<Article> getAllMyArticles(String pseudo) throws DALException;
	
}
