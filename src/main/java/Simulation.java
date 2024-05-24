import java.util.List;

public class Simulation {
    private List<Customer> customers;
    private List<Beer> beers;

    public Simulation(List<Customer> customers, List<Beer> beers) {
        this.customers = customers;
        this.beers = beers;
    }

    public void run (int iterations, String customerType) {
        for (int i = 0; i < iterations; i++) {

            for (Customer customer : customers) {
                Beer chosenBeer = customer.getBehavior().chooseBeer(beers);
                customer.drink(chosenBeer);
                if (customer.getDrunkenness() > 100.0 && customer.getBehavior() instanceof Drunkard) {
                    ((Drunkard) customer.getBehavior()).remove();
                    customer.setDrunkenness(0.0);
                }
            }

            printState(i, customerType); // TODO remove after testing
        }
    }

    public void printState(int iteration, String customerType) {
        System.out.println("Iteration: " + iteration);
        for (Customer customer : customers) {
            if (customer.getDrunkenness() <= 100.0 && (customerType.equals("All") || customer.getBehavior().getClass().getSimpleName().equals(customerType) || customer.getName().equals(customerType))) {
                System.out.println("Customer: " + customer.getName()
                        + " Drunkenness: " + customer.getDrunkenness()
                        + " Resistance: " + customer.getResistance()
                        + " Current Beer: " + (customer.getCurrentBeer() != null ? customer.getCurrentBeer().getName() : "None"));
            }
        }
    }
}
