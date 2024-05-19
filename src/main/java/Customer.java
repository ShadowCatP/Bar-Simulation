public class Customer {
    private String name;
    private double resistance;
    private double drunkenness;
    private Behavior behavior;

    public Customer (String name, double resistance, Behavior behavior) {
        this.name = name;
        this.resistance = resistance;
        this.behavior = behavior;
    }

    public void drink(Beer beer) {
        if (beer == null) {
            return;
        }
        drunkenness += beer.getStrength() * resistance;
    }

    public Behavior getBehavior() {
        return behavior;
    }

    public double getDrunkenness() {
        return drunkenness;
    }
}
