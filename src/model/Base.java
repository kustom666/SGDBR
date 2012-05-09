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
