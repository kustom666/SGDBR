package controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import model.*;
import types.*;

public class Crud {

	private Table usedTable;
	private Column usedColumn;
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
	
	public void importFromCSV(String contenucsv){
		
	}
	public void displayTable()
	{
		String buffMax = new String();
		
		//BuffMax contiens tous les caractères de la ligne contenant les descriptifs de colonne
		for(int i = 0; i< usedTable.getArrCol().size(); i++){
			buffMax+= "| "+usedTable.getArrCol().get(i).getLabel() + ":" +usedTable.getArrCol().get(i).getType().typeToString()+" ";
		}
		//Impression de la première ligne
		for(int i=0; i< buffMax.length()+1; i++){
			System.out.print("-");
		}
		System.out.println();
		
		//Impression des espaces avant le nom de table pour le centrer dans la table
		System.out.print("|");
		for(int i=0; i< (buffMax.length()-(usedTable.getTableName().length()+2))/2; i++){
			System.out.print(" ");
		}
		
		//Impression du nom de table
		System.out.print(usedTable.getTableName());
		
		//Impression des espaces après le nom de table pour le centrer dans la table
		for(int i=0; i< (buffMax.length()-(usedTable.getTableName().length()+2))/2+2; i++){
			System.out.print(" ");
		}
		System.out.println("|");
		
		for(int i=0; i< buffMax.length()+1; i++){
			System.out.print("-");
		}
		System.out.println();
		
		//Impression de la ligne de descriptif colonne
		System.out.println(buffMax+"|");
		
		for(int i=0; i< buffMax.length()+1; i++){
			System.out.print("-");
		}
		System.out.println();
		
		for(int i=0; i< usedTable.size(); i++){			
			usedTable.get(i).outputLine(usedTable.getArrCol());		
		}
		System.out.println();

	}

	

	public void setUpTable(ArrayList<Couple> alc){
		int i=0;
		for(i = 0; i<alc.size();i++ ){
			Column c = this.createCol(alc.get(i).getNom(), alc.get(i).getTypeC());
			this.usedTable.addCol(c);
		}
	}
	

	public ArrayList<Couple> construireALSetUp(ArrayList<String> a, ArrayList<Types> l){
		if(a.size()!=l.size()){
			System.out.println("Erreur : Trop de noms de colonne ou trop de types différents, défault d'intégrité");
			return null;
		}
		else
		{
			ArrayList<Couple> alc = new ArrayList<Couple>();
			for(int i=0;i<a.size();i++){
				alc.add(new Couple(a.get(i), l.get(i)));
			}
			return alc;
		}
	}

	public void initialise(String tName){
		Table initTable = new Table(tName);
		this.usedTable = initTable;
	}

	public void fullCreate(String tName, ArrayList<String> colNames, ArrayList<Types> types){
		initialise(tName);
		ArrayList<Couple> buffHM = construireALSetUp(colNames, types);
		setUpTable(buffHM);
	}

	public void fullCreate(String tName, ArrayList<String> colNames, ArrayList<Types> types, ArrayList<Line> l){
		initialise(tName);
		ArrayList<Couple> buffHM = construireALSetUp(colNames, types);
		setUpTable(buffHM);
		ajouterLignes(l);

	}
	public void addColumn(Column c){
		this.usedTable.addCol(c);
	}
	public void removeColumn(Column c){
		this.usedTable.supCol(c);
	}
	public Column getUsedColumn(String name){
		return this.usedTable.getCol(name);
	}
	public void setUsedColumn(Column c){
		this.usedColumn=c;
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
