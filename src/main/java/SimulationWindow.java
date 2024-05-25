import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationWindow extends JFrame {
    private JTextArea textArea;
    private JButton startButton;
    private JScrollPane scrollPane;
    private int numberOfIterations;
    private double minRegResistance, minConnResistance, minDrunkardResistance;
    private double maxRegResistance, maxConnResistance, maxDrunkardResistance;
    private static String customerType;

    public SimulationWindow(int numberOfIterations,
    int minRegResistance_, int minConnResistance_, int minDrunkardResistance_,
    int maxRegResistance_, int maxConnResistance_, int maxDrunkardResistance_,
                            String customerType) {
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

        this.numberOfIterations = numberOfIterations;

        minRegResistance = minRegResistance_ / 100.0;
        minConnResistance = minConnResistance_ / 100.0;
        minDrunkardResistance = minDrunkardResistance_ / 100.0;

        maxRegResistance = maxRegResistance_ / 100.0;
        maxConnResistance = maxConnResistance_ / 100.0;
        maxDrunkardResistance = maxDrunkardResistance_ / 100.0;

        this.customerType = customerType;
    }

    private void startSimulation() {
        // TODO Initialize the simulation
        Beer.createBeers();

        Random rand = new Random();
        List<Customer> customers = new ArrayList<>();
        double resistance;
        for (int i = 0; i < 6; i++) {
            resistance = minRegResistance + (maxRegResistance - minRegResistance) / rand.nextDouble();
            customers.add(new Customer("Regular_" + (i + 1), resistance, new Regular()));
            resistance = minConnResistance + (maxConnResistance - minConnResistance) / rand.nextDouble();
            customers.add(new Customer("Connoisseur_" + (i + 1), resistance, new Connoisseur()));
            resistance = minDrunkardResistance + (maxDrunkardResistance - minDrunkardResistance) / rand.nextDouble();
            customers.add(new Customer("Drunkard_" + (i + 1), resistance, new Drunkard()));
        }

        Simulation simulation = new Simulation(customers, Beer.getBeers());
        simulation.run(numberOfIterations, customerType);
    }
}