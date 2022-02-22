package fr.eni.encheres.dal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import fr.eni.encheres.bo.Enchere;

public interface EnchereDAO {
	
	/**
	 * @param no_article
	 * @return un Objet enchère qui provient de la BDD qui a pour no_article, celui indiqué en paramètre
	 * @throws DALException
	 */
	Enchere getEnchere( int no_article ) throws DALException;
	
	
	/**
	 * Modifie dans la base de données un utilisateur qui a pour no_article
	 * celui indiqué en parèmetre.
	 * Modifie sa date, son prix, et son enchérisseur
	 * @param now
	 * @param prix
	 * @param no_utilisateur
	 * @param no_article
	 * @throws DALException
	 */
	void updateEnchere(  LocalDateTime now, int prix, int no_utilisateur, int no_article ) throws DALException;
	
	
	/**
	 * Ajoute en BDD une nouvelle enchère
	 * @param enchere
	 * @throws DALException
	 */
	void insertEnchere ( Enchere enchere) throws DALException;
	


}
