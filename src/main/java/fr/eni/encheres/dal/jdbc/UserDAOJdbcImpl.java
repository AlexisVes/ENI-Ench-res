package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.UserDAO;

public class UserDAOJdbcImpl implements UserDAO{

	private static final String INSERT_USER ="INSERT INTO UTILISATEURS ( pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur ) VALUES(?,?,?,?,?,?,?,?,?,100,0)";
	private static final String SELECT_USER ="SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE pseudo = ?";
	private static final String UPDATE_USER ="UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ? WHERE no_utilisateur = ?;";
	private static final String DELETE_USER ="DELETE UTILISATEURS WHERE pseudo = ?;";
	private static final String SELECT_USER_BY_ID ="SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE no_utilisateur = ?";

	
	public User getUserById(int no_utilisateur) throws DALException
	{
		//Établir la connexion avec la bases de données 
		User userBDD = null;
		try (Connection cnx = ConnectionProvider.getConnection()){

			//créer la commande 
			PreparedStatement rqt = cnx.prepareStatement(SELECT_USER_BY_ID );
			//valoriser les paramètres
			rqt.setInt(1, no_utilisateur);
			//récupérer le contenu du SELECT_USER dans un resultset
			ResultSet rs = rqt.executeQuery();
			
			//s'appuyer sur ce result set pour alimenter les variables de l'objet User qui sera retourné 
			if(rs != null) {
				rs.next();
				int userId = rs.getInt(1);
				String username =rs.getString(2);
				String name = rs.getString(3);
				String firstname = rs.getString(4);
				String email = rs.getString(5);
				String phone = rs.getString(6);
				String street = rs.getString(7);
				String zipCode = rs.getString(8);
				String city = rs.getString(9);
				String password = rs.getString(10);
				int credit = rs.getInt(11);
				byte admin = rs.getByte(12);
				
				//on créé l'objet userBDD
				userBDD = new User(userId, username, name, firstname, email, phone, street, zipCode, city, password, credit, admin);
			} 
			
		} catch (SQLException e) {
			System.out.println("user inconnu");
			throw new DALException("Utilisateur inconnu");
		}
		
		return userBDD;
	}
	
	@Override
	/**
	 * Methode permettant de récupérer un User depuis la base de données afin de le fournir aux couches supérieures
	 */
	public User getUser(String pseudo) throws DALException {
		//Établir la connexion avec la bases de données 
		User userBDD = null;
		try (Connection cnx = ConnectionProvider.getConnection()){

			//créer la commande 
			PreparedStatement rqt = cnx.prepareStatement(SELECT_USER);
			//valoriser les paramètres
			rqt.setString(1, pseudo);
			//récupérer le contenu du SELECT_USER dans un resultset
			ResultSet rs = rqt.executeQuery();
			
			//s'appuyer sur ce result set pour alimenter les variables de l'objet User qui sera retourné 
			if(rs != null) {
				rs.next();
				int userId = rs.getInt(1);
				String username =rs.getString(2);
				String name = rs.getString(3);
				String firstname = rs.getString(4);
				String email = rs.getString(5);
				String phone = rs.getString(6);
				String street = rs.getString(7);
				String zipCode = rs.getString(8);
				String city = rs.getString(9);
				String password = rs.getString(10);
				int credit = rs.getInt(11);
				byte admin = rs.getByte(12);
				
				//on créé l'objet userBDD
				userBDD = new User(userId, username, name, firstname, email, phone, street, zipCode, city, password, credit, admin);
			} 
			
		} catch (SQLException e) {
			System.out.println("user inconnu");
			throw new DALException("Utilisateur inconnu");
		}
		
		return userBDD;
	}
	
	public void insertUser( User user ) throws DALException
	{

		try (Connection cnx = ConnectionProvider.getConnection()){
			
			//On prepare la requête SQL
			PreparedStatement rqt = cnx.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
			
			//On y insère nos paramètres, on n'oubliant pas de convertir pour que ce soit compatible à SQL
			rqt.setString(1, user.getPseudo());
			rqt.setString(2, user.getNom());
			rqt.setString(3, user.getPrenom());
			rqt.setString(4, user.getEmail());
			rqt.setString(5, user.getTel());
			rqt.setString(6, user.getRue());
			rqt.setString(7, user.getCodePostal());
			rqt.setString(8, user.getVille());
			rqt.setString(9, user.getPassword());
			
			//Éxécution de la requête SQL
			int numberAffectedLines = rqt.executeUpdate();
	
		} 
		catch (SQLException e) 
		{
			
			if( e.getMessage().contains("UK_email"))
			{
				throw new DALException("Cette adresse mail est déjà utilisée");
			}
			else
			{
				throw new DALException("Pseudo déjà existant");
			}

		}
		
	}

	@Override
	public void updateUser(User user) throws DALException {
		//Établir la connexion avec la bases de données 
		PreparedStatement rqt;
		//créer la commande 
		try (Connection cnx = ConnectionProvider.getConnection()){
			rqt = cnx.prepareStatement(UPDATE_USER);
			
			//on envoie les données dans la BDD pour que celles-ci soient modifiées 
			rqt.setString(1, user.getPseudo());
			rqt.setString(2, user.getNom());
			rqt.setString(3, user.getPrenom());
			rqt.setString(4, user.getEmail());
			rqt.setString(5, user.getTel());
			rqt.setString(6, user.getRue());
			rqt.setString(7, user.getCodePostal());
			rqt.setString(8, user.getVille());
			rqt.setString(9, user.getPassword());
			rqt.setInt(10, user.getUserId());
			//Éxécution de la requête SQL
			rqt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteUser(String pseudo) throws DALException {
		//Établir la connexion avec la bases de données et créer la commande
		PreparedStatement rqt;
		try (Connection cnx = ConnectionProvider.getConnection()){
			rqt = cnx.prepareStatement(DELETE_USER);
			rqt.setString(1, pseudo);
			//Éxécution de la requête de suppression en SQL
			rqt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
