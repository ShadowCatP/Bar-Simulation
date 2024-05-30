import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Beer {

    static Vector<Beer> beers;

    private String name;
    private int strength;
    private int quantity;

    public Beer(String name, int strength, int quantity) {
        this.name = name;
        this.strength = strength;
        this.quantity = quantity;
    }

    static void createBeers(HashMap<String, Integer> beerQuantities, HashMap<String, Integer> beerStrengths) {
        beers = new Vector<Beer>();
        for (Map.Entry<String, Integer> entry : beerQuantities.entrySet()) {
            String beerName = entry.getKey();
            int quantity = entry.getValue();
            int strength = beerStrengths.get(beerName);
            beers.add(new Beer(beerName, strength, quantity));
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

    public int getQuantity() {
        return quantity;
    }
}
