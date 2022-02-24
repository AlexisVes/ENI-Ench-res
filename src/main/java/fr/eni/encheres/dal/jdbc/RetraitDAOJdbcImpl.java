package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.RetraitDAO;

public class RetraitDAOJdbcImpl implements RetraitDAO
{
	
	private static final String INSERT_RETRAIT = "INSERT INTO RETRAITS VALUES(?, ?, ?, ?);";
	
	private static final String DELETE_RETRAIT = "DELETE RETRAITS WHERE no_article = ?;";

	private static final String SELECT_RETRAIT = "SELECT no_article, rue, code_postal, ville FROM RETRAITS WHERE no_article = ?;";
	
	
	public void deleteRetrait( int noArticle ) throws DALException
	{
		try (Connection cnx = ConnectionProvider.getConnection())
		{

			//On prepare la requête SQL
			PreparedStatement rqt = cnx.prepareStatement(DELETE_RETRAIT);
			
			rqt.setInt(1, noArticle);

			//Éxécution de la requête SQL
			int numberAffectedLines = rqt.executeUpdate();
			
		} 
		catch ( SQLException e) 
		{
			e.printStackTrace();
			throw new DALException("Problème d'extraction des articles de la base. Cause : " + e.getMessage());
		}
	}
	
	
	@Override
	/**
	 * Une méthode permettant d'insérer en base de données le lieu du retrait 
	 */
	public void insertRetrait(Retrait retrait) throws DALException 
	{
		
		try (Connection cnx = ConnectionProvider.getConnection())
		{

			//On prepare la requête SQL
			PreparedStatement rqt = cnx.prepareStatement(INSERT_RETRAIT);
			
			//On y insère nos paramètres, on n'oubliant pas de convertir pour que ce soit compatible à SQL
			rqt.setInt(1, retrait.getNoArticle());
			System.out.println(retrait.getNoArticle());
			rqt.setString(2, retrait.getRue());
			rqt.setString(3, retrait.getCodePostal());
			rqt.setString(4, retrait.getVille());

			//Éxécution de la requête SQL
			int numberAffectedLines = rqt.executeUpdate();
			
		} 
		catch (DALException | SQLException e) 
		{
			e.printStackTrace();
			throw new DALException("Problème d'extraction des articles de la base. Cause : " + e.getMessage());
		}
		
	}

	@Override
	public Retrait getRetrait(int noArticle) throws DALException
	{
		
		Retrait retraitBDD = null;
		
		try (Connection cnx = ConnectionProvider.getConnection())
		{
						
			PreparedStatement rqt = cnx.prepareStatement(SELECT_RETRAIT);
			
			rqt.setInt(1, noArticle);
			System.out.println(noArticle);
			
			ResultSet rs = rqt.executeQuery();
			
			rs.next();
			if( rs != null) 
			{
				System.out.println(rs.getInt(1));
				int no_article = rs.getInt(1);
				String rue = rs.getString(2);
				String code_postal = rs.getString(3);
				String ville = rs.getString(4);
				
				retraitBDD = new Retrait(no_article, rue, code_postal, ville );		
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new DALException("Problème d'extraction des retraits de la base. Cause : " + e.getMessage());
		}
		
		return retraitBDD;
		
	}

}

