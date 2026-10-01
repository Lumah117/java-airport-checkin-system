public class booking {
    private String bookingReference;
    private passenger passenger;
    private flight flight;

    // Constructor
    public booking(String bookingReference, passenger passenger, flight flight) {
        this.bookingReference = bookingReference;
        this.passenger = passenger;
        this.flight = flight;
    }

    // Getters and Setters
    public String getBookingReference() {
        return bookingReference;
    }

    public void setBookingReference(String bookingReference) {
        this.bookingReference = bookingReference;
    }

    public passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(passenger passenger) {
        this.passenger = passenger;
    }

    public flight getFlight() {
        return flight;
    }

    public void setFlight(flight flight) {
        this.flight = flight;
    }

    @Override
    public String toString() {
        return "Booking{" +
               "bookingReference='" + bookingReference + '\'' +
               ", passenger=" + passenger +
               ", flight=" + flight +
               '}';
    }
}
