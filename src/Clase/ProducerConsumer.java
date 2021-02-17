package Clase;
//aceasta clasa este o clasa abstracta ce implementeaza interfata respectiv functiile run din threaduri.
//producerul si consumerul vor mosteni aceasta clasa
//de asemenea, aceasta clasa mosteneste clasa Thread
//cam ce am avut la laborator :)
public abstract class ProducerConsumer extends Thread implements ProducerConsumerInterface{
    protected Buffer buffer; //variabila de tip buffer
    protected boolean isConsumer; //variabila booleana care ne spune daca trebuie sa ruleze producerul sau consumerul
    protected Image img; //imaginea ce va fi folosita la prelucrare
    protected int nr_biti; //variabila ce va fi folosita pentru algoritmul de prelucrare
    
    protected ProducerConsumer(Boolean isConsumer, Image img, int nr_biti) { //constructor
        super(); //apelam constructorul clasei thread fara parametrii
        this.isConsumer = isConsumer; //salvam parametrii din constructor in variabilele de clasa
        this.img = img;
        this.nr_biti = nr_biti;
    }
    
    public Image getImg() { //getter pt imagine
        return img;
    }
    
    public void setImg(Image img) { //setter pt imagine
        this.img = img;
    }
    
    public abstract void doWork() throws InterruptedException; //functia abstracta ce va trebui sa fie implementata de clasele copii

    //polimorfism: suprascriem functia run din Thread
    public void run() { //functia run va apela functiile doWork din Producer si Consumer
        try {
        	this.doWork();
        } 
        catch (InterruptedException e) {} //catch exceptions
    }
}
