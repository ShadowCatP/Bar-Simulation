import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.*;

public class SimulationWindow extends JFrame {
    private JTextArea textArea;
    private JButton startButton, nextIterButton;
    private JLabel iterationLabel;
    private int minRegResistance, minConnResistance, minDrunkardResistance, minOccasionalDrinkerResistance;
    private int maxRegResistance, maxConnResistance, maxDrunkardResistance, maxOccasionalDrinkerResistance;
    List<Customer> customers;
    Simulation simulation;
    List<JLabel> drunkennessLabels;
    List<JLabel> choosenBeers;
    int maxIterations;
    int currIteration = 1;
    double delaying;
    public SimulationWindow(int maxIterations_, double delaying_,
                            int minRegResistance_, int minConnResistance_, int minDrunkardResistance_, int minOccasionalDrinkerResistance_,
                            int maxRegResistance_, int maxConnResistance_, int maxDrunkardResistance_, int maxOccasionalDrinkerResistance_,
                            HashMap<String, Integer> beerStrengths) {

        setTitle("Simulation Window");
        setSize(1200, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textArea = new JTextArea();
        textArea.setEditable(false);


        customers = new ArrayList<>();
        maxIterations = maxIterations_;
        delaying = delaying_;

        //-----------------Beer-----------------
        Beer.createBeers(beerStrengths);
        //-----------------/Beer-----------------

        //---------------------drunkennessLabels--and--choosenBeers---------------------
        drunkennessLabels = new ArrayList<>();
        choosenBeers = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            drunkennessLabels.add(new JLabel());
            drunkennessLabels.get(i).setFont(new Font("Arial", Font.BOLD, 14));
            add(drunkennessLabels.get(i));

            choosenBeers.add(new JLabel());
            choosenBeers.get(i).setFont(new Font("Arial", Font.BOLD, 14));
            add(choosenBeers.get(i));
        }
        //---------------------/drunkennessLabels--and--choosenBeers---------------------


        //-----------------startButton-----------------
        startButton = new JButton("Start Simulation");
        startButton.setFocusable(false);
        startButton.setBounds(650, 800, 200, 50);

        startButton.addActionListener(e -> {
            simulation = new Simulation(customers, Beer.getBeers(), 1);
            startSimulation();
        });

        add(startButton);
        //-----------------/startButton-----------------

        //-----------------nextIterButton-----------------
        if (maxIterations == 0) {
            nextIterButton = new JButton("Go to next iteration");
            nextIterButton.setFocusable(false);
            nextIterButton.setBounds(300, 800, 200, 50);
            nextIterButton.setEnabled(false);
            nextIterButton.addActionListener(e -> {
                iterationLabel.setText("Iteration: " + String.valueOf(++currIteration));
                runSimulationWithGUI();
                repaint();
            });
            add(nextIterButton);
        }
        //-----------------/nextIterButton-----------------

        minRegResistance = minRegResistance_;
        minConnResistance = minConnResistance_;
        minDrunkardResistance = minDrunkardResistance_;
        minOccasionalDrinkerResistance = minOccasionalDrinkerResistance_;

        maxRegResistance = maxRegResistance_;
        maxConnResistance = maxConnResistance_;
        maxDrunkardResistance = maxDrunkardResistance_;
        maxOccasionalDrinkerResistance = maxOccasionalDrinkerResistance_;
    }

