package fr.eni.encheres.bll;

import java.time.LocalTime;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.EnchereDAO;
import fr.eni.encheres.dal.RetraitDAO;

public class EnchereManager {
	
	
	public EnchereManager() throws DALException {
		super();
		enchereDAO = DAOFactory.createEnchereDAO("JDBC");
	}
	
	private EnchereDAO enchereDAO;

	public void controlerEnchere (int prixSaisi, Article article, int no_utilisateur) {
		
		LocalTime now = LocalTime.now();
		
		Enchere enchere = enchereDAO.getEnchere(article.getNoArticle());
		
		if( enchere != null) 
		{
			
			if( prixSaisi > article.getPrixVente() ) 
			{
				enchereDAO.updateEnchere( now, prixSaisi, no_utilisateur);
			}
		}
		else
		{
			enchereDAO.
		}
		
	}

}
