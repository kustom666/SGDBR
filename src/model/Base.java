package model;

import java.util.ArrayList;

public class Base extends ArrayList<Table> {

	private static final long serialVersionUID = 690495388715860154L;
	private String nom;	
	
	public Base(String n)
	{
		this.nom = n;
	}
	
	/**
	 * addTable ajoute des tables � la base.
	 * @params aTables les tables � ajouter
	 * @author Paul Forti
	 * */
	public void addTables(ArrayList<Table> aTables){
		for(int i=0; i<aTables.size();i++){
			this.add(aTables.get(i));
		}
	}
	
	/**
	 * saveBase enregistre la base de donn�es dans un fichier CSV pour une r�utilisation future
	 * @author Paul Forti
	 * */
	public void saveBase(){
		
	}
}
