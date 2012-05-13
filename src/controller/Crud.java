package controller;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Date;
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
	
	public Types parseTypeFromString(String aparse){
		Types t = null;
		if(aparse == "Integer")
		{
			t = new Sinteger();
		}
		else if(aparse == "Text")
		{
			t = new Text();
		}
		else if(aparse == "Float")
		{
			t = new SFloat();
		}
		else if(aparse == "Date")
		{
			t = new SDate();
		}
		else if(aparse == "Char")
		{
			t = new SChar();
		}
		else if(aparse == "Byte")
		{
			t = new SBit();
		}
		
		return t;
	}
	
	public String importFile(String Filename){
		String output = new String("");
		String buffer = new String("");;
		
		try {
			FileReader fis = new FileReader(Filename);
			BufferedReader das = new BufferedReader(fis);
			while((buffer = das.readLine())!= null){
				output+= buffer + "\n";
			}
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier "+Filename+" n'a pas été trouvé, arrêt de l'importation");
		} catch (IOException e) {
			System.out.println("Erreur d'entrée sortie sur le fichier "+Filename+", arrêt de l'importation");
		}
		return output;
	}
	
	public void importFromCSV(String Filename){

		String contenucsv = importFile(Filename);
		
		String splitted[];
		
		splitted = contenucsv.split("[ \n]");
		
		Table buffT = new Table(splitted[0]);
		System.out.println(splitted[0]);
		
		String columns[] = splitted[1].split(";");
		
		for(String s : columns){
			String colcreate[] = s.split(":");
			Instruction is = new Instruction();
			Column buffc = null;
			try {
				buffc = new Column(colcreate[0],is.toType(colcreate[1]));
			} catch (TypeException e) {
				System.out.println("Problème de type dans l'import. Arrèt!");
			}
			if(colcreate[2] == "True")
			{
				buffc.setMandatory(true);
			}
			else if(colcreate[2] == "False")
			{
				buffc.setMandatory(false);
			}
			
			if(colcreate[3] == "True")
			{
				buffc.setCandidate(true);
			}
			else if(colcreate[3] == "False")
			{
				buffc.setCandidate(false);
			}
			
			if(colcreate[4] == "True")
			{
				buffc.setForeign(true);
			}
			else if(colcreate[4] == "False")
			{
				buffc.setForeign(false);
			}
			
			if(colcreate[5] == "True")
			{
				buffc.setNotNull(true);
			}
			else if(colcreate[5] == "False")
			{
				buffc.setNotNull(false);
			}
			
			if(colcreate[6] == "True")
			{
				buffc.setUnique(true);
			}
			else if(colcreate[6] == "False")
			{
				buffc.setUnique(false);
			}
			
			if(colcreate[7] == "True")
			{
				buffc.setPrimaryKey(true);
			}
			else if(colcreate[7] == "False")
			{
				buffc.setPrimaryKey(false);
			}
			
			buffT.addCol(buffc);
		}
		
		for(int j=2; j<splitted.length; j++){
			String items[] = splitted[j].split(";");
			Line buffl = new Line();
	
			for(int f = 0; f<items.length; f++){
				Column buffarcol = buffT.getArrCol().get(f);
				if(buffarcol.getType().typeToString() == "SInteger"){
					buffl.add(f, new Sinteger(Integer.parseInt(items[f])));
				}
				if(buffarcol.typeToString()=="SFloat"){
					buffl.add(f, new SFloat(Float.parseFloat(items[f])));
				}
				if(buffarcol.typeToString()=="SDate"){
					String date[] = items[f].split("/");
					buffl.add(f, new SDate(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2])));
				}
				if(buffarcol.typeToString()=="SChar"){
					char buffchar[] = items[f].toCharArray();
					buffl.add(f, new SChar(buffchar, items[f].length()));
				}
				if(buffarcol.typeToString()=="Text"){
					buffl.add(f, new Text(items[f]));
				}
				if(buffarcol.typeToString()=="Byte"){
					buffl.add(f, new SBit(items[f].getBytes()));
				}
				/*buffl.add(new Sinteger(Integer.parseInt(items[f])));*/
			}
			buffT.add(buffl);
		}
		this.usedTable = buffT;
		this.displayTable();
	}
	public void displayTable()
	{
		String buffMax = new String();
		
		//BuffMax contiens tous les caractères de la ligne contenant les descriptifs de colonne
		for(int i = 0; i< usedTable.getArrCol().size(); i++){
			buffMax+= "| "+usedTable.getArrCol().get(i).getLabel() + ":" +usedTable.getArrCol().get(i).getType().typeToString()+"";
			
			if(usedTable.getArrCol().get(i).isMandatory() == true)
			{
				buffMax+=": Mandatory";
			}
			
			if(usedTable.getArrCol().get(i).isCandidate() == true)
			{
				buffMax+=": Candidate";
			}
			if(usedTable.getArrCol().get(i).isCheck() == true)
			{
				buffMax+=": Check";
			}
			if(usedTable.getArrCol().get(i).isForeign() == true)
			{
				buffMax+=": Foreign";
			}
			if(usedTable.getArrCol().get(i).isNotNull() == true)
			{
				buffMax+=": Not Null";
			}
			if(usedTable.getArrCol().get(i).isPrimaryKey() == true)
			{
				buffMax+=": Primary";
			}
			if(usedTable.getArrCol().get(i).isUnique() == true)
			{
				buffMax+=": Unique";
			}
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
