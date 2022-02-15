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

	public void createUser(	String pseudo, String nom, String prenom, String email, String tel, String rue,
							String codePostal, String ville, String password) throws BLLException, DALException{

		User user = null;
		int credit=0; 
		byte admninistrateur=0;
		
		//Vérification du nombre de caractères dans le pseudo (ne doit pas dépasser 30 caractères)
		if(pseudo.length() > 30) {
			throw new BLLException("Le pseudo ne doit pas dépasser 30 caractères");
		}
		//Vérification du nombre de caractères dans le nom (ne doit pas dépasser 30 caractères)
		if(nom.length() > 30) {
			throw new BLLException("Le nom ne doit pas dépasser 30 caractères");
		}
		//Vérification du nombre de caractères dans le prénom (ne doit pas dépasser 30 caractères)
		if(prenom.length() > 30) {
			throw new BLLException("Le prenom ne doit pas dépasser 30 caractères");
		}
		//Vérification du nombre de caractères dans le mail (ne doit pas dépasser 50 caractères)
		if(email.length() > 50) {
			throw new BLLException("Le mail ne doit pas dépasser 50 caractères");
		}
		//Vérification du nombre de caractères dans le téléphone (ne doit pas dépasser 15 caractères)
		if(tel.length() > 15) {
			throw new BLLException("Le téléphone ne doit pas dépasser 15 caractères");
		}
		//Vérification du nombre de caractères dans la rue (ne doit pas dépasser 30 caractères)
		if(rue.length() > 30) {
			throw new BLLException("Le nom de la rue ne doit pas dépasser 30 caractères");
		}
		//Vérification du nombre de caractères dans le code postal (ne doit pas dépasser 10 caractères)
		if(codePostal.length() > 10) {
			throw new BLLException("Le code postal ne doit pas dépasser 10 caractères");
		}
		//Vérification du nombre de caractères dans la ville (ne doit pas dépasser 50 caractères)
		if(ville.length() > 50) {
			throw new BLLException("Le nom de la ville ne doit pas dépasser 50 caractères");
		}
		//Vérification du nombre de caractères dans le mot de passe (ne doit pas dépasser 30 caractères)
		if(ville.length() > 30) {
			throw new BLLException("Le mot de passe ne doit pas dépasser 30 caractères");
		}
		//creation de l'instance d'utilisateur à partir des informations saisies par l'utilisateur
		user = new User(pseudo, nom, prenom, email, tel, rue, codePostal, ville, password, credit, admninistrateur);
		try {
			userDAO.insertUser(user);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
