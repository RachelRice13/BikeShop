
public class Motorbike extends Bike{

	private String make; // the make of the motorbike
    private int engineSize; // the size of the engine
    private int noOfSeats; // the number of seats the motorbike has
    private boolean storage; // whether the motorbike has storage
    
    // Constructors
    public Motorbike() {
        super();
        this.make = "";
        this.engineSize = 0;
        this.noOfSeats = 0;
        this.storage = false;
    }
    public Motorbike(String name, int stock, double price, String col, int wheels, String obj ,String make, int eng, int seats, boolean stor) {
        super(name,stock,price,col,wheels,"Motorbike");
        this.make = make;
        this.engineSize = eng;
        this.noOfSeats = seats;
        this.storage = stor;
    }
    
    // Getter and Setter methods
    public String getMake() {
        return this.make;
    }
    public void setMake(String m) {
        this.make = m;
    }
    
    public int getEngineSize() {
        return this.engineSize;
    }
    public void setEngineSize(int e) {
        this.engineSize = e;
    }
    
    public int getSeats() {
        return this.noOfSeats;
    }
    public void setSeats(int nS) {
        this.noOfSeats = nS;
    }
    
    public boolean getStorage() {
        return this.storage;
    }
    public void setStorage(boolean s) {
        this.storage = s;
    }
    
    // Display method
    public void display() {
        super.display();
        System.out.println("Make = " + this.make);
        System.out.println("Engine size = " + this.engineSize + "cc");
        System.out.println("Number of seats = " + this.noOfSeats);
        if (this.storage)
            System.out.println("This motorbike has storage.");
    }
}
