package hym;

public class Instructor {
    private String id;
    private String firstName;
    private String lastName;
    private String title;
    private Course[] courses;
    private String gender;
    private String email;
    private String phoneNumber;

    public Instructor(String id) {
        this.id = id;
        System.out.println("Created a new instance of Instructor, ID: " + id);
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
