package hym;

public class Building {
    private static String buildingID;
    private static String address;
    private static String enterCode;
    private static String adminID;
    private static String adminName;
    private static Integer layerNum;
    private static Long area;
    private static Boolean hasElevator;

    public Building(String bid){
        buildingID = bid;
        System.out.println("Create a new instance of Class Building, ID is: " + buildingID);
    }

    public void setAddress(String add){
        address = add;
    }

    public void setEnterCode(String ec){
        enterCode = ec;
    }

    public String getBuildingID(){
        return buildingID;
    }
}
