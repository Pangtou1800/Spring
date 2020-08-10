package pt.joja.bean;

public class Book {


    public Book() {
    }

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public void myInit() {
        System.out.println("Open the book");
    }

    public void myDestroy() {
        System.out.println("Close the book");
    }

    private String name;

    private String author;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
