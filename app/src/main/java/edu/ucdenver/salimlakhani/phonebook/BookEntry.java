package edu.ucdenver.salimlakhani.phonebook;

public class BookEntry {

    private String bookTitle;
    private String author;
    private String readStatus;

    // Constructor to match the provided parameters
    public BookEntry(String bookTitle, String author, String readStatus) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.readStatus = readStatus;
    }

    // Getters and setters
    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }
}
