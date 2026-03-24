
import java.util.Map;


    /**
     * main class
     *
     * use case 4 - Room Search and Availability Check

     * @author Aayusha Kuikel
     * @version 4.0
     */
    class RoomSearchService  {
        public void
        searchAvailableRooms(
                Inventory inventory,
                Room singleRoom,
                Room doubleRoom,
                Room suiteRoom
        ) {
            Map<String, Integer> availability = inventory.getRoomAvailability();
            if (availability.get("Single Room") > 0) {
                singleRoom.displayRoomDetails();
                System.out.println(" Available : " + availability.get("Single Room"));
                if (availability.get("Double Room") > 0) {
                    System.out.println(" Available : " + availability.get("Double Room"));
               doubleRoom.displayRoomDetails();
                }

                if (availability.get("Suite Room") > 0) {
                    System.out.println(" Available : " + availability.get("Suite Room"));
                suiteRoom.displayRoomDetails();
                }
            }
        }
    }


public class UseCase4RoomSearch {
    public static void main(String[] args){
        SingleRoom singleRoom=new SingleRoom();
        DoubleRoom doubleRoom=new DoubleRoom();
        SuiteRoom suiteRoom=new SuiteRoom();
        Inventory inventory=new Inventory();
        RoomSearchService searchService=new RoomSearchService();
        searchService.searchAvailableRooms(inventory,singleRoom,doubleRoom,suiteRoom);


    }

}







