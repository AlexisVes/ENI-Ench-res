package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.DALException;

public class CategorieDAOJdbcImpl implements CategorieDAO{
	
	private static final String SELECT_CATEGORIE = "SELECT no_categorie, libelle FROM CATEGORIES;";

	@Override
	public List<Categorie> getCategories() throws DALException {

		//On établit la connexion vers la BDD
		List<Categorie> listeCategorie = new ArrayList<Categorie>();
		Connection cnx = null;
		
		cnx = ConnectionProvider.getConnection();		
		Statement rqt;
		
		try {
			rqt = cnx.createStatement();
			ResultSet rs = rqt.executeQuery(SELECT_CATEGORIE);
			
			while(rs.next()) {
				Categorie categorieCourant = new Categorie(rs.getInt("no_categorie"),rs.getString("libelle"));
				listeCategorie.add(categorieCourant);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listeCategorie;
	}

}
