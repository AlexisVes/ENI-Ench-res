package fr.eni.encheres.bll;

import java.util.ArrayList;
import java.util.List;



public class BLLException extends Exception
{
	private List<String> messages;
	
	public BLLException(){
		super();
		messages = new ArrayList<String>();
	}
	
	public void addMessage(String message){
		messages.add(message);
	}
	
	public List<String> getMessages() {
		return messages;
	}
	
	
	public boolean hasErrors(){
		return messages.size() > 0 ?true:false;
	}
}
