package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Retrait;

public interface RetraitDAO {
	
	/**
	 * Ajoute en BDD un nouveau retrait
	 * @param retrait
	 * @throws DALException
	 */
	void insertRetrait(Retrait retrait) throws DALException;

	
	/**
	 * 
	 * @param noArticle
	 * @return un objet retrait qui provient d'un élément de la BDD
	 * qui a pour no_article le int en paramètre
	 * @throws DALException
	 */
	Retrait getRetrait(int noArticle) throws DALException;


	/**
	 * Delele un retrait qui a pour no_article notre int en
	 * paramètre
	 * @param noArticle
	 * @throws DALException
	 */
	void deleteRetrait( int noArticle ) throws DALException;

}