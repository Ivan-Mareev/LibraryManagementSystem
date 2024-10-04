package LibraryManagementSystem;


import java.util.*;

public class LibraryManagementSystem {
    private List<LibraryItem> items;
    private List<Reader> readers;
    private Map<LibraryItem, Integer> availableCopies;

    public LibraryManagementSystem() {
        items = new ArrayList<>();
        readers = new ArrayList<>();
        availableCopies = new HashMap<>();
    }

    public void addBook(Book book, int copies) {
        items.add(book);
        availableCopies.put(book, copies);
    }

    public void removeBook(Book book) {
        items.remove(book);
        availableCopies.remove(book);
    }

    public void updateBook(Book book, int newCopies) {
        if (availableCopies.containsKey(book)) {
            availableCopies.put(book, newCopies);
        }
    }

    public void addReader(Reader reader) {
        readers.add(reader);
    }

    public void removeReader(Reader reader) {
        readers.remove(reader);
    }

    public void updateReader(Reader reader, String readerTicket, String name, String surname) {
        if (reader.readerTicket.equalsIgnoreCase(readerTicket)) {
            reader.firstName = name;
            reader.lastName = surname;
        }
    }

    public boolean borrowItem(Reader reader, LibraryItem item) {
        if (availableCopies.containsKey(item) && availableCopies.get(item) > 0) {
            item.borrowItem(reader);
            reader.borrowItem(item);
            availableCopies.put(item, availableCopies.get(item) - 1);
            return true;
        }
        return false;
    }

    public void returnItem(Reader reader, LibraryItem item) {
        item.returnItem();
        reader.returnItem(item);
        availableCopies.put(item, availableCopies.get(item) + 1);
    }

    public List<LibraryItem> searchLibraryItems(String query) {
        List<LibraryItem> results = new ArrayList<>();
        for (LibraryItem item : items) {
            if (item.matches(query)) {
                results.add(item);
            }
        }
        return results;
    }


    public List<Reader> searchReaders(String query) {
        List<Reader> results = new ArrayList<>();
        for (Reader reader : readers) {
            if (reader.matches(query)) {
                results.add(reader);
            }
        }
        return results;
    }

    public void printItems() {
        for (LibraryItem item : items) {
            System.out.println(item);
        }
    }

    public void printReaders() {
        for (Reader reader : readers) {
            System.out.println(reader);
        }
    }
    public void showLibraryItems() {
        System.out.println(items.toString());
    }
    public List<Reader> getReaders() {
        return readers;
    }
}
