import java.io.FileWriter;
import java.io.IOException;

// This class writes the results of the simulation to a CSV file.
// TODO Adjust for the Drunkard behavior

public class CSVWriter {

    private static final String CSV_HEADER = "Iteration,Customer ID,Customer Type,Beer Type,Drunkenness Level,Resistance Level,Remaining Customers,Return Status";
    private FileWriter fileWriter;

    public CSVWriter(String fileName) throws IOException {
        fileWriter = new FileWriter(fileName);
        fileWriter.append(CSV_HEADER);
        fileWriter.append("\n");
    }

    public void writeData (int iteration, Customer customer, int remainingCustomers) throws IOException {
        fileWriter.append(String.valueOf(iteration));
        fileWriter.append(",");
        fileWriter.append(customer.getName());
        fileWriter.append(",");
        fileWriter.append(customer.getBehavior().getClass().getSimpleName());
        fileWriter.append(",");
        fileWriter.append(customer.getCurrentBeer() != null ? customer.getCurrentBeer().getName() : "None");
        fileWriter.append(",");
        fileWriter.append(String.valueOf(customer.getDrunkenness()));
        fileWriter.append(",");
        fileWriter.append(String.valueOf(customer.getResistance()));
        fileWriter.append(",");
        fileWriter.append(String.valueOf(remainingCustomers));
        fileWriter.append(",");
        if (customer.getBehavior() instanceof Drunkard) {
            Drunkard drunkard = (Drunkard) customer.getBehavior();
            if (drunkard.isRemoved() && !drunkard.hasReturned()) {
                fileWriter.append("Will return");
            } else if (drunkard.isRemoved() && drunkard.hasReturned()) {
                fileWriter.append("Left");
            } else {
                fileWriter.append("Stayed");
            }
        } else {
            fileWriter.append(customer.isRemoved() ? "Left" : "Stayed");
        }
        fileWriter.append("\n");
    }

    public void close() throws IOException {
        fileWriter.flush();
        fileWriter.close();
    }
}
