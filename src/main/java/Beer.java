import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Beer {

    static Vector<Beer> beers;

    private String name;
    private int strength;

    public Beer(String name, int strength) {
        this.name = name;
        this.strength = strength;
    }

    static void createBeers(HashMap<String, Integer> beerStrengths) {
        beers = new Vector<Beer>();
        for (Map.Entry<String, Integer> entry : beerStrengths.entrySet()) {
            String beerName = entry.getKey();
            int strength = beerStrengths.get(beerName);
            beers.add(new Beer(beerName, strength));
        }
    }

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }

    public static Vector<Beer> getBeers() {
        return beers;
    }
}
