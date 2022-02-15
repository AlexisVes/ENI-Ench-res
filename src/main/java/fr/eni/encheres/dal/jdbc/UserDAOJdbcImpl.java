package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.UserDAO;

public class UserDAOJdbcImpl implements UserDAO{
	private static final String SELECT_USER ="SELECT  pseudo, email, mot_de_passe FROM UTILISATEURS WHERE pseudo = ?";

	@Override
	/**
	 * Methode permettant de récupérer un User depuis la base de données afin de le fournir aux couches supérieures
	 */
	public User getUser(String pseudo) throws DALException {
		//Établir la connexion avec la bases de données 
		Connection cnx = null;
		User userBDD = null;
		try {
			cnx = ConnectionProvider.getConnection();
;
			//créer la commande 
			PreparedStatement rqt = cnx.prepareStatement(SELECT_USER);
			//valoriser les paramètres
			rqt.setString(1, pseudo);
			//récupérer le contenu du SELECT_USER dans un resultset
			ResultSet rs = rqt.executeQuery();
			;
			//s'appuyer sur ce result set pour alimenter les variables de l'objet User qui sera retourné 
			if(rs != null) {
				rs.next();
				String username =rs.getString(1);
				String email =rs.getString(2);
				String password =rs.getString(3);
				
				//on créé l'objet userBDD
				userBDD = new User(username, email, password);
				cnx.close();
			} 
			
		} catch (SQLException e) {
			System.out.println("user inconnu");
			throw new DALException("Utilisateur inconnu");
		}
		return userBDD;
	}
}
