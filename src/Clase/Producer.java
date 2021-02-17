package Clase;

public class Producer extends ProducerConsumer { //clasa producer ce mosteneste clasa abstracta ProducerConsumer
	private Buffer buffer; //variabila de tip buffer
		
	int w = img.getSize().getWidth() / 4; //salvam latimea / 4 si inaltimea in variabilele w si h
	int h = img.getSize().getHeight();
	
	//creem o matrice auxiliara care reprezinta UN SFERT din matricea aferenta pozei
	public int[][] aux = new int [w][h];
	
	public Producer (Buffer b, Image img, int nr_biti) { //constructorul clasei
		super(false, img, nr_biti); //apelam constructorul clasei parinte
		buffer = b; //salvam variabila de tip buffer
	}
	
	@Override //implementam functia corespunzatoare clasei parinte abstracte (practic va fi run-ul lui producer)
	public void doWork () {
		int l = 0; //variabila in care vom pastra linia matricei
		
		for (int i = 0; i < 4; i++) {//iteram de 4 ori (poza are 4 sferturi)
			
			for(int j = 0; j < aux.length; j++) {
				for (int k = 0; k < aux[j].length; k++) {
					aux[j][k] = img.getImg().getRGB(l,k); //in matricea aux salvam PIXELII POZEI
				} //aux reprezinta o matrice egala cu un sfert din poza
				l++; //incrementam l la urmatorul pas
			}
			
			buffer.put(aux); //punem acea matrice in buffer, aceasta va fi primita de consumer
			System.out.println("Producatorul a pus un sfert de imagine."); //afisam ca producerul a pus matricea
			
			try {
				sleep(2000); // asteptam 2 secunde dupa ce producerul pune matricea
			} 
			catch (InterruptedException e) { } //exceptii
		}
	}
}

