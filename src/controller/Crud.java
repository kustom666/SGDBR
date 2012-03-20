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
		System.out.println("--------------------");

		System.out.println("| "+usedTable.getTableName()+" |");
		System.out.println("--------------------");
		for(int i=0; i<usedTable.getArrCol().size(); i++){
			System.out.print("| "+this.usedTable.getArrCol().get(i).getLabel()+" ");
		}
		System.out.println("|");
		System.out.println("--------------------");
		for(int i=0; i< usedTable.size(); i++){
			
			usedTable.get(i).outputLine();
			
		}
		System.out.println("|");
		System.out.println("--------------------");

	}
}
