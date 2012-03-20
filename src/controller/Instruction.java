package controller;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Table;
import sun.misc.Regexp;
import types.Types;

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
		 String[] tab;
		 String tp=new String();
		 ArrayList<Types> colTypes=new ArrayList();
		 ArrayList<String> colNames=new ArrayList();
			
		 if(reponse==true){
			tab=requete.split("CREATE TABLE ");
			for (int i=0;i<tab.length;i++){
					tp=tab[i];
			}
			tab=tp.split("[^a-zA-Z0-9]");		
			for (int i=1;i<tab.length;i++){
				if(!tab[i].equals("")){
					if(i%2==0){
						colTypes.add(tab[i]);
					}
					else{
						colNames.add(tab[i]);
					}
				}	
			}
		}
		else{
			System.out.println("faux");
		}
		return temp=new Table(tab[0],colNames,colTypes);
	}
}

