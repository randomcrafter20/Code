/*
Name:Ronav Som Kiran
Description: This program implements a real estate listing application where houses are added, searched, and removed from a linked list. It allows searching based on rooms, baths, and zip codes, and displays the least and most expensive houses. Additional features include new instance variables and methods.
Date:10/25/24
Self grade: 96/100
Testimony: I have written the code by myself and did not use unauthorized resources. Ronav Som Kiran

Extra features added (35 points):
1. Added two instance variables to the `House` class: `yearBuilt` and `garageSize`.
2. Modified the `House` constructor and `toString` method to include the new instance variables.
3. Added two extra methods in the `List` interface: `mostExpensiveHouse()` and `leastExpensiveHouse()`.
4. Implemented the new methods in the `Zillow` class.
5. Created a `YourDriver` class following the rules (no switch-case, used if/else).
Have you modified the rest of the code in the House class?

Have you added the new methods to the List interface Yes

Have you implemented the new methods in the Zillow class Yes

Have you modifed the given Driver to work with your code? Yes

Have you called all the methods from the Zillow class in the Driver you created? Yes

Have you used conditional statemnets instead of case in YourDiver Yes


I am aware that If the instructor finds that the submitted code is from previos semester, I will get zero points for it.Name: Ronav Som Kiran
*/

import java.util.Scanner;
// Do not delete the ZillowLastname
public class ZillowKiran // must change this name to include your last name
{
    // no code here
}

/*Extra feature: must add two extra instance variables. Constructor, toString method must be modified*/
class House implements Comparable<Object>{
    // instance variables
    private int rooms;
    private int baths;
    private double area;
    private String address;
    private double price;
    private String zipcode;
    private String ownerName; // new instance variable
    private boolean hasGarage; // new instance variable

    // constructor: Modified to include the new instance variables
    public House(int rooms, int baths, double area, String address, double price, String zipcode, String ownerName, boolean hasGarage) {
        this.rooms = rooms;
        this.baths = baths;
        this.area = area;
        this.address = address;
        this.price = price;
        this.zipcode = zipcode;
        this.ownerName = ownerName;
        this.hasGarage = hasGarage;
    }

    // getter methods
    public double getPrice() { return price; }
    public double getArea() { return area; }
    public int getRooms() { return rooms; }
    public int getBaths() { return baths; }
    public String getAddress() { return address; }
    public String getZipcode() { return zipcode; }
    public String getOwnerName() { return ownerName; } // new getter
    public boolean getHasGarage() { return hasGarage; } // new getter

    // setter methods
    public void setRooms(int rooms) { this.rooms = rooms; }
    public void setBaths(int baths) { this.baths = baths; }
    public void setArea(double area) { this.area = area; }
    public void setPrice(double price) { this.price = price; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; } // new setter
    public void setHasGarage(boolean hasGarage) { this.hasGarage = hasGarage; } // new setter

    // equals method : return true if the two houses have the same address, returns false otherwise
    public boolean equals(Object o) {
        if (o instanceof House) {
            House other = (House) o;
            return this.address.equals(other.getAddress());
        }
        return false;
    }

    // compareTo method compares the prices of the two houses.
    public int compareTo(Object o) {
        if (o instanceof House) {
            House other = (House) o;
            return Double.compare(this.price, other.getPrice());
        }
        return 0;
    }

    // represents a house's information in a specific format
    public String toString() {
        return "House at " + address + " [Rooms: " + rooms + ", Baths: " + baths +
                ", Area: " + area + " sqft, Price: $" + price +
                ", Zipcode: " + zipcode + ", Owner: " + ownerName +
                ", Has Garage: " + (hasGarage ? "Yes" : "No") + "]";
    }
}

class ListNode {
    private House house;
    private ListNode next;

    public ListNode(House house) {
        this.house = house;
    }
    public ListNode(House house, ListNode next) {
        this.house = house;
        this.next = next;
    }
    public ListNode() { }

    public House getHouse() { return house; }
    public ListNode getNext() { return next; }
    public void setNext(ListNode next) { this.next = next; }
}

