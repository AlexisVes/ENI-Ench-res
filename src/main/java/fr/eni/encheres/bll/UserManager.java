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
				userDAO = DAOFactory.createUserDAO("JDBC");
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
	
	
	/**
	 * 
	 * @param pseudo : Le pseudo rentré par l'utilisateur dans la tentative de connexion
	 * @param password : Le mot de passe rentré par l'utilisateur dans la tentative de connexion
	 * @return un booléen true si les données rentrées par l'utilisateur correspondent à
	 * un utilisateur dans notre base de données
	 * @throws BLLException 
	 */
	public boolean searchUser(String pseudo, String password) throws BLLException
	{
		User user = null;
		try {

			//Si il éxiste bien un utilisateur avec ce pseudo
			if(userDAO.getUser(pseudo) != null) 
			{
				user = userDAO.getUser(pseudo);

				//On compare le mot de passe rentré par l'utilisateur et celui trouvé en base de données
				if(password.equals(user.getPassword()))
				{     
					return true;
				}
				else
				{
					BLLException exception = new BLLException();
					exception.addMessage("Le mot de passe rentré ne correspond pas au nom d'utilisateur");
					throw exception;
				}
			}
		} 
		catch (DALException e) 
		{
			BLLException exception = new BLLException();
			exception.addMessage("Le pseudo rentré ne correspond à aucun utilisateurs");
			throw exception;
		}
		return false;
	}


	public void createUser(	String pseudo, String nom, String prenom, String email, String tel, String rue,
							String codePostal, String ville, String password) throws BLLException{

		BLLException exception = new BLLException();
		User user = null;
		int credit=0; 
		byte admninistrateur=0;
		
		//Vérification du nombre de caractères dans le pseudo (ne doit pas dépasser 30 caractères)
		if(pseudo.length() > 30) {
			exception.addMessage("Le pseudo ne doit pas dépasser 30 caractères");
		}
		
//		if( pseudo.contains("^[a-zA-Z0-9]+$"))
//		{
//			exception.addMessage("Le pseudo ne doit pas contenir de caractères spéciaux");
//		}
		//Vérification du nombre de caractères dans le nom (ne doit pas dépasser 30 caractères)
		if(nom.length() > 30) {
			exception.addMessage("Le nom ne doit pas dépasser 30 caractères");
		}
		//Vérification du nombre de caractères dans le prénom (ne doit pas dépasser 30 caractères)
		if(prenom.length() > 30) {
			exception.addMessage("Le prénom ne doit pas dépasser 30 caractères");
		}
		//Vérification du nombre de caractères dans le mail (ne doit pas dépasser 50 caractères)
		if(email.length() > 50) {
			exception.addMessage("Le mail ne doit pas dépasser 50 caractères");
		}
		//Vérification du nombre de caractères dans le téléphone (ne doit pas dépasser 15 caractères)
		if(tel.length() > 15) {
			exception.addMessage("Le téléphone ne doit pas dépasser 15 caractères");
		}
		//Vérification du nombre de caractères dans la rue (ne doit pas dépasser 30 caractères)
		if(rue.length() > 30) {
			exception.addMessage("Le nom de la rue ne doit pas dépasser 30 caractères");
		}
		//Vérification du nombre de caractères dans le code postal (ne doit pas dépasser 10 caractères)
		if(codePostal.length() > 10) {
			exception.addMessage("Le code postal ne doit pas dépasser 10 caractères");
		}
		//Vérification du nombre de caractères dans la ville (ne doit pas dépasser 50 caractères)
		if(ville.length() > 50) {
			exception.addMessage("Le nom de la ville ne doit pas dépasser 50 caractères");
		}
		//Vérification du nombre de caractères dans le mot de passe (ne doit pas dépasser 30 caractères)
		if(password.length() > 30) {
			exception.addMessage("Le mot de passe ne doit pas dépasser 30 caractères");
		}
		//creation de l'instance d'utilisateur à partir des informations saisies par l'utilisateur
		
		if(exception.hasErrors()) {
			throw exception;
		}
		
		user = new User(pseudo, nom, prenom, email, tel, rue, codePostal, ville, password, credit, admninistrateur);
		try {
			userDAO.insertUser(user);
		} catch (DALException e) {
			BLLException BLLException = new BLLException();
			BLLException.addMessage(e.getMessage());
			throw BLLException;
		}
	}
	
}

