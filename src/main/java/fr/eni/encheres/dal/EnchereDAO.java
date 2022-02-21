package fr.eni.encheres.dal;

import java.time.LocalTime;

import fr.eni.encheres.bo.Enchere;

public interface EnchereDAO {
	
	Enchere getEnchere( int no_article );
	
	void updateEnchere(  LocalTime now, int prix, int no_utilisateur );
	
	void insertEnchere (  LocalTime now, int prix, int no_utilisateur );

}
