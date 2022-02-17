package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UserDAO;

public class CategorieManager {
	
	//volonté de n'avoir qu'une seule instance de cette classe en mémoire => SINGLETON
		private static CategorieManager instance=null;

	private CategorieManager() {
		/*
		 * Le constructeur permet d'initialiser la variable membre avisDAO pour 
		 * permettre une communication avec la base de données. 
		 */
		//avisDAO = new AvisDAOJdbcImpl();
			try {
				userDAO = DAOFactory.createUserDAO("JDBC");
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //passer par la Factory
	}
	
	public synchronized static CategorieManager getInstance() {
		if (instance == null) {
			instance = new CategorieManager();
		}
		return instance;
	}
	
	private UserDAO userDAO;
	
	public List<Categorie> getCategories(){
		
		//userDao.getC
		
		return null;
	}
	
}
