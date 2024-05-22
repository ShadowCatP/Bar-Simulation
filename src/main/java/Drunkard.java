import java.util.List;
import java.util.Random;

public class Drunkard implements Behavior {
    private int counter = 0;
    private boolean removed = false;
    private boolean hasReturned = false;

    @Override
    public Beer chooseBeer(List<Beer> beers) {
        if (removed) {
            counter++;
            if (counter >= 5 && !hasReturned) {
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

    public boolean isRemoved() {
        return removed;
    }
}
