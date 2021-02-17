package Clase;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//aceasta este clasa in care citim imaginea sursa dupa numele fisierului si in care regasim metoda
//de scriere in fisierul destinatie
public class Image { 
	private Dimensiuni size; //atribut de tip dimensiuni (ce contine atributele width si height)
	BufferedImage img; //atribut de tip bufferedimgage in care stocam imaginea
	public String name; //numee fisierului ce contine imaginea
	
	public Dimensiuni getSize() { //getter pt marime
		return size;
	}
	
	public void setSize(Dimensiuni size) { //setter pt marime
		this.size = size;
	}
	
	public BufferedImage getImg() { //getter pt imagine
        return img;
    }
	
    public void setImg(BufferedImage img) { //setter pt imagine
        this.img = img;
    }
    
    public String getName(){ //geter pt numele fisierului sursa (nu avem nevoie de setter la el)
    	return this.name;
    }
    
    
    public Image(String name) throws InterruptedException { //constructorul clasei, primeste ca parametru numele pozei
    	try {
    		File input = new File(name); //salvam in input un file cu acel nume
    		this.name = name; //salvam numele in variabila de clasa
    		img = ImageIO.read(input); //citim imaginea in variabila img
    		size = new Dimensiuni(img.getHeight(), img.getWidth()); //in size salvam dimensiunile pozei
    	}
    	
    	catch(IOException e) { //exceptii, iesim din program daca se intampla ceva
    		System.out.println("Fisierul pe care vreti sa-l procesati nu exista.");
    		System.exit(0);
    	}
    }
    //metoda de scriere a fisierului destinatie. primeste ca parametru numele fisierului destinatie
    public void writeFile(String write_name) throws InterruptedException {
    	try {
    		ImageIO.write(img, "BMP", new File(write_name)); //scriem imaginea in fisierul destinatie
    	}
    	
    	catch (IOException e) { //exceptii
    		System.out.println("Nu am putut salva imaginea procesata!");
    		System.exit(0);
    	}
    }
}
