package LibraryManagementSystem;


import java.util.List;
import java.util.Scanner;

public class Main {
    private static LibraryManagementSystem library = new LibraryManagementSystem();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean flag = false;
        while (!flag) {
            String choice = scanner.nextLine().toLowerCase();

            switch (choice) {
                case "добавить книгу":
                    addBook(scanner);
                    break;
                case "удалить книгу":
                    removeBook(scanner);
                    break;
                case "добавить читателя":
                    addReader(scanner);
                    break;
                case "обновить читателя":
                    updateReader(scanner);
                    break;
                case "удалить читателя":
                    removeReader(scanner, library);
                    break;
                case "вернуть книгу":
                    returnBook(scanner);
                    break;
                case "обновить книгу":
                    updateBook(scanner);
                    break;
                case "поиск книг":
                    searchLibraryItems(scanner);
                    break;
                case "поиск читателей":
                    searchReaders(scanner);
                    break;
                case "взять книгу":
                    borrowBook(scanner);
                    break;
                case "end":
                    flag = true;
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        }

}
    private static void addBook(Scanner scanner) {
        System.out.print("Введите название книги: ");
        String title = scanner.nextLine();
        System.out.print("Введите автора книги: ");
        String author = scanner.nextLine();
        System.out.print("Введите год издания книги: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Введите жанр книги: ");
        String genre = scanner.nextLine();
        System.out.print("Введите количество экземпляров: ");
        int copies = scanner.nextInt();
        Book book = new FictionBook(title, author, year, isbn, genre);
        library.addBook(book, copies);
        System.out.println("Книга добавлена.");
    }

    private static void updateBook(Scanner scanner) {
        System.out.print("Введите ISBN книги: ");
        String isbn = scanner.nextLine();
        System.out.print("Введите новое количество копий: ");

        int newCopies;
        while (true) {
            if (scanner.hasNextInt()) {
                newCopies = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.print("Пожалуйста, введите целое число для количества копий: ");
                scanner.nextLine();
            }
        }

        List<LibraryItem> items = library.searchLibraryItems(isbn);
        LibraryItem item = items.stream().findFirst().orElse(null);

        if (item != null && item instanceof Book) {
            library.updateBook((Book) item, newCopies);
            System.out.println("Книга успешно обновлена.");
        } else {
            System.out.println("Книга с указанным ISBN не найдена.");
        }
    }

    private static void removeBook(Scanner scanner) {
        System.out.print("Введите ISBN книги для удаления: ");
        String isbn = scanner.nextLine();

        Book bookToRemove = library.searchLibraryItems(isbn).stream()
                .filter(item -> item instanceof Book && ((Book) item).getIsbn().equalsIgnoreCase(isbn))
                .map(item -> (Book) item)
                .findFirst()
                .orElse(null);

        if (bookToRemove != null) {
            library.removeBook(bookToRemove);
            System.out.println("Книга успешно удалена.");
        } else {
            System.out.println("Книга с указанным ISBN не найдена.");
        }
    }


    private static void addReader(Scanner scanner) {
        System.out.print("Введите имя читателя: ");
        String firstName = scanner.nextLine();
        System.out.print("Введите фамилию читателя: ");
        String lastName = scanner.nextLine();
        System.out.print("Введите номер читательского билета: ");
        String readerTicket = scanner.nextLine();
        /*System.out.print("Введите дату рождения (ГГГГ-ММ-ДД): ");*/
        System.out.print("Читатель является студентом или преподавателем? (s/p): ");
        String type = scanner.nextLine();
        Reader reader;
        if (type.equalsIgnoreCase("s")) {
            System.out.print("Введите номер студенческого билета: ");
            String studentId = scanner.nextLine();
            reader = new Student(firstName, lastName, readerTicket, studentId);
        } else {
            System.out.print("Введите департамент: ");
            String department = scanner.nextLine();
            reader = new Professor(firstName, lastName, readerTicket, department);
        }
        library.addReader(reader);
        System.out.println("Читатель добавлен");
    }

    private static void updateReader(Scanner scanner) {
        System.out.print("Введите номер читательского билета: ");
        String readerTicket = scanner.nextLine();

        for (Reader reader : library.getReaders()) {
            if (reader.readerTicket.equalsIgnoreCase(readerTicket)) {
                System.out.print("Введите новое имя: ");
                String name = scanner.nextLine();

                System.out.print("Введите новую фамилию: ");
                String surname = scanner.nextLine();

                library.updateReader(reader, readerTicket, name, surname);
                System.out.println("Читатель успешно обновлён.");
                return;
            }
        }

        System.out.println("Читатель с указанным номером билета не найден.");
    }

    private static void removeReader(Scanner scanner, LibraryManagementSystem library) {
        System.out.print("Введите номер читательского билета для удаления: ");
        String ticketNumber = scanner.nextLine();

        Reader readerToRemove = null;
        for (Reader reader : library.getReaders()) {
            if (reader.getReaderTicket().equals(ticketNumber)) {
                readerToRemove = reader;
                break;
            }
        }

        if (readerToRemove != null) {
            library.removeReader(readerToRemove);
            System.out.println("Читатель с номером билета '" + ticketNumber + "' успешно удалён.");
        } else {
            System.out.println("Читатель с номером билета '" + ticketNumber + "' не найден.");
        }
    }


    private static void borrowBook(Scanner scanner) {
        System.out.print("Введите номер читательского билета: ");
        String readerTicket = scanner.nextLine();
        System.out.print("Введите ISBN книги: ");
        String isbn = scanner.nextLine();
        Reader reader = library.searchReaders(readerTicket).stream().findFirst().orElse(null);
        LibraryItem item = library.searchLibraryItems(isbn).stream().findFirst().orElse(null);
        if (reader != null && item != null) {
            boolean success = library.borrowItem(reader, item);
            if (success) {
                System.out.println("Книга выдана.");
            } else {
                System.out.println("Недостаточно экземпляров.");
            }
        } else {
            System.out.println("Читатель или книга не найдены.");
        }
    }

    private static void returnBook(Scanner scanner) {
        System.out.print("Введите номер читательского билета: ");
        String readerTicket = scanner.nextLine();
        System.out.print("Введите ISBN книги: ");
        String isbn = scanner.nextLine();
        Reader reader = library.searchReaders(readerTicket).stream().findFirst().orElse(null);
        LibraryItem item = library.searchLibraryItems(isbn).stream().findFirst().orElse(null);
        if (reader != null && item != null) {
            library.returnItem(reader, item);
            System.out.println("Книга возвращена.");
        } else {
            System.out.println("Читатель или книга не найдены.");
        }
    }

    private static void searchLibraryItems(Scanner scanner) {
        System.out.print("Введите поисковый запрос: ");
        String query = scanner.nextLine();
        List<LibraryItem> results = library.searchLibraryItems(query);
        if (results.isEmpty()) {
            System.out.println("Ничего не найдено.");
        } else {
            for (LibraryItem item : results) {
                System.out.println(item);
            }
        }
    }

    private static void searchReaders(Scanner scanner) {
        System.out.print("Введите поисковый запрос: ");
        String query = scanner.nextLine();
        List<Reader> results = library.searchReaders(query);
        if (results.isEmpty()) {
            System.out.println("Ничего не найдено.");
        } else {
            for (Reader reader : results) {
                System.out.println(reader);
            }
        }
    }
    public static void show() {
        library.showLibraryItems();
    }

}