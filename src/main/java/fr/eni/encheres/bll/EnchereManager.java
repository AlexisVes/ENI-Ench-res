package fr.eni.encheres.bll;

import java.time.LocalDate;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.EnchereDAO;

public class EnchereManager {
	
	
	public EnchereManager() throws DALException {
		super();
		enchereDAO = DAOFactory.createEnchereDAO("JDBC");
	}
	
	private EnchereDAO enchereDAO;

	public void controlerEnchere (int prixSaisi, Article article, int no_utilisateur) {
		
		LocalDate now = LocalDate.now();
		
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
			enchere = new Enchere( now, prixSaisi, article.getNoArticle(),  no_utilisateur );
			enchereDAO.insertEnchere(enchere);
		}
		
	}

}
