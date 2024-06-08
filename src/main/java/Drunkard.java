import java.util.List;
import java.util.Random;
import java.awt.Color;

public class Drunkard extends Customer {
    private int counter = 0;
    private boolean removed = false;
    private boolean hasReturned = false;
    public Drunkard(String name, double resistance, int x, int y) {
        super(name, resistance, x, y, Color.orange);
    }

    @Override
    public Beer chooseBeer(List<Beer> beers) {
        if (removed) {
            counter++;
            if (counter >= 4 && !hasReturned) {
                color = Color.orange;
                removed = false;
                hasReturned = true;
                counter = 0;
            }

            return null;
        } else {
            counter = 0;
            Random rand = new Random();
            return beers.get(rand.nextInt(beers.size()));
        }
    }

    public void remove() {
        removed = true;
        counter = 0;
    }


    @Override
    public void drink(Beer beer) {
        super.drink(beer);
        if (getDrunkenness() > 100.0) {
           remove();
           setDrunkenness(0.0);
           color = Color.red;
        }
    }

    public boolean isRemoved() {
        return removed;
    }
    public boolean hasReturned() {
        return hasReturned;
    }
}
