import java.util.Map;

/**
 *
 * *
 * * CLASS - RoomInventory
 * * Use Case 3: Centralized Room Inventory Management
 * * Description:
 * * This class acts as the single source of truth
 * * for room availability in the hotel.
 * *
 * * Room pricing and characteristics are obtained
 * * from Room objects, not duplicated here.
 * *
 * * This avoids multiple sources of truth and
 * * keeps responsibilities clearly separated.

 * * @author: Aayusha Kuikel
 * * @version 3.0
 */
import java.util.HashMap;
class Inventory{
    private Map<String,Integer> roomAvailability;

    public Inventory(){
        roomAvailability=new HashMap<>();
        initializeInventory();


    }
    private void initializeInventory(){
        roomAvailability.put("Single Room",5);
        roomAvailability.put("Double Room",3);
        roomAvailability.put("Suite Room",2);
    }
    public  Map<String , Integer>getRoomAvailability(){

        return roomAvailability;
    }

}
public class RoomInventory{
    public static void main (String [] args){
        SingleRoom singleRoom = new SingleRoom();
        DoubleRoom doubleRoom=new DoubleRoom();
        SuiteRoom suiteRoom=new SuiteRoom();
        Inventory inventory=new Inventory();
        System.out.println("Single Room:");
        singleRoom.displayRoomDetails();
        System.out.println("Available: "+inventory.getRoomAvailability().get("Single Room"));
        System.out.println("double Room:");
        doubleRoom.displayRoomDetails();
        System.out.println("Available: "+inventory.getRoomAvailability().get("Double Room"));
        System.out.println("Suite Room:");
        suiteRoom.displayRoomDetails();
        System.out.println("Available: "+inventory.getRoomAvailability().get("Suite Room"));

    }
}


