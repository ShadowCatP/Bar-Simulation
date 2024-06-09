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
    private int numberOfIterations;
    private double minRegResistance, minConnResistance, minDrunkardResistance, minOccasionalDrinkerResistance;
    private double maxRegResistance, maxConnResistance, maxDrunkardResistance, maxOccasionalDrinkerResistance;
    List<Customer> customers;
    Simulation simulation;
    List<JLabel> drunkennessLabels;
    List<JLabel> choosenBeers;
    int maxIterations;
    int currIteration = 1;
    int delaying;
    public SimulationWindow(int maxIterations_, int delaying_,
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
            simulation = new Simulation(customers, Beer.getBeers());
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

        //------------------------iterationLabel-------------------------------
        iterationLabel = new JLabel("Iteration: 1");
        iterationLabel.setBounds(525, 680, 500, 200);
        iterationLabel.setFont(new Font("Arial", Font.BOLD, 22));
        add(iterationLabel);
        //------------------------/iterationLabel-------------------------------

        //------------------------RegularsLabel-------------------------------
        JLabel RegularsLabel = new JLabel("<html>Regular<br>drinkers</html>");
        RegularsLabel.setBounds(1050, -25, 500, 200);
        RegularsLabel.setForeground(Color.DARK_GRAY);
        RegularsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(RegularsLabel);
        //------------------------/RegularsLabel-------------------------------

        //------------------------ConnoisseursLabel-------------------------------
        JLabel ConnoisseursLabel = new JLabel("<html>Connoisseur<br>drinkers</html>");
        ConnoisseursLabel.setBounds(1050, 170, 500, 200);
        ConnoisseursLabel.setForeground(Color.PINK);
        ConnoisseursLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(ConnoisseursLabel);
        //------------------------/ConnoisseursLabel-------------------------------

        //------------------------DrunkardsLabel-------------------------------
        JLabel DrunkardsLabel = new JLabel("<html>Drunkard<br>drinkers</html>");
        DrunkardsLabel.setBounds(1050, 370, 500, 200);
        DrunkardsLabel.setForeground(Color.ORANGE);
        DrunkardsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(DrunkardsLabel);
        //------------------------/DrunkardsLabel-------------------------------

        //------------------------OccasionalDrinkersLabel-------------------------------
        JLabel OccasionalDrinkersLabel = new JLabel("<html>Occasional<br>drinkers</html>");
        OccasionalDrinkersLabel.setBounds(1050, 570, 500, 200);
        OccasionalDrinkersLabel.setForeground(Color.BLUE);
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
                    }
                };
                timer.schedule(newTask, i * delaying * 1000);
            }
        }
    }

    public void runSimulationWithGUI() {
        simulation.run();

        int i = 0;
        for (Customer customer : customers) {
            drunkennessLabels.get(i).setBounds(customer.getX() - 20, customer.getY() - 50, 150, 50);
            choosenBeers.get(i).setBounds(customer.getX() - 20, customer.getY() + 40, 150, 50);

            double drunkenness = customer.getDrunkenness();
            if (drunkenness > 100.0) {
                drunkenness = 100.0;
            }

            drunkennessLabels.get(i).setText("<html>drunkenness<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + drunkenness + "</html>");
            drunkennessLabels.get(i).setForeground(customer.getCurrColor());

            choosenBeers.get(i).setText("     " + customer.getCurrentBeer().getName());
            choosenBeers.get(i).setForeground(customer.getCurrColor());

            i++;
        }
        repaint();
    }
}