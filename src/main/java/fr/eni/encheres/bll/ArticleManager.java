package fr.eni.encheres.bll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.dal.ArticleDAO;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;

public class ArticleManager 
{
	
	//volonté de n'avoir qu'une seule instance de cette classe en mémoire => SINGLETON
		private static ArticleManager instance=null;
		
		private ArticleManager() {
			/*
			 * Le constructeur permet d'initialiser la variable membre avisDAO pour 
			 * permettre une communication avec la base de données. 
			 */
			//avisDAO = new AvisDAOJdbcImpl();
				try 
				{
					articleDAO = DAOFactory.createArticleDAO("JDBC");
				} 
				catch (DALException e) 
				{
					e.printStackTrace();
				} 
				//passer par la Factory
		}
		
		
		public synchronized static ArticleManager getInstance()
		{
			if (instance == null) 
			{
				instance = new ArticleManager();
			}
			return instance;
		}
		
		private ArticleDAO articleDAO;
		
		
		/**
		 * Récupération grâce à la DAO de tous les articles
		 * @return on retourne dans une liste tout les articles encore en ventee
		 * @throws BLLException 
		 */
		public List<Article> getArticlesAvailable() throws BLLException
		{
			List<Article> articles = null;
			
			//Récupération de tout les articles dans la DAO
			try 
			{
				if( articleDAO.getArticles() != null )
				{
					articles = articleDAO.getArticles();
				}
			} 
			catch (DALException e) 
			{
				e.printStackTrace( );
				BLLException exception = new BLLException();
				exception.addMessage(e.getMessage());
				throw exception;	
			}		
			return articles;	
		}
		
		
		/**
		 * Insère dans la base de données l'article recu en paramètre
		 * @param article
		 * @throws BLLException
		 */
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
			} 
			catch (DALException e) 
			{
				e.printStackTrace();
				BLLException dalException = new BLLException();
				dalException.addMessage(e.getMessage());
				throw dalException;	
			}
		}
		
		
		/**
		 * On récupère un objet article dans la base de données avec son nom
		 * @param nom String qui correspond au nom d'un article en base de données
		 * @return l'article correspondant au nom rentré en paramètre
		 * @throws BLLException 
		 */
		public Article getArticle( String nom ) throws BLLException
		{
			Article article = null;
			
			try 
			{
				article =  articleDAO.getArticle(nom);
			} 
			catch (DALException e) 
			{
				e.printStackTrace();
				BLLException exception = new BLLException();
				exception.addMessage(e.getMessage());
				throw exception;	
			}
			
			return article;
		}
		
		
		/**
		 * 
		 * @param mot
		 * @return Une liste d'Article qui contiennent dans leurs noms notre paramètre
		 * @throws BLLException 
		 */
		public List<Article> getArticlesByName( String mot ) throws BLLException
		{
			try 
			{
				return articleDAO.getArticlesByName(mot);
			}
			catch (DALException e) 
			{
				e.printStackTrace();
				BLLException exception = new BLLException();
				exception.addMessage(e.getMessage());
				throw exception;	
			}
		}
		
		
		/**
		 * 
		 * @param mot
		 * @param categorie
		 * @return  Une liste d'Article qui ont pour catégorie le paramètre rentré
		 * et qui contiennent dans leurs noms notre paramètre
		 * @throws BLLException 
		 */
		public List<Article> getArticlesByCategorie( String mot, int categorie) throws BLLException
		{
			try 
			{
				return articleDAO.getArticlesByCat(mot, categorie);
			} 
			catch (DALException e) 
			{
				e.printStackTrace();
				BLLException exception = new BLLException();
				exception.addMessage(e.getMessage());
				throw exception;	
			}
		}
		
		
		
		/**
		 * 
		 * @param articles
		 * @param recherche
		 * @return Une liste d'Article qui contiennent dans leurs noms notre paramètre
		 */
		public List<Article> orderArticleByNames(List<Article> articles, String recherche)
		{
			List<Article> ordererArticles = new ArrayList<Article>(); 
			
			for( Article article : articles)
			{
				if( article.getNomArticle().contains(recherche))
				{
					
					ordererArticles.add(article);
				}
			}
			return ordererArticles;
		}
		
		
		
		/**
		 * 
		 * @param articles
		 * @param recherche
		 * @param categorie
		 * @return Une liste d'Article qui ont pour catégorie le paramètre rentré
		 * et qui contiennent dans leurs noms notre paramètre
		 */
		public List<Article> orderArticleByCatAndNames(List<Article> articles, String recherche, int categorie )
		{

			List<Article> ordererArticles = new ArrayList<Article>(); 
			
			for( Article article : articles)
			{
				if( article.getNomArticle().contains(recherche) && article.getNoCategorie() == categorie )
				{
					
					ordererArticles.add(article);
				}
			}
			return ordererArticles;
		}
		
		
		
		/**
		 * 
		 * @param pseudo LE nom du possesseur des articles
		 * @param mode renvoi les vendu, a vendre ou en vente selon le mode choisi, respectivement: sold, futur, sell
		 * @return
		 * @throws BLLException 
		 */
		public List<Article> getMyArticles( String pseudo, String mode) throws BLLException
		{
			try {
				
				switch(mode)
				{
					case "on sell" : return articleDAO.getMyArticles(pseudo);
					case "future" : return articleDAO.getMyFuturArticles(pseudo);
					case "sold" : return articleDAO.getMySoldArticles(pseudo);
					case "all" : return articleDAO.getAllMyArticles(pseudo);
				}
				
			}	
			catch(DALException e)
			{
				e.printStackTrace();
				BLLException exception = new BLLException();
				exception.addMessage(e.getMessage());
				throw exception;	
			}
			return null;
		}
		
		
		
		/**
		 * @param pseudo
		 *@param mode renvoi les vendu, a vendre ou en vente selon le mode choisi, respectivement: sold, futur, sell
		 * @return
		 * @throws BLLException 
		 */
		public List<Article> getArticles( String mode ) throws BLLException
		{
			try {
				
				switch(mode)
				{
					case "on sell" : return articleDAO.getArticles();
				}
				
			}	
			catch(DALException e)
			{
				e.printStackTrace();
				BLLException exception = new BLLException();
				exception.addMessage(e.getMessage());
				throw exception;	
			}
			return null;
		}

		
		public void deleteArticle( int no_article ) throws BLLException
		{
			try
			{
				articleDAO.deleteArticle(no_article);
			} 
			catch (DALException e) 
			{
				e.printStackTrace();
				BLLException exception = new BLLException();
				exception.addMessage(e.getMessage());
				throw exception;
			}
		}

		public void updateArticleById(Article article) throws BLLException 
		{
			try 
			{
				articleDAO.updateArticleById(article);
			} 
			catch 
			(DALException e) 
			{
				e.printStackTrace();
				BLLException exception = new BLLException();
				exception.addMessage(e.getMessage());
				throw exception;
			}
		}

}
