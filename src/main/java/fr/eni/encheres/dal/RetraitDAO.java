package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Retrait;

public interface RetraitDAO {
	void insertRetrait(Retrait retrait) throws DALException;
	
	Retrait getRetrait() throws DALException;
}
