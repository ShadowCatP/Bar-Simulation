import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationWindow extends JFrame {
    private JTextArea textArea;
    private JButton startButton;
    private JScrollPane scrollPane;
    private static int numberOfIterations;
    private static double minResistance;
    private static double maxResistance;
    private static String customerType;

    public SimulationWindow(int numberOfIterations, int minResistance, int maxResistance, String customerType) {
        setTitle("Simulation Window");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        startButton = new JButton("Start Simulation");
        startButton.addActionListener(e -> startSimulation());
        startButton.setFocusable(false);
        add(startButton, BorderLayout.SOUTH);

        this.numberOfIterations = numberOfIterations;
        this.minResistance = minResistance / 100.0;
        this.maxResistance = maxResistance / 100.0;
        this.customerType = customerType;
    }

    private void startSimulation() {
        // TODO Initialize the simulation
        Beer.createBeers();

        Random rand = new Random();
        List<Customer> customers = new ArrayList<>();
        double resistance;
        for (int i = 0; i < 6; i++) {
            resistance = minResistance + (maxResistance - minResistance) * rand.nextDouble();
            customers.add(new Customer("Regular_" + (i + 1), resistance, new Regular()));
            resistance = minResistance + (maxResistance - minResistance) * rand.nextDouble();
            customers.add(new Customer("Connoisseur_" + (i + 1), resistance, new Connoisseur()));
            resistance = minResistance + (maxResistance - minResistance) * rand.nextDouble();
            customers.add(new Customer("Drunkard_" + (i + 1), resistance, new Drunkard()));
        }

        Simulation simulation = new Simulation(customers, Beer.getBeers());
        simulation.run(numberOfIterations, customerType);
    }
}