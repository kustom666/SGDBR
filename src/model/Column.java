package model;
import java.util.ArrayList;

public class Column extends ArrayList{
	
	private String label;
	private Object type;
	
	public Column(String label)
	{
		this.label = label;
	}
	
	public Column(String label, Object dataType)
	{
		this.label = label;
		this.type = dataType;
	}
}
