package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Categorie;

public interface CategorieDAO {

	List<Categorie> getCategories() throws DALException; 
	
	String getCategorie(int noCategorie) throws DALException;
}
