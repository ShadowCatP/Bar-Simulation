import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.List;
import java.util.Locale;
import java.awt.Color;

public abstract class Customer {
    private String name;
    private double resistance;
    private double drunkenness;
    private DecimalFormat numberFormat;
    private Beer currentBeer;
    private boolean isRemoved;
    private Image alive_image, dead_image;
    private int x, y;
    public abstract Beer chooseBeer(List<Beer> beers);

    public Customer (String name, String path, double resistance, int x_, int y_) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');
        numberFormat = new DecimalFormat("#.00", symbols);
        this.name = name;
        this.resistance = Double.parseDouble(numberFormat.format(resistance));
        alive_image = new ImageIcon(path + ".png").getImage();
        dead_image = new ImageIcon(path + " umarly.png").getImage();

        x = x_; y = y_;
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
        drunkenness += beer.getStrength() * (resistance / 100.0);
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
        if (currentBeer == null || drunkenness >= 100.0 || drunkenness == 0.0) {
            return new Beer("None", 0);
        }
        else {
        return currentBeer;
        }
    }

    public boolean isRemoved() {
        return isRemoved;
    }
    public Image getImage(){
        if (drunkenness >= 100)  {
            return dead_image;
        } else {
            return alive_image;
        }
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
