package Clase;

public class Buffer { //clasa buffer
	public int[][] matrix; //matricea ce va fi transferata de la producer la consumer
	private boolean available = false ; //variabila booleana care spune care thread sa functioneze
	
	public synchronized int[][] get () { //functia get
		while (!available) { //daca available este false se asteapta ca producatorul sa puna valoare
			try {
				wait (); // Asteapta producatorul sa puna o valoare			
			} 
			catch (InterruptedException e) { //catch exception daca nu functioneaza
				e.printStackTrace (); //si afisam stack-trace
			}
		}
		
		available = false ; //daca s-a ajuns aici inseamna ca consumerul a primit valoarea si transformam variabila booleana in false
		notifyAll (); //notificam toate celelalte threaduri
		return matrix; //functia get returneaza matricea
	}
	
	public synchronized void put (int[][] matrix) { //functia cu care producerul va pune in consumer
		while (available) {
			try {
				wait (); // Asteapta consumatorul sa preia valoarea				
			} 
			catch (InterruptedException e) {
				e.printStackTrace ();
			}
		}
		
		this.matrix = matrix ; //salvam matricea
		available = true; //facem available true
		notifyAll (); //notificam celelalte threaduri
	}
}
