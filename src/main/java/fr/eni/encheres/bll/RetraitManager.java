package fr.eni.encheres.bll;

import fr.eni.encheres.dal.RetraitDAO;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;

public class RetraitManager {
	
	//volonté de n'avoir qu'une seule instance de cette classe en mémoire => SINGLETON
			private static RetraitManager instance=null;
			
			private RetraitManager() {
				/*
				 * Le constructeur permet d'initialiser la variable membre avisDAO pour 
				 * permettre une communication avec la base de données. 
				 */
				//avisDAO = new AvisDAOJdbcImpl();
					try {
						retraitDAO = DAOFactory.createRetraitDAO("JDBC");
					} catch (DALException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} //passer par la Factory
			}
			
			public synchronized static RetraitManager getInstance() {
				if (instance == null) {
					instance = new RetraitManager();
				}
				return instance;
			}
			
			private RetraitDAO retraitDAO;

			public void insertRetrait( Retrait retrait )
			{
				try {
					retraitDAO.insertRetrait(retrait);
				} catch (DALException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
}
