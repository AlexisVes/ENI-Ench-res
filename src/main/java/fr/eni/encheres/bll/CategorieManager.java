package fr.eni.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UserDAO;

public class CategorieManager 
{
	
	//volonté de n'avoir qu'une seule instance de cette classe en mémoire => SINGLETON
	private static CategorieManager instance=null;

	private CategorieManager() {
		/*
		 * Le constructeur permet d'initialiser la variable membre avisDAO pour 
		 * permettre une communication avec la base de données. 
		 */
		//avisDAO = new AvisDAOJdbcImpl();
			try 
			{
				categorieDAO = DAOFactory.createCategorieDAO("JDBC");
			}
			catch (DALException e)
			{
				e.printStackTrace();
			} 
	}
	
	public synchronized static CategorieManager getInstance()
	{
		if (instance == null) 
		{
			instance = new CategorieManager();
		}
		return instance;
	}
	
	private CategorieDAO categorieDAO;
	
	
	
	/**
	 * @return La liste de toutes les catégories de la base de données
	 * @throws BLLException
	 */
	public List<Categorie> getCategories() throws BLLException
	{	
		List<Categorie> categories = new ArrayList<Categorie>();
		
		try 
		{	
			categories = categorieDAO.getCategories();	
		} 
		catch (DALException e) 
		{
			e.printStackTrace( );
			BLLException exception = new BLLException();
			exception.addMessage(e.getMessage());
			throw exception;	
		}
		
		return categories;
	}
	
	
	/**
	 * @param noCategorie
	 * @return Le libellé de la catégorie
	 * @throws BLLException
	 */
	public String getLibelleCategorie(int noCategorie) throws BLLException 
	{
		String libelleCategorie = null;
		try 
		{
			libelleCategorie = categorieDAO.getCategorie(noCategorie);
		} 
		catch (DALException e) 
		{
			e.printStackTrace( );
			BLLException exception = new BLLException();
			exception.addMessage(e.getMessage());
			throw exception;	
		}
		
		return libelleCategorie;
	}
	
	
}
