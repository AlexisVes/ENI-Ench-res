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
	private static final String INSERT_USER =" INSERT INTO UTILISATEURS ( no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe ) VALUES(?,?,?,?,?,?,?,?,?,?,0)";

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
	
	public void insertUser( User user )
	{
		Connection cnx = null;
		try {
			cnx = ConnectionProvider.getConnection();
			
			//On prepare la requête SQL
			PreparedStatement rqt = cnx.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
			
			//On y insère nos paramètres, on n'oubliant pas de convertir pour que ce soit compatible à SQL
			rqt.setString(1, user.getPseudo());
			rqt.setString(2, user.getNom());
			rqt.setString(3, user.getPrenom());
			rqt.setString(4, user.getEmail());
			rqt.setString(5, user.getTelephone());
			rqt.setString(6, user.getRue());
			rqt.setString(7, user.getCodePostal());
			rqt.setString(8, user.getVille());
			rqt.setString(8, user.getPassword());
			
			cnx.close();	
		} 
		catch (DALException | SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
}
