import java.text.DecimalFormat;

public class Customer {
    private String name;
    private double resistance;
    private double drunkenness;
    private Behavior behavior;
    private DecimalFormat numberFormat = new DecimalFormat("#.00");
    private Beer currentBeer;

    public Customer (String name, double resistance, Behavior behavior) {
        this.name = name;
        this.resistance = Double.parseDouble(numberFormat.format(resistance));
        this.behavior = behavior;
    }

    public void drink(Beer beer) {
        if (beer == null || drunkenness >= 100.0) {
            return;
        }
        currentBeer = beer;
        drunkenness += beer.getStrength() * resistance;
        drunkenness = Double.parseDouble(numberFormat.format(drunkenness));
    }

    public Behavior getBehavior() {
        return behavior;
    }

    public double getDrunkenness() {
        return drunkenness;
    }

    public String getName() {
        return name;
    }

    public double getResistance() {
        return resistance;
    }

    public void setDrunkenness(double drunkenness) {
        this.drunkenness = drunkenness;
    }

    public Beer getCurrentBeer() {
        return currentBeer;
    }
}
