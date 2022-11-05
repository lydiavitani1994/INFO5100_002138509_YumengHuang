package hym;

public class TA {
    private String id;
    private String studentId;
    private String courseId;
    private String email;
    private float salary;
    private String ssn;
    private String bankInfo;
    private float workedHours;

    public TA(String id) {
        this.id = id;
        System.out.println("Created a new instance of TA, ID: " + id);
    }

    public String getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourseId() {
        return courseId;
    }
}
