import java.util.*;
public class MyDriver {
    public static void main(String[] args) {
        Instagram myInsta = new Instagram();
        /*Adding followers to your list*/
        /*the boolean field indicates whether you want to follow them back*/
        myInsta.follow(true, "Alice", "Johnson", "aliceJ");
        myInsta.follow(false, "Bob", "Smith", "bobS");
        myInsta.follow(true, "Charlie", "Brown", "charlieB");
        myInsta.follow(true, "Dana", "White", "danaW");
        myInsta.follow(false, "Elon", "Musk", "elonM");

        /*Displaying your followers*/
        System.out.println("Your followers' information\n");
        System.out.println(myInsta);

        /*Unfollowing a user*/
        System.out.println("Removing Charlie Brown from your followers list");
        myInsta.delete("Charlie", "Brown");

        /*Displaying the list*/
        System.out.println("List of followers after removing Charlie Brown:");
        System.out.println(myInsta);

        /*Adding a new follower*/
        System.out.println("Adding Jane Doe to your list of followers");
        myInsta.follow(true, "Jane", "Doe", "janeD");

        /*Displaying the followers*/
        System.out.println("List of your followers:");
        System.out.println(myInsta);

        /*Searching for a follower*/
        System.out.println("Searching for Elon Musk (elonM) in your followers list");
        if (!myInsta.find("Elon", "Musk")) {
            System.out.println("Elon Musk is not in your list of followers");
        }

        System.out.println("\n***************************");
        System.out.println("You are following " + myInsta.followersNum() + " people");
        System.out.println("You have " + myInsta.followingsNum() + " followers");

        Scanner kb = new Scanner(System.in);
        System.out.println("Enter the first name and the last name of the person that you want to follow back: ");
        String first = kb.next();
        String last = kb.next();
        myInsta.followBack(first, last);
        System.out.println(myInsta);

        System.out.println("Enter the username of the user that you would like to block");
        String username = kb.next();

        myInsta.blockUser(username);

        System.out.println(myInsta);
        System.out.println("Enter the username of the user that you would like to unblock");
        String username1 = kb.next();

        myInsta.unblockUser(username1);

        System.out.println(myInsta);
    }
}
// User class representing a user in the Instagram app
class User implements Comparable<User> {
    private String first;
    private String last;
    private String username;
    private boolean followBack;
    private boolean isBlocked; // extra attribute
    private int followerCount;  // extra attribute

    // Constructor for User class
    public User(boolean followBack, String first, String last, String username){
        this.first = first;
        this.last = last;
        this.username = username;
        this.followBack = followBack;
        this.isBlocked = false; // default not blocked
        this.followerCount = 0; // default follower count
    }

    // Getter for followBack status
    public boolean getFollow() {
        return followBack;
    }

    // Method to unfollow a user
    public void unfollow() {
        System.out.println("Unfollowed: " + username);
    }

    // Method to follow a user
    public void follow() {
        System.out.println("Followed: " + username);
        followerCount++; // increasing follower count when followed
    }

    // Getter for first name
    public String getFirst() {
        return first;
    }

    // Getter for last name
    public String getLast() {
        return last;
    }

    // Setter for first name
    public void setFirst(String first) {
        this.first = first;
    }

    // Setter for last name
    public void setLast(String last) {
        this.last = last;
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Compare users based on username
    public int compareTo(User o) {
        return this.username.compareTo(o.getUsername());
    }

    // Check if two users are equal based on username
    public boolean equals(User other) {
        return this.username.equals(other.getUsername());
    }

    // toString method to display user information
    public String toString() {
        return "User: " + first +
                " " + last +
                " (" + username +
                "), Follower Count: " + followerCount +
                ", Follow Back: " + followBack +
                ", Is Blocked: " + isBlocked;
    }

    // Getter for followerCount
    public int getFollowerCount() {
        return followerCount;
    }

    // Method to block a user (extra method)
    public void blockUser() {
        this.isBlocked = true;
        System.out.println(username + " has been blocked.");
    }

    // Method to unblock a user (extra method)
    public void unblockUser() {
        this.isBlocked = false;
        System.out.println(username + " has been unblocked.");
    }

    // Getter for isBlocked status
    public boolean isBlocked() {
        return isBlocked;
    }
}

// Instagram class representing the app functionality
class Instagram {
    private ArrayList<User> app;

    Instagram() {
        app = new ArrayList<User>();
    }

    // Method to follow back a user based on name
    public void followBack(String first, String last) {
        for (User u : app) {
            if (u.getFirst().equals(first) && u.getLast().equals(last) && !u.getFollow()) {
                u.follow();
                break;
            }
        }
    }

    // Method to add a user to follow
    public boolean follow(boolean followBack, String first, String last, String username) {
        User newUser = new User(followBack, first, last, username);
        app.add(newUser);
        newUser.follow();
        return true;
    }

    // Method to delete a user (unfollow and remove)
    public boolean delete(String first, String last) {
        for (User u : app) {
            if (u.getFirst().equals(first) && u.getLast().equals(last)) {
                app.remove(u);
                u.unfollow();
                return true;
            }
        }
        return false;
    }

    // Method to find a user by name
    public boolean find(String first, String last) {
        for (User u : app) {
            if (u.getFirst().equals(first) && u.getLast().equals(last)) {
                return true;
            }
        }
        return false;
    }

    // Getter for the list of users
    public ArrayList<User> getList() {
        return app;
    }

    // Method to return the number of followers
    public int followersNum() {
        return (int) app.stream().filter(User::getFollow).count();
    }

    // Method to return the number of followings
    public int followingsNum() {
        return app.size();
    }

    // toString method to display all users
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (User u : app) {
            result.append(u.toString()).append("\n");
        }
        return result.toString();
    }

    // Extra method to block a user by name
    public void blockUser(String username) {
        for (User u : app) {
            if (u.getUsername().equals(username)) {
                u.blockUser();
                break;
            }
        }
    }

    // Extra method to unblock a user by name
    public void unblockUser(String username) {
        for (User u : app) {
            if (u.getUsername().equals(username)) {
                u.unblockUser();
                break;
            }
        }
    }
}

