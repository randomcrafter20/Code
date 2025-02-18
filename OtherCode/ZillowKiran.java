
import java.util.Scanner;

public class MyDriver {
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
