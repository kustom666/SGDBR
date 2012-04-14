package controller;
import java.util.ArrayList;
import java.util.regex.Pattern;


import model.Column;
import model.Line;
import model.Table;
import types.SChar;
import types.SDate;
import types.Sinteger;
import types.SFloat;
import types.Text;
import types.Types;

public class Instruction {
	private Crud temp=new Crud();

	public Instruction() {

	}

	public Table CreateTable(String requete){
		String[] tab= null;
		String nomTable = new String();
		String tp=new String();
		ArrayList<Types> colTypes=new ArrayList<Types>();
		ArrayList<String> colNames=new ArrayList<String>();

		tab=requete.split("CREATE TABLE ");
		for (int i=0;i<tab.length;i++){
			tp=tab[i];
		}

		tp=tp.replace("(","");
		tp=tp.replace(", ",",");
		tp=tp.replace(" ,",",");
		tab=tp.split("[^a-zA-Z0-9]");
		nomTable = tab[0];

		for (int i=1;i<tab.length;i++){
			if(i%2==0){
				try {
					colTypes.add(toType(tab[i]));
				} catch (TypeException e) {
					e.printStackTrace();
				}
			}
			else{
				colNames.add(tab[i]);
			}
		}
		temp.fullCreate(nomTable,colNames,colTypes);
		return temp.getUsedTable();
	}

	public Table SelectFrom(String requete){

		return temp.getUsedTable();
	}
	
	public Table AlterTable(String requete){
		String tp=null;
		String[] tab= null;
		Column col = null;

		tp=requete.replace("ALTER TABLE ","");
		tab=tp.split("[^a-zA-Z0-9]");

		for(int i=0;i<tab.length;i++){
			tab[i].trim();
		}

		if(tab[1].equalsIgnoreCase("ADD")){
			try {
				col=new Column(tab[2],toType(tab[3]));
			} catch (TypeException e) {
				e.printStackTrace();
			}
			temp.getUsedTable().addCol(col);
		}
		else if(tab[1].equalsIgnoreCase("DROP")){
			col=temp.getUsedTable().getCol(tab[2]);
			temp.getUsedTable().supCol(col);
		}
		else if(tab[1].equalsIgnoreCase("CHANGE")){
			try {
				temp.getUsedTable().getCol(tab[2]).setType(toType(tab[4]));
			} catch (TypeException e) {
				e.printStackTrace();
			}
			temp.getUsedTable().getCol(tab[2]).setLabel(tab[3]);
		}
		else if(tab[1].equalsIgnoreCase("MODIFY")){
			try {
				temp.getUsedTable().getCol(tab[2]).setType(toType(tab[3]));
			} catch (TypeException e) {
				e.printStackTrace();
			}
		}
		return temp.getUsedTable();
	}

	public Table InsertInto(String requete){
		String[] tab=null;
		String nomTable;
		ArrayList<Column> colName = new ArrayList<Column>();
		ArrayList<String> values = new ArrayList<String>();
		int i=1;

		requete=requete.replace("INSERT INTO ","");
		requete=requete.replace("(","");
		tab=requete.split("[^a-zA-Z0-9.]");
		nomTable=tab[0];

		for(int j=0;j<tab.length;j++){
			tab[j].trim();
		}

		Line l=new Line();
		if(nomTable.equals(temp.getUsedTable().getTableName())){
			int j;

			for(i=1; !tab[i].equals("VALUES"); ++i){
				colName.add(temp.getUsedTable().getCol(tab[i]));
			}
			for(i=i+1; i<tab.length; ++i){
				values.add(tab[i]);
			}
			if(colName.containsAll(temp.getUsedTable().getArrCol())){
				for(int iter=0;iter<values.size();iter++){
					try {
						l.add(initLine(values.get(iter)));
					} catch (TypeException e) {
						e.printStackTrace();
					}
				}
			}
			else if(!colName.containsAll(temp.getUsedTable().getArrCol())){
				for(int iter=0;iter<temp.getUsedTable().getArrCol().size();iter++){
					if(colName.contains(temp.getUsedTable().getArrCol().get(iter))){
						for(j=0;j<values.size();++j){
							try {
								l.add(initLine(values.get(j)));
							} catch (TypeException e) {
								e.printStackTrace();
							}
						}
					}
					else{
						l.add(new Text("NULL"));
					}
				}
			}
		}
		temp.getUsedTable().insert(l);
		return temp.getUsedTable();
	}

	public Table Update(String requete){
		
		return temp.getUsedTable();
	}
	public Table DeleteFrom(String requete){
		return temp.getUsedTable();
	}

	public Types toType(String requete)throws TypeException{
		Types type = null;
		if(requete.equalsIgnoreCase("int")){
			type=new Sinteger();
		}
		else if(requete.equalsIgnoreCase("float")){
			type= new SFloat();
		}
		else if(requete.equalsIgnoreCase("date")){
			type= new SDate();
		}
		else if(requete.equalsIgnoreCase("char")){
			type= new SChar();
		}
		else if(requete.equalsIgnoreCase("text")){
			type= new Text();
		}
		else {
			throw new TypeException(requete);
		}
		return type;
	}
	public Types initLine(String val) throws TypeException{
		Types type = null;

		Pattern Char=Pattern.compile("^[A-Za-z]$");
		Pattern Date=Pattern.compile("^[0-9]{4} [0-9]{2} [0-9]{2}$");
		Pattern Float=Pattern.compile("^[0-9]*\\.[0-9]*$");
		Pattern Sinteger=Pattern.compile("^[0-9]*$");
		Pattern Text=Pattern.compile("^[a-zA-Z]$");


		boolean reponseChar =Char.matcher(val).matches();
		boolean reponseDate =Date.matcher(val).matches();
		boolean reponseFloat =Float.matcher(val).matches();
		boolean reponseSinteger =Sinteger.matcher(val).matches();
		boolean reponseText= Text.matcher(val).matches();

		if(reponseSinteger){
			type=new Sinteger(Integer.parseInt(val));
		}
		else if(reponseFloat){
			type= new SFloat(java.lang.Float.parseFloat(val));
		}
		else if(reponseDate){
			String[] tab=val.split(" ");
			type= new SDate(Integer.parseInt(tab[0]),Integer.parseInt(tab[1]),Integer.parseInt(tab[2]));
		}
		else if(reponseChar){
			type= new SChar(val.toCharArray(),val.charAt(0));
		}
		else if(reponseText){
			type= new Text(val);
		}
		else{
			throw new TypeException(val);
		}
		return type;
	}
}


