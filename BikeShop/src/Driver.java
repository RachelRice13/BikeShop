import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Driver {

	ArrayList<Bike> listOfBikes; 
    ArrayList<Bike> customOrders;
    int numberBikes; // number of bikes in the ArrayList
    
    // Constructor
    public Driver() {
        listOfBikes = new ArrayList<Bike>();
        customOrders = new ArrayList<Bike>();

        if (readFromFile() == true) {
            System.out.println("\nBikes have been found and loaded into the system....");
            mainMenu();
        } else {
            System.out.println("\nBikes have not been found so they need to be created....");
            mainMenu();
        }       
        saveToFile();
    }
    
    // (2) Display Main Menu
    public void mainMenu() {
        Scanner scan = new Scanner(System.in);
        int option;
        String delay;

        do {
            System.out.print("\f");
            System.out.println("\nWelcome to Rice Bike Shop");
            System.out.println("Main Menu");
            System.out.println("1. Customer Menu");
            System.out.println("2. Staff Menu");
            System.out.println("3. Exit Main Menu");

            System.out.println("\nEnter a menu option: ");
            option = scan.nextInt();
            scan.nextLine();

            if(option == 1)
                customerMenu();
            else if(option == 2)
                staffMenu();
            else
                System.out.println("Exiting the main menu....");

        } while(option !=3);
    }

    // (3) Display Customer Menu
    public void customerMenu() {
        Scanner scan = new Scanner(System.in);
        int option;
        String delay;

        do {
            System.out.print("\f");
            System.out.println("Customer Menu");
            System.out.println("1. Display all bicycles for sale");
            System.out.println("2. Display all motorbikes for sale");
            System.out.println("3. Buy one or more bicycles");
            System.out.println("4. Buy one or more motorbikes");
            System.out.println("5. Order Custom bike");
            System.out.println("6. Return to Main Menu");

            System.out.println("\nEnter a menu option: ");
            option = scan.nextInt();
            scan.nextLine();

            if(option == 1)
                displayBicycles();
            else if(option == 2)
                displayMotorbikes();
            else if(option == 3)
                buyBicycles();
            else if(option == 4)
                buyMotorbikes();
            else if(option == 5)
                orderCustomBike();
            else
                System.out.println("Returning to Main Menu");

            System.out.println("\n\n\nPress return to continue....");
            delay = scan.nextLine();
        }while(option !=6);
    }

    // (4) Display all bicycles
    public void displayBicycles() {
        System.out.print("\f");
        System.out.println("\n\nDisplay Bicycle details....");
        for (Bike b : listOfBikes) {
            if ( b instanceof Bicycle)
                b.display();
        }
    }

    // (5) Display all motorbikes
    public void displayMotorbikes() {
        System.out.print("\f");
        System.out.println("\n\nDisplay motorbike details....");
        for (Bike m : listOfBikes) {
            if ( m instanceof Motorbike)
                m.display();
        }
    }

    // (6) Buy one or more bicycles
    public void buyBicycles() {
        Scanner scan = new Scanner(System.in);
        String bicName;
        int buyBic;
        double sale;

        System.out.print("\f");
        System.out.println("\nBuy bicycles");

        // (6.1) Ask for the name of bicycle and validate (does it exist?)
        System.out.println("Please input the name of the bicycle you are looking for here: ");
        bicName = scan.nextLine();

        Bike myBike = getBicycleByName(bicName);

        if(myBike == null) {
            System.out.println("Sorry, that bicycle does not exist here.");
        } else {        
            // (6.2) Ask how many bicycles they want and validate (is there enough in stock?)
            do {
                System.out.println("How many bicycles are you looking to buy? ");
                buyBic = scan.nextInt();
                if(buyBic > myBike.getBikeStock()) {
                    System.out.println("Sorry, we don't have enough of that bicycle in stock. We currently have " + myBike.getBikeStock() + " in stock.");
                }
            }while(buyBic > myBike.getBikeStock());

            // (6.3) Update stock levels
            myBike.setBikeStock(myBike.getBikeStock() - buyBic);
            // (6.4) Calculate cost of sales
            sale = myBike.getPrice() * buyBic;
            // (6.5) Display cost of sale
            System.out.println("The cost of this sale is €" + sale);
        }
    }

    public Bike getBicycleByName(String bikeName) {
        for(Bike b : listOfBikes) {
            if ( b instanceof Bicycle && b.getName().equalsIgnoreCase(bikeName)) {
                return b;
            }
        }
        return null;
    }

    // (7) Buy one or more motorbikes
    public void buyMotorbikes()     {
        Scanner scan = new Scanner(System.in);
        String bicName;
        int buyMot;
        double sale;

        System.out.print("\f");
        System.out.println("\nBuy Motorbikes");

        //  Ask for the name of motorbike and validate (does it exist?)
        System.out.println("Please input the name of the motorbike you are looking for here: ");
        bicName = scan.nextLine();
        Bike myBike = getMotorbikeByName(bicName);

        if(myBike == null) {
            System.out.println("Sorry, that motorbike does not exist here.");
        } else {        
            // Ask how many motorbikes they want and validate (is there enough in stock?)
            do {
                System.out.println("How many motorbikes are you looking to buy? ");
                buyMot = scan.nextInt();
                if(buyMot > myBike.getBikeStock()) {
                    System.out.println("Sorry, we don't have enough of that motorbike in stock. We currently have " + myBike.getBikeStock() + " in stock.");
                }
            }while(buyMot > myBike.getBikeStock());

            // (6.3) Update stock levels
            myBike.setBikeStock(myBike.getBikeStock() - buyMot);
            // (6.4) Calculate cost of sales
            sale = myBike.getPrice() * buyMot;
            // (6.5) Display cost of sale
            System.out.println("The cost of this sale is €" + sale);
        }
    }

    public Bike getMotorbikeByName(String bikeName) {
        for(Bike b : listOfBikes) {
            if ( b instanceof Motorbike && b.getName().equalsIgnoreCase(bikeName)) {
                return b;
            }
        }
        return null;
    }

    // (8) Order Custom bike
    public void orderCustomBike() {
        Scanner scan = new Scanner(System.in);
        String bikeType, colour,name, bicType, motMake;
        int noOfWheels, engSize, noOfSeats, stock = 0;
        boolean electric = false, basket = false, storage = false;
        double price = 0;
        Bicycle bc;
        Motorbike mb;

        System.out.print("\f");
        System.out.println("\nOrder custom bike");

        // (8.1) Ask do they want to buy a bicycle or motorbike
        do{
            System.out.println("Do you want to buy a bicycle or a motorbike?");
            bikeType = scan.nextLine();
        }while(!bikeType.equalsIgnoreCase("Bicycle") && !bikeType.equalsIgnoreCase("Motorbike"));

        // (8.2) Ask for the different variables for the custom order
        System.out.println("What colour " + bikeType + " do you want?");
        colour = scan.nextLine();

        do {        
            System.out.println("How many wheels do you want? (Between 2 and 4)");
            noOfWheels = scan.nextInt();
        }while(noOfWheels < 2 || noOfWheels > 4);

        System.out.println("Please enter your name here: ");
        scan.nextLine();
        name = scan.nextLine();

        if(bikeType.equalsIgnoreCase("Bicycle")) {
            bicType = inputTypeOrMakeAndValidate("type", "bicyle", "mountain", "road", "hybrid", "kids");
            electric = electric();
            basket = storage("bicycle", "a basket");
            // (8.3) Calculate price of sale
            price = calculateBicPrice(noOfWheels, electric, basket, bicType);
            // (8.4) Create custom order
            bc = new Bicycle(name,stock,price,colour,noOfWheels,"Bicycle",bicType,electric,basket);
            // (8.5) Add to custom order array
            customOrders.add(bc);
            // (8.6) Display information for this order
            bc.display();
        } else {
            motMake = inputTypeOrMakeAndValidate("make", "motorbike", "Harley Davidson", "Ducati", "Yamaha", "Honda");
            engSize = inputEngineSize();
            noOfSeats = inputWheelsOrSeatsAndValidate("motorbike", "seats", 1, 2);
            storage = storage("motorbike", "storage");

            // (8.3) Calculate price of sale
            price = calculateMotPrice(noOfWheels,noOfSeats,storage, motMake,engSize);
            // (8.4) Create custom order
            mb = new Motorbike(name,stock,price,colour,noOfWheels,"Motorbike",motMake,engSize,noOfSeats,storage);
            // (8.5) Add to custom order array
            customOrders.add(mb);
            // (8.6) Display information for this order
            mb.display();
        }
    }

    public double calculateBicPrice(int noOfWheels,boolean electric, boolean basket, String type)     {
        double customOrd = 200;

        if(noOfWheels == 3)
            customOrd = customOrd + 50;
        else if(noOfWheels == 4)
            customOrd = customOrd + 100; 
        else
            customOrd = customOrd + 0;
        
        if(electric == true)
            customOrd = customOrd + 100;
        else
            customOrd = customOrd + 0;
        
        if(basket == true)
            customOrd = customOrd + 25;
        else
            customOrd = customOrd + 0;

        if(type.equalsIgnoreCase("Mountain") || type.equalsIgnoreCase("Hybrid"))
            customOrd = customOrd + 100;
        else if(type.equalsIgnoreCase("Kids"))
            customOrd = customOrd + 50; 
        else
            customOrd = customOrd + 0; 

        return customOrd;
    }

    public double calculateMotPrice(int noOfWheels,int noOfSeats,boolean storage, String make, int engSize) {
        double customOrd = 5000;

        if(noOfWheels == 3)
            customOrd = customOrd + 50;
        else if(noOfWheels == 4)
            customOrd = customOrd + 100; 
        else
            customOrd = customOrd + 0;

        if(noOfSeats == 2)
            customOrd = customOrd + 100;
        else
            customOrd = customOrd + 0;

        if(storage == true)
            customOrd = customOrd + 50;
        else
            customOrd = customOrd + 0;

        if(make.equalsIgnoreCase("Harley Davidson") )
            customOrd = customOrd + 6000;
        else if(make.equalsIgnoreCase("Ducati"))
            customOrd = customOrd + 4000; 
        else
            customOrd = customOrd + 2000; 

        if(engSize <= 1000 && engSize > 500)
            customOrd = customOrd + 1000;
        else if(engSize <= 500 && engSize > 250)    
            customOrd = customOrd + 500;
        else if(engSize <= 250)
            customOrd = customOrd + 0;
            
        return customOrd;
    }

    // (9) Display Staff Menu
    public void staffMenu() {
        Scanner scan = new Scanner(System.in);
        int option;
        String delay;

        do {
            System.out.print("\f");
            System.out.println("Staff Menu");
            System.out.println("1. Add new type of bicycle");
            System.out.println("2. Add new type of motorbike");
            System.out.println("3. Update stock of bike from delivery");
            System.out.println("4. Display custom orders");
            System.out.println("5. Return to Main Menu");

            System.out.println("\nEnter a menu option: ");
            option = scan.nextInt();
            scan.nextLine();

            if(option == 1)
                addNewBicycle();
            else if(option == 2)
                addNewMotorbike();
            else if(option == 3)
                addNewStock();
            else if(option == 4)
                displayCustomBike();
            else
                System.out.println("Returning to Main Menu");

            System.out.println("\n\n\nPress return to continue....");
            delay = scan.nextLine();
        }while(option !=5);
    }

    // (10) Add new type of bicycle to the stock list 
    public void addNewBicycle() {
        Scanner scan = new Scanner(System.in);
        System.out.print("\f");
        String name, colour, type;
        int stock, wheels;
        double price;
        boolean electric = false;
        boolean basket = false;
        Bicycle bc;

        System.out.print("\n\nAdding a new type of bicycle");
        // Input bicycle information
        name = inputNameOrColour("bicycle","name");
        stock = inputStock("bicycle");
        price = inputPrice("bicycle");
        colour = inputNameOrColour("bicycle","colour");
        wheels = inputWheelsOrSeatsAndValidate("bicycle", "wheels", 2, 4);
        type = inputTypeOrMakeAndValidate("type", "bicyle", "mountain", "road", "hybrid", "kids");
        electric = electric();
        basket = storage("bicycle", "a basket");

        // (10.3) Create a bicycle
        bc = new Bicycle(name,stock,price,colour,wheels,"Bicycle",type,electric, basket);
        // (10.4) Add bicycle to arraylist
        listOfBikes.add(bc);
        // (10.5) Display bicycle information 
        System.out.print("\f");
        bc.display();
    }

    // (11) Add new type of motorbike to the stock list 
    public void addNewMotorbike() {
        Scanner scan = new Scanner(System.in);
        System.out.print("\f");
        String name, colour, make;
        int stock, wheels, size, seats;
        double price;
        boolean storage = false;        

        // Input motorbike information
        System.out.print("\n\nAdding a new type of motorbike");
        name = inputNameOrColour("motorbike","name");

        stock = inputStock("motorbike");
        price = inputPrice("motorbike");
        colour = inputNameOrColour("motorbike","colour");
        wheels = inputWheelsOrSeatsAndValidate("motorbike", "wheels", 2, 4);
        make = inputTypeOrMakeAndValidate("make", "motorbike", "Harley Davidson", "Ducati", "Yamaha", "Honda");
        size = inputEngineSize();
        seats = inputWheelsOrSeatsAndValidate("motorbike", "seats", 1, 2);
        storage = storage("motorbike", "storage");

        // Create a motorbike
        Motorbike mb = new Motorbike(name,stock,price,colour,wheels,"Motorbike",make,size,seats,storage);
        // Add motorbike to arraylist
        listOfBikes.add(mb);
        // Display motorbike information
        System.out.print("\f");
        mb.display();
    }

    // (10.1) & (10.2) Ask for the name and different variables for the bicycle
    public String inputNameOrColour(String bike, String info) {
        Scanner scan = new Scanner(System.in);
        String name;

        System.out.println("\tPlease input the " + info + " of the " + bike + " here: ");
        name = scan.nextLine();

        return name;
    }

    public int inputStock(String bike) {
        Scanner scan = new Scanner(System.in);
        int stock;
        System.out.println("Please input how many of the " + bike + " are in stock here: ");
        stock = scan.nextInt();
        return stock;
    }

    public double inputPrice(String bike) {
        Scanner scan = new Scanner(System.in);
        double price;
        System.out.println("Please input the price of the " + bike + " here: ");
        price = scan.nextDouble();
        return price;
    }

    public int inputWheelsOrSeatsAndValidate(String bike, String info, int one, int two) {
        Scanner scan = new Scanner(System.in);
        int wheels;
        do {
            System.out.println("Please input the number of "+ info + " on the " + bike + " here (Between " + one + " and " + two + "): ");
            wheels = scan.nextInt();
        }while(wheels < one || wheels > two);
        return wheels;
    }

    public String inputTypeOrMakeAndValidate(String info, String bike, String t1, String t2, String t3, String t4) {
        Scanner scan = new Scanner(System.in);
        String type;
        do {
            System.out.println("Please input the " + info + " of " + bike + " (" + t1 + ", " + t2 + ", " + t3 + " or " + t4 + ") here: ");
            type = scan.nextLine();
        }while(!type.equalsIgnoreCase(t1) && !type.equalsIgnoreCase(t2) && !type.equalsIgnoreCase(t3) && !type.equalsIgnoreCase(t4));
        return type;
    }

    public boolean storage(String bike, String stor) {
        Scanner scan = new Scanner(System.in);
        String answer;
        boolean storage = false;
        do {
            System.out.println("Does the " + bike + " have " + stor + " (Yes/No): ");
            answer = scan.nextLine();
        }while(!answer.equalsIgnoreCase("Yes") && !answer.equalsIgnoreCase("No"));

        if(answer.equalsIgnoreCase("Yes"))
            storage = true;

        return storage;
    }

    public boolean electric() {
        Scanner scan = new Scanner(System.in);
        String answer;
        boolean electric = false;
        do {
            System.out.println("Is the bicycle electric (Yes/No): ");
            answer = scan.nextLine();
        }while(!answer.equalsIgnoreCase("Yes") && !answer.equalsIgnoreCase("No"));

        if(answer.equalsIgnoreCase("Yes"))
            electric = true;

        return electric;
    }

    public int inputEngineSize() {
        Scanner scan = new Scanner(System.in);
        int size;
        System.out.println("Please input the engine size of the motorbike here: ");
        size = scan.nextInt();
        return size;
    }

    // (12) Add new stock to the stock list 
    public void addNewStock() {
        Scanner scan = new Scanner(System.in);
        String bikeName;
        int delivery;

        System.out.print("\f");
        System.out.println("\nUpdate stock from delivery");

        // (12.1) Ask for the name of the bicycle/motorbike
        System.out.println("Please enter the name of the bike that is being delivered here: ");
        bikeName = scan.nextLine();

        Bike myBike = getBikeByName(bikeName);
        if(myBike == null) {
            System.out.println("Sorry, that bike does not exist here.");
        }
        else {        
            // (12.2) Ask how many bicycles/motorbikes have arrived
            System.out.println("How many bikes have been delivered? ");
            delivery = scan.nextInt();

            // (12.3) Update stock levels
            myBike.setBikeStock(myBike.getBikeStock() + delivery);

            // (12.4) Display new stock levels
            System.out.println("There is now " + myBike.getBikeStock() + " of this bike in stock.");
        }
    }

    // Finding and returning the bike with the same name as the bike that is being delivered
    public Bike getBikeByName(String bikeName)     {
        for(Bike b : listOfBikes)         {
            if(b.getName().equalsIgnoreCase(bikeName)) {
                return b;
            }
        }
        return null;
    }

    // (13) Display Custom Order
    public void displayCustomBike()     {
        System.out.print("\f");
        System.out.println("\nDisplaying custom order");
        for (Bike c : customOrders)         {
            if ( c instanceof Bike)
                c.display();
        }
    }

    // (14) Save stock from program into a data file
    public void saveToFile() {
        ObjectOutputStream fileOut;

        try{
            fileOut = new ObjectOutputStream(new FileOutputStream("bike.dat"));
            for (Bike b : listOfBikes) {
                fileOut.writeObject(b);
            }
            fileOut.close();
        }
        catch (IOException e) {
            System.out.println("Sorry, no room left in storage.");
        }
    }

    public boolean readFromFile() {
        int index = 0;
        ObjectInputStream fileIn;
        Bike aBike;

        try{
            fileIn = new ObjectInputStream(new FileInputStream("bike.dat"));
            System.out.println("Created bike successfully");
            aBike = (Bike) fileIn.readObject();
            index = 1;
            while (aBike != null) {
                listOfBikes.add(aBike);
                aBike = (Bike) fileIn.readObject();
                index++;
            }
            fileIn.close();
            return true;
        }
        catch (IOException e) {
            if(index > 0) {
                numberBikes = index;
                return true;
            } else {
                System.out.println("Bikes have not been created yet, you will need to input the bike information\n");
                return false;
            }
        }
        catch (ClassNotFoundException e) {
            System.out.println("Class Error : " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        new Driver();
    }

}
