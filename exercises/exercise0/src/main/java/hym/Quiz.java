package hym;

public class Quiz {
    private String id;
    private String courseId;
    private String timestamp;
    private float weight;
    private int retries;
    private float grade;
    private String content;
    private String issuer;

    public Quiz(String id) {
        this.id = id;
        System.out.println("Created a new instance of Quiz, ID: " + id);
    }

    public String getId() {
        return id;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
