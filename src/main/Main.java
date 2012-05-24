package main;

import java.util.Scanner;


import controller.Crud;

import controller.TestPattern;
import controller.TestPatternException;
import model.Base;

public class Main {
	
	public static void main(String[] args) {
		
		Crud controlleur = new Crud();
		TestPattern t=new TestPattern();
		String test = "";
		System.out.println("Bienvenue sur le CLI SGDBR 0.1a, initialisation de la base");
		System.out.println("Veuillez donner un Nom à votre base svp ...");
		Scanner scan =new Scanner(System.in);
		String baseName=scan.nextLine();
		Base base=new Base(baseName);
		
		System.out.println("Base "+baseName+" initialisée, en attente de commandes");
		do
		{	
			Scanner sc = new Scanner(System.in);
			test = sc.nextLine();
			
			if(test.endsWith(";") && !test.endsWith("quit;"))
			{
				try {
					base=(t.test(test));
				} catch (TestPatternException e) {
					
				}
				
				for(int i=0;i<base.size();i++){
					controlleur.setUsedTable(base.get(i));
					if(controlleur.getUsedTable().getTableName().equals("resultat")){
						base.remove(controlleur.getUsedTable().getTableName());
					}
					
				}
			}
		}while(!test.endsWith("quit;") || !test.endsWith(";"));
	}
}