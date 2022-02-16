package fr.eni.encheres.bll;

import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UserDAO;

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
					userDAO = DAOFactory.createDAO("JDBC");
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
		
		private UserDAO userDAO;
		
		public List<Article> 

}
