package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.DALException;

public class CategorieDAOJdbcImpl implements CategorieDAO{
	
	private static final String SELECT_CATEGORIES = "SELECT no_categorie, libelle FROM CATEGORIES;";
	private static final String SELECT_CATEGORIE = "SELECT libelle FROM CATEGORIES WHERE no_categorie=?;";

	@Override
	public List<Categorie> getCategories() throws DALException {

		//On établit la connexion vers la BDD
		List<Categorie> listeCategorie = new ArrayList<Categorie>();
		Statement rqt;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			rqt = cnx.createStatement();
			ResultSet rs = rqt.executeQuery(SELECT_CATEGORIES);
			
			while(rs.next()) {
				Categorie categorieCourant = new Categorie(rs.getInt("no_categorie"),rs.getString("libelle"));
				listeCategorie.add(categorieCourant);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listeCategorie;
	}
	
	public String getCategorie(int noCategorie) throws DALException {
		String libelleCategorie = null;
		
		//On établit la connexion vers la BDD

	
		try (Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement rqt = cnx.prepareStatement(SELECT_CATEGORIE);

			rqt.setInt(1, noCategorie);
					
			ResultSet rs = rqt.executeQuery();
					
			rs.next();
					
			if(rs != null) {
				libelleCategorie = rs.getString("libelle");
			}
					
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return libelleCategorie;
	}

}
