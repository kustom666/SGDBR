package model;
import java.util.HashMap;

public class Column extends HashMap<Integer, Object>{
	
	private String label;
	private Object type;
	private Integer index;
	
	public Column(String label)
	{
		this.label = label;
		this.index = new Integer(0);
	}
	
	public Column(String label, Object dataType)
	{
		this.label = label;
		this.type = dataType;
		this.index = new Integer(0);
	}
	
}
