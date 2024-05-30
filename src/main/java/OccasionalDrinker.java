import java.util.List;
import java.util.Random;

public class OccasionalDrinker implements Behavior {
    private int counter = 0;


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
