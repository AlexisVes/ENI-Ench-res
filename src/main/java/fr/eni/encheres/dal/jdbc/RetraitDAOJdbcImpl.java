package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.RetraitDAO;

public class RetraitDAOJdbcImpl implements RetraitDAO{
	
	private static final String INSERT_RETRAIT = "INSERT INTO RETRAITS VALUES(?, ?, ?, ?);";

	private static final String SELECT_RETRAIT = "SELECT no_article, rue, code_postal, ville FROM RETRAITS WHERE no_article = ?;";
	
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
			
			cnx.close();
	
		} 
		catch (DALException | SQLException e) 
		{
			
			System.out.println("ça ne marche pas !");

		}
		
	}

	@Override
	public Retrait getRetrait(int noArticle) throws DALException {
		
		Connection cnx = null;

		Retrait retraitBDD = null;
		
		try {
			
			cnx = ConnectionProvider.getConnection();
			
			PreparedStatement rqt = cnx.prepareStatement(SELECT_RETRAIT);
			
			rqt.setInt(1, noArticle);
			
			ResultSet rs = rqt.executeQuery();
			
			if( rs != null) {
				rs.next();
				
				int no_article = rs.getInt(1);
				String rue = rs.getString(2);
				String code_postal = rs.getString(3);
				String ville = rs.getString(4);
				
				retraitBDD = new Retrait(no_article, rue, code_postal, ville );
				
			}
			
			
		} catch (SQLException e) {
			//propager une exception personnalisée
			throw new DALException("Problème d'extraction des retraits de la base. Cause : " + e.getMessage());
		}
		
		try {
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retraitBDD;
		
	}

}
