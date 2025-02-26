package it.unipv.ingsfw.aga.handler;
import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.ArrayList;

import it.unipv.ingsfw.aga.model.evento.Evento;
import it.unipv.ingsfw.aga.model.biglietto.Biglietto;

public class Handler {
	
	public Handler(){
		
	}
	
	public boolean report(Evento evento, ArrayList<Biglietto> biglietti,String dir) {
		boolean result=false;
		try {
		      File myObj = new File(dir+"\\"+evento.getData()+".txt");
		      if (myObj.createNewFile()) 
		        System.out.println("File created: " + myObj.getName());
		      else 
		        System.out.println("File already exists."+myObj.getName()+"    "+dir);
		      result=this.write(evento,myObj, biglietti);      
		      
	    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
		
		return result;
	}
	
	
	public boolean write(Evento evento,File myObj,ArrayList<Biglietto> biglietti) {
		boolean result=false;
		try {
		      FileWriter myWriter = new FileWriter(myObj);
		      myWriter.write(evento.toString()+"\n\n\n");
		      for (Biglietto i : biglietti) {
		    	  myWriter.write(i.stampaBiglietto()+"\n\n");
		        }
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
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
		//System.out.println(h.report());
	}

}
