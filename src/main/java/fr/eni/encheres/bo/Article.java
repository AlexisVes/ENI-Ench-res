package fr.eni.encheres.bo;

import java.time.LocalDate;

public class Article {
	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private int prixInitial;
	private int prixVente;
	private String etatVente;
	private int noCategorie;
	private String pseudo;
	private int noUtilisateur;
	private String photoArticle;
	
	private Enchere enchere;
	private Categorie categorie;
	private User user;
	private Retrait retrait;
	
	
	public Article() 
	{
		super();
	}

	public Article(String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int prixInitial, int prixVente, String etatVente, int noCategorie,
			int noUtilisateur, String pseudo) 
	{
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.noCategorie = noCategorie;
		this.pseudo = pseudo;
	}
	
	public Article(String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int prixInitial, int noUtilisateur, int noCategorie, String photoArticle)
	{
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.noUtilisateur = noUtilisateur;
		this.noCategorie = noCategorie;
		this.photoArticle = photoArticle;
	}
	
	public Article( int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int prixInitial, int prixVente, int noUtilisateur, int noCategorie, String photoArticle)
	{
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.noUtilisateur = noUtilisateur;
		this.noCategorie = noCategorie;
		this.noArticle = noArticle;
		this.prixVente = prixVente;
		this.photoArticle = photoArticle;
	}

	public int getNoArticle() 
	{
		return noArticle;
	}

	public void setNoArticle(int noArticle) 
	{
		this.noArticle = noArticle;
	}

	public String getNomArticle() 
	{
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) 
	{
		this.nomArticle = nomArticle;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	public LocalDate getDateDebutEncheres() 
	{
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(LocalDate dateDebutEncheres)
	{
		this.dateDebutEncheres = dateDebutEncheres;
	}

	public LocalDate getDateFinEncheres() 
	{
		return dateFinEncheres;
	}

	public void setDateFinEncheres(LocalDate dateFinEncheres) 
	{
		this.dateFinEncheres = dateFinEncheres;
	}

	public int getPrixInitial() 
	{
		return prixInitial;
	}

	public void setPrixInitial(int prixInitial) 
	{
		this.prixInitial = prixInitial;
	}

	public int getPrixVente() 
	{
		return prixVente;
	}

	public void setPrixVente(int prixVente) 
	{
		this.prixVente = prixVente;
	}

	public String getEtatVente()
	{
		return etatVente;
	}

	public void setEtatVente(String etatVente) 
	{
		this.etatVente = etatVente;
	}

	public int getNoCategorie()
	{
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) 
	{
		this.noCategorie = noCategorie;
	}

	public String getPseudo()
	{
		return pseudo;
	}

	public void setPseudo(String pseudo)
	{
		this.pseudo = pseudo;
	}

	public int getNoUtilisateur() 
	{
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) 
	{
		this.noUtilisateur = noUtilisateur;
	}

	public Enchere getEnchere() {
		return enchere;
	}

	public void setEnchere(Enchere enchere) {
		this.enchere = enchere;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Retrait getRetrait() {
		return retrait;
	}

	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}

	public String getPhotoArticle() {
		return photoArticle;
	}

	public void setPhotoArticle(String photoArticle) {
		this.photoArticle = photoArticle;
	}

}

