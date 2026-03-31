import java.util.*;

/**
 * ================================================================
 * CLASS - UseCase9ErrorHandlingValidation (Main Class)
 * ================================================================
 *
 * Demonstrates validation and error handling in booking system.
 *
 * @author Aayusha
 * @version 9.0
 */
public class UC9{

    public static void main(String[] args) {

        BookingService service = new BookingService();

        try {
            // Valid booking
            service.bookRoom("Aayusha", "Single");

            // Invalid room type
            service.bookRoom("Samir", "Luxury");

        } catch (InvalidBookingException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        try {
            // Force inventory error
            service.bookRoom("Anjan", "Suite");
            service.bookRoom("Test", "Suite"); // exceeds limit

        } catch (InvalidBookingException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println("\nSystem is still running safely...");
    }
}

/**
 * ================================================================
 * CLASS - BookingService
 * ================================================================
 *
 * Handles booking logic with validation and error handling.
 */
class BookingService {

    private Map<String, Integer> roomInventory;

    public BookingService() {
        roomInventory = new HashMap<>();
        roomInventory.put("Single", 2);
        roomInventory.put("Double", 2);
        roomInventory.put("Suite", 1);
    }

    /**
     * Books a room after validation.
     *
     * @param customerName name of guest
     * @param roomType type of room
     * @throws InvalidBookingException if validation fails
     */
    public void bookRoom(String customerName, String roomType)
            throws InvalidBookingException {

        validateRoomType(roomType);
        validateAvailability(roomType);

        // Reduce inventory
        roomInventory.put(roomType, roomInventory.get(roomType) - 1);

        System.out.println("Booking confirmed for " + customerName +
                " in " + roomType + " room.");
    }

    /**
     * Validates if room type exists.
     */
    private void validateRoomType(String roomType)
            throws InvalidBookingException {

        if (!roomInventory.containsKey(roomType)) {
            throw new InvalidBookingException(
                    "Invalid room type: " + roomType);
        }
    }

    /**
     * Validates availability of rooms.
     */
    private void validateAvailability(String roomType)
            throws InvalidBookingException {

        if (roomInventory.get(roomType) <= 0) {
            throw new InvalidBookingException(
                    "No available rooms for type: " + roomType);
        }
    }
}

/**
 * ================================================================
 * CLASS - InvalidBookingException
 * ================================================================
 *
 * Custom exception for invalid booking scenarios.
 */
class InvalidBookingException extends Exception {

    public InvalidBookingException(String message) {
        super(message);
    }
}
