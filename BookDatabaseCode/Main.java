import controller.DBConnect;
import view.Display;

public class Main {
    public static void main(String[] args) {
            int count;
            DBConnect db = new DBConnect("library.db");
            //Create the Database
            db.createDatabase();
            //Create a table
            db.addTables();
            //Create a default set of books since it helps testing
            count = db.addbooks();
            Display dp = new Display();
            //Adding a Menu function that does everything
            dp.menu(count);
  //          dp.lookupbooks();
    }
}