package hym;

public class Student {
    private String id;
    private String firstName;
    private String lastName;
    private String gender;
    private float gpa;
    private String beginSemester;
    private String graduateSemester;
    private String major;

    public Student(String id) {
        this.id = id;
        System.out.println("Created a new instance of Student, ID: " + id);
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
