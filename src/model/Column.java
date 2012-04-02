package model;
import java.util.ArrayList;

import types.Types;


public class Column extends ArrayList<Types>{

	private static final long serialVersionUID = 1437340961489074002L;
	private String label;
	private Types type;

	//Propri�t�s
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

	public Column(String label) {
		this.label=label;
	}

	//Accesseurs
	public boolean isMandatory() {
		return isMandatory;
	}
	public void setMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
	}
	public boolean isCandidate() {
		return isCandidate;
	}
	public void setCandidate(boolean isCandidate) {
		this.isCandidate = isCandidate;
	}
	public boolean isForeign() {
		return isForeign;
	}
	public void setForeign(boolean isForeign) {
		this.isForeign = isForeign;
	}
	public boolean isNotNull() {
		return notNull;
	}
	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	public boolean isUnique() {
		return unique;
	}
	public void setUnique(boolean unique) {
		this.unique = unique;
	}
	public Types getForeignKey() {
		return foreignKey;
	}
	public void setForeignKey(Types foreignKey) {
		this.foreignKey = foreignKey;
	}
	public boolean isPrimaryKey() {
		return isPrimaryKey;
	}
	public void setPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
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
