import java.util.List;
import java.util.Random;
import java.awt.Color;

public class Regular extends Customer {
    public Regular(String name, double resistance, int x, int y) {
        super(name, resistance, x, y, Color.blue);
    }

    @Override
    public Beer chooseBeer(List<Beer> beers) {
        Random rand = new Random();
        return beers.get(rand.nextInt(beers.size()));
    }
}
