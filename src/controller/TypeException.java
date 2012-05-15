package controller;
/**
 * Exception générée si le type à instancié est different de ceux implémentés
 * @author tonycoriolle
 *
 */
public class TypeException extends Exception{

	private static final long serialVersionUID = 1L;

	public TypeException(String val){
		System.out.println("Le type "+val+" est inconnu veuillez utiliser un des types suivant :\n -int\n -float\n -date\n -char\n -text\n -bit\n");
	}
}
