import java.util.Vector;

public class Beer {

    static Vector<Beer> beers;

    static void createBeers() {
        beers = new Vector<Beer>();
        beers.add(new Beer("Corona Light", 8));
        beers.add(new Beer("Heineken", 12));
        beers.add(new Beer("Blackout Stout", 17));
        beers.add(new Beer("Guinness",22));
    }
    private String name;
    private int strength;

    public Beer(String name, int strength) {
        this.name = name;
        this.strength = strength;
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
