import java.util.*;

/**
 * ================================================================
 * CLASS - UseCase8BookingHistoryReport (Main Class)
 * ================================================================
 *
 * Demonstrates Booking History and Reporting functionality.
 * Stores confirmed bookings and generates reports.
 *
 * @author Aayusha
 * @version 8.0
 */
public class UC8{

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        // Create sample reservations
        Reservation r1 = new Reservation("R101", "Aayusha", "Single");
        Reservation r2 = new Reservation("R102", "Samir", "Double");
        Reservation r3 = new Reservation("R103", "Anjan", "Suite");

        // Confirm bookings → add to history
        history.addReservation(r1);
        history.addReservation(r2);
        history.addReservation(r3);

        // Display all bookings
        System.out.println("===== BOOKING HISTORY =====");
        history.displayAllReservations();

        // Generate report
        System.out.println("\n===== BOOKING REPORT =====");
        reportService.generateSummaryReport(history.getAllReservations());
    }
}

/**
 * ================================================================
 * CLASS - Reservation
 * ================================================================
 *
 * Represents a confirmed reservation.
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

    public String getCustomerName() {
        return customerName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void display() {
        System.out.println("ID: " + reservationId +
                ", Name: " + customerName +
                ", Room: " + roomType);
    }
}

/**
 * ================================================================
 * CLASS - BookingHistory
 * ================================================================
 *
 * Stores confirmed reservations in insertion order.
 */
class BookingHistory {

    private List<Reservation> reservations;

    public BookingHistory() {
        reservations = new ArrayList<>();
    }

    /**
     * Adds a confirmed reservation to history.
     */
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    /**
     * Returns all stored reservations.
     */
    public List<Reservation> getAllReservations() {
        return reservations;
    }

    /**
     * Displays all reservations.
     */
    public void displayAllReservations() {
        for (Reservation r : reservations) {
            r.display();
        }
    }
}

/**
 * ================================================================
 * CLASS - BookingReportService
 * ================================================================
 *
 * Generates reports from booking history.
 */
class BookingReportService {

    /**
     * Generates a summary report.
     */
    public void generateSummaryReport(List<Reservation> reservations) {

        System.out.println("Total Bookings: " + reservations.size());

        Map<String, Integer> roomCount = new HashMap<>();

        for (Reservation r : reservations) {
            roomCount.put(r.getRoomType(),
                    roomCount.getOrDefault(r.getRoomType(), 0) + 1);
        }

        System.out.println("Room Type Distribution:");
        for (String type : roomCount.keySet()) {
            System.out.println(type + " : " + roomCount.get(type));
        }
    }
}