/**
 * ==================================
 * RoomAllocationService
 * ==================================
 *
 * this class is responsible for conforming booking request and assigning room
 *
 * it ensures each room has unique id ,inventory updated immediately and no room is double- booked
 * @author Aayusha Kuikel
 * @version 6.0
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

 class RoomAllocationService {
    private Set<String> allocateRoomIds;
    private Map<String,Set<String>> assignedRoomsByType;

    public RoomAllocationService(){
        allocateRoomIds=new HashSet<>();
        assignedRoomsByType =new HashMap<>();
    }

    public void allocateRoom (Reservation reservation, Inventory inventory){
        String roomType= reservation.getRoomType();

        Map<String ,Integer> availability=inventory.getRoomAvailability();
        if(availability.containsKey(roomType) && availability.get(roomType)-1>0){
            String roomID =generateRoomId(roomType);
            allocateRoomIds.add(roomID);

            assignedRoomsByType.putIfAbsent(roomType, new HashSet<>());
            assignedRoomsByType.get(roomType).add(roomID);
            inventory.updateAvailability(roomType, availability.get(roomType) - 1);

            System.out.println("Booking confirmed for Guest: "
                    + reservation.getGuestName()
                    + ", Room ID: " + roomID);

        }
        else {
            System.out.println("No room available for the Guest: "+reservation.getGuestName());
        }

    }

    private String generateRoomId(String roomType){
        int count = assignedRoomsByType
                .getOrDefault(roomType, new HashSet<>())
                .size() + 1;

        if (roomType.equals("Single Room")) {
            return "Single-" + count;
        } else if (roomType.equals("Double Room")) {
            return "Double-" + count;
        } else {
            return "Suite-" + count;
        }
    }

}
/**
 * =============================
 * MAIN CLASS
 * ==============================
 *
 * this class demonstrate how booking request are confirmed
 * it consumes booking order in FIFO and update inventory immediately
 * @author Aayusha Kuikel
 * @version 6.0
 */
public class UseCase6RoomAllocation {

    public static void main(String[] args) {

        Inventory inventory=new Inventory();
        RoomAllocationService service=new RoomAllocationService();

        Reservation r1 = new Reservation("Aayusha", "Single Room");
        Reservation r2 = new Reservation("Samir", "Suite Room");
        Reservation r3 = new Reservation("Ayush", "Single Room");

        System.out.println("Room allocating Service");
        service.allocateRoom(r1,inventory);
        service.allocateRoom(r2,inventory);
        service.allocateRoom(r3,inventory);


    }
}
