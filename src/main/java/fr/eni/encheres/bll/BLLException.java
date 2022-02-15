package fr.eni.encheres.bll;

public class BLLException extends Exception {
	
	private String message;
	
	public BLLException(String string) {
		this.message = string;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	
}
