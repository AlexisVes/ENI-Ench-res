package fr.eni.encheres.bll;

import java.time.LocalDate;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.EnchereDAO;

public class EnchereManager {
	
	//volonté de n'avoir qu'une seule instance de cette classe en mémoire => SINGLETON
	private static EnchereManager instance=null;
	
	
	public EnchereManager() throws DALException 
	{
		super();
		enchereDAO = DAOFactory.createEnchereDAO("JDBC");
	}
	
	private EnchereDAO enchereDAO;
	
	
	public Enchere getEnchere(int no_article)
	{
		return enchereDAO.getEnchere(no_article);
	}
	
	

	public void controlerEnchere (int prixSaisi, Article article, int no_utilisateur) 
	{
		
		LocalDate now = LocalDate.now();
		
		Enchere enchere = enchereDAO.getEnchere(article.getNoArticle());
		
		
		if( enchere != null) 
		{
			
			if( prixSaisi > article.getPrixVente() ) 
			{
				enchereDAO.updateEnchere( now, prixSaisi, no_utilisateur, article.getNoArticle());
			}
		}
		else
		{
			enchere = new Enchere( now, prixSaisi, article.getNoArticle(),  no_utilisateur );
			enchereDAO.insertEnchere(enchere);
		}
		
	}
	
	public void insertEnchere( Enchere enchere )
	{
		enchereDAO.insertEnchere(enchere);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instance;
	}

}
