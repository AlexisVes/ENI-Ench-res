package fr.eni.encheres.bll;

import java.time.LocalDate;
import java.util.List;

import fr.eni.encheres.dal.ArticleDAO;
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
				System.out.println(articleDAO.getArticles() + "articlemanager");
				if( articleDAO.getArticles() != null )
				{
					articles = articleDAO.getArticles();
				}
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace( );
			}
			//On retire les articles pour lesquelles la date de fin d'enchère est terminée
			for( Article article : articles)
			{
				if( article.getDateFinEncheres().compareTo(LocalDate.now()) <= 0 )
				{
					articles.remove(article);
				}
			}
			
			return articles;
		}

}
