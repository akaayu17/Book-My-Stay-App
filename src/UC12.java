import java.io.*;
import java.util.*;

/**
 * ================================================================
 * CLASS - Use Case 12 Data Persistence Recovery 
 * ================================================================
 *
 * Demonstrates saving and restoring booking + inventory state
 * using serialization.
 *
 * @author Aayusha
 * @version 12.0
 */
public class UC12{

    public static void main(String[] args) {

        PersistenceService persistence = new PersistenceService();
        BookingSystem system;

        // Try loading previous state
        system = persistence.loadState();

        if (system == null) {
            System.out.println("No saved data found. Starting fresh...");
            system = new BookingSystem();
        } else {
            System.out.println("System state restored successfully!");
        }

        // Perform operations
        system.bookRoom("R1", "Aayusha", "Single");
        system.bookRoom("R2", "Samir", "Double");

        system.displayState();

        // Save state before shutdown
        persistence.saveState(system);
        System.out.println("\nSystem state saved.");
    }
}

/**
 * ================================================================
 * CLASS - BookingSystem
 * ================================================================
 *
 * Holds inventory and booking data.
 */
class BookingSystem implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String, Integer> inventory;
    private Map<String, Reservation> bookings;

    public BookingSystem() {
        inventory = new HashMap<>();
        bookings = new HashMap<>();

        inventory.put("Single", 2);
        inventory.put("Double", 2);
        inventory.put("Suite", 1);
    }

    /**
     * Books a room.
     */
    public void bookRoom(String id, String name, String roomType) {

        if (!inventory.containsKey(roomType)) {
            System.out.println("Invalid room type");
            return;
        }

        if (inventory.get(roomType) <= 0) {
            System.out.println("No rooms available");
            return;
        }

        Reservation r = new Reservation(id, name, roomType);
        bookings.put(id, r);

        inventory.put(roomType, inventory.get(roomType) - 1);

        System.out.println("Booking confirmed: " + id);
    }

    /**
     * Displays current system state.
     */
    public void displayState() {

        System.out.println("\n--- Inventory ---");
        for (String type : inventory.keySet()) {
            System.out.println(type + " : " + inventory.get(type));
        }

        System.out.println("\n--- Bookings ---");
        for (Reservation r : bookings.values()) {
            System.out.println(r);
        }
    }
}

/**
 * ================================================================
 * CLASS - Reservation
 * ================================================================
 *
 * Serializable booking object.
 */
class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String roomType;

    public Reservation(String id, String name, String roomType) {
        this.id = id;
        this.name = name;
        this.roomType = roomType;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Room: " + roomType;
    }
}

/**
 * ================================================================
 * CLASS - PersistenceService
 * ================================================================
 *
 * Handles saving and loading system state.
 */
class PersistenceService {

    private static final String FILE_NAME = "booking_data.ser";

    /**
     * Saves system state to file.
     */
    public void saveState(BookingSystem system) {

        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            out.writeObject(system);

        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    /**
     * Loads system state from file.
     */
    public BookingSystem loadState() {

        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            return (BookingSystem) in.readObject();

        } catch (FileNotFoundException e) {
            return null; // first run
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data. Starting fresh...");
            return null;
        }
    }
}
