package fr.eni.encheres.bo;

public class User {
	
	private int noUtilisateur;
	private String pseudo;
	//private String nom;
	//private String prenom;
	private String email;
	//private String tel;
	//private String rue;
	//private String codePostal;
	//private String ville;
	private String password;
	//private int credit;
	//private boolean admninistrateur;
	
	public User(int noUtilisateur, String pseudo, String email, String password) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.email = email;
		this.password = password;
	}

	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
