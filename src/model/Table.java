package model;

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
}
