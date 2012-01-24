package model;

import java.util.HashMap;
import java.lang.Integer;
public class Table<indexO, columns> extends HashMap<Integer, Column> {
	
	private Integer index;
	
	public Table()
	{
		this.index = new Integer(0);
	}
	
	public Table(String[] columnNames)
	{
		this.index = new Integer(0);
		this.create(columnNames, index);
	}
	
	private void create(String[] columnNames, Integer indexRow)
	{
		for (String s : columnNames)
		{
			System.out.println("Création d'une colonne avec un label : "+s);
			Column buffCol = new Column(s);
			this.put(indexRow, buffCol);
		}
	}
	
	public void add(Object abstractType, Integer indexRow)
	{
		Column buffCol = this.get(indexRow);
	}
}
