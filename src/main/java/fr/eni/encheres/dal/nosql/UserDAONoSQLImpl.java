package fr.eni.encheres.dal.nosql;

import java.util.List;

import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.UserDAO;

public class UserDAONoSQLImpl implements UserDAO{

	@Override
	public User getUser(String pseudo) throws DALException {
		return null;
	
	}

	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(User user) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(String pseudo) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUserById(int no_utilisateur) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getIdByPseudo(String pseudo) throws DALException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> selectAllUsers() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

}
