package model;
public class Book {
    private int id;
    private String bookName;
    private String author;
    private String owner;
    private boolean checkedOut;
    private String checkedOutTo;
    private int phone;
    private String email;
    private int price;
    public Book(String bookName, String author, String owner, boolean b, Object o, int phone, String email, int price) {
    }
    // Constructor, getters, and setters
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", owner='" + owner + '\'' +
                ", checkedOut=" + checkedOut +
                ", checkedOutTo='" + checkedOutTo + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", price=" + price +
                '}';
    }
}