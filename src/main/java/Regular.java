import java.util.List;
import java.util.Random;

public class Regular implements Behavior {
    @Override
    public Beer chooseBeer(List<Beer> beers) {
        Random rand = new Random();
        return beers.get(rand.nextInt(beers.size()));
    }
}
