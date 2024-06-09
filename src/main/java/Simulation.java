import java.io.IOException;
import java.util.List;
import javax.swing.JLabel;
public class Simulation {
    private List<Customer> customers;
    private List<Beer> beers;
    private CSVWriter csvWriter;

    private int currIter = 0;

    public Simulation(List<Customer> customers, List<Beer> beers) {
        this.customers = customers;
        this.beers = beers;

        try {
            csvWriter = new CSVWriter("simulation_results.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        for (Customer customer : customers) {
            Beer chosenBeer = customer.chooseBeer(beers);
            customer.drink(chosenBeer);

            double drunkenness = customer.getDrunkenness();
            if (drunkenness > 100.0) {
                drunkenness = 100.0;
            }

            try {
                csvWriter.writeData(currIter, customer, customers.size());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Print the quantity of each type of beer
        for (Beer beer : beers) {
            System.out.println("Beer: " + beer.getName());
        }
        printState(currIter); // TODO remove after testing
        ++currIter;
    }

    public void printState(int iteration) {
        System.out.println("Iteration: " + iteration);
        for (Customer customer : customers) {
            if (customer instanceof Drunkard) {
                Drunkard drunkard = (Drunkard) customer;
                if (drunkard.isRemoved() && drunkard.hasReturned()) {
                    continue;
                }
            }
            if (customer.getDrunkenness() <= 100.0) {
                System.out.println("Customer: " + customer.getName()
                        + " Drunkenness: " + customer.getDrunkenness()
                        + " Resistance: " + customer.getResistance()
                        + " Current Beer: " + (customer.getCurrentBeer() != null ? customer.getCurrentBeer().getName() : "None"));
            }
        }
    }
    public int currIter(){
        return currIter;
    }
}
