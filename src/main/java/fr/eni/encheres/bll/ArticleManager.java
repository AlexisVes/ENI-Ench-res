package fr.eni.encheres.bll;

import java.time.LocalDate;
import java.util.List;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.dal.ArticleDAO;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;

public class ArticleManager {
	
	//volonté de n'avoir qu'une seule instance de cette classe en mémoire => SINGLETON
		private static ArticleManager instance=null;
		
		private ArticleManager() {
			/*
			 * Le constructeur permet d'initialiser la variable membre avisDAO pour 
			 * permettre une communication avec la base de données. 
			 */
			//avisDAO = new AvisDAOJdbcImpl();
				try {
					articleDAO = DAOFactory.createArticleDAO("JDBC");
				} catch (DALException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //passer par la Factory
		}
		
		public synchronized static ArticleManager getInstance() {
			if (instance == null) {
				instance = new ArticleManager();
			}
			return instance;
		}
		
		private ArticleDAO articleDAO;
		
		/**
		 * Récupération grâce à la DAO de tous les articles
		 * @return on retourne dans une liste tout les articles encore en ventee
		 */
		public List<Article> getArticlesAvailable()
		{
			List<Article> articles = null;
			
			//Récupération de tout les articles dans la DAO
			try {
				if( articleDAO.getArticles() != null )
				{
					articles = articleDAO.getArticles();
				}
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace( );
			}
			
			
			return articles;
			
		}
		
		public void vendreArticle( Article article ) throws BLLException
		{
			
			//nom < 30, description < 300, dateDébut > dateJour, 
			BLLException exception = new BLLException();
			
			//Vérification du nombre de caractères dans le nom d'articcle (ne doit pas dépasser 30 caractères)
			if(article.getNomArticle().length() > 30) {
				exception.addMessage("Le nom de l'article ne doit pas dépasser 30 caractères");
			}
			
			if(article.getDescription().length() > 300) {
				exception.addMessage("La description ne doit pas dépasser 300 caractères");
			}
			
			if(article.getDateDebutEncheres().compareTo(LocalDate.now()) < 0) {
				exception.addMessage("La date de début d'enchère ne peut commencer dans le passé");
			}
			
			if(exception.hasErrors()) {
				throw exception;
			}
			
			try {
				articleDAO.insertArticle(article);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public Article getArticle( String nom )
		{
			
			Article article = null;
			
			try {
				article =  articleDAO.getArticle(nom);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return article;
		}

}
