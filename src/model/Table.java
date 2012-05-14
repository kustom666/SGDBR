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
public class Table extends ArrayList<Line> {

	private static final long serialVersionUID = 5771048924784085348L;
	private ArrayList<Column> arrCol = new ArrayList<Column>();
	private String tableName;

	public Table(String tName){
		this.tableName = tName;
	}

	/**
	 * @deprecated
	 * */
	public Table(String tName, Line addLine){
		this.tableName = tName;
		this.add(addLine);
	}

	public void insert(Line li)
	{
		this.add(li);
	}

	public void supress(Line li)
	{
		this.remove(li);

	}

	public void update(int index, Line li)
	{
		this.set(index, li);
	}

	public void addCol(Column col)
	{
		this.arrCol.add(col);
	}

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

	public Column getCol(String name){
		Column c =null;
		for(int i=0;i<this.arrCol.size();i++){
			if(this.arrCol.get(i).getLabel().equals(name)){
				c=this.arrCol.get(i);
			}
		}
		return c;
	}
	
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
