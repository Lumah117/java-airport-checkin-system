public class passenger {
    private String name;
    private String bookingReference;
    private String flightCode;
    private boolean checkedIn;
    private double baggageWeight;
    private double baggageVolume;

    // Constructor
    public passenger(String name, String bookingReference, String flightCode, boolean checkedIn, double baggageWeight, double baggageVolume) {
        this.name = name;
        this.bookingReference = bookingReference;
        this.flightCode = flightCode;
        this.checkedIn = false; // Initially, the passenger is not checked in
        this.baggageWeight = baggageWeight;
        this.baggageVolume = baggageVolume;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookingReference() {
        return bookingReference;
    }

    public void setBookingReference(String bookingReference) {
        this.bookingReference = bookingReference;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }
    
    public double getBaggageWeight() {
        return baggageWeight;
    }

    public void setBaggageWeight(double baggageWeight) {
        this.baggageWeight = baggageWeight;
    }

    public double getBaggageVolume() {
        return baggageVolume;
    }

    public void setBaggageVolume(double baggageVolume) {
        this.baggageVolume = baggageVolume;
    }

    @Override
    public String toString() {
        return "Passenger{" +
               "name='" + name + '\'' +
               ", bookingReference='" + bookingReference + '\'' +
               ", flightCode='" + flightCode + '\'' +
               ", checkedIn=" + checkedIn +
               ", baggageWeight=" + baggageWeight +
               ", baggageVolume=" + baggageVolume +
               '}';
    }
}