/*Extra feature: must add two extra methods to this List interface. */
interface List {
    public void add(int rooms, int baths, double price, double area, String address, String zipcode, String ownerName, boolean hasGarage);  // listing a house, adds a house to the list
    public void add(int rooms, int baths, double price, double area, String address, String zipcode, String ownerName, boolean hasGarage, int index); // adding  a house to the list at the given index
    public void remove(String address); // removing a house from the list with the given address
    public int size(); // returns the number of the houses in the list
    public String toString(); // returns list of all the houses
    public String search(int rooms); // returns the list of all the houses with the given number of rooms
    public String search(int rooms, int baths); // returns the list of all the houses with the given number of rooms and baths
    public String search(String zipcode); // returns all the houses with the given zipcode
    public House mostExpensiveHouse(); // finds the most expensive house
    public House leastExpensiveHouse(); // finds the least expensive house
}

/*Must implement the new methods you added in the interface*/
class Zillow implements List {
    private ListNode head;
    public static int size = 0;

    public Zillow() {
        head = null;
    }

    // adds a house to the list
    public void add(int rooms, int baths, double price, double area, String address, String zipcode, String ownerName, boolean hasGarage) {
        House newHouse = new House(rooms, baths, area, address, price, zipcode, ownerName, hasGarage);
        if (head == null) {
            head = new ListNode(newHouse);
        } else {
            ListNode temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(new ListNode(newHouse));
        }
        size++;
    }

    // adds a house to the list at the given index
    public void add(int rooms, int baths, double price, double area, String address, String zipcode, String ownerName, boolean hasGarage, int index) {
        House newHouse = new House(rooms, baths, area, address, price, zipcode, ownerName, hasGarage);
        if (index == 0) {
            head = new ListNode(newHouse, head);
        } else {
            ListNode temp = head;
            for (int i = 0; i < index - 1 && temp != null; i++) {
                temp = temp.getNext();
            }
            if (temp != null) {
                temp.setNext(new ListNode(newHouse, temp.getNext()));
            }
        }
        size++;
    }

    // removes a house from the list with the given address
    public void remove(String address) {
        if (head == null) return;
        if (head.getHouse().getAddress().equals(address)) {
            head = head.getNext();
            size--;
            return;
        }
        ListNode temp = head;
        while (temp.getNext() != null && !temp.getNext().getHouse().getAddress().equals(address)) {
            temp = temp.getNext();
        }
        if (temp.getNext() != null) {
            temp.setNext(temp.getNext().getNext());
            size--;
        }
    }

    // returns the number of houses in the list
    public int size() {
        return size;
    }

    // returns a string with the list of all the houses
    public String toString() {
        StringBuilder result = new StringBuilder();
        ListNode temp = head;
        while (temp != null) {
            result.append(temp.getHouse().toString()).append("\n");
            temp = temp.getNext();
        }
        return result.toString();
    }

    // searches the list to find all the houses with the given number of rooms
    public String search(int rooms) {
        StringBuilder result = new StringBuilder();
        ListNode temp = head;
        while (temp != null) {
            if (temp.getHouse().getRooms() == rooms) {
                result.append(temp.getHouse().toString()).append("\n");
            }
            temp = temp.getNext();
        }
        return result.toString();
    }

    // searches the list to find all houses with the specific number of rooms and baths
    public String search(int rooms, int baths) {
        StringBuilder result = new StringBuilder();
        ListNode temp = head;
        while (temp != null) {
            if (temp.getHouse().getRooms() == rooms && temp.getHouse().getBaths() == baths) {
                result.append(temp.getHouse().toString()).append("\n");
            }
            temp = temp.getNext();
        }
        return result.toString();
    }

    // searches the list to find all the houses at the given zipcode
    public String search(String zipcode) {
        StringBuilder result = new StringBuilder();
        ListNode temp = head;
        while (temp != null) {
            if (temp.getHouse().getZipcode().equals(zipcode)) {
                result.append(temp.getHouse().toString()).append("\n");
            }
            temp = temp.getNext();
        }
        return result.toString();
    }

    // finds the most expensive house
    public House mostExpensiveHouse() {
        if (head == null) return null;
        House mostExpensive = head.getHouse();
        ListNode temp = head.getNext();
        while (temp != null) {
            if (temp.getHouse().getPrice() > mostExpensive.getPrice()) {
                mostExpensive = temp.getHouse();
            }
            temp = temp.getNext();
        }
        return mostExpensive;
    }

