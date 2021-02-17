package Clase;
public class Dimensiuni { //in aceasta clasa salvam dimensiunile pozei cu care lucram
	private int width; //avem ca atribute latimea si inaltimea pozei
	private int height;
	
	public Dimensiuni(int height, int width) { //constructor in care salvam dimensiunile
		this.width = width;
		this.height = height;
	}
	
	public int getHeight() { //setteri si getteri pentru inaltime si latime
        return height;
    }
	
    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
}
