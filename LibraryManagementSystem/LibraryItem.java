package LibraryManagementSystem;


import java.util.ArrayList;
import java.util.List;

public abstract class LibraryItem implements Searchable<LibraryItem> {
    protected String title;
    protected int year;
    protected boolean available;
    protected List<Reader> borrowers;

    public LibraryItem(String title, int year) {
        this.title = title;
        this.year = year;
        this.available = true;
        this.borrowers = new ArrayList<>();
    }
    public List<Reader> getBorrowers() {
        return borrowers;
    }

    public void borrowItem(Reader reader) {
        if (available) {
            borrowers.add(reader);
            available = false;
        }
    }

    public void returnItem() {
        if (!borrowers.isEmpty()) {
            borrowers.remove(borrowers.size() - 1);
            available = true;
        }
    }

    @Override
    public boolean matches(String query) {
        return title.toLowerCase().contains(query.toLowerCase());
    }

    @Override
    public String toString() {
        return "LibraryItem{" + "title='" + title + ", year=" + year + ", available=" + available + '}';
    }
}
