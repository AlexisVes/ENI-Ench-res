package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Enchere;

public interface EnchereDAO {
	
	Enchere getEnchere( int no_article );

}
