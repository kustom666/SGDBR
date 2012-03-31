package model;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Base extends ArrayList<Table> {

	private static final long serialVersionUID = 690495388715860154L;
	private String nom;	
	
	public Base(String n)
	{
		this.nom = n;
	}
	
	/**
	 * addTable ajoute des tables à la base.
	 * @params aTables les tables à ajouter
	 * @author Paul Forti
	 * */
	public void addTables(ArrayList<Table> aTables){
		for(int i=0; i<aTables.size();i++){
			this.add(aTables.get(i));
		}
	}
	
	/**
	 * saveBase enregistre la base de données dans un fichier pour une réutilisation future
	 * @author Paul Forti
	 * */
	public void saveBase(){
		FileOutputStream output;
		try{
			output = new FileOutputStream(new File(nom));
			DataOutputStream dout = new DataOutputStream(output);
			
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}
