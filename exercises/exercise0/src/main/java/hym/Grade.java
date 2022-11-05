package hym;

public class Grade {
    private String studentID;
    private String courseID;
    private float grade;
    private Integer retryNum;
    private String issuer;
    private float weight;
    private String type;
    private String semester;
    private String gradeId;

    public Grade(String id) {
        this.gradeId = id;
        System.out.println("Created a new instance of Grade, ID: " + id);
    }

    public String getStudentID() {
        return studentID;
    }

    public float getGrade() {
        return grade;
    }

    public String getGradeId() {
        return gradeId;
    }
}