    private void startSimulation() {

        //------------------------iterationLabel-------------------------------
        iterationLabel = new JLabel("Iteration: 1");
        iterationLabel.setBounds(525, 680, 500, 200);
        iterationLabel.setFont(new Font("Arial", Font.BOLD, 22));
        add(iterationLabel);
        //------------------------/iterationLabel-------------------------------

        //------------------------RegularsLabel-------------------------------
        JLabel RegularsLabel = new JLabel("<html>Regular<br>drinkers</html>");
        RegularsLabel.setBounds(1050, -15, 500, 200);
        RegularsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(RegularsLabel);
        //------------------------/RegularsLabel-------------------------------

        //------------------------ConnoisseursLabel-------------------------------
        JLabel ConnoisseursLabel = new JLabel("<html>Connoisseur<br>drinkers</html>");
        ConnoisseursLabel.setBounds(1050, 180, 500, 200);
        ConnoisseursLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(ConnoisseursLabel);
        //------------------------/ConnoisseursLabel-------------------------------

        //------------------------DrunkardsLabel-------------------------------
        JLabel DrunkardsLabel = new JLabel("<html>Drunkard<br>drinkers</html>");
        DrunkardsLabel.setBounds(1050, 385, 500, 200);
        DrunkardsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(DrunkardsLabel);
        //------------------------/DrunkardsLabel-------------------------------

        //------------------------OccasionalDrinkersLabel-------------------------------
        JLabel OccasionalDrinkersLabel = new JLabel("<html>Occasional<br>drinkers</html>");
        OccasionalDrinkersLabel.setBounds(1050, 585, 500, 200);
        OccasionalDrinkersLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(OccasionalDrinkersLabel);
        //------------------------/OccasionalDrinkersLabel-------------------------------


        Random rand = new Random();

        int startX = 30, startY = 50, x = startX, y = startY, distanceX = 180, distanceY = 200;
        double resistance;

        for (int i = 0; i < 6; i++) {
            resistance = minRegResistance + (maxRegResistance - minRegResistance) * rand.nextDouble();
            customers.add(new Regular("Regular_" + (i + 1), resistance, x, y));
            y += distanceY;
            resistance = minConnResistance + (maxConnResistance - minConnResistance) * rand.nextDouble();
            customers.add(new Connoisseur("Connoisseur_" + (i + 1), resistance, x, y));
            y += distanceY;
            resistance = minDrunkardResistance + (maxDrunkardResistance - minDrunkardResistance) * rand.nextDouble();
            customers.add(new Drunkard("Drunkard_" + (i + 1), resistance, x, y));
            y += distanceY;
            resistance = minOccasionalDrinkerResistance + (maxOccasionalDrinkerResistance - minOccasionalDrinkerResistance) * rand.nextDouble();
            customers.add(new OccasionalDrinker("OccasionalDrinker_" + (i + 1), resistance, x, y));
            y = startY; x += distanceX;
        }
        x = startX;
        y = startY;


        DrawCustomers drawAll = new DrawCustomers(customers);

        startButton.setEnabled(false);

        if (maxIterations == 0) {
            nextIterButton.setEnabled(true);
            runSimulationWithGUI();
            drawAll.setBounds(0, 0, 1000, 800);
            repaint();
            add(drawAll);
        }
        else {
            drawAll.setBounds(0, 0, 1000, 800);
            Timer timer = new Timer();
            for (int i = 0; i < maxIterations; i++) {
                final int finalI = i;
                TimerTask newTask = new TimerTask() {
                    @Override
                    public void run() {
                        iterationLabel.setText("Iteration: " + String.valueOf(finalI + 1));
                        runSimulationWithGUI();
                        repaint();
                        add(drawAll);
                        if (finalI >= maxIterations - 1) {
                            JLabel endButton = new JLabel("End of the simulation!!!");
                            endButton.setFont(new Font("Arial", Font.BOLD, 22));
                            endButton.setFocusable(false);
                            endButton.setBounds(350, 800, 300, 50);

                            add(endButton);
                        }
                    }
                };
                timer.schedule(newTask, (long) (i * delaying * 1000));

            }
        }
    }

    public void runSimulationWithGUI() {
        simulation.run();

        int i = 0;
        for (Customer customer : customers) {
            drunkennessLabels.get(i).setBounds(customer.getX() - 10, customer.getY() - 50, 150, 50);
            choosenBeers.get(i).setBounds(customer.getX() - 20, customer.getY() + 55, 150, 50);

            double drunkenness = customer.getDrunkenness();
            if (drunkenness > 100.0) {
                drunkenness = 100.0;
            }

            drunkennessLabels.get(i).setText("<html>drunkenness<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + drunkenness + "</html>");
            choosenBeers.get(i).setText("     " + customer.getCurrentBeer().getName());

            i++;
        }
        repaint();
    }
}