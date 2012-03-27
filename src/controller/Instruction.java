package controller;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Line;
import model.Table;
import types.Date;
import types.Sinteger;
import types.Float;
import types.Types;
import types.Char;

public class Instruction {
	private String requete;
	private Table temp;
	private Pattern syntaxe = Pattern.compile("^CREATE TABLE [a-zA-Z0-9]* \\([[a-zA-Z0-9]* [A-Za-z]*,]*\\);$");
	private Matcher match = syntaxe.matcher(requete);
	private boolean reponse = match.matches();

	public Instruction(String requete){
		this.requete=requete;
	}

	public Table CreateTable(String requete){
		 String[] tab= null;
		 String nomTable = null;
		 String tp=new String();
		 ArrayList<Types> colTypes=new ArrayList<Types>();
		 ArrayList<String> colNames=new ArrayList<String>();

		 if(reponse==true){
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
		}
		else{
			System.out.println("Requete inconnue\n Usage: CREATE TABLE nomTable (nomCol type,...,nomCol type);");
		}
		return temp=new Table(nomTable,colNames,colTypes);
	}

	public Table SelectFrom(String requete){
		if(reponse==true){

		}
		else{
		System.out.println("Requete inconnue\n Usage: SELECT nomCol,...,nomCol FROM nomTable WHERE condition;");
		}
		return temp;
			}
	public Table AlterTable(String requete){
		return temp;
		}
	public Table DropTable(String requete){
		return temp;
	}
	public Table InsertInto(String requete){
		return temp;
	}
	public Table Update(String requete){
		return temp;
	}
	public Table DeleteFrom(String requete){
		return temp;
	}

	public Types toType(String requete){
		Types type = null;
		if(requete.equalsIgnoreCase("int")){
			type=new Sinteger();
		}
		else if(requete.equalsIgnoreCase("float")){
			type= new Float();
		}
		else if(requete.equalsIgnoreCase("date")){
			type= new Date();
		}
		else if(requete.equalsIgnoreCase("char")){
			type= new Char();
		}
		return type;
	}
}

