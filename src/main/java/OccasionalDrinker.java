import java.util.List;
import java.util.Random;

public class OccasionalDrinker extends Customer {
    private int counter = 0;

    public OccasionalDrinker(String name, double resistance) {
        super(name, resistance);
    }


    @Override
    public Beer chooseBeer(List<Beer> beers) {
        counter++;
        if (counter % 2 == 0) {
            Random rand = new Random();
            return beers.get(rand.nextInt(beers.size()));
        } else {
            return null;
        }
    }
}
