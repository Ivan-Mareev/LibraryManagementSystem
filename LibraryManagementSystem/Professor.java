package LibraryManagementSystem;


public class Professor extends Reader {
    private String department;

    public Professor(String firstName, String lastName, String readerTicket, String department) {
        super(firstName, lastName, readerTicket);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", readerTicket='" + readerTicket + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
