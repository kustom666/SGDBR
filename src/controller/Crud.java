package controller;
import model.*;
import types.*;

public class Crud {
	
	private Table usedTable;
	
	public Crud()
	{
		
	}
	
	public Crud(Table t)
	{
		this.usedTable = t;
	}
	
	public void setUsedTable(Table t)
	{
		this.usedTable = t;
	}
	
	public void displayTable()
	{
		System.out.println("|          "+usedTable.getTableName()+"          |");
		System.out.println("--------------------");
		System.out.println("||");
		System.out.println("--------------------");

	}
}
