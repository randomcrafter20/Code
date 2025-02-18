

import java.util.*;

public class Person{
    //implement your code here
    public String firstName;
    public String lastName;
    public String number;
    public String address;//Attribute for address
    public Person(String firstName, String lastName, String number, String address){
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.address = address;
    }
    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public String getNumber(){return number;}
    public String getAddress(){return address;}

    public void setFirstName(String newFirstName){firstName = newFirstName;}
    public void setLastName(String newLastName){lastName = newLastName;}

    public void setEmail(String newAddress) {address = newAddress;}
    public void setNumber(String newNumber){number = newNumber;}

    public String toString(){
        return "\nFirst Name: "+ firstName +
                "\nLast Name: "+ lastName +
                "\nPhone Number: "+ number +
                "\nAddress: "+ address;
    }
    public boolean equals(Object o){
        if (!(o instanceof Person)) {
            return false;
        }
        // Cast to Person
        Person other = (Person) o;
        // Compare last names
        if (lastName.equals(other.lastName)) {
            if(firstName.equals(other.firstName)){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }


}
/*must add an extra instance variable
modify the required method accordingly*/
class Passenger extends Person{
    //implement your code here
    private int seatNumber;
    private String classType;
    private String ticketId;
    private String mealPreference;//Attribute for meal preference of the passenger

    public Passenger(String firstName, String lastName, String number, String address, int seatNumber, String classType, String ticketId, String mealPreference){
        super(firstName, lastName, number, address);
        this.seatNumber = seatNumber;
        this.classType = classType;
        this.ticketId = ticketId;
        this.mealPreference = mealPreference;

    }
    public int getSeat() {
        return seatNumber;
    }

    public void changeSeatNumber(int newSeatNumber) {
        seatNumber = newSeatNumber;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String newClassType) {
        classType = newClassType;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String newTicketId) {
        ticketId = newTicketId;
    }

    public String getMealPreference() {
        return mealPreference;
    }

    public void setMealPreference(String newMealPreference) {
       mealPreference = newMealPreference;
    }
    public String toString() {
        return "\nTicket ID: " + ticketId +
                "\nSeat: " + seatNumber +
                "\nClass: " + classType +
                "\nMeal Preference: " + mealPreference;
    }
}
/*
Must add one extra method to this interface.
*/
interface list {
    public boolean add(Object o);
    public Object search(Object o);
    public boolean delete(Object o);
    public void printLast();
    public void takeOff();
    public void landed();
}
/*must add an extra instance variable
modify the required method accordingly*/

class Airplane implements list{
    //declare the instance variable
    private static int count = 0;
    private Passenger[] plane;
    private boolean takenOff;
    private boolean landed;
    private int planeNum;
    private String destination;

    public Airplane(int planeNum, String destination) {
        this.plane = new Passenger[10];
        this.takenOff = false;
        this.planeNum = planeNum;
        this.destination = destination;
    }


    public int getPlaneNumber() {
        return planeNum;
    }

    public void setPlaneNumber(int newPlaneNum) {
        planeNum = newPlaneNum;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String newDestination) {
        destination = newDestination;
    }

    public static int getCount() {
        return count;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Destination: ").append(destination).append("\n"); // Include destination in the output
        for (Passenger p : plane) {
            if (p != null) {
                sb.append(p.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    // Implement methods from List interface as in the previous implementation
    public boolean add(Object o) {
        if (!(o instanceof Passenger)) return false;
        if (takenOff) return false;

        Passenger passenger = (Passenger) o;
        for (int i = 0; i < plane.length; i++) {
            if (plane[i] == null) {
                plane[i] = passenger;
                count++;
                return true;
            }
        }
        return false;
    }


    public Object search(Object o) {
        if (!(o instanceof String)) return null;
        String lastName = (String) o;

        for (Passenger passenger : plane) {
            if (passenger != null && passenger.getLastName().equalsIgnoreCase(lastName)) {
                return passenger;
            }
        }
        return null;
    }


    public boolean delete(Object o) {
        if (!(o instanceof String)) return false;
        String lastName = (String) o;

        for (int i = 0; i < plane.length; i++) {
            if (plane[i] != null && plane[i].getLastName().equalsIgnoreCase(lastName)) {
                plane[i] = null;
                count--;
                return true;
            }
        }
        return false;
    }

    public void printLast() {
        for (Passenger passenger : plane) {
            if (passenger != null) {
                System.out.println(passenger.getLastName());
            }
        }
    }

    public void takeOff() {
        takenOff = true;
    }
    public void landed(){
        if (takenOff) {
            landed = true;
            takenOff = false;
            System.out.println("The plane has landed at " + destination + ".");
        } else {
            System.out.println("The plane hasn't taken off yet, so it can't land.");
        }
    }


}
/* Do not delete the given driver , The class Driver must be in your code when you submit it
Once you complete all the classes uncommnet the given driver to test your code.*/

class Driver {
   public static void main(String[]args) {
      Scanner in = new Scanner(System.in);

      Scanner kb = new Scanner(System.in);
      Airplane plane = new Airplane(817345, "Toronto");
      Passenger p1 = new Passenger("Bobbys", "Smith", "123456789","123, Stollmore" ,1, "First class", "916-222-3333", "Veg");
      Passenger p2 = new Passenger("Johnny", "Apples", "987654321","352, Block Drive" ,8, "Business class", "818-000-1234", "Vegan");
      Passenger p3 = new Passenger("Tommy", "Jerrys", "567123489","793, Corbrone Ave" ,32, "Economy class","202-222-3333", "Non-Veg No nuts");
      Passenger p4 = new Passenger("Candy", "Cruze", "982134567","629, Flianders Ct" ,15, "Premium Economy class","707-444-5555", "Halal");

      //this passenger will be added after take off
      Passenger p5 = new Passenger("Kalotiii", "Aaronn", "762134589","832, Fast Lane" ,5, "Economy plus","817-222-6666","Vegan");

      plane.add(p1);
      plane.add(p2);
      plane.add(p3);
      plane.add(p4);
      System.out.println("The plane is about to take off");
      plane.takeOff();
      System.out.println("adding a passenger after take off");
      plane.add(p5);
      String repeat = "";
      while(plane.getCount() > 0)
      {

         System.out.println("Here is the list of the passengers in this plane");
         System.out.println("There are " + plane.getCount() + " Passengers on this plane");
         System.out.println(plane + "\n");

         System.out.println("Testing the printLast method to display the last names");
         plane.printLast();
         System.out.println();

         System.out.println("Testing the static method getCount");
         System.out.println("This train has " + plane.getCount() + " Passengers\n");

         System.out.print("Enter the last name of the passenger to search for: ");
         String lastName = in.nextLine();
         System.out.println(plane.search(lastName));
         System.out.println();

         System.out.println("Testing the delete method");
         System.out.print("Enter the last name of the passenger to be deleted: ");
         String last = in.nextLine();
         plane.delete(last);
         System.out.println("Passenger " + last + " has been removed from the list\n");

         System.out.println("Here is the updated list");
         System.out.println(plane);
         System.out.println("*********************");
         System.out.print("Press any key to continue : ");
         repeat = kb.nextLine();
      }
      System.out.println("No passenger left on this airplane");

   }
}
/*Complete the following driver to include the required code.*/
/*20 points is allocated for this driver*/
class yourDriver
{
    public static void main(String[] args) {
        // Instantiate an airplane with a destination
        Scanner scanner = new Scanner(System.in);
        String repeat = "";
        Airplane united = new Airplane(101, "New York");

        // Create 5 passengers with meal preferences
        Passenger p1 = new Passenger("John", "Doe", "555-1234", "123 Street", 1, "Economy", "ID123", "Vegetarian");
        Passenger p2 = new Passenger("Jane", "Armen", "555-2345", "456 Avenue", 2, "Business", "ID124", "Non-Vegetarian");
        Passenger p3 = new Passenger("Emily", "Johnson", "555-3456", "789 Boulevard", 3, "First Class", "ID125", "Vegan");
        Passenger p4 = new Passenger("Michael", "Brown", "555-4567", "1010 Drive", 4, "Premium Economy", "ID126", "Gluten-Free");
        Passenger p5 = new Passenger("Sarah", "Williams", "555-5678", "1111 Road", 5, "Economy", "ID127", "Kosher");


        // Add passengers to the plane
        united.add(p1);
        united.add(p2);
        united.add(p3);
        united.add(p4);
        united.add(p5);
        while (united.getCount() > 0) {

            // Print all passenger details
            System.out.println("Passenger List:");
            System.out.println(united);

            System.out.println("\nChecking if two passengers are the same:");
            boolean isEqual = p1.equals(p2); // Comparing p1 and p2
            System.out.println("Is " + p1.getFirstName() + " " + p1.getLastName() + " equal to " + p2.getFirstName() + " " + p2.getLastName() + "? " + isEqual);

            // Search for a passenger by last name
            System.out.print("Enter last name to search for a passenger: ");
            String lastNameToSearch = scanner.nextLine();
            Passenger found = (Passenger) united.search(lastNameToSearch);
            if (found != null) {
                System.out.println("Passenger Found: " + found);
            } else {
                System.out.println("Passenger not found.");
            }

            // Delete a passenger
            System.out.print("Enter last name to delete a passenger: ");
            String lastNameToDelete = scanner.nextLine();
            boolean deleted = united.delete(lastNameToDelete);
            if (deleted) {
                System.out.println("Passenger " + lastNameToDelete + " deleted successfully.");
            } else {
                System.out.println("Passenger not found for deletion.");
            }

            // Print last names of all passengers
            System.out.println("Last names of passengers:");
            united.printLast();

            // Take off the plane
            united.takeOff();
            System.out.println("Plane is in the air.");

            // Attempt to add a passenger after takeoff
            Passenger p6 = new Passenger("David", "Clark", "555-6789", "2222 Lane", 6, "Economy", "ID128", "Halal");
            System.out.println("Attempting to add a passenger after takeoff:");
            united.add(p6); // This should fail

            // Land the plane
            united.landed();
            System.out.println("Plane has landed.");

            // Attempt to take off again after landing
            united.takeOff();// This should fail
            System.out.print("Press any key to continue : ");
            repeat = scanner.nextLine();
        }
    }
}
