

/**
 * ===================================================
 * CLASS - Reservation
 * ===================================================
 * Use Case 5: Booking Request (FIFO)
 *
 * Description:
 * This class represents a booking request made by a guest.
 *
 * At this stage, a reservation only captures intent,
 * not confirmation or room allocation.
 * @author Aayusha Kuike
 * @version 5.0
 */
import java.util.LinkedList;
import java.util.Queue;
class Reservation {

    // Name of the guest making the booking
    private String guestName;

    // Requested room type
    private String roomType;

    // Constructor
    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    // Getter for room type
    public String getRoomType() {
        return roomType;
    }
}

class BookingRequestQueue {



    // Queue that stores booking requests
   Queue<Reservation> requestQueue;

    // Initialize empty queue
    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    // Add booking request
    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
    }

    // Get next request (FIFO)
    public Reservation getNextRequest() {
        return requestQueue.poll();
    }

    // Check if queue has requests
    public boolean hasPendingRequests() {
        return !requestQueue.isEmpty();
    }
}
/**
 * * MAIN CLASS - UseCase5BookingRequestQueue
 *
 * * Use Case 5: Booking Request (First-Come-First-Served)
 *
 * * Description:
 * * This class demonstrates how booking
 * * requests are accepted and queued
 * * in a fair and predictable order.
 * *
 *
 * * No room allocation or inventory
 * * update is performed here.
 * *
 *
 * * @version 5.0
 * */

public class UC5BookingRequestQueue {
    public static void main(String [] args){
        System.out.println("Booking Request Queue");
        // Initialize booking queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

// Create booking requests
        Reservation r1 = new Reservation("Abhi", "Single");
        Reservation r2 = new Reservation("Subha", "Double");
        Reservation r3 = new Reservation("Vanmathi", "Suite");

// Add requests to the queue
        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

// Display queued booking requests in FIFO order
        while (bookingQueue.hasPendingRequests()) {
            Reservation r = bookingQueue.getNextRequest();
            System.out.println("Processing booking for Guest: "
                    + r.getGuestName()
                    + ", Room Type: "
                    + r.getRoomType());
        }
    }

}
