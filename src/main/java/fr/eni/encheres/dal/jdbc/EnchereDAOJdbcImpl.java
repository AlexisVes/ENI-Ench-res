package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.EnchereDAO;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	
	
	private static final String SELECT_ENCHERE = "SELECT no_enchere, date_enchere, montant_enchere, no_article, no_utilisateur WHERE no_article = ?;";

	private static final String UPDATE_ENCHERE = "UPDATE ENCHERES SET date_enchere = ?; montant_enchere = ?, no_utilisateur = ? WHERE no_article = ?;";
	
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
		catch (SQLException e) {
			//propager une exception personnalisée
			e.printStackTrace();

		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}


	@Override
	public void updateEnchere( LocalTime now, int prix, int no_utilisateur) {
		
Connection cnx = null;
		
		try {
			
			cnx = ConnectionProvider.getConnection();
			
			PreparedStatement rqt = cnx.prepareStatement(UPDATE_ENCHERE);
			
			rqt.setTime(1,  Time.valueOf(now));
			rqt.setInt( 2 , prix );
			rqt.setInt( 3 , no_utilisateur );
			
			ResultSet rs = rqt.executeQuery();
			
			
		}
		catch (SQLException e) {
			//propager une exception personnalisée
			e.printStackTrace();

		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
