import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// This class writes the results of the simulation to a CSV file.
// TODO Adjust for the Drunkard behavior

public class CSVWriter {

    private static final String CSV_HEADER = "Iteration,Customer ID,Customer Type,Beer Type,Drunkenness Level,Resistance Level,Return Status";
    private FileWriter fileWriter;

    public CSVWriter(String fileName) throws IOException {
        File directory = new File("simulation_results");
        if (!directory.exists()) {
            directory.mkdir();
        }
        fileWriter = new FileWriter(new File(directory, fileName));
        fileWriter.append(CSV_HEADER);
        fileWriter.append("\n");
    }

    public void writeData (int iteration, Customer customer) throws IOException {
        fileWriter.append(String.valueOf(iteration));
        fileWriter.append(",");
        fileWriter.append(customer.getName());
        fileWriter.append(",");
        fileWriter.append(customer.getClass().getSimpleName());
        fileWriter.append(",");
        fileWriter.append(customer.getCurrentBeer() != null ? customer.getCurrentBeer().getName() : "None");
        fileWriter.append(",");
        fileWriter.append(String.valueOf(customer.getDrunkenness()));
        fileWriter.append(",");
        fileWriter.append(String.valueOf(customer.getResistance()));
        fileWriter.append(",");
        if (customer instanceof Drunkard) {
            Drunkard drunkard = (Drunkard) customer;
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
