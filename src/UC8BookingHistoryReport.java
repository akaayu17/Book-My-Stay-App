/**
 * ======================================
 * AddOnService
 * ======================================
 *
 * this class represent a optional service which can be added to a conformed reservation
 *
 * provides getter method for service name and service cost
 *
 * @version 7.0
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
 class AddOnService {
    private String serviceName;
    private double cost;

    public  AddOnService(String serviceName, double cost){
        this.serviceName=serviceName;
        this.cost=cost;

    }

    public String getServiceName(){
        return serviceName;
    }
    public double getCost(){
        return cost;
    }

}

/**
 * ======================================
 * AddOnServiceManager
 * ======================================
 *
 * this class manages the optional services of the confirmed booked client
 *
 * it supports attaching  a list of add on services to a single reservation Id
 *
 * @version 7.0
 *
 */



class AddOnServiceManager {
    private Map<String, List<AddOnService>> serviceByReservation;

    public  AddOnServiceManager(){
        serviceByReservation=new HashMap<>();
    }

    public void addService(String reservationId, AddOnService service){
        serviceByReservation.putIfAbsent(reservationId,new ArrayList<>());
        serviceByReservation.get(reservationId).add(service);



    }

    public double calculateTotalServiceCost(String reservationId){
        double total=0;
        if(serviceByReservation.containsKey(reservationId)){
            for(AddOnService service:serviceByReservation.get(reservationId)){
                total+=service.getCost();
            }
        }
        return total;
    }
}
/**
 * ==================================
 * Main class - AddOnServiceSelection
 *===================================
 *
 * this class demonstrate optional services can be attached to a confirmed booking
 *
 *
 * services are added after room allocation do not affect the inventory
 *
 * @author Anjan
 * @version 7.0
 */


class AddOnServiceSelection {
    public static void main(){
        AddOnServiceManager manager=new AddOnServiceManager();
        String reservationId="Single-1";
        AddOnService AirpotPickup=new AddOnService("Airport pickup",1000);
        AddOnService Spa=new AddOnService("Spa",10000);

        System.out.println("Add-On Service Selection: ");
        manager.addService(reservationId,AirpotPickup);
        manager.addService(reservationId,Spa);
        System.out.println("Reservation Id: "+ reservationId);
        System.out.println("Total Add-On Cost: "+ manager.calculateTotalServiceCost(reservationId) );

    }
}/**
 * ========================
 * BookingHistory
 * =======================
 *
 *this class maintain the records of confirmed booking
 *this class also provides ordered storage for historical and reporting purpose
 *
 * @version 8.0
 */



class BookingHistory {
    private List<Reservation> confirmedReservation;

    public BookingHistory(){
        confirmedReservation= new ArrayList<>();
    }

    public void addReservation(Reservation reservation){
        confirmedReservation.add(reservation);
    }

    public List<Reservation> getConfirmedReservation() {
        return confirmedReservation;
    }
}
/**
 * =================================
 * BookingReportService
 * ===================================
 *
 * this class generate booking report from the booking history
 *
 * @version 8.0
 */
class BookingReportService {

    public void generateReport(BookingHistory history){
        System.out.println("Booking History Report: ");
        if(history.getConfirmedReservation()!=null){
            for(Reservation reservation : history.getConfirmedReservation()){
                System.out.println("Guest "+reservation.getGuestName()+", Room Type: "+reservation.getRoomType().replace(" Room"," "));
            }
        }

    }
}
/**
 * =========================================
 * MAIN CLASS: UseCase8BookingHistoryReport
 * =========================================
 *
 * this class demonstrates how confirmed booking are stored and reported
 * @author anjan
 * @version 8.0
 */
class UseCase8BookingHistoryReport {

    public static void main (String[] args){

        BookingHistory history=new BookingHistory();
        history.addReservation(new Reservation("Aayusha","Single Room"));
        history.addReservation(new Reservation("Samir","Double Room"));
        history.addReservation(new Reservation("Ayush","Suite Room"));

        BookingReportService service=new BookingReportService();

        service.generateReport(history);
    }

}


