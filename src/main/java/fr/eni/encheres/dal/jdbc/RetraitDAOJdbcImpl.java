package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.RetraitDAO;

public class RetraitDAOJdbcImpl implements RetraitDAO{
	
	private static final String INSERT_RETRAIT = "INSERT INTO RETRAITS VALUES(?, ?, ?, ?);";

	@Override
	/**
	 * Une méthode permettant d'insérer en base de données le lieu du retrait 
	 */
	public void insertRetrait(Retrait retrait) throws DALException {
		
try (Connection cnx = ConnectionProvider.getConnection()){
			
			//On prepare la requête SQL
			PreparedStatement rqt = cnx.prepareStatement(INSERT_RETRAIT);
			
			//On y insère nos paramètres, on n'oubliant pas de convertir pour que ce soit compatible à SQL
			rqt.setInt(1, retrait.getNoArticle());
			rqt.setString(2, retrait.getRue());
			rqt.setString(3, retrait.getCodePostal());
			rqt.setString(4, retrait.getVille());

			
			//Éxécution de la requête SQL
			int numberAffectedLines = rqt.executeUpdate();
	
		} 
		catch (DALException | SQLException e) 
		{
			
			System.out.println("ça ne marche pas !");

		}
		
	}

	@Override
	public Retrait getRetrait() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

}
