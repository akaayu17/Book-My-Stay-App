import java.util.*;

/**
 * ================================================================
 * CLASS - UC7 Add-On Service Selection
 * ================================================================
 *
 * <p>
 * Demonstrates Add-On Service Selection functionality in a hotel system.
 * Users can attach optional services (like Breakfast, Spa, etc.)
 * to a reservation and calculate the total cost.
 * </p>
 *
 * @author Aayusha Kuikel
 * @version 7.0
 */
public class UC7 {

    /**
     * Main method - Entry point of the program.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        AddOnServiceManager1 manager = new AddOnServiceManager1();

        // Create services
        AddOnService breakfast = new AddOnService("Breakfast", 500);
        AddOnService spa = new AddOnService("Spa", 700);
        AddOnService pickup = new AddOnService("Airport Pickup", 300);

        String reservationId = "Single-1";

        // Add services to reservation
        manager.addService(reservationId, breakfast);
        manager.addService(reservationId, spa);
        manager.addService(reservationId, pickup);

        // Calculate total cost
        double totalCost = manager.calculateTotalServiceCost(reservationId);

        // Output
        System.out.println("Add-On Service Selection");
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + totalCost);
    }
}

/**
 * ================================================================
 * CLASS - AddOnService
 * ================================================================
 *
 
 * Represents an optional service that can be added
 * to a reservation.

 * @author Aayusha
 * @version 7.0
 */
class AddOnService {

    /** Name of the service */
    private String serviceName;

    /** Cost of the service */
    private double cost;

    /**
     * Constructor to initialize service details.
     *
     * @param serviceName name of the service
     * @param cost cost of the service
     */
    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    /**
     * Gets the service name.
     *
     * @return service name
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * Gets the service cost.
     *
     * @return cost of service
     */
    public double getCost() {
        return cost;
    }
}

/**
 * ================================================================
 * CLASS - AddOnServiceManager1
 * ================================================================

 *
 * @author Aayusha
 * @version 7.0
 */
class AddOnServiceManager1 {

    /**
     * Stores services mapped to reservation IDs.
     *
     * Key   -> Reservation ID  
     * Value -> List of AddOnService objects
     */
    private Map<String, List<AddOnService>> servicesByReservation;

    /**
     * Constructor - Initializes the service manager.
     */
    public AddOnServiceManager1() {
        servicesByReservation = new HashMap<>();
    }

    /**
     * Adds an add-on service to a reservation.
     *
     * @param reservationId unique reservation ID
     * @param service add-on service to attach
     */
    public void addService(String reservationId, AddOnService service) {

        servicesByReservation.putIfAbsent(reservationId, new ArrayList<>());
        servicesByReservation.get(reservationId).add(service);
    }

    /**
     * Calculates the total cost of all services
     * linked to a reservation.
     *
     * @param reservationId reservation ID
     * @return total cost of services
     */
    public double calculateTotalServiceCost(String reservationId) {

        List<AddOnService> services = servicesByReservation.get(reservationId);

        if (services == null) {
            return 0.0;
        }

        double total = 0.0;

        for (AddOnService service : services) {
            total += service.getCost();
        }

        return total;
    }
}