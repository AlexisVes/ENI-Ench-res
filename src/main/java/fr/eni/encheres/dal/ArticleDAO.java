package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Article;

public interface ArticleDAO {
	List<Article> getArticles() throws DALException;
	
	void insertArticle(Article article) throws DALException;
	
	Article getArticle( String nom ) throws DALException;
}
