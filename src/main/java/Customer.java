import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public abstract class Customer {
    private String name;
    private double resistance;
    private double drunkenness;
    private DecimalFormat numberFormat;
    private Beer currentBeer;
    private boolean isRemoved;

    public abstract Beer chooseBeer(List<Beer> beers);

    public Customer (String name, double resistance) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');
        numberFormat = new DecimalFormat("#.00", symbols);
        this.name = name;
        this.resistance = Double.parseDouble(numberFormat.format(resistance));
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
