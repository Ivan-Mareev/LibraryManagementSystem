package LibraryManagementSystem;

public abstract class Book extends LibraryItem {
    protected String author;
    protected String isbn;

    public Book(String title, String author, int year, String isbn) {
        super(title, year);
        this.author = author;
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public boolean matches(String name) {
        return super.matches(name) || author.toLowerCase().contains(name.toLowerCase()) || isbn.contains(name);
    }

    public abstract String getBookDetails();
}
