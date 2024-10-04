package LibraryManagementSystem;

public class Student extends Reader {
    private String studentId;

    public Student(String firstName, String lastName, String readerTicket, String studentId) {
        super(firstName, lastName, readerTicket);
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    @Override
    public String toString() {
        return "Student{" + "firstName='" + firstName + ", lastName='" + lastName + ", readerTicket='" + readerTicket + ", studentId='" + studentId + '}';
    }
    @Override
    public boolean matches(String name) {
        return studentId.equalsIgnoreCase(name);
    }
}

