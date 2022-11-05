package hym;

public class Printer {
    private String id;
    private String name;
    private String buildingId;
    private int paperPrinted;
    private float profit;
    private String color;
    private String roomId;
    private boolean supportScan;

    public Printer(String id) {
        this.id = id;
        System.out.println("Created a new instance of Printer, ID: " + id);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBuildingId() {
        return buildingId;
    }
}
