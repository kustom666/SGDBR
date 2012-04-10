package controller;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import model.Column;
import model.Line;
import model.Table;
import types.Date;
import types.Sinteger;
import types.SFloat;
import types.Text;
import types.Types;
import types.Char;

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
						colTypes.add(toType(tab[i]));
					}
					else{
						colNames.add(tab[i]);
					}
				}
				temp.fullCreate(nomTable,colNames,colTypes);
				Table te = temp.getUsedTable();
				return temp.getUsedTable();
			}

	public Table SelectFrom(String requete){

		return temp.getUsedTable();
			}
	public Table AlterTable(String requete){
		String tp=null;
		String[] tab= null;
		Column col;

		tp=requete.replace("ALTER TABLE ","");
		tab=tp.split("[^a-zA-Z0-9]");
		temp.initialise(tab[0]);
		temp.getUsedTable();

		if(tab[1].equalsIgnoreCase("ADD")){
			col=new Column(tab[2],toType(tab[3]));
			temp.addColumn(col);
		}
		else if(tab[1].equalsIgnoreCase("DROP")){
			col=new Column(tab[2]);
			temp.removeColumn(col);
		}
		else if(tab[1].equalsIgnoreCase("CHANGE")){
			col=new Column(tab[3],toType(tab[4]));
			temp.getUsedColumn(tab[2]);
			temp.setUsedColumn(col);
		}
		else if(tab[1].equalsIgnoreCase("MODIFY")){
			temp.getUsedColumn(tab[2]).setType(toType(tab[3]));
		}
		return temp.getUsedTable();
		}

	public Table InsertInto(String requete){
		String[] tab=null;
		String nomTable;
		String tp=null;
		ArrayList<Line> values = new ArrayList<Line>();
		ArrayList<Column> colName = new ArrayList<Column>();
		ArrayList<String> val=new ArrayList<String>();

		requete=requete.replace("INSERT INTO ","");
		requete=requete.replace("(","");
		tab=requete.split("[^a-zA-Z0-9]");
		nomTable=tab[0];

		if(nomTable.equals(temp.getUsedTable().getTableName())){
		//temp.initialise(nomTable);
		//temp.getUsedTable();
		for(int i=1;i<tab.length;i++){
			if(!tab[i].equalsIgnoreCase("VALUES")){
			colName.add(temp.getUsedTable().getCol(tab[i]));
			}
			else{
				for(int j=i+1;j<tab.length;j++){
					val.add(tab[j]);
				}
			}
		}
		for (int i=0;i<colName.size();i++){
			for (int j=i;j<val.size();j++){
				if(temp.getUsedTable().getArrCol().contains(colName.get(i))){

					Line l=new Line();
					Types type=initLine(val.get(j));
					l.add(type);
					values.add(l);
				}
				else{
					System.out.println("La colonne "+colName.get(i)+" n'existe pas\n");
				}
			}
		}
		temp.ajouterLignes(values);
		return temp.getUsedTable();
	}
		else{
			System.out.println("La table "+nomTable+" n'existe pas \n");
			return null;
		}
	}

	public Table Update(String requete){
		return temp.getUsedTable();
	}
	public Table DeleteFrom(String requete){
		return temp.getUsedTable();
	}

	public Types toType(String requete){
		Types type = null;
		if(requete.equalsIgnoreCase("int")){
			type=new Sinteger();
		}
		else if(requete.equalsIgnoreCase("float")){
			type= new SFloat();
		}
		else if(requete.equalsIgnoreCase("date")){
			type= new Date();
		}
		else if(requete.equalsIgnoreCase("char")){
			type= new Char();
		}
		else if(requete.equalsIgnoreCase("text")){
			type= new Text();
		}
		return type;
	}
	public Types initLine(String val){
		Types type=null;
		Pattern bit=Pattern.compile("[true-false]");
		Pattern Char=Pattern.compile("^[A-Za-z]$");
		Pattern Date=Pattern.compile("^[0-9]{4} [0-9]{2} [0-9]{2}$");
		Pattern Float=Pattern.compile("^[0-9]*\\.[0-9]*$");
		Pattern Sinteger=Pattern.compile("^[0-9]$*");
		Pattern Text=Pattern.compile("^[a-zA-Z]$");

		boolean reponseBit =bit.matcher(val).matches();
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
			type= new Date(Integer.parseInt(tab[0]),Integer.parseInt(tab[1]),Integer.parseInt(tab[2]));
		}
		else if(reponseChar){
			type= new Char(val.toCharArray(),val.charAt(0));
		}
		else if(reponseText){
			type= new Text(val);
		}

		return type;
	}
}

