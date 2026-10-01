import java.util.Map;

public class reportGenerator {
    private checkInSystem checkInSystem;

    // Constructor
    public reportGenerator(checkInSystem checkInSystem) {
        this.checkInSystem = checkInSystem;
    }

    // Method to generate a report
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        double totalWeight = 0;
        double totalVolume = 0;
        double totalFees = 0;
        int checkedInPassengers = 0; 

        for (Map.Entry<String, booking> entry : checkInSystem.getBookings().entrySet()) {
            booking booking = entry.getValue();
            passenger passenger = booking.getPassenger();
            flight flight = booking.getFlight();

            if (passenger.isCheckedIn()) {
                double weight = passenger.getBaggageWeight();
                double volume = passenger.getBaggageVolume();
                double fee = checkInSystem.calculateLuggageFee(passenger.getBookingReference(), weight, volume);

                report.append("Passenger: ").append(passenger.getName())
                      .append(", Flight: ").append(flight.getFlightCode())
                      .append(", Weight: ").append(weight)
                      .append(", Volume: ").append(volume)
                      .append(", Fee: ").append(fee).append("\n");

                totalWeight += weight;
                totalVolume += volume;
                totalFees += fee;
                checkedInPassengers++;
            }
        }

        report.append("\nSummary:\n")
              .append("Total Checked-In Passengers: ").append(checkedInPassengers)
              .append("\nTotal Luggage Weight: ").append(totalWeight)
              .append("\nTotal Luggage Volume: ").append(totalVolume)
              .append("\nTotal Fees Collected: ").append(totalFees);

        return report.toString();
    }
}
