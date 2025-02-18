package controller;

import view.Display;

import java.sql.*;
import java.util.Scanner;
public class DBConnect {
    private String dbName;
    private String url;


    public DBConnect(String dbName){
        this.dbName = dbName;
        this.url = "jdbc:sqlite:C:/sqlite/db/"+dbName;
    }

    public void setupconnection()
    {
        try( Connection conn = DriverManager.getConnection(url))
        {
            System.out.println("Connection Established");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ResultSet connection(String cmd)
    {
        try( Connection conn = DriverManager.getConnection(url))
        {
            if(conn != null)
            {
                Statement st = null;
                st = conn.createStatement();
                // Execute a query to find the matching records
                ResultSet rs = null;
                try {
                    rs = st.executeQuery(cmd);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

             //   System.out.println("Connection established");
                return rs;
            }
        } catch(SQLException e)
        {
            System.out.println("Db not connected");
            System.out.println(e.getMessage());
        }
        return null;
    }


    public void lkup_by_bookname(String book){
        System.out.println("Option 2 selected \n");
        // Connect to the database
        try (Connection conn = DriverManager.getConnection(url)) {

            // Create a statement object
            java.sql.Statement stamt = conn.createStatement();

            // Execute a query to find the matching records
            java.sql.ResultSet rs = stamt.executeQuery("SELECT * FROM books WHERE bookname LIKE '%" + book + "%'");
            // Check if any results were found
            if (rs.next()) {
                // Print the results in a tabular format
                System.out.println("Found records for " + book + ":");
                System.out.println("----------------------------------------");
                System.out.println("ID|Name|Author|Owner|Checkedout|Checkoutto|Phone|Email|Price to Rent|Extra");
                do {
                    // Print each column value separated by a pipe
                    System.out.print(rs.getString(1)); // First column
                    for (int i = 2; i <= rs.getMetaData().getColumnCount(); i++) {
                        System.out.print("|" + rs.getString(i)); // Subsequent columns
                    }
                    System.out.println();
                } while (rs.next());
                System.out.println("----------------------------------------");
        //        checkoutbook(username);
            } else {
                // Print a message that no results were found
                System.out.println("No records found for " + book + ".");
            }

        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void lkup_by_author (String author2) {
        System.out.println("Option 1 selected \n");
        // Connect to the database
        try (Connection conn = DriverManager.getConnection(url)) {

            // Create a statement object
            java.sql.Statement stamt = conn.createStatement();

            // Execute a query to find the matching records
            //java.sql.ResultSet rs = stamt.executeQuery("SELECT * FROM books WHERE author = '" + author2 + "'");
            java.sql.ResultSet rs = stamt.executeQuery("SELECT * FROM books WHERE author LIKE '%" + author2 + "%'");

            // Check if any results were found
            if (rs.next()) {
                // Print the results in a tabular format
                System.out.println("Found records for " + author2 + ":");
                System.out.println("----------------------------------------");
                System.out.println("ID|Name|Author|Owner|Checkedout|Checkoutto|Phone|Email|Price to Rent|Extra");
                do {
                    // Print each column value separated by a pipe
                    System.out.print(rs.getString(1)); // First column
                    for (int i = 2; i <= rs.getMetaData().getColumnCount(); i++) {
                        System.out.print("|" + rs.getString(i)); // Subsequent columns
                    }
                    System.out.println();
                } while (rs.next());
                System.out.println("----------------------------------------");
            } else {
                // Print a message that no results were found
                System.out.println("No records found for " + author2 + ".");
            }

        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    public void createDatabase() {
    //    System.out.println("Connecting to DB");
        try(Connection conn = DriverManager.getConnection(url)){
            if(conn != null){
      //          System.out.println("Connection established");
            }
        } catch(SQLException e){
            System.out.println("Db not connected");
            System.out.println(e.getMessage());
        }
    }
    public void addTables(){
        Statement stmt = null;
    //    System.out.println("Creating Tables in db if it does not exist");
        try {
            Connection conn = DriverManager.getConnection(url) ;
            stmt = conn.createStatement();

            String createTableSQL = "CREATE TABLE IF NOT EXISTS books (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "bookname TEXT," +
                    "author TEXT," +
                    "owner TEXT," +
                    "checkedout BOOLEAN," +
                    "checkedoutName TEXT," +
                    "phone INTEGER," +
                    "email TEXT," +
                    "price INTEGER" +
                    ");";

            stmt.execute(createTableSQL);
            stmt.close();

        } catch (SQLException e) {
            System.out.println("Table has NOT been created");
            System.out.println(e.getMessage());
        }
     //   System.out.println("Table has been created");
    }

    public int addbooks() {

        int count = 0;
        //     System.out.println("Adding books to db");
        // create a Statement from the connection
        try {
            Connection conn = DriverManager.getConnection(url);
            String cquery = "SELECT COUNT(*) FROM books";

            // create a Statement from the connection
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(cquery);
            // Check if the result set is not null and has a next row
            if (rs != null && rs.next()) {
                // Get the first column value as an integer
                count = rs.getInt(1);

                // Check if the count is greater than zero
                if (count > 0) {
                    // There are entries in the database
                    System.out.println("Number of books in the library is " + count);
                } else {
                    // There are no entries in the database
                    // insert the data
                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('Calculus: Early Transcendentals', 'James Stewart', 'Frank Lee', 1234567890, 'franklee@example.com', 20);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('Introduction to Algorithms', 'Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, Clifford Stein', 'Grace Kim', 2345678901, 'gracekim@example.com', 25);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('Linear Algebra and Its Applications', 'David C. Lay, Steven R. Lay, Judi J. McDonald', 'Harry Potter', 3456789012, 'harrypotter@example.com', 15);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('Discrete Mathematics and Its Applications', 'Kenneth H. Rosen', 'Irene Adler', 4567890123, 'ireneadler@example.com', 18);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('Artificial Intelligence: A Modern Approach', 'Stuart Russell, Peter Norvig', 'Jack Sparrow', 5678901234, 'jacksparrow@example.com', 22);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('Database System Concepts', 'Abraham Silberschatz, Henry F. Korth, S. Sudarshan', 'Kate Winslet', 6789012345, 'katewinslet@example.com', 20);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('Operating System Concepts', 'Abraham Silberschatz, Peter B. Galvin, Greg Gagne', 'Leo DiCaprio', 7890123456, 'leodicaprio@example.com', 19);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('Computer Networks', 'Andrew S. Tanenbaum, David J. Wetherall', 'Mary Poppins', 8901234567, 'marypoppins@example.com', 21);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('The C Programming Language', 'Brian W. Kernighan, Dennis M. Ritchie', 'Nathan Drake', 9012345678, 'nathandrake@example.com', 12);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('The Elements of Statistical Learning', 'Trevor Hastie, Robert Tibshirani, Jerome Friedman', 'Olivia Wilde', 0123456789, 'oliviawilde@example.com', 24);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('The Elements of Statistical Learning', 'Trevor Hastie, Robert Tibshirani, Jerome Friedman', 'Olivia Wilde', 0123456789, 'oliviawilde@example.com', 24);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('An Introduction to Statistical Learning', 'Gareth James, Daniela Witten, Trevor Hastie, Robert Tibshirani', 'Peter Parker', 1234567891, 'peterparker@example.com', 18);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('An Introduction to Statistical Learning', 'Gareth James, Daniela Witten, Trevor Hastie, Robert Tibshirani', 'Peter Parker', 1234567891, 'peterparker@example.com', 18);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('Python for Data Analysis', 'Wes McKinney', 'Quinn Fabray', 2345678912, 'quinnfabray@example.com', 16);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('Python for Data Analysis', 'Wes McKinney', 'Quinn Fabray', 2345678912, 'quinnfabray@example.com', 16);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('R for Data Science', 'Hadley Wickham, Garrett Grolemund', 'Rachel Berry', 3456789123, 'rachelberry@example.com', 17);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('Machine Learning: A Probabilistic Perspective', 'Kevin P. Murphy', 'Sam Winchester', 4567891234, 'samwinchester@example.com', 23);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('Deep Learning', 'Ian Goodfellow, Yoshua Bengio, Aaron Courville', 'Tina Cohen-Chang', 5678912345, 'tinacohenchang@example.com', 26);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('Pattern Recognition and Machine Learning', 'Christopher M. Bishop', 'Uma Thurman', 6789123456, 'umathurman@example.com', 25);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('Reinforcement Learning: An Introduction', 'Richard S. Sutton, Andrew G. Barto', 'Vin Diesel', 7891234567, 'vindiesel@example.com', 22);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('Natural Language Processing with Python', 'Steven Bird, Ewan Klein, Edward Loper', 'Wendy Williams', 8912345678, 'wendywilliams@example.com', 19);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('Speech and Language Processing', 'Daniel Jurafsky, James H. Martin', 'Xavier Woods', 9123456789, 'xavierwoods@example.com', 21);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('Computer Vision: Algorithms and Applications', 'Richard Szeliski', 'Yara Greyjoy', 1234567889, 'yaragreyjoy@example.com', 20);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('Digital Image Processing', 'Rafael C. Gonzalez, Richard E. Woods', 'Zack Snyder', 2345678890, 'zacksnyder@example.com', 18);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('The Design of Everyday Things', 'Don Norman', 'Anna Kendrick', 3456788901, 'annakendrick@example.com', 15);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('The Art of Computer Programming', 'Donald E. Knuth', 'Bruce Wayne', 4567889012, 'brucewayne@example.com', 30);");


                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('The Design of Everyday Things', 'Don Norman', 'Anna Kendrick', 3456788901, 'annakendrick@example.com', 15);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('The Art of Computer Programming', 'Donald E. Knuth', 'Bruce Wayne', 4567889012, 'brucewayne@example.com', 30);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('The Mythical Man-Month', 'Frederick P. Brooks Jr.', 'Cersei Lannister', 5678890123, 'cerseilannister@example.com', 14);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('Code Complete', 'Steve McConnell', 'Dwayne Johnson', 6788901234, 'dwaynejohnson@example.com', 16);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('The Pragmatic Programmer', 'Andrew Hunt, David Thomas', 'Ellen DeGeneres', 7890123456, 'ellendegeneres@example.com', 17);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('Clean Code', 'Robert C. Martin', 'Frodo Baggins', 8901234567, 'frodobaggins@example.com', 18);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('Refactoring', 'Martin Fowler', 'Gandalf the Grey', 9012345678, 'gandalfthegrey@example.com', 19);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('Design Patterns', 'Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides', 'Hermione Granger', 0123456789, 'hermionegranger@example.com', 20);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('The Clean Coder', 'Robert C. Martin', 'Indiana Jones', 1234567890, 'indianajones@example.com', 16);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('Head First Design Patterns', 'Eric Freeman, Elisabeth Robson, Bert Bates, Kathy Sierra', 'Jessica Jones', 2345678901, 'jessicajones@example.com', 15);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('Test Driven Development', 'Kent Beck', 'Katniss Everdeen', 3456789012, 'katnisseverdeen@example.com', 17);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('The Art of Unit Testing', 'Roy Osherove', 'Luke Skywalker', 4567890123, 'lukeskywalker@example.com', 18);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('Domain-Driven Design', 'Eric Evans', 'Mickey Mouse', 5678901234, 'mickeymouse@example.com', 19);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('Clean Architecture', 'Robert C. Martin', 'Nancy Drew', 6789012345, 'nancydrew@example.com', 18);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('The DevOps Handbook', 'Gene Kim, Jez Humble, Patrick Debois, John Willis', 'Oliver Queen', 7890123456, 'oliverqueen@example.com', 20);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('Continuous Delivery', 'Jez Humble, David Farley', 'Phoebe Buffay', 8901234567, 'phoebebuffay@example.com', 21);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('The Phoenix Project', 'Gene Kim, Kevin Behr, George Spafford', 'Ron Weasley', 9012345678, 'ronweasley@example.com', 16);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('The Lean Startup', 'Eric Ries', 'Sherlock Holmes', 0123456789, 'sherlockholmes@example.com', 17);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('The Innovators Dilemma', 'Clayton M. Christensen', 'Tony Stark', 1234567891, 'tonystark@example.com', 18);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('The Effective Engineer', 'Edmond Lau', 'Veronica Mars', 2345678912, 'veronicamars@example.com', 19);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('Soft Skills', 'John Sonmez', 'Walter White', 3456789123, 'walterwhite@example.com', 15);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('The Software Craftsman', 'Sandro Mancuso', 'Xena Warrior Princess', 4567891234, 'xenawarriorprincess@example.com', 16);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('Peopleware', 'Tom DeMarco, Timothy Lister', 'Yoda', 5678912345, 'yoda@example.com', 14);");

                    statement.executeUpdate("INSERT INTO books (bookname, author, owner, phone, email, price)\n" +
                            "VALUES ('The Managers Path', 'Camille Fournier', 'Zoe Washburne', 6789123456, 'zoewashburne@example.com', 17);");

                    ResultSet rs1 = statement.executeQuery(cquery);
                    // Check if the result set is not null and has a next row
                    if (rs1 != null && rs1.next()) {
                        // Get the first column value as an integer
                        count = rs1.getInt(1);

                        // Check if the count is greater than zero
                        if (count > 0) {
                            // There are entries in the database
//                            System.out.println("Number of books in the library is " + count);
                        }
                    }


                }
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // Handle any connection exceptions
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Entries have NOT been created");
            System.out.println(e.getMessage());
        }



        return count;

    }


    public void checkoutbook(int bookid, String username){
        Statement stmt = null;
        try (Connection conn = DriverManager.getConnection(url)) {

            java.sql.Statement stmt3 = conn.createStatement();

            // Define the query to select a row based on some condition
            String query = "SELECT * FROM books WHERE id = '" + bookid + "'";

            // Execute the query and get the result set
            java.sql.ResultSet rs = stmt3.executeQuery(query);

            if (rs.next()) {
                // Get the values of the columns in the selected row
                int chkid = rs.getInt("id");
                boolean chekout = rs.getBoolean("checkedout");
                String bookname = rs.getString("bookname");
                int bprice = rs.getInt("price");
                String oname = rs.getString ("owner");

                // Print the values of the selected row
                System.out.println("Selected Book:");
                System.out.println("id: " + chkid + ", checkedout: " + chekout + ", book: " + bookname + ", is owned by " + oname
                + "is offering the book at the price of $"+ bprice);

                if(chekout == false)
                {
                    System.out.println("Book is not checked out");
                    System.out.println("Checking out book to " + username);
                    query = "UPDATE books SET checkedout = TRUE, checkedoutname = '" +username +"' WHERE id = '" + bookid + "'";

                        // Execute the query and get the result set
                        int blah1 = stmt3.executeUpdate(query);

              }
                else {

                    System.out.println("Book is already checkedout \n");
                }

            } else {
                // Print a message that no row was found
                System.out.println("No row found for the condition.");
            }


        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public String returnbook(int ID, String username)
    {
        Statement stmt = null;
        int rtexit = 0;

        try (Connection conn = DriverManager.getConnection(url)) {

            // Create a statement object
            java.sql.Statement rtstmnt = conn.createStatement();


            if (rtexit == 0) {

                // Define the query to select a row based on some condition
                String query = "SELECT * FROM books WHERE id = '" + ID + "'";

                // Execute the query and get the result set
                java.sql.ResultSet rs1 = rtstmnt.executeQuery(query);
                java.sql.Statement stmt3 = conn.createStatement();


                if (rs1.next()) {
                    // Get the values of the columns in the selected row
                    int chkid = rs1.getInt("id");
                    boolean chekout = rs1.getBoolean("checkedout");
                    String bookname = rs1.getString("bookname");
                    int bprice = rs1.getInt("price");
                    String oname = rs1.getString("owner");

                    if (chekout == true) {
                        query = "UPDATE books SET checkedout = FALSE, checkedoutName = NULL WHERE id = '" + ID + "'";

                        // Execute the query and get the result set
                        int blah = stmt3.executeUpdate(query);
                        return bookname;
                    } else {

                        System.out.println("Book is not checked out \n");
                    }

                } else {
                    // Print a message that no row was found
                    System.out.println("No row found for the condition.");
                }

            }


        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    public void printrentedbks(String username) throws SQLException {

        Statement stmt = null;
        int rtexit = 0;

        try (Connection conn = DriverManager.getConnection(url)) {

            // Create a statement object
            java.sql.Statement rtstmnt = conn.createStatement();

            // Execute a query to find the matching records
            java.sql.ResultSet rs = rtstmnt.executeQuery("SELECT * FROM books WHERE checkedoutName = '" + username + "'");

            // Check if any results were found
            if (rs.next()) {
                // Print the results in a tabular format
                System.out.println("Found records for " + username + ":");
                System.out.println("----------------------------------------");
                System.out.println("ID|Name|Author|Owner|Checkedout|Checkoutto|Phone|Email|Price to Rent|Extra");
                do {
                    // Print each column value separated by a pipe
                    System.out.print(rs.getString(1)); // First column
                    for (int i = 2; i <= rs.getMetaData().getColumnCount(); i++) {
                        System.out.print("|" + rs.getString(i)); // Subsequent columns
                    }
                    System.out.println();
                } while (rs.next());
                System.out.println("----------------------------------------");

            } else {
                // Print a message that no results were found
                System.out.println("No records found for " + username + ".");
                rtexit = 1;
            }

        }
    }


    public void addBook(String bookName, String authorName, String ownerName, long phoneNumber, String emailAddress, double price) {
        try (Connection conn = DriverManager.getConnection(url)) {

            // create a Statement from the connection
            Statement statement = conn.createStatement();

            // insert the data
            String insertQuery = "INSERT INTO books (bookname, author, owner, phone, email, price) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, bookName);
                preparedStatement.setString(2, authorName);
                preparedStatement.setString(3, ownerName);
                preparedStatement.setLong(4, phoneNumber);
                preparedStatement.setString(5, emailAddress);
                preparedStatement.setDouble(6, price);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
 //                   System.out.println("Book added successfully!");
                } else {
                    System.out.println("Failed to add the book. Please try again.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }
}

