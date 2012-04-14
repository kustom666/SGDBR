package controller;

public class TypeException extends Exception{

	public TypeException(String val){
		System.out.println("Le type "+val+" est inconnu veuillez utiliser un des types suivant :\n -int\n -float\n -date\n -char\n -text\n -bit\n");
	}
}
