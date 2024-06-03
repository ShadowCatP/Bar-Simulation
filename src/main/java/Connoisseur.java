import java.util.List;
import java.util.Random;

public class Connoisseur extends Customer {
    private Beer lastBeer = null;
    private int sameBeerCount = 1;

    public Connoisseur(String name, double resistance) {
        super(name, resistance);
    }

    @Override
    public Beer chooseBeer(List<Beer> beers) {
        if (sameBeerCount < 3 && lastBeer != null) {
            sameBeerCount++;
            return lastBeer;
        } else {
            sameBeerCount = 1;
            Random rand = new Random();
            lastBeer = beers.get(rand.nextInt(beers.size()));
            return lastBeer;
        }
    }
}
