package hym;

public class Course {
    private String courseID;
    private String courseName;
    private String instructor;
    private String time;
    private Integer credit;
    private Boolean isOptional;
    private String college;
    private Classroom classroom;

    public Course(String id) {
        this.courseID = id;
        System.out.println("Create a new instance of Class Course, ID is: " + courseID);
    }


    public Boolean getOptional() {
        return isOptional;
    }

    public Integer getCredit() {
        return credit;
    }

    public String getCourseID() {
        return courseID;
    }
}
