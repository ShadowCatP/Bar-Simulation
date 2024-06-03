import java.util.List;
import java.util.Random;

public class Regular extends Customer {
    public Regular(String name, double resistance) {
        super(name, resistance);
    }

    @Override
    public Beer chooseBeer(List<Beer> beers) {
        Random rand = new Random();
        return beers.get(rand.nextInt(beers.size()));
    }
}
