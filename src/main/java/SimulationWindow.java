import javax.swing.*;
import java.util.*;
import java.util.List;

public class SimulationWindow extends JFrame {
    private JTextArea textArea;
    private JButton startButton, nextIterButton;
    private int numberOfIterations;
    private double minRegResistance, minConnResistance, minDrunkardResistance, minOccasionalDrinkerResistance;
    private double maxRegResistance, maxConnResistance, maxDrunkardResistance, maxOccasionalDrinkerResistance;
    private String customerType;
    private HashMap<String, Integer> beerQuantities;
    private HashMap<String, Integer> beerStrengths;
    List<Customer> customers;
    Simulation simulation;
    public SimulationWindow(int numberOfIterations,
                            int minRegResistance_, int minConnResistance_, int minDrunkardResistance_, int minOccasionalDrinkerResistance_,
                            int maxRegResistance_, int maxConnResistance_, int maxDrunkardResistance_, int maxOccasionalDrinkerResistance_,
                            String customerType, HashMap<String, Integer> beerQuantities, HashMap<String, Integer> beerStrengths) {
        setTitle("Simulation Window");
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        textArea = new JTextArea();
        textArea.setEditable(false);

        customers = new ArrayList<>();

        //-----------------Beer-----------------
        this.customerType = customerType;
        this.numberOfIterations = numberOfIterations;
        this.beerQuantities = beerQuantities;
        this.beerStrengths = beerStrengths;
        Beer.createBeers(beerQuantities, beerStrengths);
        //-----------------/Beer-----------------

        //-----------------startButton-----------------
        startButton = new JButton("Start Simulation");
        startButton.setFocusable(false);
        startButton.setBounds(600, 700, 200, 50);

        startButton.addActionListener(e -> {
            simulation = new Simulation(customers, Beer.getBeers());
            startSimulation();
        });

        add(startButton);
        //-----------------/startButton-----------------

        //-----------------nextIterButton-----------------
        nextIterButton = new JButton("Go to next iteration");
        nextIterButton.setFocusable(false);
        nextIterButton.setBounds(200, 700, 200, 50);
        nextIterButton.setEnabled(false);
        nextIterButton.addActionListener(e -> {
            simulation.run();
        });
        add(nextIterButton);
        //-----------------/nextIterButton-----------------

        minRegResistance = minRegResistance_ / 100.0;
        minConnResistance = minConnResistance_ / 100.0;
        minDrunkardResistance = minDrunkardResistance_ / 100.0;
        minOccasionalDrinkerResistance = minOccasionalDrinkerResistance_ / 100.0;

        maxRegResistance = maxRegResistance_ / 100.0;
        maxConnResistance = maxConnResistance_ / 100.0;
        maxDrunkardResistance = maxDrunkardResistance_ / 100.0;
        maxOccasionalDrinkerResistance = maxOccasionalDrinkerResistance_ / 100.0;
    }

    private void startSimulation() {
        Random rand = new Random();

        int startXAndY = 50, x = startXAndY, y = startXAndY, distanceX = 280, distanceY = 100;
        double resistance;
        for (int i = 0; i < 6; i++) {
            resistance = minRegResistance + (maxRegResistance - minRegResistance) * rand.nextDouble();
            customers.add(new Regular("Regular_" + (i + 1), resistance, x, y));
            x += distanceX;
            resistance = minConnResistance + (maxConnResistance - minConnResistance) * rand.nextDouble();
            customers.add(new Connoisseur("Connoisseur_" + (i + 1), resistance, x, y));
            x += distanceX;
            resistance = minDrunkardResistance + (maxDrunkardResistance - minDrunkardResistance) * rand.nextDouble();
            customers.add(new Drunkard("Drunkard_" + (i + 1), resistance, x, y));
            x += distanceX;
            resistance = minOccasionalDrinkerResistance + (maxOccasionalDrinkerResistance - minOccasionalDrinkerResistance) * rand.nextDouble();
            customers.add(new OccasionalDrinker("OccasionalDrinker_" + (i + 1), resistance, x, y));
            x = startXAndY; y += distanceY;
        }
        y = startXAndY;

        DrawAll drawAll = new DrawAll(customers);
        drawAll.setBounds(0, 0, 1000, 800);
        repaint();
        add(drawAll);
        startButton.setEnabled(false);
        nextIterButton.setEnabled(true);

        simulation.run();
    }
}