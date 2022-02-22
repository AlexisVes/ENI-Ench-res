package fr.eni.encheres.dal;

import fr.eni.encheres.bo.User;

public interface UserDAO {
	
	/**
	 * @param pseudo
	 * @return un objet User provenant d'une ligne de la
	 * BDD qui a pour pseudo, la String rentrée en paramètre
	 * @throws DALException
	 */
	User getUser(String pseudo) throws DALException;
	
	
	/**
	 * @param no_utilisateur
	 * @return un objet User provenant d'une ligne de la
	 * BDD qui a pour user_id, le int rentrée en paramètre
	 * @throws DALException
	 */
	User getUserById(int no_utilisateur) throws DALException;
	
	
	/**
	 * @param pseudo
	 * @return un int représentant l'id de l'utilisateur
	 * possédant le pseudo rentré en paramètre
	 * @throws DALException
	 */
	int getIdByPseudo (String pseudo) throws DALException;
	
	
	/**
	 * Créer un nouvelle utilisateur dans la base de données
	 * @param user
	 * @throws DALException
	 */
	void insertUser(User user) throws DALException;
	
	
	/**
	 * Modifie un utilisateur dans la base de données
	 * @param user
	 * @throws DALException
	 */
	void updateUser(User user) throws DALException;
	
	
	/**
	 * Supprime un utilisateur de la base de données
	 * @param pseudo
	 * @throws DALException
	 */
	void deleteUser(String pseudo) throws DALException;

}

