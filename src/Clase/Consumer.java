package Clase;
//in aceasta clasa vom primi un sfert de matrice, o vom prelucra si o vom scrie intr-o matrice finala
//matricea finala va fi poza procesata
public class Consumer extends ProducerConsumer { //consumer mosteneste clasa abstracta
	private Buffer buffer; //variabila de tip buffer
	
	int w = img.getSize().getWidth() / 4; //salvam dimensiunile
	int h = img.getSize().getHeight();
	
	public int[][] aux = new int [w][h]; //matricea ce va fi primita de la producer
	public int[][] matrix_c = new int [w*4][h]; //matricea finala
	
	
	public Consumer (Buffer b, Image img, int nr_biti) { //constructor
		super(false, img, nr_biti); //apelam constructorul clasei parinte in care salvam variabilele necesare
		buffer = b; //salvam vatiabila de tip buffer
	}
	
	@Override //practic aici va fi functia run a acestei clase de consumer (aici facem si prelucrarea)
	public void doWork () throws InterruptedException {
		int l = 0; //acelasi l ca la producer
		int nr_biti_culoare = nr_biti / 3; //vedem pe cati biti va fi fiecare culoare in parte in poza procesata
		for (int i = 0; i < 4; i++) { //iteram de 4 ori pentru fiecare sfert
			aux = buffer.get(); //primim matricea auxliara de la producer (ce reprezinta un sfert din poza)
			System.out.println("Consumatorul a primit un sfert de imagine. El o va prelucra");
			System.out.println(); //aici incepe prelucrarea
			
			for(int j = 0; j < aux.length; j++) { //iteram prin matricea aux
				for (int k = 0; k < aux[j].length; k++) {
					
					int color = aux[j][k]; //savam pixelul in variabila color
					int a = (color >> 24) & 255; //luam saturatia
	                int r = (color >> 16) & 255; //luam pixelul red
	                int g = (color >> 8) & 255; //luam pixelul green
	                int b = color & 255; // luam pixelul blue
	                
	                int auxx = 255 << (8 - nr_biti_culoare); //variabila ce o folosim pentru un si logic
	                r = r & auxx; //fiecare culoare o facem SI LOGIC cu acest numar din auxx pentru a ramane fix cu numarul de biti dorit
	                g = g & auxx; //mai multe detalii in documentatie
	                b = b & auxx;
	                
	                color = (a << 24) | (r << 16) | (g << 8) | b; //refacem pixelul cu noile valori
					matrix_c[l][k] = color; //salvam in matricea finala
				}
				l++; //dupa fiecare pas incrementam l
			}
		}
		
		for (int i = 0; i < matrix_c.length; i++) {
			for (int j = 0; j < matrix_c[i].length; j++)
				img.getImg().setRGB(i, j, matrix_c[i][j]); //luam fiecare pixel din imagine si il inlocuim cu pixelul calculat mai sus (procesat)
		}
		
		img.writeFile(img.getName() + "_prelucrata.bmp"); //scriem imaginea prelucrata in fisierul destinatie folosindu-ne de numele imaginii sursa
	}
}
