import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gui {
    private checkInSystem checkInSystem;
    private JFrame frame;
    private JTextField bookingRefField;
    private JTextField luggageWeightField;
    private JTextField luggageVolumeField;
    private JTextArea outputArea;

    // Constructor
    public gui (checkInSystem checkInSystem) {
        this.checkInSystem = checkInSystem;
        initializeUI(); 
    }

    // Initialise the User Interface
    private void initializeUI() {
        frame = new JFrame("Airport Check-In System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(0, 1));

        // Booking Reference Input
        bookingRefField = new JTextField(20);
        frame.add(new JLabel("Enter Booking Reference:"));
        frame.add(bookingRefField);

        // Luggage Weight Input
        luggageWeightField = new JTextField(20);
        frame.add(new JLabel("Enter Luggage Weight (kg):"));
        frame.add(luggageWeightField);

        // Luggage Volume Input
        luggageVolumeField = new JTextField(20);
        frame.add(new JLabel("Enter Luggage Volume (m^3):"));
        frame.add(luggageVolumeField);

        // Check-in Button
        JButton checkInButton = new JButton("Check-In");
        checkInButton.addActionListener(new CheckInButtonListener());
        frame.add(checkInButton);

        // Output Area
        outputArea = new JTextArea(5, 20);
        outputArea.setEditable(false);
        frame.add(new JScrollPane(outputArea));

        frame.pack();
        frame.setVisible(true);
    } 

    // Action Listener for Check-In Button
    class CheckInButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String bookingRef = bookingRefField.getText();
            double weight = Double.parseDouble(luggageWeightField.getText());
            double volume = Double.parseDouble(luggageVolumeField.getText());

            // Process check-in and calculate fees
            checkInSystem.checkIn(bookingRef);
            passenger passenger = checkInSystem.getPassenger(bookingRef);
            double fee = checkInSystem.calculateLuggageFee(passenger.getBookingReference(), weight, volume);

            outputArea.setText("Checked in successfully.\nExcess luggage fee: " + fee);
        }
    }

    // Main method to run the GUI
    public static void main(String[] args) {
        // Initialise the check-in system (this should be replaced with actual system initialisation)
        checkInSystem checkInSystem = new checkInSystem();
        new gui (checkInSystem);
    }
}
