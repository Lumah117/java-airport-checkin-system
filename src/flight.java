import java.util.ArrayList;

public class flight {
    private String flightCode;
    private String destination;
    private String carrier;
    private int capacity;
    private double maxBaggageWeight;
    private double maxBaggageVolume;
    private ArrayList<passenger> passengerList;

    // Constructor
    public flight(String flightCode, String destination, String carrier, int capacity, double maxBaggageWeight, double maxBaggageVolume) {
        this.flightCode = flightCode;
        this.destination = destination;
        this.carrier = carrier;
        this.capacity = capacity;
        this.maxBaggageWeight = maxBaggageWeight;
        this.maxBaggageVolume = maxBaggageVolume;
        this.passengerList = new ArrayList<>();
    }

    // Method to add a passenger to the flight
    public void addPassenger(passenger passenger) {
        if (passengerList.size() < capacity) {
            passengerList.add(passenger);
        } else {
            System.out.println("Flight is full. Cannot add more passengers.");
        }
    }

    // Getters and Setters
    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getMaxBaggageWeight() {
        return maxBaggageWeight;
    }

    public void setMaxBaggageWeight(double maxBaggageWeight) {
        this.maxBaggageWeight = maxBaggageWeight;
    }

    public double getMaxBaggageVolume() {
        return maxBaggageVolume;
    }

    public void setMaxBaggageVolume(double maxBaggageVolume) {
        this.maxBaggageVolume = maxBaggageVolume; 
    }

    public ArrayList<passenger> getPassengerList() {
        return passengerList;
    }

    // Method to calculate the total baggage weight
    public double calculateTotalBaggageWeight() {
        double totalWeight = 0;
        for (passenger passenger : passengerList) {
            totalWeight += passenger.getBaggageWeight();
        }
        return totalWeight;
    }

    // Method to calculate the total baggage volume
    public double calculateTotalBaggageVolume() {
        double totalVolume = 0;
        for (passenger passenger : passengerList) {
            totalVolume += passenger.getBaggageVolume();
        }
        return totalVolume;
    }

    @Override
    public String toString() {
        return "Flight{" +
               "flightCode='" + flightCode + '\'' +
               ", destination='" + destination + '\'' +
               ", carrier='" + carrier + '\'' +
               ", capacity=" + capacity +
               ", maxBaggageWeight=" + maxBaggageWeight +
               ", maxBaggageVolume=" + maxBaggageVolume +
               ", passengerList=" + passengerList +
               '}';
    }
}
