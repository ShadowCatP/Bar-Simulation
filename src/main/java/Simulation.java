import java.io.IOException;
import java.util.List;

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

    public void run(int iterations, String customerType) {
        for (Customer customer : customers) {
            Beer chosenBeer = customer.chooseBeer(beers);
            customer.drink(chosenBeer);
            try {
                csvWriter.writeData(currIter, customer, customers.size());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Print the quantity of each type of beer
        for (Beer beer : beers) {
            System.out.println("Beer: " + beer.getName() + ", Quantity: " + beer.getQuantity());
        }
        printState(currIter); // TODO remove after testing

        //try {
        //    csvWriter.close();
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
//
        //++currIter;
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
}