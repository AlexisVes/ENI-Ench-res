package fr.eni.encheres.dal;

import java.time.LocalDate;
import java.time.LocalTime;

import fr.eni.encheres.bo.Enchere;

public interface EnchereDAO {
	
	Enchere getEnchere( int no_article );
	
	void updateEnchere(  LocalDate now, int prix, int no_utilisateur );
	
	void insertEnchere ( Enchere enchere);

	


}
