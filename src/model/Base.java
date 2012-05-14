package model;

import java.util.ArrayList;

/**
 * La classe Base est une collection de table représentant la base gérée.
 * @author Paul Forti
 * */
public class Base extends ArrayList<Table> {

	private static final long serialVersionUID = 690495388715860154L;
	private String nom;	
	
	/**
	 * Le constructeur de base permet de créer la base avec pour seul prérequis le nom de la base à créer
	 * @param n le nom de la base
	 * @author Paul Forti
	 * */
	public Base(String n)
	{
		this.nom = n;
	}
	
	/**
	 * addTable ajoute des tables à la base, de façon séquentielle.
	 * @params aTables les tables à ajouter
	 * @author Paul Forti
	 * */
	public void addTables(ArrayList<Table> aTables){
		for(int i=0; i<aTables.size();i++){
			this.add(aTables.get(i));
		}
	}
	public Table getTable(String name){
		Table table =new Table(null);
		for(int i=0;i<this.size();i++){
			if(this.get(i).getTableName().equals(name)){
				table=this.get(i);
				break;
			}
		}
		return table;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
}
