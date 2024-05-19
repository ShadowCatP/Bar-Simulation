import java.util.Iterator;
import java.util.List;

public class Simulation {
    private List<Customer> customers;
    private List<Beer> beers;

    public Simulation(List<Customer> customers, List<Beer> beers) {
        this.customers = customers;
        this.beers = beers;
    }

    public void run (int iterations) {
        for (int i = 0; i < iterations; i++) {
            for (Customer customer : customers) {
                Beer chosenBeer = customer.getBehavior().chooseBeer(beers);
                customer.drink(chosenBeer);
                if (customer.getDrunkenness() > 1.0 && customer.getBehavior() instanceof Drunkard) {
                    ((Drunkard) customer.getBehavior()).remove();
                }
            }
        }
    }
}
