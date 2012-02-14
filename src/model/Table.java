package model;

import java.util.ArrayList;
import types.Types;
public class Table extends ArrayList<Line> {

	private static final long serialVersionUID = 5771048924784085348L;
	private ArrayList<Column> arrCol;

	public Table(String[] columnNames, ArrayList<Types> listType)
	{
		int i = 0;
		for(String s : columnNames){
			Column buffCol = new Column(s, listType.get(i));
			arrCol.add(buffCol);
			i++;
			System.out.println("Ajout d'une colonne ayant pour label : " + s);
		}
	}
	public void insert(Line li)
	{
		this.add(li);
	}
	public void supress(Line li)
	{
		this.remove(li);
	}
}
