package hym;

public class Homework {
    private String id;
    private String courseId;
    private float weight;
    private String timestamp;
    private String dueTime;
    private int retries;
    private float grade;
    private String content;

    public Homework(String id) {
        this.id = id;
        System.out.println("Created a new instance of Homework, ID: " + id);
    }

    public String getId() {
        return id;
    }

    public float getWeight() {
        return weight;
    }

    public String getContent() {
        return content;
    }
}


