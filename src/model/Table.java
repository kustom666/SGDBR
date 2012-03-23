package model;

import java.util.ArrayList;
import types.Types;
public class Table extends ArrayList<Line> {

	private static final long serialVersionUID = 5771048924784085348L;
	private ArrayList<Column> arrCol = new ArrayList<Column>();
	private String tableName;
	
	/**
	 * Le constructeur de la table nécessite une liste de noms de colonnes et de types de colonnes, ainsi que le nom de la table
	 * @param tName le nom de la table
	 * @param colNames les noms de colomnes 
	 * @param colTypes les types de colomnes
	 * @deprecated
	 * */
	public Table(String tName,ArrayList<String> colNames, ArrayList<Types> colTypes, ArrayList<Line> l)
	{
		this.tableName = tName;
		int i = 0;
		for(i=0; i<colNames.size();i++){
			Column buffCol = new Column(colNames.get(i), colTypes.get(i)); 
			this.arrCol.add(buffCol);
			System.out.println("Ajout d'une colonne de nom" + colNames.get(i));
			
		}
		
		for(i=0; i<l.size(); i++){
			this.add(l.get(i));
		}
	}
	
	public Table(String tName){
		this.tableName = tName;
	}
	
	public Table(String tName, Line addLine){
		this.tableName = tName;
		this.add(addLine);
		System.out.println("Ajout d'une nouvelle line");
	}
	
	public void insert(Line li)
	{
		this.add(li);
		System.out.println("Ajout d'une nouvelle line");

	}
	
	public void supress(Line li)
	{
		this.remove(li);
		System.out.println("Suppression d'une line");

	}
	
	public void update(int index, Line li)
	{
		this.set(index, li);
		System.out.println("Remplacement d'une line");
	}
	
	public void addCol(Column col)
	{
		this.arrCol.add(col);
	}
	
	public void supCol(Column col)
	{
		this.remove(col);
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
	

		
}
