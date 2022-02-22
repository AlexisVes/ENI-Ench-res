package fr.eni.encheres.bo;

public class User 
{
	
	private int userId;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String tel;
	private String rue;
	private String codePostal;
	private String ville;
	private String password;
	private int credit;
	private byte admninistrateur;
	
	

	public User(String pseudo, String nom, String prenom, String email, String tel, String rue,
			String codePostal, String ville, String password, int credit, byte admninistrateur) 
	{
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.password = password;
		this.credit = credit;
		this.admninistrateur = admninistrateur;
	}
	
	public User(int userId, String pseudo, String nom, String prenom, String email, String tel, String rue,
			String codePostal, String ville, String password, int credit, byte admninistrateur) 
	{
		super();
		this.userId = userId;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.password = password;
		this.credit = credit;
		this.admninistrateur = admninistrateur;
	}



	public int getUserId() 
	{
		return userId;
	}

	public String getPseudo()
	{
		return pseudo;
	}



	public void setPseudo(String pseudo) 
	{
		this.pseudo = pseudo;
	}



	public String getNom()
	{
		return nom;
	}



	public void setNom(String nom) 
	{
		this.nom = nom;
	}



	public String getPrenom() 
	{
		return prenom;
	}



	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
	}



	public String getEmail() 
	{
		return email;
	}



	public void setEmail(String email)
	{
		this.email = email;
	}



	public String getTel() 
	{
		return tel;
	}



	public void setTel(String tel)
{
		this.tel = tel;
	}



	public String getRue() 
	{
		return rue;
	}



	public void setRue(String rue) 
	{
		this.rue = rue;
	}



	public String getCodePostal() 
	{
		return codePostal;
	}



	public void setCodePostal(String codePostal) 
	{
		this.codePostal = codePostal;
	}



	public String getVille() 
	{
		return ville;
	}



	public void setVille(String ville) 
	{
		this.ville = ville;
	}



	public String getPassword()
	{
		return password;
	}



	public void setPassword(String password)
	{
		this.password = password;
	}



	public int getCredit() 
	{
		return credit;
	}



	public void setCredit(int credit) 
	{
		this.credit = credit;
	}



	public byte getAdmninistrateur() 
	{
		return admninistrateur;
	}



	public void setAdmninistrateur(byte admninistrateur) 
	{
		this.admninistrateur = admninistrateur;
	}

}
