package Test;

import java.util.Scanner;
import Clase.*;

public class Main {
    public static void main(String... args) throws InterruptedException { //varargs
        System.out.print("Introduceti numarul de biti al culorilor pozei / pozelor procesate (un numar mai mic de 24): ");
		Scanner in = new Scanner(System.in); //introducem variabila aceea cu nr_biti de la tastatura
        int nr_de_biti = in.nextInt();
        
        long startTime; //variabile pentru a vedea timpul de rulare al citirii pozei
        long endTime;
        long timeElapsed;
        
        System.out.println("");
        
        if (args.length == 0) { //verificam daca s-au dat argumente din linia de comanda
        	System.out.println("Acest progam functioneaza cu parametrii din linia de comanda.");
    		System.out.println("Daca se afiseaza acest mesaj, inseamna ca ei nu sunt setati din IDE de la RUN -> Run configuration");
    		System.out.println("Trebuie sa scriem la Program Arguments: ./src/poza1.bmp ./src/poza2.bmp");
    		System.out.println("Detaliez mai mult in documentatie.");
    		System.exit(0);
        }
        
        for(int i = 0; i < args.length; i++) { //aplicam alogritmul pentru fiecare poza data ca argument in linia de comanda
        	
        	startTime = System.nanoTime(); //incepem sa inregistram timpul de executie
        	Image img = new Image(args[i]);  //salvam imaginea intr-o variabila de tip clasa Image
	        endTime = System.nanoTime();
	        timeElapsed = endTime - startTime; //vedem cat timp a durat sa citim
	        System.out.println("Citire informatii identificare fisiere sursa + fisiere destinatie + citirea fisierului sursa: " +
                    timeElapsed / 1000000 + " milisecunde");
	        
	        
	        Buffer b = new Buffer(); //creem variabila de tip buffer
	        Producer p1 = new Producer(b, img, nr_de_biti); //creem producerul si consumerul
	        Consumer c1 = new Consumer(b, img, nr_de_biti); 
	        
	        p1.start(); //pornim aceste 2 threaduri
	        c1.start(); //in consumer va fi procesata poza si scrisa in fisierul destinatie
	        //nu am mai inregistrat timpul deoarece am si sleepuri si era greu pentru lucrul cu threaduri
	        System.out.println("---------------");
        }
        
    }
    
}