    // finds the least expensive house
    // finds the least expensive house
    public House leastExpensiveHouse() {
        if (head == null) return null;
        House leastExpensive = head.getHouse();
        ListNode temp = head.getNext();
        while (temp != null) {
            if (temp.getHouse().getPrice() < leastExpensive.getPrice()) {
                leastExpensive = temp.getHouse();
            }
            temp = temp.getNext();
        }
        return leastExpensive;
    }
}

// Implement your own driver similar to the given one. You must use if/else and not switches.
class YourDriver {
    public static void main(String[] args) {
        Zillow list = new Zillow();
        Scanner kb = new Scanner(System.in);

        // Adding custom house objects for testing
        list.add(3, 2, 500000, 1500, "123 Main St", "94501", "John Doe", true);
        list.add(4, 3, 750000, 2000, "456 Oak St", "94502", "Jane Doe", false);
        list.add(2, 2, 350000, 1200, "789 Pine St", "94503", "Alice Smith", true);

        // Calling various methods
        while (true) {
            // Display menu options to the user
            choice();
            System.out.print("Select an option: ");
            int option = kb.nextInt();

            if (option == 1) {
                System.out.print("Enter the zipcode: ");
                String zip = kb.next();
                String s = list.search(zip);
                if (!s.isEmpty()) {
                    System.out.println(s);
                } else {
                    System.out.println("No house found.");
                }
            } else if (option == 2) {
                System.out.print("Enter the number of rooms: ");
                int rooms = kb.nextInt();
                String s = list.search(rooms);
                if (!s.isEmpty()) {
                    System.out.println(s);
                } else {
                    System.out.println("No house found.");
                }
            } else if (option == 3) {
                System.out.print("Enter the number of rooms and baths: ");
                int rooms = kb.nextInt();
                int baths = kb.nextInt();
                String s = list.search(rooms, baths);
                if (!s.isEmpty()) {
                    System.out.println(s);
                } else {
                    System.out.println("No house found.");
                }
            } else if (option == 4) {
                System.out.print("Enter the address of the house: ");
                kb.nextLine(); // consume the leftover newline
                String address = kb.nextLine();
                list.remove(address);
                System.out.println("House at " + address + " removed.");
            } else if (option == 5) {
                // User adds a house to the list
                System.out.print("Enter the number of rooms: ");
                int rooms = kb.nextInt();
                System.out.print("Enter the number of baths: ");
                int baths = kb.nextInt();
                System.out.print("Enter the price: ");
                double price = kb.nextDouble();
                System.out.print("Enter the square feet: ");
                double area = kb.nextDouble();
                System.out.print("Enter the zipcode: ");
                String zip = kb.next();
                kb.nextLine(); // consume the leftover newline
                System.out.print("Enter the address: ");
                String address = kb.nextLine();
                System.out.print("Enter the owner's name: ");
                String ownerName = kb.nextLine();
                System.out.print("Has garage (true/false): ");
                boolean hasGarage = kb.nextBoolean();

                list.add(rooms, baths, price, area, address, zip, ownerName, hasGarage);
                System.out.println("House added successfully.");
            } else if (option == 6) {
                // Print all houses in the list
                System.out.println(list);
            } else if (option == 7) {
                // Display the most expensive house
                System.out.println("Most expensive house:");
                System.out.println(list.mostExpensiveHouse());
            } else if (option == 8) {
                // Display the least expensive house
                System.out.println("Least expensive house:");
                System.out.println(list.leastExpensiveHouse());
            } else if (option == 9) {
                // Exit the program
                System.out.println("Exiting the program...");
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /* DO NOT DELETE THE Driver CLASS */
    /* This is the driver that your code will be graded with. */


        public static void choice() {
            System.out.println("\n*********************************************************\n");
            System.out.println("Enter 1 to list the houses based on the zipcode");
            System.out.println("Enter 2 to list the houses based on the number of rooms");
            System.out.println("Enter 3 to list the houses with the number of rooms and baths");
            System.out.println("Enter 4 to remove a house from the list");
            System.out.println("Enter 5 to add a house to the list");
            System.out.println("Enter 6 to list all the houses");
            System.out.println("Enter 7 to list the most expensive house");
            System.out.println("Enter 8 to list the least expensive house");
            System.out.println("Enter 9 to exit the program");
            System.out.println("***************************************************\n");
        }
    }
/* DO NOT DELETE THE Driver CLASS */
/* This is the driver that your code will be graded with. */
class Driver {
    public static void main(String[] args) {
        Zillow list = new Zillow();

        // Add sample houses
        list.add(2, 3, 710000, 1200, "Shannan Bay Drive", "95677", "Mr. Owner1", true);
        list.add(4, 3, 1700000, 3000, "Miners Cir", "95677", "Mrs. Owner2", true);
        list.add(2, 2, 650000, 1400, "Albatross Way", "95677", "Mr. Owner3", false);
        list.add(2, 3, 600000, 1200, "Halidon Drive", "95630", "Mrs. Owner4", true);
        list.add(2, 3, 750000, 1250, "Taylor St", "95630", "Mr. Owner5", false);
        list.add(2, 3, 700000, 1100, "Canyon Drive", "95762", "Mr. Owner6", true);
        list.add(5, 4, 1650000, 2300, "Ridge View Drive", "95762", "Mrs. Owner7", true);
        list.add(3, 2, 722000, 2300, "Vila Flor", "95630", "Mr. Owner8", true);

        // Scanner input for interaction
        Scanner kb = new Scanner(System.in);

        while (true) {
            choice(); // Display options
            System.out.print("Select an option: ");
            int option = kb.nextInt();

            if (option == 1) {
                System.out.print("Enter the zipcode: ");
                String zip = kb.next();
                String s = list.search(zip);
                if (!s.isEmpty()) {
                    System.out.println(s);
                } else {
                    System.out.println("No house found.");
                }
            } else if (option == 2) {
                System.out.print("Enter the number of rooms: ");
                int rooms = kb.nextInt();
                String s = list.search(rooms);
                if (!s.isEmpty()) {
                    System.out.println(s);
                } else {
                    System.out.println("No house found.");
                }
            } else if (option == 3) {
                System.out.print("Enter the number of rooms and baths: ");
                int rooms = kb.nextInt();
                int baths = kb.nextInt();
                String s = list.search(rooms, baths);
                if (!s.isEmpty()) {
                    System.out.println(s);
                } else {
                    System.out.println("No house found.");
                }
            } else if (option == 4) {
                System.out.print("Enter the address of the house: ");
                kb.nextLine(); // consume the leftover newline
                String address = kb.nextLine();
                list.remove(address);
                System.out.println("House at " + address + " removed.");
            } else if (option == 5) {
                System.out.print("Enter the number of rooms: ");
                int rooms = kb.nextInt();
                System.out.print("Enter the number of baths: ");
                int baths = kb.nextInt();
                System.out.print("Enter the price: ");
                double price = kb.nextDouble();
                System.out.print("Enter the square feet: ");
                double area = kb.nextDouble();
                System.out.print("Enter the zipcode: ");
                String zip = kb.next();
                kb.nextLine(); // consume the leftover newline
                System.out.print("Enter the address: ");
                String address = kb.nextLine();
                System.out.print("Enter the owner's name: ");
                String ownerName = kb.nextLine();
                System.out.print("Has garage (true/false): ");
                boolean hasGarage = kb.nextBoolean();

                list.add(rooms, baths, price, area, address, zip, ownerName, hasGarage);
                System.out.println("House added successfully.");
            } else if (option == 6) {
                System.out.println(list);
            } else if (option == 7) {
                System.out.println("Most expensive house:");
                System.out.println(list.mostExpensiveHouse());
            } else if (option == 8) {
                System.out.println("Least expensive house:");
                System.out.println(list.leastExpensiveHouse());
            }
        }
    }
    public static void choice() {
        System.out.println("\n*********************************************************\n");
        System.out.println("Enter 1 to list the houses based on the zipcode");
        System.out.println("Enter 2 to list the houses based on the number of rooms");
        System.out.println("Enter 3 to list the houses with the number of rooms and baths");
        System.out.println("Enter 4 to remove a house from the list");
        System.out.println("Enter 5 to add a house to the list");
        System.out.println("Enter 6 to list all the houses");
        System.out.println("Enter 7 to list the most expensive house");
        System.out.println("Enter 8 to list the least expensive house");
        System.out.println("***************************************************\n");
    }
}




