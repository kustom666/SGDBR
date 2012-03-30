package controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import model.*;
import types.*;

public class Crud {
	
	private Table usedTable;
	
	public Crud()
	{
		
	}

	public Crud(Table t)
	{
		this.usedTable = t;
	}
	

	public Column createCol(String n, Types t){
		 Column c = new Column(n, t);
		 return c;
	}
	
	public void ajouterLignes(ArrayList<Line> l){
		for(int i=0; i< l.size();i++){
			this.usedTable.insert(l.get(i));
		}
	}
	public void displayTable()
	{
		String buffMax = new String();
		
		for(int i=0; i<usedTable.getArrCol().size(); i++){
			buffMax+=("| "+this.usedTable.getArrCol().get(i).getLabel()+" ");
		}
		
		for(int i=0; i< buffMax.length()+1; i++){
			System.out.print("-");
		}
		System.out.println();
		
		System.out.print("|");
		for(int i=0; i< (buffMax.length()-(usedTable.getTableName().length()+2))/2; i++){
			System.out.print(" ");
		}
		
		System.out.print(usedTable.getTableName());
		
		for(int i=0; i< (buffMax.length()-(usedTable.getTableName().length()+2))/2+1; i++){
			System.out.print(" ");
		}
		System.out.println("|");
		
		for(int i=0; i< buffMax.length()+1; i++){
			System.out.print("-");
		}
		System.out.println();
		
		System.out.println(buffMax+"|");
		
		
		for(int i=0; i< buffMax.length()+1; i++){
			System.out.print("-");
		}
		System.out.println();
		
		for(int i=0; i< usedTable.size(); i++){
			
			usedTable.get(i).outputLine();
			
		}
		
		for(int i=0; i< buffMax.length()+1; i++){
			System.out.print("-");
		}
		System.out.println();

	}

	
	public void setUpTable(HashMap<String, Types> hm){
		
		Iterator it = hm.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String,Types> buff = (Map.Entry<String,Types>)it.next();
			Column c = this.createCol(buff.getKey(), buff.getValue());
			this.usedTable.addCol(c);
		}
	}
	
	public HashMap<String,Types> construireHMsetUp(ArrayList<String> a, ArrayList<Types> l){
		if(a.size()!=l.size()){
			System.out.println("Erreur : Trop de noms de colonne ou trop de types différents, défault d'intégrité");
			return null;
		}
		else
		{
			HashMap<String,Types> hm = new HashMap<String,Types>();
			for(int i=0;i<a.size();i++){
				System.out.println("Ajout d'une colonne de nom : "+a.get(i)+" de type : " +l.get(i).typeToString());
				hm.put(a.get(i), l.get(i));
			}
			return hm;
		}
	}
	
	public void initialise(String tName){
		Table initTable = new Table(tName);
		this.usedTable = initTable;
	}
	
	public void fullCreate(String tName, ArrayList<String> colNames, ArrayList<Types> types){
		initialise(tName);
		HashMap<String,Types> buffHM = construireHMsetUp(colNames, types);
		setUpTable(buffHM);	
	}
	
	public void fullCreate(String tName, ArrayList<String> colNames, ArrayList<Types> types, ArrayList<Line> l){
		initialise(tName);
		System.out.println("Cr�ation d'une table de nom : " +tName);
		HashMap<String,Types> buffHM = construireHMsetUp(colNames, types);
		setUpTable(buffHM);
		ajouterLignes(l);
		
	}
	public void addColumn(Column c){
		this.usedTable.addCol(c);
	}
	public void removeColumn(Column c){
		this.usedTable.supCol(c);
	}

	public void read(ArrayList<Column> selected){
		
	}
	
	public void setUsedTable(Table t)
	{
		this.usedTable = t;
	}
	public Table getUsedTable(){
		return this.usedTable;
	}

}
