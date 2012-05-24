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
/**
 * La classe Crud est la classe la plus importante dans la gestion de la base de donnée.
 * Elle permet d'effectuer toutes les actions accessibles par le language SQL sur la table actuellement contrôlée
 * @author Paul Forti
 * 
 * */
public class Crud {

	private Table usedTable;
	private Column usedColumn;
	/**
	 * Le constructeur de base Crud() se contente de créer le contrôleur.
	 * @author Kustom
	 * */
	public Crud()
	{

	}

	/**
	 * Le constructeur de crud le plus utilisé. Il permet de créer le contrôleur tout en lui alouant une table
	 * @param t la table à allouer
	 * @author Kustom
	 * */
	public Crud(Table t)
	{
		this.usedTable = t;
	}

	/**
	 * La méthode createCol permet de créer une colonne et de la récupérer via le retour
	 * @param n le label de la colonne
	 * @param t le type de l'information stockée
	 * @return c la colonne crée
	 * @author Paul Forti
	 * 
	 * */
	public Column createCol(String n, Types t){
		Column c = new Column(n, t);
		return c;
	}
	/**
	 * La méthode ajouterLignes permet d'ajouter une séquence de lignes à la table actuellement contrôlée 
	 * @param l Une liste de lignes à ajouter
	 * @author Paul Forti
	 * */
	public void ajouterLignes(ArrayList<Line> l){
		for(int i=0; i< l.size();i++){
			this.usedTable.insert(l.get(i));
		}
	}

