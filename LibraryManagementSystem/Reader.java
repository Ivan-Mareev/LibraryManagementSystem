package LibraryManagementSystem;

import java.util.ArrayList;
import java.util.List;

public abstract class Reader implements Searchable<Reader> {
    protected String firstName;
    protected String lastName;
    protected String readerTicket;
    protected List<LibraryItem> borrowedItems;

    public Reader(String firstName, String lastName, String readerTicket) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.readerTicket = readerTicket;
        this.borrowedItems = new ArrayList<>();
    }
        public List<LibraryItem> getBorrowedItems() {
        return borrowedItems;
    }

    public void borrowItem(LibraryItem item) {
        borrowedItems.add(item);
    }

    public void returnItem(LibraryItem item) {
        borrowedItems.remove(item);
    }

    @Override
    public boolean matches(String name) {
        return readerTicket.equalsIgnoreCase(name) || firstName.toLowerCase().contains(name.toLowerCase()) || lastName.toLowerCase().contains(name.toLowerCase());
    }

    @Override
    public String toString() {
        return "Reader{" + "firstName='" + firstName + ", lastName='" + lastName + ", readerTicket='" + readerTicket + '}';
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getReaderTicket() {
        return readerTicket;
    }
}
