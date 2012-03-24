package model;
import java.util.ArrayList;

import types.Types;


public class Column extends ArrayList<Types>{
	
	private static final long serialVersionUID = 1437340961489074002L;
	private String label;
	private Types type;
	
	//Propriétés
	private boolean isMandatory;
	private boolean isCandidate;
	private boolean isForeign;
	
	//Contraintes
	private boolean notNull;
	private boolean check;
	private boolean unique;
	
	private Types foreignKey = null;
	private boolean isPrimaryKey;
	
	
	public Column(String label, Types dataType)
	{
		this.label = label;
		this.type = dataType;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Types getType() {
		return type;
	}
	public void setType(Types type) {
		this.type = type;
	}
	
}
