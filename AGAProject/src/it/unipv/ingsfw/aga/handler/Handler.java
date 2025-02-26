package it.unipv.ingsfw.aga.handler;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.ArrayList;

import it.unipv.ingsfw.aga.model.evento.Evento;
import it.unipv.ingsfw.aga.model.biglietto.Biglietto;

public class Handler {
	
	private Evento evento;//se riesco stampo le cose dell'evento
	private ArrayList<Biglietto> biglietti;//lista biglietti
	
	public Handler(Evento evento, ArrayList<Biglietto> biglietti) {
		this.evento=evento;
		this.biglietti=biglietti;
	}
	
	public Handler(){
		
	}
	
	public boolean report() {
		boolean result=false;
		try {
			
		      File myObj = new File("src/properties/Evento.txt");
		      if (myObj.createNewFile()) 
		        System.out.println("File created: " + myObj.getName());
		      else 
		        System.out.println("File already exists.");
		      result=true;
		      
	    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		//Evento e=new Evento()
		Handler h=new Handler();
		System.out.println(h.report());
	}

}
