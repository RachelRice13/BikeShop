import java.io.Serializable;

public class Bike implements Serializable {

    private String name; // the name of each bike 
    private int bikeStock; // the number of bikes in stock
    private double price; // the price of each bike
    private String colour; // the colour of the bike
    private int noOfWheels; // the number of wheels a bike has
    
    // Constructors
    public Bike() {
        this.name = "";
        this.bikeStock = 0;
        this.price = 0;
        this.colour = "";
        this.noOfWheels = 0;
    }
    public Bike(String n, int s, double p, String c, int w, String o) {
        this.name = n;
        this.bikeStock = s;
        this.price = p;
        this.colour = c;
        this.noOfWheels = w;
    }
    
    // Getter and Setter methods
    public String getName() {
        return this.name;
    }
     public void setName(String n) {
        this.name = n;
    }
    
    public int getBikeStock() {
        return this.bikeStock;
    }
    public void setBikeStock(int s) {
        this.bikeStock = s;
    }
    
    public double getPrice() {
        return this.price;
    }
    public void setPrice(double p) {
        this.price = p;
    }
    
    public String getColour() {
        return this.colour;
    }
    public void setColour(String c) {
        this.colour = c;
    }
    
    public int getWheels() {
        return this.noOfWheels;
    }
    public void setWheels(int w) {
        this.noOfWheels = w;
    }
    
    // Display method
    public void display() {
        System.out.println("\nName = " + this.name);
        System.out.println("Number of bikes in stock = " + this.bikeStock);
        System.out.println("Price = €" + this.price);
        System.out.println("Colour = " + this.colour);
        System.out.println("Number of wheels = " + this.noOfWheels);
    }
    
}
