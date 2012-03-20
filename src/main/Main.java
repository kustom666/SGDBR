package main;

import java.util.ArrayList;
import java.util.Scanner;

import controller.Crud;

import types.Sinteger;
import types.Types;
import types.Sinteger;
import model.Base;
import model.Line;
import model.Table;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Types> al = new ArrayList<Types>();
		al.add(new Sinteger(18));
		ArrayList<String> alS = new ArrayList<String>();
		alS.add(" numero");
		al.add(new Sinteger(190));
		alS.add(" age");

		Table buffTable = new Table("tableOrigin",alS, al);
		
		Crud crudManip = new Crud(buffTable);
		
		crudManip.displayTable();
		
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
