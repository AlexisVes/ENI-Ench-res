package fr.eni.encheres.bo;

import java.time.LocalDateTime;

public class Enchere 
{
	
	int no_enchere;
	LocalDateTime date_enchere;
	int montant_enchere;
	int no_article;
	int no_utilisateur;
	String pseudo;
	
	private Article article;
	
	public Enchere( LocalDateTime date_enchere, int montant_enchere, int no_article, int no_utilisateur) 
	{
		super();
		this.date_enchere = date_enchere;
		this.montant_enchere = montant_enchere;
		this.no_article = no_article;
		this.no_utilisateur = no_utilisateur;
	}
	
	public Enchere( LocalDateTime date_enchere, int montant_enchere, int no_article, int no_utilisateur, Article article) 
	{
		super();
		this.date_enchere = date_enchere;
		this.montant_enchere = montant_enchere;
		this.no_article = no_article;
		this.no_utilisateur = no_utilisateur;
		this.article = article;
	}
	

	public Enchere(int no_enchere, LocalDateTime date_enchere, int montant_enchere, int no_article, int no_utilisateur) 
	{
		super();
		this.no_enchere = no_enchere;
		this.date_enchere = date_enchere;
		this.montant_enchere = montant_enchere;
		this.no_article = no_article;
		this.no_utilisateur = no_utilisateur;
	}
	
	public Enchere( int no_enchere, LocalDateTime date_enchere, int montant_enchere, int no_article, int no_utilisateur, String pseudo)
	{
		super();
		this.no_enchere = no_enchere;
		this.date_enchere = date_enchere;
		this.montant_enchere = montant_enchere;
		this.no_article = no_article;
		this.no_utilisateur = no_utilisateur;
		this.pseudo = pseudo;
	}



	public LocalDateTime getDate_enchere() 
	{
		return date_enchere;
	}

	public void setDate_enchere(LocalDateTime date_enchere) 
	{
		this.date_enchere = date_enchere;
	}

	public int getMontant_enchere()
	{
		return montant_enchere;
	}

	public void setMontant_enchere(int montant_enchere) 
	{
		this.montant_enchere = montant_enchere;
	}

	public int getNo_article() 
	{
		return no_article;
	}

	public void setNo_article(int no_article)
	{
		this.no_article = no_article;
	}

	public int getNo_utilisateur()
	{
		return no_utilisateur;
	}

	public void setNo_utilisateur(int no_utilisateur)
	{
		this.no_utilisateur = no_utilisateur;
	}

	public String getPseudo() 
	{
		return pseudo;
	}

	public void setPseudo(String pseudo) 
	{
		this.pseudo = pseudo;
	}

	public int getNo_enchere() {
		return no_enchere;
	}

	public void setNo_enchere(int no_enchere) {
		this.no_enchere = no_enchere;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	
	
}
