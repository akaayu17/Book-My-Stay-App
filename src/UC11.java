import java.util.*;

/**
 * ================================================================
 * CLASS - Use Case 11 Concurrent Booking Simulation
 * ================================================================
 *
 * Simulates concurrent booking requests using threads and ensures
 * thread-safe inventory updates using synchronization.
 *
 * @author Aayusha
 * @version 11.0
 */
public class UC11 {

    public static void main(String[] args) {

        BookingService service = new BookingService();

        // Simulate multiple users (threads)
        Thread t1 = new Thread(new BookingTask(service, "Aayusha", "Single"));
        Thread t2 = new Thread(new BookingTask(service, "Samir", "Single"));
        Thread t3 = new Thread(new BookingTask(service, "Anjan", "Single"));

        // Start threads
        t1.start();
        t2.start();
        t3.start();
    }
}

/**
 * ================================================================
 * CLASS - BookingTask
 * ================================================================
 *
 * Represents a booking request executed by a thread.
 */
class BookingTask implements Runnable {

    private BookingService service;
    private String customerName;
    private String roomType;

    public BookingTask(BookingService service, String customerName, String roomType) {
        this.service = service;
        this.customerName = customerName;
        this.roomType = roomType;
    }

    @Override
    public void run() {
        service.bookRoom(customerName, roomType);
    }
}

/**
 * ================================================================
 * CLASS - BookingService
 * ================================================================
 *
 * Handles booking with thread-safe inventory updates.
 */
class BookingService {

    private Map<String, Integer> inventory;

    public BookingService() {
        inventory = new HashMap<>();
        inventory.put("Single", 2); // Only 2 rooms available
    }

    /**
     * Thread-safe booking method using synchronized block.
     */
    public void bookRoom(String customerName, String roomType) {

        synchronized (this) { // critical section

            System.out.println(customerName + " is trying to book...");

            if (!inventory.containsKey(roomType)) {
                System.out.println("Invalid room type");
                return;
            }

            int available = inventory.get(roomType);

            if (available <= 0) {
                System.out.println("No rooms available for " + customerName);
                return;
            }

            // Simulate delay (to expose race condition if not synchronized)
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Allocate room
            inventory.put(roomType, available - 1);

            System.out.println("Booking SUCCESS for " + customerName +
                    " | Remaining: " + (available - 1));
        }
    }
}