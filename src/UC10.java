import java.util.*;

/**
 * ================================================================
 * CLASS - Use Case 10 Booking Cancellation
 * ================================================================
 *
 * Demonstrates booking cancellation with inventory rollback.
 *
 * @author Aayusha Kuikel
 * @version 10.0
 */
public class UC10 {

    public static void main(String[] args) {

        BookingService service = new BookingService();
        CancellationService cancelService = new CancellationService(service);

        try {
            // Create bookings
            service.bookRoom("R1", "Aayusha", "Single");
            service.bookRoom("R2", "Samir", "Double");

            // Cancel booking
            cancelService.cancelBooking("R1");

            // Try cancelling again (should fail)
            cancelService.cancelBooking("R1");

        } catch (InvalidBookingException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println("\nSystem remains consistent after rollback.");
    }
}

/**
 * ================================================================
 * CLASS - Reservation
 * ================================================================
 *
 * Represents a booking.
 */
class Reservation {

    private String reservationId;
    private String customerName;
    private String roomType;

    public Reservation(String reservationId, String customerName, String roomType) {
        this.reservationId = reservationId;
        this.customerName = customerName;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getRoomType() {
        return roomType;
    }
}

/**
 * ================================================================
 * CLASS - BookingService
 * ================================================================
 *
 * Handles booking and inventory.
 */
class BookingService {

    private Map<String, Integer> inventory;
    private Map<String, Reservation> bookings;

    public BookingService() {
        inventory = new HashMap<>();
        bookings = new HashMap<>();

        inventory.put("Single", 2);
        inventory.put("Double", 2);
        inventory.put("Suite", 1);
    }

    /**
     * Books a room.
     */
    public void bookRoom(String id, String name, String roomType)
            throws InvalidBookingException {

        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type");
        }

        if (inventory.get(roomType) <= 0) {
            throw new InvalidBookingException("No rooms available");
        }

        Reservation r = new Reservation(id, name, roomType);
        bookings.put(id, r);

        inventory.put(roomType, inventory.get(roomType) - 1);

        System.out.println("Booking confirmed: " + id);
    }

    public Reservation getReservation(String id) {
        return bookings.get(id);
    }

    public void removeReservation(String id) {
        bookings.remove(id);
    }

    public void increaseInventory(String roomType) {
        inventory.put(roomType, inventory.get(roomType) + 1);
    }
}

/**
 * ================================================================
 * CLASS - CancellationService
 * ================================================================
 *
 * Handles booking cancellation and rollback logic.
 */
class CancellationService {

    private BookingService bookingService;
    private Stack<String> rollbackStack;

    public CancellationService(BookingService bookingService) {
        this.bookingService = bookingService;
        rollbackStack = new Stack<>();
    }

    /**
     * Cancels a booking safely.
     */
    public void cancelBooking(String reservationId)
            throws InvalidBookingException {

        Reservation r = bookingService.getReservation(reservationId);

        if (r == null) {
            throw new InvalidBookingException("Booking not found or already cancelled");
        }

        // Push to rollback stack
        rollbackStack.push(reservationId);

        // Restore inventory
        bookingService.increaseInventory(r.getRoomType());

        // Remove booking
        bookingService.removeReservation(reservationId);

        System.out.println("Booking cancelled: " + reservationId);
    }
}

/**
 * ================================================================
 * CLASS - InvalidBookingException
 * ================================================================
 *
 * Custom exception for booking errors.
 */
class InvalidBookingException extends Exception {

    public InvalidBookingException(String message) {
        super(message);
    }
}
