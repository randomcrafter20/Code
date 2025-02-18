package view;

import java.nio.file.attribute.DosFileAttributes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import controller.DBConnect;

public class Display {

    public void menu(int count) {
        System.out.println("Welcome to my Library Management System");
        System.out.println("There are " +count +" books in the library!");
        Statement stmt = null;
        Scanner sc = new Scanner(System.in); // create a Scanner object
        System.out.println("Enter your Name");
        String username = sc.nextLine(); // read a string

        System.out.println("Hello " + username +"!");
        System.out.println("How do you want to lookup a book? ");
        System.out.println("Enter '1' for Lookup by Author ");
        System.out.println("Enter '2' for Lookup by BookName ");
        System.out.println("Enter '3' to return a book ");
        System.out.println("Enter '4' to add a book to the library ");
        System.out.println("Enter '5' to show all books checkout to you");
        System.out.println("Enter '6' to exit ");
        int choice = sc.nextInt();
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        // Connect to the Model
        DBConnect db = new DBConnect("library.db");
        db.setupconnection();

        do {
            if(choice == 1){
                System.out.println("Enter the Author you want to Lookup");
                String author1 = sc2.nextLine();
                db.lkup_by_author(author1);
                Scanner sc3 = new Scanner (System.in); // create a Scanner object
                System.out.println("Do you want to check out a book enter Y and N");
                String ret = sc3.nextLine();
                String ret1 = "y";

                if (ret.equalsIgnoreCase(ret1)) {
                    System.out.println("Enter the ID of the book that you want to checkout \n");
                    int bookID = sc3.nextInt(); // read a string
                    System.out.println("BookID is " + bookID);
                    if (bookID > count)
                        System.out.println("Book ID is invalid");
                    else
                        db.checkoutbook(bookID, username);
                }
                else continue;
            }
            else if (choice == 2){
                System.out.println("Enter the Book you want to Lookup");
                String bookname = sc2.nextLine();
                db.lkup_by_bookname(bookname);
                Scanner sc3 = new Scanner (System.in); // create a Scanner object
                System.out.println("Do you want to check out a book enter Y and N");
                String ret = sc3.nextLine();
                String ret1 = "y";

                if (ret.equalsIgnoreCase(ret1)) {
                    System.out.println("Enter the ID of the book that you want to checkout \n");
                    int bookID = sc3.nextInt(); // read a string
                    System.out.println("BookID is " + bookID);
                    if (bookID > count)
                        System.out.println("Book ID is invalid");
                    else
                        db.checkoutbook(bookID, username);
                }
                else continue;
            }
            else if (choice == 3){
                System.out.println("Looking up books that you have rented");
                try {
                    db.printrentedbks(username);
                } catch (SQLException e) {
                    System.out.println("print failed");
                    throw new RuntimeException(e);
                }
                System.out.println("Enter the ID of the book you want to return");
                Scanner sc4 = new Scanner (System.in); // create a Scanner object
                int bookID = sc4.nextInt(); // read a string
                System.out.println("BookID is " +bookID);
                if (bookID > count)
                    System.out.println("Book ID is invalid");
                else {
                    System.out.println("Returning book...");
                    String rbook = null;
                    rbook = db.returnbook(bookID, username);
                    System.out.println("id: " + bookID + "   book:" + rbook + "is now returned");
                }
            }
            else if (choice == 4){

                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter book name: ");
                String bookName = scanner.nextLine();

                System.out.println("Enter author name: ");
                String authorName = scanner.nextLine();

                System.out.println("Enter owner name: ");
                String ownerName = scanner.nextLine();

                System.out.println("Enter phone number: ");
                long phoneNumber = scanner.nextLong();

                System.out.println("Enter email address: ");
                String emailAddress = scanner.next();

                System.out.println("Enter price: ");
                double price = scanner.nextDouble();

                db.addBook(bookName,authorName, ownerName, phoneNumber, emailAddress, price );
                count = count + 1;

                System.out.println("book:" + bookName + " has now been added to the Library");
            }

            else if (choice == 5){
                try {
                    db.printrentedbks(username);
                } catch (SQLException e) {
                    System.out.println("print failed");
                    throw new RuntimeException(e);
                }
            }

            System.out.println("\nWhat do you want to do next? ");
            System.out.println("Enter '1' for Lookup by Author ");
            System.out.println("Enter '2' for Lookup by BookName ");
            System.out.println("Enter '3' to return a book ");
            System.out.println("Enter '4' to add a book to the library ");
            System.out.println("Enter '5' to show all books checkout to you");
            System.out.println("Enter '6' to exit ");
            choice = sc1.nextInt();
            sc1 = new Scanner(System.in);
        } while (choice != 6);

        System.out.println("Thank you and Bye \n");
        sc1.close();
    }

    public void view_chkoutbook(java.sql.ResultSet rs, String authorname1, String un) throws SQLException {
        // Print the results in a tabular format
        System.out.println("Found records for " + authorname1 + ":");
        System.out.println("----------------------------------------");
        System.out.println("ID|Name|Author|Owner|Checkedout|Checkoutto|Phone|Email|Price to Rent|Extra");
        System.out.println("----------------------------------------");
    }
}