	/**
	 * La méthode importFile permet d'importer le contenu d'un fichier texte (encodé en UTF-8) et de le stocker dans un string qui sera retourné.
	 * Les caractères de saut de ligne sont corrigés par cette méthode
	 * @param Filename le chemin d'accès du fichier à ouvrir
	 * @return output le String contenant le contenu du fichier importé
	 * @author Paul Forti
	 * */
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
	/**
	 * La méthode importFromCSV permet d'importer une table directement depuis un fichier de sauvegarde et de la placer en tant que table contrôlée
	 * @param Filename le chemin d'accès du fichier à importer
	 * @author Paul Forti
	 * */
	public void importFromCSV(String Filename){

		String contenucsv = importFile(Filename);

		String splitted[];

		splitted = contenucsv.split("[ \n]");

		Table buffT = new Table(splitted[0]);
		System.out.println(splitted[0]);

		String columns[] = splitted[1].split(";");

		for(String s : columns){
			String colcreate[] = s.split(":");
			ArrayList<String> alprops = new ArrayList<String>();
			Instruction is = new Instruction();
			Column buffc = null;
			try {
				buffc = new Column(colcreate[0],is.toType(colcreate[1]));
			} catch (TypeException e) {
				System.out.println("Problème de type dans l'import. Arrèt!");
			}
			for(int cont = 2; cont<colcreate.length;cont++){
				alprops.add(colcreate[cont]);
			}

			if(alprops.contains("Mandatory"))
			{
				buffc.setMandatory(true);
			}
			else if(!alprops.contains("Mandatory"))
			{
				buffc.setMandatory(false);
			}
			if(alprops.contains("Candidate"))
			{
				buffc.setCandidate(true);
			}
			else if(!alprops.contains("Candidate"))
			{
				buffc.setCandidate(false);
			}
			if(alprops.contains("Foreign"))
			{
				buffc.setForeign(true);
			}
			else if(!alprops.contains("Foreign"))
			{
				buffc.setForeign(false);
			}
			if(alprops.contains("Primary"))
			{
				buffc.setPrimaryKey(true);
			}
			else if(!alprops.contains("Primary"))
			{
				buffc.setPrimaryKey(false);
			}
			if(alprops.contains("NotNull"))
			{
				buffc.setNotNull(true);
			}
			else if(!alprops.contains("NotNull"))
			{
				buffc.setNotNull(false);
			}
			if(alprops.contains("Unique"))
			{
				buffc.setUnique(true);
			}
			else if(!alprops.contains("Unique"))
			{
				buffc.setUnique(false);
			}
			buffT.addCol(buffc);
		}

		for(int j=2; j<splitted.length; j++){
			String items[] = splitted[j].split(";");
			Line buffl = new Line();

			for(int f = 0; f<items.length; f++){
				Column buffarcol = buffT.getArrCol().get(f);
				if(buffarcol.getType().typeToString() == "int"){
					if(items[f].equalsIgnoreCase("NULL")){
						buffl.add(f, new Text(items[f]));
					}
					else{
						buffl.add(f, new Sinteger(Integer.parseInt(items[f])));
					}
				}
				if(buffarcol.getType().typeToString()=="float"){
					if(items[f].equalsIgnoreCase("NULL")){
						buffl.add(f, new Text(items[f]));
					}
					else{
						buffl.add(f, new SFloat(Float.parseFloat(items[f])));
					}
				}
				if(buffarcol.getType().typeToString()=="date"){
					if(items[f].equalsIgnoreCase("NULL")){
						buffl.add(f, new Text(items[f]));
					}
					else{
						String date[] = items[f].split("/");
						buffl.add(f, new SDate(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2])));
					}
				}
				if(buffarcol.getType().typeToString()=="char"){
					if(items[f].equalsIgnoreCase("NULL")){
						buffl.add(f, new Text(items[f]));
					}
					else{
						char buffchar[] = items[f].toCharArray();
						buffl.add(f, new SChar(buffchar, items[f].length()));
					}
				}
				if(buffarcol.getType().typeToString()=="text"){
					buffl.add(f, new Text(items[f]));
				}
				if(buffarcol.getType().typeToString()=="byte"){
					if(items[f].equalsIgnoreCase("NULL")){
						buffl.add(f, new Text(items[f]));
					}
					else{
						buffl.add(f, new SBit(items[f].getBytes()));
					}
				}
				/*buffl.add(new Sinteger(Integer.parseInt(items[f])));*/
			}
			buffT.add(buffl);
		}
		this.usedTable = buffT;
		this.displayTable();
	}

	/**
	 * La méthode displayTable permet d'écrire une représentation textuelle de la table actuellement contrôlée sur la sortie système
	 * @author Paul Forti
	 * */
	public void displayTable()
	{
		String buffMax = new String();

		//BuffMax contiens tous les caractères de la ligne contenant les descriptifs de colonne
		for(int i = 0; i< usedTable.getArrCol().size(); i++){
			buffMax+= "| "+usedTable.getArrCol().get(i).getLabel() + ":" +usedTable.getArrCol().get(i).getType().typeToString()+"";

			if(usedTable.getArrCol().get(i).isMandatory() == true)
			{
				buffMax+=":Mandatory";
			}

			if(usedTable.getArrCol().get(i).isCandidate() == true)
			{
				buffMax+=":Candidate";
			}
			if(usedTable.getArrCol().get(i).isCheck() == true)
			{
				buffMax+=":Check";
			}
			if(usedTable.getArrCol().get(i).isForeign() == true)
			{
				buffMax+=":Foreign";
			}
			if(usedTable.getArrCol().get(i).isNotNull() == true)
			{
				buffMax+=":Not Null";
			}
			if(usedTable.getArrCol().get(i).isPrimaryKey() == true)
			{
				buffMax+=":Primary";
			}
			if(usedTable.getArrCol().get(i).isUnique() == true)
			{
				buffMax+=":Unique";
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


	/**
	 * La méthode setUpTable permet de mettre en place la table à créer grâce à un couple de nom et de type de colonne
	 * @param alc l'array list de coulple nom/type
	 * @see Couple
	 * @see construireALSetUp
	 * @author Paul Forti
	 * */
	public void setUpTable(ArrayList<Couple> alc){
		int i=0;
		for(i = 0; i<alc.size();i++ ){
			Column c = this.createCol(alc.get(i).getNom(), alc.get(i).getTypeC());
			this.usedTable.addCol(c);
		}
	}

	/**
	 * La méthode construireALSetUp construit un arrayList composé de couples de nom et de type de colonnes à partir de deux array list différents.
	 * @param a l'ArrayList contenant les noms de toutes les colonnes à créer
	 * @param l l'ArrayList contenant les types de toutes les colonnes à créer
	 * @see Couple
	 * @author Paul Forti
	 * */
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

	/**
	 * La méthode initialise permet de créer une table et de l'allouer au contrôleur à l'aide de son seul nom
	 * @param tName le nom de la table
	 * @author Paul Forti
	 * */
	public void initialise(String tName){
		Table initTable = new Table(tName);
		this.usedTable = initTable;
	}

	/**
	 * La méthode fullCreate permet de mettre en place une table de façon correcte et de l'allouer au contrôleur 
	 * @param tName le nom de la table
	 * @param colNames l'array list contenant le nom des colonnes
	 * @param types l'array list contenant les types des colonnes
	 * @author Paul Forti
	 * */
	public void fullCreate(String tName, ArrayList<String> colNames, ArrayList<Types> types){
		initialise(tName);
		ArrayList<Couple> buffHM = construireALSetUp(colNames, types);
		setUpTable(buffHM);
	}

	/**
	 * Méthode surchargée de fullCreate, ajoutant des lignes à insérer dans la table
	 * @param tName le nom de la table
	 * @param colNames l'array list contenant le nom des colonnes
	 * @param types l'array list contenant les types des colonnes
	 * @param l l'array list contenant toutes les lignes à ajouter
	 * @see fullCreate
	 * @author Paul Forti
	 * */
	public void fullCreate(String tName, ArrayList<String> colNames, ArrayList<Types> types, ArrayList<Line> l){
		initialise(tName);
		ArrayList<Couple> buffHM = construireALSetUp(colNames, types);
		setUpTable(buffHM);
		ajouterLignes(l);

	}

	/**
	 * La méthode addColumn permet d'ajouter une colonne à la table actuellement contrôlée
	 * @param c la colonne à ajouter
	 * @author Paul Forti
	 * */
	public void addColumn(Column c){
		this.usedTable.addCol(c);
	}
	/**
	 * La méthode remove permet de supprimer une colonne de la table actuellement contrôlée
	 * @param c la colonne à supprimer
	 * @author Paul Forti
	 * */
	public void removeColumn(Column c){
		this.usedTable.supCol(c);
	}
	public Column getUsedColumn(String name){
		return this.usedTable.getCol(name);
	}
	public void setUsedColumn(Column c){
		this.usedColumn=c;
	}

	public void setUsedTable(Table t)
	{
		this.usedTable = t;
	}
	public Table getUsedTable(){
		return this.usedTable;
	}

}
