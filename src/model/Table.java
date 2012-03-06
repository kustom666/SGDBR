package model;

import java.util.ArrayList;
import types.Types;
public class Table extends ArrayList<Line> {

	private static final long serialVersionUID = 5771048924784085348L;
	private ArrayList<Line> arrLines;
	private ArrayList<Column> arrCol;
	private String tableName;

	public Table(ArrayList<String> colNames, ArrayList<Types> colTypes )
	{
		int i = 0;
		for(i=0; i<colNames.size();i++){
			Column buffCol = new Column(colNames.get(i), colTypes.get(i)); 
			this.arrCol.add(buffCol);
			System.out.println("Ajout d'une colonne de nom" + colNames.get(i));
		}
	}
	public Table(String tName){
		this.tableName = tName;
	}
	public Table(String tName, Line addLine){
		this.tableName = tName;
		this.arrLines.add(addLine);
		System.out.println("Ajout d'une nouvelle line");
	}
}
