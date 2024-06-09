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

    public void run(List<JLabel> drunkennessLabels, List<JLabel> choosenBeers) {
        int i = 0;
        for (Customer customer : customers) {
            Beer chosenBeer = customer.chooseBeer(beers);
            customer.drink(chosenBeer);

            drunkennessLabels.get(i).setBounds(customer.getX() - 20, customer.getY() - 50, 150, 50);
            choosenBeers.get(i).setBounds(customer.getX() - 20, customer.getY() + 40, 150, 50);

            double drunkenness = customer.getDrunkenness();
            if (drunkenness > 100.0) {
                drunkenness = 100.0;
            }

            drunkennessLabels.get(i).setText("<html>drunkenness<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + drunkenness + "</html>");
            drunkennessLabels.get(i).setForeground(customer.getCurrColor());

            choosenBeers.get(i).setText("     " + customer.getCurrentBeer().getName());
            choosenBeers.get(i).setForeground(customer.getCurrColor());

            try {
                csvWriter.writeData(currIter, customer, customers.size());
            } catch (IOException e) {
                e.printStackTrace();
            }
            i++;
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
