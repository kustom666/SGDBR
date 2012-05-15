package model;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import types.Types;

/**
 * La classe table représente une table en mémoire. Ensemble d'une collection de lignes pour le stockage de données, et de colonnes pour l'intégrité
 * et le traitement des données.
 * 
 * @author Paul Forti
 * */
public class Table extends ArrayList<Line> {

	private static final long serialVersionUID = 5771048924784085348L;
	private ArrayList<Column> arrCol = new ArrayList<Column>();
	private String tableName;

	/**
	 * Le constructeur de table se contente d'un string contenant le nom de la table pour la créer
	 * @param tName le nom de la table
	 * @author Paul Forti
	 * */
	public Table(String tName){
		this.tableName = tName;
	}

	/**
	 * La méthode insert ajoute une ligne à la table
	 * @param li la ligne à ajouter
	 * @author Paul Forti
	 * */
	public void insert(Line li)
	{
		this.add(li);
	}
	
	/**
	 * La méthode suppress retire une ligne à la table
	 * @param li la ligne à supprimer
	 * @author Paul Forti
	 * */
	public void supress(Line li)
	{
		this.remove(li);

	}

	/**
	 * La méthode update remplace une ligne par une autre dans la table
	 * @param index l'index de la ligne à modifier
	 * @param li la ligne à insérer à la place de l'originale
	 * @author Paul Forti
	 * */
	public void update(int index, Line li)
	{
		this.set(index, li);
	}

	/**
	 * La méthode addCol ajoute une colonne à la table
	 * @param col la ligne à ajouter
	 * @author Paul Forti
	 * */
	public void addCol(Column col)
	{
		this.arrCol.add(col);
	}

	/**
	 * La méthode supCol supprime une colonne de la table
	 * @param col la colonne à supprimer
	 * @author Paul Forti
	 * */
	public void supCol(Column col)
	{
		this.arrCol.remove(col);
	}

	//Accesseurs
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public ArrayList<Column> getArrCol() {
		return arrCol;
	}

	public void setArrCol(ArrayList<Column> arrCol) {
		this.arrCol = arrCol;
	}

	/**
	 * La méthode getCol récupère et retourne la colonne possédant le nom voulu
	 * @param name le nom de colonne
	 * @author Paul Forti
	 * */
	public Column getCol(String name){
		Column c =null;
		for(int i=0;i<this.arrCol.size();i++){
			if(this.arrCol.get(i).getLabel().equals(name)){
				c=this.arrCol.get(i);
			}
		}
		return c;
	}
	
	/**
	 * La méthode saveTable permet de sauvegarder une table dans un fichier précis 
	 * @param filename le chemin du fichier dans lequel sauvegarder
	 * @author Paul Forti
	 * */
	public void saveTable(String filename){
		BufferedWriter output;
		try {
			output = new BufferedWriter(new FileWriter(filename));
			
			output.write(this.tableName+"\n");
			
			for(int i = 0; i< this.arrCol.size(); i++){
				output.write(this.arrCol.get(i).getLabel()+":"+this.arrCol.get(i).getType().typeToString()+this.arrCol.get(i).colProp()+";");
			}
			
			output.write("\n");

			for(int i=0;i<this.size();i++){
				output.write(this.get(i).outputLineToString().replace("|", "").replaceFirst(";", ""));
				
			}
			
			output.close();
		} catch (FileNotFoundException e) {
			System.out.println("Erreur : impossible d'accéder au fichier " +  filename +" en écriture. Sauvegarde de la table annulée");
		} catch (IOException e) {
			System.out.println("Erreur : le fichier "+filename+" est accessible, mais un bug à empéché l'écriture dans ce fichier. Sauvegarde de la table annulée");
		}
		
	}
	
}
