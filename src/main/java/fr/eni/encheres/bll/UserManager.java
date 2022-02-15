package fr.eni.encheres.bll;

import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UserDAO;

public class UserManager {
	//volonté de n'avoir qu'une seule instance de cette classe en mémoire => SINGLETON
	private static UserManager instance=null;
	
	private UserManager() {
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
	
	public synchronized static UserManager getInstance() {
		if (instance == null) {
			instance = new UserManager();
		}
		return instance;
	}
	
	private UserDAO userDAO;
	
	public boolean searchUser(String pseudo, String password)
	{
		User user = null;
		try {

			if(userDAO.getUser(pseudo) != null) 
			{
				user = userDAO.getUser(pseudo);

				if(password.equals(user.getPassword()))
				{     
					return true;
				}
			}
		} 
		catch (DALException e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public void createUser(int noUtilisateur, String pseudo, String nom, String prenom, String email, String tel, String rue,
			String codePostal, String ville, String password) throws BLLException {
		User user = null;
		int credit=0; 
		byte admninistrateur=0;
		//creation de l'instance d'utilisateur à partir des informations saisies par l'utilisateur
		user = new User(noUtilisateur, pseudo, nom, prenom, email, tel, rue, codePostal, ville, password, credit, admninistrateur);
		userDAO.insertUser(user);
	}

	private Exception BLLException(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
