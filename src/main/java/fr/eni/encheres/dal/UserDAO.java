package fr.eni.encheres.dal;

import fr.eni.encheres.bo.User;

public interface UserDAO {
	User getUser(String pseudo) throws DALException;
	
	void insertUser(User user) throws DALException;
	
	void updateUser(User user) throws DALException;
	
	void deleteUser(String pseudo) throws DALException;
}

