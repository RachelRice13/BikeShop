
public class Bicycle extends Bike{
	private String type; // the type of bicycle
    private boolean electric; // whether a bicycle is electric or not
    private boolean basket; // whether the bicycle comes with a basket or not

    // Constructors
    public Bicycle() {
        super();
        this.type = "";
        this.electric = false;
        this.basket = false;       
    }
    public Bicycle(String name, int stock, double price, String col, int wheel, String obj ,String type, boolean ele, boolean bask) {
        super(name,stock,price,col,wheel,obj);
        this.type = type;
        this.electric = ele;
        this.basket = bask;   
    }

    // Getter and Setter methods
    public String getType() {
        return this.type;
    }
    public void setType(String t) {
        this.type = t;
    }

    public boolean getElectric() {
        return this.electric;
    }
    public void setElectric(boolean e) {
        this.electric = e;
    }

    public boolean getBasket() {
        return this.basket;
    }
    public void setBasket(boolean b) {
        this.basket = b;
    }

    // Display method
    public void display() {
        super.display();
        System.out.println("Type = " + this.type);
        if(this.electric)
            System.out.println("This bicycle is electric.");

        if(this.basket)
            System.out.println("This bicycle has a basket.");
    }
    
}
