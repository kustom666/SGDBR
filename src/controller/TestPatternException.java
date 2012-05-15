package controller;
/**
 * Exception à générer si la requete passé à la console n'existe pas 
 * @author tonycoriolle
 *
 */
public class TestPatternException extends Exception{
	private static final long serialVersionUID = 1L;

	public TestPatternException(){
		System.out.println("requete inconnue ou mauvaise utilisation de la requete \n");
	}
}