package controller;

import java.util.regex.Pattern;

import model.Base;

public class TestPattern {
	private Base temp;
	private Instruction instruction=new Instruction();
	//private EditBase edit =new EditBase();
	
	public Base test(String i) throws TestPatternException{
		Pattern create=Pattern.compile("^CREATE TABLE [a-zA-Z0-9]*\\s*\\([[_a-zA-Z0-9]* [A-Za-z]*\\s*,]*\\)\\s*;$",Pattern.MULTILINE);
		Pattern alterAdd=Pattern.compile("^ALTER TABLE [a-zA-Z0-9]* ADD [a-zA-Z0-9]* [a-zA-Z]*\\s*;$",Pattern.MULTILINE);
		Pattern alterDrop=Pattern.compile("^ALTER TABLE [a-zA-Z0-9]* DROP [a-zA-Z0-9]*\\s*;$",Pattern.MULTILINE);
		Pattern alterChange=Pattern.compile("^ALTER TABLE [a-zA-Z0-9]* CHANGE [a-zA-Z0-9]* [a-zA-Z0-9]* [a-zA-Z]*\\s*;$",Pattern.MULTILINE);
		Pattern alterModify=Pattern.compile("^ALTER TABLE [a-zA-Z0-9]* MODIFY [a-zA-Z0-9]* [a-zA-Z]*\\s*;$",Pattern.MULTILINE);
		Pattern insertInto=Pattern.compile("^INSERT INTO [[a-zA-Z0-9]*,]* \\([[a-zA-Z0-9]*\\s*,]*\\) VALUES\\s\\([[a-zA-Z0-9./\"]*\\s*,]*\\)\\s*;$",Pattern.MULTILINE);
		
		Pattern selectFrom=Pattern.compile("^SELECT [[a-zA-Z0-9_]*\\s*,]* FROM [[a-zA-Z0-9],*]+\\s*;$",Pattern.MULTILINE);
		Pattern selectFromWhere=Pattern.compile("^SELECT [[a-zA-Z0-9_]*\\s*,]* FROM [a-zA-Z0-9]* WHERE [a-zA-Z0-9.=<>!\\s*]*\\s*;$",Pattern.MULTILINE);
		
		Pattern deleteFrom=Pattern.compile("^DELETE FROM [a-zA-Z0-9]*\\s*;$", Pattern.MULTILINE);
		Pattern deleteFromWhere=Pattern.compile("^DELETE FROM [a-zA-Z0-9]* WHERE [a-zA-Z0-9!=<>.\\s*]*\\s*;$", Pattern.MULTILINE);
		
		Pattern update=Pattern.compile("^UPDATE [a-zA-Z0-9]* SET [a-zA-Z0-9]* = [a-zA-Z0-9.\"/]*\\s*;$", Pattern.MULTILINE);
		Pattern updateWhere=Pattern.compile("^UPDATE [a-zA-Z0-9]* SET [a-zA-Z0-9]* = [a-zA-Z0-9.\"/]* WHERE [a-zA-Z0-9.=<>!\\s*]*\\s*;$", Pattern.MULTILINE);

		Pattern sauvBase=Pattern.compile("^BACKUP DATABASE\\s*;$");
		Pattern chargeBase=Pattern.compile("^LOAD DATABASE [a-zA-Z]*\\s*;$");
		Pattern chargeCommande=Pattern.compile("^LOAD [a-zA-Z./]*\\s*;$");
		
		boolean reponseCreate =create.matcher(i).matches();
		boolean reponseAlterAdd =alterAdd.matcher(i).matches();
		boolean reponseAlterDrop =alterDrop.matcher(i).matches();
		boolean reponseAlterChange =alterChange.matcher(i).matches();
		boolean reponseAlterModify =alterModify.matcher(i).matches();
		
		boolean reponseInsert =insertInto.matcher(i).matches();
		boolean reponseUpdate =update.matcher(i).matches();
		boolean reponseUpdateWhere =updateWhere.matcher(i).matches();
		
		boolean reponseSelect= selectFrom.matcher(i).matches();
		boolean reponseSelectWhere= selectFromWhere.matcher(i).matches();
		
		boolean reponseDelete=deleteFrom.matcher(i).matches();
		boolean reponseDeleteWhere=deleteFromWhere.matcher(i).matches();

		boolean reponseSauv=sauvBase.matcher(i).matches();
		boolean reponseCharge=chargeBase.matcher(i).matches();
		boolean reponseCommande=chargeCommande.matcher(i).matches();
		
		if (reponseCreate){

			temp=instruction.createTable(i);
		}
		else if(reponseAlterAdd^reponseAlterDrop^reponseAlterChange^reponseAlterModify){
			temp=instruction.alterTable(i);
		}
		else if(reponseInsert){
			temp=instruction.insertInto(i);
		}
		else if(reponseUpdate^reponseUpdateWhere){
			temp=instruction.update(i);
		}
		else if(reponseSelect^reponseSelectWhere){
			temp=instruction.selectFrom(i);
		}
		else if(reponseDelete^reponseDeleteWhere){
			temp=instruction.deleteFrom(i);
		}
		else if(reponseSauv){
			instruction.sauvBase();
		}
		else if(reponseCharge){
			instruction.chargeBase(i);
		}
		else if(reponseCommande){
			//temp=edit.LancementSeqCommand(i);
		}
		else if(reponseCharge){
			temp=instruction.chargeBase(i);
		}
		else {
			throw new TestPatternException();
		}
		
		return temp;

	}
	
}
