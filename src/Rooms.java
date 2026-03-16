/***
 * ABSTRACT CLASS - Room

 * Use Case 2: Basic Room Types & Static Availability

 * Description:
 * This abstract class represents a generic hotel room.

 * It models attributes that are intrinsic to a room type
 * and remain constant regardless of availability.

 * Inventory-related concerns are intentionally excluded.

 * author: Aayusha Kuikel
 * @version 2.0
 */
 abstract class Room {
    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;

    public Room(int numberOfBeds, int squareFeet, double pricePerNight) {
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    public void displayRoomDetails() {
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Size: " + squareFeet);
        System.out.println("Price Per Night: " + pricePerNight);
    }
}
     class SingleRoom extends Room{
         public SingleRoom(){
             super(1,250,1500.0);

         }
     }
      class DoubleRoom extends Room{
         public DoubleRoom(){
             super(2,400,2500.0);

         }
     }
      class SuiteRoom extends Room{
         public SuiteRoom(){
             super(3,750,5000.0);
         }
     }
     public class Rooms{
        public static void main(String[]args){
            System.out.println("Hotel Room Initialization");
            System.out.println("\nSingle Room:");
            SingleRoom singleroom =new SingleRoom();
            singleroom.displayRoomDetails();
            System.out.println("\nDouble Room:");
            DoubleRoom doubleroom = new DoubleRoom();
            doubleroom.displayRoomDetails();
            System.out.println("\nSuite Room:");
            SuiteRoom suiteroom=new SuiteRoom();
            suiteroom.displayRoomDetails();

        }
     }



