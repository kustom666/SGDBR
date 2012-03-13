package main;

import java.util.ArrayList;
import java.util.Scanner;

import types.Sinteger;
import types.Types;
import types.Sinteger;
import model.Base;
import model.Table;

public class Main {

	public static void main(String[] args) {
		
		/*ArrayList<Types> al = new ArrayList<Types>();
		al.add(new Sinteger(18));
		ArrayList<String> alS = new ArrayList<String>();
		alS.add("numero");
		Table buffTable = new Table("tableOrigin",alS, al);*/
		
		System.out.println("Bienvenue sur le CLI SGDBR 0.1a, initialisation de la base");
		Base workingBase = new Base("default");
		System.out.println("Base default initialisée, en attente de commandes");
		String buffIn = new String();
		
		while(!buffIn.endsWith(";"))
		{
			Scanner sc = new Scanner(System.in);
			buffIn += sc.nextLine();
		}
		
	}

}
