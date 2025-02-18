import java.util.*;

public class LinkBudgetCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Getting user input for each parameter
        System.out.print("Enter Transmitter Power (dBm): ");
        double txPower = scanner.nextDouble();

        System.out.print("Enter Transmitter Antenna Gain (dBi): ");
        double txGain = scanner.nextDouble();

        System.out.print("Enter Receiver Antenna Gain (dBi): ");
        double rxGain = scanner.nextDouble();

        System.out.print("Enter Path Loss (dB): ");
        double pathLoss = scanner.nextDouble();

        System.out.print("Enter Additional Losses (e.g., connectors, fading, dB): ");
        double additionalLoss = scanner.nextDouble();

        // Calculate the received power
        double receivedPower = txPower + txGain + rxGain - pathLoss - additionalLoss;

        // Display the result
        System.out.printf("Received Power at the receiver (dBm): %.2f\n", receivedPower);

        scanner.close();
    }
}
