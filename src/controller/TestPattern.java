package controller;

import java.util.regex.Pattern;

import model.Table;

public class TestPattern {
	private Table temp;
	private Instruction instruction=new Instruction();
	
	public Table test(String i){
		Pattern create=Pattern.compile("^CREATE TABLE [a-zA-Z0-9]* \\([[_a-zA-Z0-9]* [A-Za-z]*,]*\\);$",Pattern.MULTILINE);
		Pattern alterAdd=Pattern.compile("^ALTER TABLE [a-zA-Z0-9]* ADD [a-zA-Z0-9]* [a-zA-Z]*;$");
		Pattern alterDrop=Pattern.compile("^ALTER TABLE [a-zA-Z0-9]* DROP [a-zA-Z]*;$");
		Pattern alterChange=Pattern.compile("^ALTER TABLE [a-zA-Z0-9]* CHANGE [a-zA-Z]* [a-zA-Z]* [a-zA-Z]*;$");
		Pattern alterModify=Pattern.compile("^ALTER TABLE [a-zA-Z0-9]* MODIFY [a-zA-Z]* [a-zA-Z]*;$");
		Pattern insertInto=Pattern.compile("^INSERT INTO [a-zA-Z0-9]* \\([[a-zA-Z0-9]*,]*\\) VALUES \\([[a-zA-Z0-9]*,]*\\);$",Pattern.MULTILINE);
		Pattern selectFrom=Pattern.compile("^SELECT [a-zA-Z0-9]* FROM [a-zA-Z0-9]*;$",Pattern.MULTILINE);
		//Pattern deleteFrom=Pattern.compile("^DELETE FROM [a-zA-Z0-9]* WHERE \\{[a-zA-Z0-9] [<
		Pattern update=Pattern.compile("^UPDATE [a-zA-Z0-9]* SET [a-zA-Z0-9]* \\= [a-zA-Z0-9]*", Pattern.MULTILINE);
		
		boolean reponseCreate =create.matcher(i).matches();
		boolean reponseAlterAdd =alterAdd.matcher(i).matches();
		boolean reponseAlterDrop =alterDrop.matcher(i).matches();
		boolean reponseAlterChange =alterChange.matcher(i).matches();
		boolean reponseAlterModify =alterModify.matcher(i).matches();
		boolean reponseInsert =insertInto.matcher(i).matches();
		boolean reponseUpdate =update.matcher(i).matches();
		boolean reponseSelect= selectFrom.matcher(i).matches();
		//boolean reponseDelete=deleteFrom.matcher(i).matches();
		
		if (reponseCreate){
			temp=instruction.CreateTable(i);
		}
		else if(reponseAlterAdd){
			temp=instruction.AlterTable(i);
		}
		else if(reponseAlterDrop){
			temp=instruction.AlterTable(i);
		}
		else if(reponseAlterChange){
			temp=instruction.AlterTable(i);
		}
		else if(reponseAlterModify){
			temp=instruction.AlterTable(i);
		}
		else if(reponseInsert){
			temp=instruction.InsertInto(i);
		}
		else if(reponseUpdate){
			temp=instruction.Update(i);
		}
		else if(reponseSelect){
			temp=instruction.SelectFrom(i);
		}
		/*else if(reponseDelete){
			temp=instruction.deleteFrom(i);
		}*/
		else{
			System.out.println("requete inconnue ou mauvaise utilisation de la requete \n");
			return null;
		}
		return temp;
		
	}
}
