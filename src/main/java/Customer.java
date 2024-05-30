import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Customer {
    private String name;
    private double resistance;
    private double drunkenness;
    private Behavior behavior;
    private DecimalFormat numberFormat;
    private Beer currentBeer;
    private boolean isRemoved;

    public Customer (String name, double resistance, Behavior behavior) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');
        numberFormat = new DecimalFormat("#.00", symbols);
        this.name = name;
        this.resistance = Double.parseDouble(numberFormat.format(resistance));
        this.behavior = behavior;
    }

    public void drink(Beer beer) {
        if (beer == null) {
            currentBeer = null;
            return;
        }

        if (drunkenness >= 100.0) {
            isRemoved = true;
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

    public boolean isRemoved() {
        return isRemoved;
    }
}
