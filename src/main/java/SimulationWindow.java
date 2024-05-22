import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationWindow extends JFrame {
    private JTextArea textArea;
    private JButton startButton;
    private JScrollPane scrollPane;

    public SimulationWindow() {
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
        add(startButton, BorderLayout.SOUTH);
    }

    private void startSimulation() {
        // TODO Initialize the simulation
        Beer.createBeers();

        Random rand = new Random();
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            double resistance = 0.7 + (1.0 - 0.7) * rand.nextDouble();
            customers.add(new Customer("Regular_" + (i + 1), resistance, new Regular()));
            resistance = 0.7 + (1.0 - 0.7) * rand.nextDouble();
            customers.add(new Customer("Connoisseur_" + (i + 1), resistance, new Connoisseur()));
            resistance = 0.7 + (1.0 - 0.7) * rand.nextDouble();
            customers.add(new Customer("Drunkard_" + (i + 1), resistance, new Drunkard()));
        }

        Simulation simulation = new Simulation(customers, Beer.getBeers());
        simulation.run(10, "Regular");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimulationWindow().setVisible(true));
    }
}