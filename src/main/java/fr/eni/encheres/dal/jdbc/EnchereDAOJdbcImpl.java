package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.EnchereDAO;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	
	
	private static final String SELECT_ENCHERE = "SELECT no_enchere, date_enchere, montant_enchere, no_article, no_utilisateur WHERE no_article = ?;";

	private static final String UPDATE_ENCHERE = "UPDATE ENCHERES SET date_enchere = ?; montant_enchere = ?, no_utilisateur = ? WHERE no_article = ?;";
	
	private static final String INSERT_ENCHERE = "INSERT INTO ENCHERES(date_enchere, montant_enchere, no_article, no-utilisateur) VALUES(?,?,?,?);";
	
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
	public void updateEnchere( Enchere enchere) {
		
		Connection cnx = null;
		
		try {
			
			cnx = ConnectionProvider.getConnection();
			
			PreparedStatement rqt = cnx.prepareStatement(UPDATE_ENCHERE);
			
			rqt.setDate(1,  Date.valueOf(enchere.getDate_enchere()));
			rqt.setInt( 2 , enchere.getMontant_enchere() );
			rqt.setInt( 3 , enchere.getNo_utilisateur() );
			
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


	@Override
	public void insertEnchere(Enchere enchere) {
		
		Connection cnx = null;
		
		try {
			
			cnx = ConnectionProvider.getConnection();
			
			PreparedStatement rqt = cnx.prepareStatement(INSERT_ENCHERE);
			
			
			rqt.setDate(1,  Date.valueOf(enchere.getDate_enchere()));
			rqt.setInt( 2, enchere.getMontant_enchere() );
			rqt.setInt(3, enchere.getNo_article());
			rqt.setInt( 4 , enchere.getNo_utilisateur() );
			
			ResultSet rs = rqt.executeQuery();
			
			
		}
		catch (SQLException e) {
			//propager une exception personnalisée
			e.printStackTrace();

		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}