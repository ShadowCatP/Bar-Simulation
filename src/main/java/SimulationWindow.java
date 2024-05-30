import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class SimulationWindow extends JFrame {
    private JTextArea textArea;
    private JButton startButton;
    private JScrollPane scrollPane;
    private int numberOfIterations;
    private double minRegResistance, minConnResistance, minDrunkardResistance, minOccasionalDrinkerResistance;
    private double maxRegResistance, maxConnResistance, maxDrunkardResistance, maxOccasionalDrinkerResistance;
    private String customerType;
    private HashMap<String, Integer> beerQuantities;
    private HashMap<String, Integer> beerStrengths;

    public SimulationWindow(int numberOfIterations,
                            int minRegResistance_, int minConnResistance_, int minDrunkardResistance_, int minOccasionalDrinkerResistance_,
                            int maxRegResistance_, int maxConnResistance_, int maxDrunkardResistance_, int maxOccasionalDrinkerResistance_,
                            String customerType, HashMap<String, Integer> beerQuantities, HashMap<String, Integer> beerStrengths) {
        setTitle("Simulation Window");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(true);
        textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        startButton = new JButton("Start Simulation");
        startButton.addActionListener(e -> startSimulation());
        startButton.setFocusable(false);
        add(startButton, BorderLayout.SOUTH);

        minRegResistance = minRegResistance_ / 100.0;
        minConnResistance = minConnResistance_ / 100.0;
        minDrunkardResistance = minDrunkardResistance_ / 100.0;
        minOccasionalDrinkerResistance = minOccasionalDrinkerResistance_ / 100.0;

        maxRegResistance = maxRegResistance_ / 100.0;
        maxConnResistance = maxConnResistance_ / 100.0;
        maxDrunkardResistance = maxDrunkardResistance_ / 100.0;
        maxOccasionalDrinkerResistance = maxOccasionalDrinkerResistance_ / 100.0;

        this.customerType = customerType;
        this.numberOfIterations = numberOfIterations;
        this.beerQuantities = beerQuantities;
        this.beerStrengths = beerStrengths;
    }

    private void startSimulation() {
        Beer.createBeers(beerQuantities, beerStrengths);

        Random rand = new Random();
        List<Customer> customers = new ArrayList<>();
        double resistance;
        for (int i = 0; i < 6; i++) {
            resistance = minRegResistance + (maxRegResistance - minRegResistance) * rand.nextDouble();
            customers.add(new Customer("Regular_" + (i + 1), resistance, new Regular()));
            resistance = minConnResistance + (maxConnResistance - minConnResistance) * rand.nextDouble();
            customers.add(new Customer("Connoisseur_" + (i + 1), resistance, new Connoisseur()));
            resistance = minDrunkardResistance + (maxDrunkardResistance - minDrunkardResistance) * rand.nextDouble();
            customers.add(new Customer("Drunkard_" + (i + 1), resistance, new Drunkard()));
            resistance = minOccasionalDrinkerResistance + (maxOccasionalDrinkerResistance - minOccasionalDrinkerResistance) * rand.nextDouble();
            customers.add(new Customer("OccasionalDrinker_" + (i + 1), resistance, new OccasionalDrinker()));
        }

        Simulation simulation = new Simulation(customers, Beer.getBeers());
        simulation.run(numberOfIterations, customerType);
    }
}