package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Categorie;

public interface CategorieDAO {

	/**
	 * @returnune liste de toutes les catégories de la base de donnée
	 * @throws DALException
	 */
	List<Categorie> getCategories() throws DALException; 
	
	/**
	 * @param noCategorie
	 * @return le libellé de la catégorie ayant le noCatégorie en paramètre
	 * @throws DALException
	 */
	String getCategorie(int noCategorie) throws DALException;
}
