package main;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.undo.UndoManager;

import controller.Crud;
import controller.Instruction;
import controller.TestPattern;
import controller.TestPatternException;
import controller.baseController;

import types.SFloat;
import types.Sinteger;
import types.Text;
import types.Types;
import types.Sinteger;
import model.Base;
import model.Line;
import model.Table;

public class Main {

	public static void main(String[] args) {

		Crud controlleur = new Crud();
		TestPattern t=new TestPattern();
		String test = "y";
		System.out.println("Bienvenue sur le CLI SGDBR 0.1a, initialisation de la base");
		System.out.println("Veuillez donner un Nom à votre base svp ...");
		Scanner scan =new Scanner(System.in);
		String baseName=scan.nextLine();
		Base base=new Base(baseName);
		System.out.println("Base "+baseName+" initialisée, en attente de commandes");
		controlleur.importFromCSV("defaultLOL");
		while(!test.equals("n"))
		{	
			String buffIn = new String();
			while(!buffIn.endsWith(";"))
			{
				Scanner sc = new Scanner(System.in);
				buffIn += sc.nextLine();
			}

			try {
				base=(t.test(buffIn));
			} catch (TestPatternException e) {
				e.printStackTrace();
			}
			
			
			for(int i=0;i<base.size();i++){
			controlleur.setUsedTable(base.get(i));
			controlleur.displayTable();
			if(controlleur.getUsedTable().getTableName().equals("resultat")){
				base.remove(controlleur.getUsedTable().getTableName());
			}
			}
			
			System.out.println("y-Continuer, n-Arrèter");
			Scanner sc = new Scanner(System.in);
			test += sc.nextLine().trim();
		}

	}
}
