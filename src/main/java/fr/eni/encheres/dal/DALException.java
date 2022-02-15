package fr.eni.encheres.dal;

public class DALException extends Exception {

	private String message;
	
	public DALException(String string) {
		this.message = string;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
