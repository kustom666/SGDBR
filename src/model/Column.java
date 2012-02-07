package model;
import java.util.ArrayList;


public class Column extends ArrayList<Types>{
	
	private static final long serialVersionUID = 1437340961489074002L;
	private String label;
	private Types type;
	
	public Column(String label)
	{
		this.label = label;
	}
	
	public Column(String label, Types dataType)
	{
		this.label = label;
		this.type = dataType;
	}
	
}
