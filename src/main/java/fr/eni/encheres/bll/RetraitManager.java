package fr.eni.encheres.bll;

import fr.eni.encheres.dal.RetraitDAO;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;

public class RetraitManager 
{
	
	//volonté de n'avoir qu'une seule instance de cette classe en mémoire => SINGLETON
			private static RetraitManager instance=null;
			
			private RetraitManager() 
			{
				/*
				 * Le constructeur permet d'initialiser la variable membre avisDAO pour 
				 * permettre une communication avec la base de données. 
				 */
				//avisDAO = new AvisDAOJdbcImpl();
					try 
					{
						retraitDAO = DAOFactory.createRetraitDAO("JDBC");
					}
					catch (DALException e) 
					{
						e.printStackTrace();
					} 
			}
			
			public synchronized static RetraitManager getInstance() 
			{
				if (instance == null) {
					instance = new RetraitManager();
				}
				return instance;
			}
			
			private RetraitDAO retraitDAO;
			
			
			public void deleteRetrait ( int no_article ) throws BLLException
			{
				try 
				{
					retraitDAO.deleteRetrait(no_article);
				} 
				catch (DALException e)
				{
					e.printStackTrace( );
					BLLException exception = new BLLException();
					exception.addMessage(e.getMessage());
					throw exception;	
				}
			}
			
			
			/**
			 * Ajoute un retrait dans notre base de données
			 * @param retrait
			 * @throws BLLException
			 */
			public void insertRetrait( Retrait retrait ) throws BLLException
			{
				try 
				{
					retraitDAO.insertRetrait(retrait);
				} 
				catch (DALException e)
				{
					e.printStackTrace( );
					BLLException exception = new BLLException();
					exception.addMessage(e.getMessage());
					throw exception;	
				}
			}
			
			/**
			 * @param noArticle
			 * @return un objet Retrait correspont au retrait avec le noArticle
			 * rentré en paramètre
			 * @throws DALException
			 */
			public Retrait getRetrait(int noArticle) throws DALException 
			{
					return retraitDAO.getRetrait(noArticle);
			}
			
}
