package model;
import java.util.ArrayList;

import types.Types;

/**
 * La classe Column modélise une colonne, qui est en fait un point de repère permettant de contrôler et déterminer l'intégrité de la table.
 * Aucune donnée pure n'est stockée grâce à cette classe
 * @author Paul Forti
 * */
public class Column{

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


	/**
	 * Ce constructeur de colonne est surchargé à partir du constructeur de base. Il permet de créer la colonne tout en lui allouant un type
	 * @param label le label de la colonne
	 * @param dataType le type de colonne
	 * @author Paul Forti
	 * */
	public Column(String label, Types dataType)
	{
		this.label = label;
		this.type = dataType;
	}
	/**
	 * Le constructeur de base de la colonne. Il suffit de fournir un label
	 * @param label le label de la colonne
	 * @author Paul Forti
	 * */
	public Column(String label) {
		this.label=label;
	}
	
	/**
	 * La méthode colProp permet de retourner un string contenant toutes les propriétés de la colonne sous forme de True/False
	 * @author Paul Forti
	 * */
	public String colProp()
	{
		String buff = new String();
		if(this.isMandatory())
		{
			buff+=":True";
		}
		
		if(!this.isMandatory())
		{
			buff+=":False";
		}
		
		
		if(this.isCandidate())
		{
			buff+=":True";
		}
		
		if(!this.isCandidate())
		{
			buff+=":False";
		}
		
		
		if(this.isForeign())
		{
			buff+=":True";
		}
		
		if(!this.isForeign())
		{
			buff+=":False";
		}
		
		
		if(!this.isNotNull())
		{
			buff+=":True";
		}
		
		if(this.isNotNull())
		{
			buff+=":False";
		}
		
		
		if(this.isUnique())
		{
			buff+=":True";
		}
		
		if(!this.isUnique())
		{
			buff+=":False";
		}
		
		
		if(this.isPrimaryKey())
		{
			buff+=":True";
		}
		
		if(!this.isPrimaryKey())
		{
			buff+=":False";
		}
		
		return buff;
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
