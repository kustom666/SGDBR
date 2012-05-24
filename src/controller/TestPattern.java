package controller;

import java.util.regex.Pattern;

import model.Base;
/**
 * La classe <b> TestPattern </b> est la classe qui gèrent toutes les instructions en mémoires
 * @author tonycoriolle
 *
 */
public class TestPattern {
	private Base temp;
	private Instruction instruction=new Instruction();
	private int nbRequete=0;
	
	/**
	 * La méthode <b> test </b> permet de tester tous les pattern referencé et de lancer la méthode adéquate si le pattern existe
	 * sinon renvoi d'une exception
	 * @param i : la requete envoyé à la console 
	 * @return la base compilée 
	 * @throws TestPatternException
	 */
	public Base test(String i) throws TestPatternException{
		Pattern create=Pattern.compile("^CREATE TABLE [a-zA-Z0-9]*\\s*\\([[_a-zA-Z0-9]* [A-Za-z]*\\s*,]*\\)\\s*;$",Pattern.MULTILINE);
		
		Pattern alterAdd=Pattern.compile("^ALTER TABLE [a-zA-Z0-9]* ADD [a-zA-Z0-9]* [a-zA-Z]*\\s*;$",Pattern.MULTILINE);
		Pattern alterDrop=Pattern.compile("^ALTER TABLE [a-zA-Z0-9]* DROP [a-zA-Z0-9]*\\s*;$",Pattern.MULTILINE);
		Pattern alterChange=Pattern.compile("^ALTER TABLE [a-zA-Z0-9]* CHANGE [a-zA-Z0-9]* [a-zA-Z0-9]* [a-zA-Z]*\\s*;$",Pattern.MULTILINE);
		Pattern alterModify=Pattern.compile("^ALTER TABLE [a-zA-Z0-9]* MODIFY [a-zA-Z0-9]* [a-zA-Z]*\\s*;$",Pattern.MULTILINE);
		
		Pattern insertInto=Pattern.compile("^INSERT INTO [[a-zA-Z0-9]*,]* \\([[a-zA-Z0-9]*\\s*,]*\\) VALUES\\s\\([[a-zA-Z0-9./\"]*\\s*,]*\\)\\s*;$",Pattern.MULTILINE);
		
		Pattern selectFrom=Pattern.compile("^SELECT [[a-zA-Z0-9_]*\\s*,]* FROM [[a-zA-Z0-9],*]+\\s*;$",Pattern.MULTILINE);
		Pattern selectFromWhere=Pattern.compile("^SELECT [[a-zA-Z0-9_*]*\\s*,]* FROM [a-zA-Z0-9]* WHERE [a-zA-Z0-9.=<>!\"\\s*]*\\s*;$",Pattern.MULTILINE);
		
		Pattern deleteFrom=Pattern.compile("^DELETE FROM [a-zA-Z0-9]*\\s*;$", Pattern.MULTILINE);
		Pattern deleteFromWhere=Pattern.compile("^DELETE FROM [a-zA-Z0-9]* WHERE [a-zA-Z0-9!=<>.\"\\s*]*\\s*;$", Pattern.MULTILINE);
		
		Pattern update=Pattern.compile("^UPDATE [a-zA-Z0-9]* SET [a-zA-Z0-9]* = [a-zA-Z0-9.\"/]*\\s*;$", Pattern.MULTILINE);
		Pattern updateWhere=Pattern.compile("^UPDATE [a-zA-Z0-9]* SET [a-zA-Z0-9]* = [a-zA-Z0-9.\"/]* WHERE [a-zA-Z0-9.=<>!\\s*]*\\s*;$", Pattern.MULTILINE);
		
		Pattern sauvBase=Pattern.compile("^BACKUP DATABASE\\s*;$",Pattern.MULTILINE);
		Pattern chargeBase=Pattern.compile("^LOAD DATABASE [a-zA-Z0-9./_]*\\s*;$",Pattern.MULTILINE);
		Pattern chargeCommande=Pattern.compile("^LOAD [a-zA-Z./]*\\s*;$");
		
		Pattern undo=Pattern.compile("^UNDO;$",Pattern.MULTILINE);
		Pattern redo=Pattern.compile("^REDO;$",Pattern.MULTILINE);
		
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
		
		boolean reponseUndo=undo.matcher(i).matches();
		boolean reponseRedo=redo.matcher(i).matches();
		
		if (reponseCreate){
			++this.nbRequete;
			temp=instruction.createTable(i,this.nbRequete);
			
			
		}
		else if(reponseAlterAdd^reponseAlterDrop^reponseAlterChange^reponseAlterModify){
			++this.nbRequete;
			temp=instruction.alterTable(i,this.nbRequete);
		}
		else if(reponseInsert){
			++this.nbRequete;
			temp=instruction.insertInto(i,this.nbRequete);
		}
		else if(reponseUpdate^reponseUpdateWhere){
			++this.nbRequete;
			temp=instruction.update(i,this.nbRequete);	
		}
		else if(reponseSelect^reponseSelectWhere){
			temp=instruction.selectFrom(i,this.nbRequete);
		}
		else if(reponseDelete^reponseDeleteWhere){
			++this.nbRequete;
			temp=instruction.deleteFrom(i,this.nbRequete);
		}
		else if(reponseSauv){
			instruction.sauvBase();
		}
		else if(reponseCharge){
			temp=instruction.chargeBase(i);
		}
		else if(reponseCommande){
			++this.nbRequete;
			temp=instruction.LancementSeqCommand(i,this.nbRequete);
		}
		else if(reponseUndo){
			System.out.println(this.nbRequete);
			--this.nbRequete;
			temp=instruction.undo(this.nbRequete);
			
		}
		else if(reponseRedo){
			System.out.println(this.nbRequete);
			++this.nbRequete;
			temp=instruction.redo(this.nbRequete);
		}
		else {
			throw new TestPatternException();
		}
		return temp;
		
	}
	
}
