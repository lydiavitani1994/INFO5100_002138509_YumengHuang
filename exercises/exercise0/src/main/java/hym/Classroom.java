package hym;

public class Classroom {
    private String classRoomID;
    private Building building;
    private Integer floor;
    private Long area;
    private Course[] courseList;
    private Integer seatNum;
    private Boolean hasProjector;
    private Boolean isPublic;

    public Classroom(String classRoomID, Building building, Integer floor, Long area, Course[] courseList, Integer seatNum, Boolean hasProjector, Boolean isPublic) {
        this.classRoomID = classRoomID;
        this.building = building;
        this.floor = floor;
        this.area = area;
        this.courseList = courseList;
        this.seatNum = seatNum;
        this.hasProjector = hasProjector;
        this.isPublic = isPublic;
        System.out.println("Create a new instance of Class classRoom, ID is: " + classRoomID);
    }

    public void setBuilding(Building building) {
        building = building;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setArea(Long area) {
        this.area = area;
    }
}
