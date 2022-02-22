package fr.eni.encheres.bll;

import java.time.LocalDate;

import fr.eni.encheres.bo.User;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.EnchereDAO;
import fr.eni.encheres.dal.UserDAO;

public class EnchereManager 
{
	
	//volonté de n'avoir qu'une seule instance de cette classe en mémoire => SINGLETON
	private static EnchereManager instance=null;
	
	
	public EnchereManager() throws DALException 
	{
		super();
		enchereDAO = DAOFactory.createEnchereDAO("JDBC");
		userDAO = DAOFactory.createUserDAO("JDBC");
	}
	
	private EnchereDAO enchereDAO;
	private UserDAO userDAO;
	
	
	/**
	 * @param no_article
	 * @return un Objet Enchere qui correspond à l'enchère qui a le no_article dans
	 * la base de données
	 * @throws BLLException 
	 */
	public Enchere getEnchere(int no_article) throws BLLException
	{
		try 
		{
			return enchereDAO.getEnchere(no_article);
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
	 * Une methode qui a la responsabilité de contrôler que le prix saisi répond aux règles métier, elle fait également la maj du solde de crédits
	 * 
	 * @param prixSaisi
	 * @param article
	 * @param no_utilisateur
	 * @throws BLLException
	 */
	public void gererEnchere (int prixSaisi, Article article, int no_utilisateur) throws BLLException 
	{
		
		LocalDate now = LocalDate.now();
		
		try {
		
			Enchere enchere = enchereDAO.getEnchere(article.getNoArticle());
		
			if( enchere != null) 
			{
				
				if( prixSaisi > article.getPrixVente() && userDAO.getUserById(no_utilisateur).getCredit() >= prixSaisi) 
				{
					User user = userDAO.getUserById(no_utilisateur);
					Enchere previousEnchere = enchereDAO.getEnchere(article.getNoArticle());
					int previousIdUser = previousEnchere.getNo_utilisateur();
					User previousUser = userDAO.getUserById(previousIdUser);
					
					
					enchereDAO.updateEnchere( now, prixSaisi, no_utilisateur, article.getNoArticle());
				
					
					user.setCredit(user.getCredit() - prixSaisi);
					userDAO.updateUser(user);
					
					
					previousUser.setCredit(previousUser.getCredit() + previousEnchere.getMontant_enchere());
					userDAO.updateUser(previousUser);
					
				}
			}

			else
			{
	
				if (prixSaisi > article.getPrixVente() && userDAO.getUserById(no_utilisateur).getCredit() >= prixSaisi) 
				{
					enchere = new Enchere( now, prixSaisi, article.getNoArticle(),  no_utilisateur );
					enchereDAO.insertEnchere(enchere);	
				}
			} 
		}
		catch (DALException e) 
		{
			e.printStackTrace( );
			BLLException exception = new BLLException();
			exception.addMessage(e.getMessage());
			throw exception;	
		}
	}
	
	public void insertEnchere( Enchere enchere ) throws BLLException
	{
		try 
		{
			enchereDAO.insertEnchere(enchere);
		} 
		catch (DALException e) 
		{
			e.printStackTrace();
			BLLException exception = new BLLException();
			exception.addMessage(e.getMessage());
			throw exception;	
		}
	}

	public synchronized static EnchereManager getInstance()
	{
		if (instance == null) 
		{
			try 
			{
				instance = new EnchereManager();
			} 
			catch (DALException e) 
			{
				e.printStackTrace();
			}
		}
		return instance;
	}

}
