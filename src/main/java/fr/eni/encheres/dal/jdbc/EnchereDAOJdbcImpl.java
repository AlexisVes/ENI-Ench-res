package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.EnchereDAO;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	
	
	private static final String SELECT_ENCHERE = "SELECT no_enchere, date_enchere, montant_enchere, no_article, no_utilisateur FROM ENCHERES WHERE no_article = ?;";

	private static final String UPDATE_ENCHERE = "UPDATE ENCHERES SET date_enchere = ?, montant_enchere = ?, no_utilisateur = ? WHERE no_article = ?;";
	
	private static final String INSERT_ENCHERE = "INSERT INTO ENCHERES(date_enchere, montant_enchere, no_article, no_utilisateur) VALUES(?,?,?,?);";
	
	public Enchere getEnchere( int no_article ) throws DALException
	{
		
		try (Connection cnx = ConnectionProvider.getConnection()) 
		{
			
			PreparedStatement rqt = cnx.prepareStatement(SELECT_ENCHERE);
			
			rqt.setInt( 1 , no_article );
			
			ResultSet rs = rqt.executeQuery();
			
			Enchere enchere = null;
			
			if( rs.next() )
			{
				enchere = new Enchere( rs.getInt(1), rs.getTimestamp(2).toLocalDateTime() , rs.getInt(3), rs.getInt(4), rs.getInt(5));
			}
			
			return enchere;
			
		}
		catch (SQLException | DALException e)
		{
			e.printStackTrace();
			throw new DALException("Problème d'extraction des articles de la base. Cause : " + e.getMessage());
		}

	}


	@Override
	public void updateEnchere( LocalDateTime now, int prix, int no_utilisateur, int no_article) throws DALException 
	{

		try (Connection cnx = ConnectionProvider.getConnection())
		{
			
			PreparedStatement rqt = cnx.prepareStatement(UPDATE_ENCHERE);

			rqt.setTimestamp(1, Timestamp.valueOf(now));
			rqt.setInt( 2 , prix );
			rqt.setInt( 3 , no_utilisateur );
			rqt.setInt( 4, no_article);

			int rs = rqt.executeUpdate();


		}
		catch 
		(SQLException | DALException e) 
		{
			e.printStackTrace();
			throw new DALException("Problème d'extraction des articles de la base. Cause : " + e.getMessage());
		}
		
	}


	@Override
	public void insertEnchere( Enchere enchere) throws DALException {
		
		try (Connection cnx = ConnectionProvider.getConnection())
		{
			
			PreparedStatement rqt = cnx.prepareStatement(INSERT_ENCHERE);
			
			rqt.setTimestamp(1, Timestamp.valueOf(enchere.getDate_enchere()));
			rqt.setInt( 2, enchere.getMontant_enchere() );
			rqt.setInt(3, enchere.getNo_article());
			rqt.setInt( 4 , enchere.getNo_utilisateur() );
	
			rqt.executeUpdate();
			
		}
		catch (SQLException | DALException e) 
		{
			e.printStackTrace();
			throw new DALException("Problème d'extraction des articles de la base. Cause : " + e.getMessage());
		} 


	}
	
}

