import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.List;
import java.util.Random;

public class SimulationWindow extends JFrame {
    private JTextArea textArea;
    private JButton startButton, nextIterButton;
    private JLabel iterationLabel;
    private int numberOfIterations; //???
    private double minRegResistance, minConnResistance, minDrunkardResistance, minOccasionalDrinkerResistance;
    private double maxRegResistance, maxConnResistance, maxDrunkardResistance, maxOccasionalDrinkerResistance;
    private String customerType; //???
    private HashMap<String, Integer> beerQuantities; //???
    private HashMap<String, Integer> beerStrengths; //???
    List<Customer> customers;
    Simulation simulation;
    int maxIterations;
    int currIteration = 1;
    int delaying;
    public SimulationWindow(int maxIterations_, int delaying_,
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
        maxIterations = maxIterations_;
        delaying = delaying_;
        //-----------------Beer-----------------
        this.customerType = customerType;
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

        if (maxIterations == 0) {
            //-----------------nextIterButton-----------------
            nextIterButton = new JButton("Go to next iteration");
            nextIterButton.setFocusable(false);
            nextIterButton.setBounds(200, 700, 200, 50);
            nextIterButton.setEnabled(false);
            nextIterButton.addActionListener(e -> {
                iterationLabel.setText("Iteration: " + String.valueOf(++currIteration));
                simulation.run();
                repaint();
            });
            add(nextIterButton);
            //-----------------/nextIterButton-----------------
        }

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

        ///------------------------iterationLabel-------------------------------
        iterationLabel = new JLabel("Iteration: 1");
        iterationLabel.setBounds(400, 550, 500, 200);
        iterationLabel.setFont(new Font("Arial", Font.BOLD, 22));
        add(iterationLabel);
        ///------------------------/iterationLabel-------------------------------

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

        if (maxIterations == 0) {
            startButton.setEnabled(false);
            nextIterButton.setEnabled(true);
            simulation.run();
            drawAll.setBounds(0, 0, 1000, 800);
            repaint();
            add(drawAll);
        }
        else {
            startButton.setEnabled(false);
            drawAll.setBounds(0, 0, 1000, 800);
            Timer timer = new Timer();
            for (int i = 0; i < maxIterations; i++) {
                final int finalI = i;
                TimerTask newTask = new TimerTask() { // Create a new TimerTask for each iteration
                    @Override
                    public void run() {
                        iterationLabel.setText("Iteration: " + String.valueOf(finalI + 1));
                        simulation.run();
                        repaint();
                        add(drawAll);
                    }
                };
                timer.schedule(newTask, i * delaying * 1000);
            }
        }
    }
}