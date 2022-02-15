package fr.eni.encheres.controllers;

import java.util.ArrayList;
import java.util.List;

public class ControllersException extends Exception
{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private List<String> messages;

		public ControllersException()
		{
			super();
			messages = new ArrayList<String>();
		}
		
		public void addMessage(String message)
		{
			messages.add(message);
		}

		public List<String> getMessages() {
			return messages;
		}
		
		public boolean hasErrors()
		{
			return messages.size() > 0 ?true:false;
		}
}
