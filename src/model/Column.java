package model;
import java.util.ArrayList;


public class Column extends ArrayList<Types>{
	
	private String label;
	private Types type;
	private Integer index;
	
	public Column(String label)
	{
		this.label = label;
		this.index = new Integer(0);
	}
	
	public Column(String label, Types dataType)
	{
		this.label = label;
		this.type = dataType;
		this.index = new Integer(0);
	}
	
}
