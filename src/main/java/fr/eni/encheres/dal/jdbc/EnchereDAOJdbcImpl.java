package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.EnchereDAO;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	
	
	private static final String SELECT_ENCHERE = "SELECT no_enchere, date_enchere, montant_enchere, no_article, no_utilisateur WHERE no_article = ?;";

	
	public Enchere getEnchere( int no_article )
	{
		
		Connection cnx = null;
		
		try {
			
			cnx = ConnectionProvider.getConnection();
			
			PreparedStatement rqt = cnx.prepareStatement(SELECT_ENCHERE);
			
			rqt.setInt( 1 , no_article );
			
			ResultSet rs = rqt.executeQuery();
			
			Enchere enchere = new Enchere( rs.getInt(1), rs.getDate(2).toLocalDate(), rs.getInt(3), rs.getInt(4), rs.getInt(5));
			
			return enchere;
			
		}
		catch( Exception e )
		{
			System.out.println("aie");
		}
		
		return null;
	}
	
}
