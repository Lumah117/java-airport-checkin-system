import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class checkInSystem {
    private Map<String, passenger> passengers;
    private Map<String, flight> flights;
    private Map<String, booking> bookings;

    // Constructor
    public checkInSystem() {
        this.passengers = new HashMap<>();
        this.flights = new HashMap<>();
        this.bookings = new HashMap<>();
        loadPassengersFromCSV("passengers.csv");
        loadFlightsFromCSV("flight.csv");
    }

    // Method to add a passenger
    public void addPassenger(passenger passenger) {
    	String ref = passenger.getBookingReference().trim().toUpperCase();
    	passengers.put(ref,  passenger);
        //passengers.put(passenger.getBookingReference(), passenger);
    }

    // Method to add a flight
    public void addFlight(flight flight) {
    	String code = flight.getFlightCode().trim().toUpperCase();
    	flights.put(code,  flight);
        //flights.put(flight.getFlightCode(), flight);
    }

    // Method to add a booking
    public void addBooking(booking booking) {
    	String ref = booking.getBookingReference().trim().toUpperCase();
    	bookings.put(ref,  booking);
        //bookings.put(booking.getBookingReference(), booking);
    	
        addPassenger(booking.getPassenger());
        addFlight(booking.getFlight());
    }
    
    // Method to get all bookings
    public Map<String, booking> getBookings() {
    	return bookings;
    }

    // Method to process check-in
    public void checkIn(String bookingReference) {
        booking booking = bookings.get(bookingReference);
        if (booking != null) {
            passenger passenger = booking.getPassenger();
            flight flight = booking.getFlight();

            // Add the passenger to the flight's passenger list
            flight.addPassenger(passenger);

            // Set the passenger's status to checked-in
            passenger.setCheckedIn(true);
        } else {
            System.out.println("Booking reference not found.");
        }
    }

    // Method to calculate luggage fees
 // Method to calculate luggage fees
    public double calculateLuggageFee(String bookingReference, double weight, double volume) {
        double fee = 0.0;
        booking booking = bookings.get(bookingReference.trim().toUpperCase());
        if (booking != null) {
            flight flight = booking.getFlight();
            if (flight != null) {
                double weightLimit = flight.getMaxBaggageWeight();
                double volumeLimit = flight.getMaxBaggageVolume();
                // Calculate excess weight fee
                if (weight > weightLimit) {
                    double weightFeeRate = 1.4; // Assuming this is defined elsewhere
                    fee += (weight - weightLimit) * weightFeeRate;
                }
                // Calculate excess volume fee
                if (volume > volumeLimit) {
                    double volumeFeeRate = 1.2; // Assuming this is defined elsewhere
                    fee += (volume - volumeLimit) * volumeFeeRate;
                }
            } else {
                System.out.println("Flight not found for the given booking reference.");
            }
        } else {
            System.out.println("Booking reference not found.");
        }
        return fee;
    }

    
//    public double calculateLuggageFee(passenger passenger, double weight, double volume) {
//       double fee = 0.0;
//        flight flight = bookings.get(passenger.getBookingReference()).getFlight();
//     double weightLimit = flight.getMaxBaggageWeight();
//      double volumeLimit = flight.getMaxBaggageVolume();
//
//        // Calculate excess weight fee
//       if (weight > weightLimit) {
//            double weightFeeRate = 1.4;
//			fee += (weight - weightLimit) * weightFeeRate ; // weightFeeRate is a constant rate per excess kilogramme
//        }
//
//        // Calculate excess volume fee
//        if (volume > volumeLimit) {
//            double volumeFeeRate = 1.2;
//			fee += (volume - volumeLimit) * volumeFeeRate ; // volumeFeeRate is a constant rate per excess cubic meter
//        }
//
//        return fee;
//    }
    
    // code to load relevant data from the CSV file
    public void loadPassengersFromCSV (String passengers) {
    	try (BufferedReader br = new BufferedReader(new FileReader("passengers.csv"))) {
    		String line;
    		while ((line = br.readLine()) != null) {
    			String[] data = line.split(",");
    			passenger passenger = new passenger(data[1],data[0],data[2], false, 0.0, 0.0); //,Double.parseDouble(data[3]), Double.parseDouble(data[4]));
    			passenger.setCheckedIn(Boolean.parseBoolean(data[3].replace("'","'").trim()));
    			addPassenger(passenger);
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    	
    public void loadFlightsFromCSV(String flights) {
    	    try (BufferedReader br = new BufferedReader(new FileReader("flights.csv"))) {
    	        String line;
    	        while ((line = br.readLine()) != null) {
    	            // Assuming CSV format: flightCode,destination,carrier,capacity,maxWeight,maxVolume
    	            String[] data = line.split(",");
    	            flight flight = new flight(data[0], data[1], data[2], Integer.parseInt(data[3]), Double.parseDouble(data[4]), Double.parseDouble(data[5])); // Flight Code, Destination, Carrier, Capacity, Max Weight, Max Volume
    	            addFlight(flight);
    	        }
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
    	}

    			 
    			
    			
    			//addFlight(flight);
    		//	addBooking(booking);
    		
   // String flightCode = data[2].trim().toUpperCase();
	// flight = flights.get(flightCode);
// booking = new booking(data[0], passenger, flight);
    
    // Getters for passengers, flights, and bookings
    public passenger getPassenger(String bookingReference) {
        return passengers.get(bookingReference);
    }

    public flight getFlight(String flightCode) {
        return flights.get(flightCode);
    }

    public booking getBooking(String bookingReference) {
        return bookings.get(bookingReference);
    }

    // Additional methods for managing passengers and flights can be added as needed
}

