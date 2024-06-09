import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.*;

// Settings window should have settings for the simulations such as the number of iterations,
// a range slider for minimal and maximal resistance for each type of customer, a fitness proportionate selection slider for distribution of chances
// to pick a beer (will do later) for a customer, a dropBox if user wants to filter the output to show only selected type of customer
// and a button to proceed to the simulation window with settings applied.

public class SettingsWindow extends JFrame {
    private JSlider minResistanceSlider;
    private JSlider maxResistanceSlider;
    private JButton saveButton;
    private JButton proceedButton;
    private JComboBox<String> customerTypeComboBox;
    private static int minRegResistance = 50, minConnResistance = 50, minDrunkardResistance = 50, minOccasionalDrinkerResistance = 50;
    private static int maxRegResistance = 50, maxConnResistance = 50, maxDrunkardResistance = 50, maxOccasionalDrinkerResistance = 50;
    private static String[] beerNames = {"Corona Light", "Heineken", "Blackout Stout", "Guinness"};
    private HashMap<String, JSlider> beerStrengthSliders;
    private HashMap<String, Integer> beerStrengths;

    SettingsWindow() {
        setTitle("Settings");
        setSize(380, 825);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new JLabel("Min resistance:")).setBounds(10, 480, 150, 30);
        minResistanceSlider = new JSlider(0, 100);
        minResistanceSlider.setMajorTickSpacing(25);
        minResistanceSlider.setMinorTickSpacing(5);
        minResistanceSlider.setPaintTicks(true);
        minResistanceSlider.setPaintLabels(true);
        minResistanceSlider.setSnapToTicks(true);
        add(minResistanceSlider).setBounds(10, 510, 300, 50);
        minResistanceSlider.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e) {
                if (minResistanceSlider.getValue() > maxResistanceSlider.getValue()) {
                    minResistanceSlider.setValue(maxResistanceSlider.getValue());
                }
            }
        });
        //-----------------/Min resistance slider-----------------

        //-----------------Max resistance slider-----------------
        add(new JLabel("Max resistance:")).setBounds(10, 550, 150, 30);
        maxResistanceSlider = new JSlider(0, 100);
        maxResistanceSlider.setMajorTickSpacing(25);
        maxResistanceSlider.setMinorTickSpacing(5);
        maxResistanceSlider.setPaintTicks(true);
        maxResistanceSlider.setPaintLabels(true);
        maxResistanceSlider.setSnapToTicks(true);
        add(maxResistanceSlider).setBounds(10, 580, 300, 50);
        maxResistanceSlider.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e) {
                if (minResistanceSlider.getValue() > maxResistanceSlider.getValue()) {
                    maxResistanceSlider.setValue(minResistanceSlider.getValue());
                }
            }
        });
        //-----------------/Max resistance slider-----------------

        //-----------------Beer strength sliders-----------------
        beerStrengthSliders = new HashMap<>();

        for (int i = 0; i < beerNames.length; i++) {
            add(new JLabel(beerNames[i] + " strength:")).setBounds(10, 10 + 75 * i, 150, 30);
            JSlider beerStrengthSlider = new JSlider(0, 100);
            beerStrengthSlider.setMajorTickSpacing(25);
            beerStrengthSlider.setMinorTickSpacing(5);
            beerStrengthSlider.setPaintTicks(true);
            beerStrengthSlider.setPaintLabels(true);
            beerStrengthSlider.setSnapToTicks(true);
            add(beerStrengthSlider).setBounds(10, 40 + 75 * i, 300, 50);
            beerStrengthSliders.put(beerNames[i], beerStrengthSlider);
        }
        //-----------------/Beer strength sliders-----------------

        //--------------------------iterationLabel--------------------------
        JLabel iterationLabel = new JLabel("Choose simulation play mode:");
        iterationLabel.setBounds(10, 250, 500, 200);
        iterationLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(iterationLabel);
        //--------------------------/iterationLabel--------------------------

        //----------------------manualMode----------------------
        JRadioButton manualMode = new JRadioButton("Manual mode");
        manualMode.setSelected(true);
        manualMode.setBounds(10, 385, 500, 30);
        manualMode.setFont(new Font("Arial", Font.BOLD, 16));
        manualMode.setFocusable(false);
        add(manualMode);
        //----------------------/manualMode----------------------

        //----------------------automaticMode----------------------
        JRadioButton automaticMode = new JRadioButton("Auto mode");
        automaticMode.setBounds(10, 430, 500, 30);
        automaticMode.setFont(new Font("Arial", Font.BOLD, 16));
        automaticMode.setFocusable(false);
        add(automaticMode);
        //----------------------/automaticMode----------------------

        //----------------------groupOfModes----------------------
        ButtonGroup modes = new ButtonGroup();
        modes.add(automaticMode);
        modes.add(manualMode);
        //----------------------/groupOfModes----------------------

        //-----------------Save button-----------------
        saveButton = new JButton("Save");
        saveButton.setBounds(10, 720, 350, 30);
        saveButton.setFocusable(false);
        saveButton.addActionListener(e -> {
            if (customerTypeComboBox.getSelectedItem() == "Regular") {
                minRegResistance = minResistanceSlider.getValue();
                maxRegResistance = maxResistanceSlider.getValue();
            }
            else if (customerTypeComboBox.getSelectedItem() == "Connoisseur") {
                minConnResistance = minResistanceSlider.getValue();
                maxConnResistance = maxResistanceSlider.getValue();
            }
            else if (customerTypeComboBox.getSelectedItem() == "Drunkard") {
                minDrunkardResistance = minResistanceSlider.getValue();
                maxDrunkardResistance = maxResistanceSlider.getValue();
            }
            else if (customerTypeComboBox.getSelectedItem() == "Occasional Drinker") {
                minOccasionalDrinkerResistance = minResistanceSlider.getValue();
                maxOccasionalDrinkerResistance = maxResistanceSlider.getValue();
            }
            else {
                minRegResistance               = minResistanceSlider.getValue();
                maxRegResistance               = maxResistanceSlider.getValue();
                minConnResistance              = minResistanceSlider.getValue();
                maxConnResistance              = maxResistanceSlider.getValue();
                minDrunkardResistance          = minResistanceSlider.getValue();
                maxDrunkardResistance          = maxResistanceSlider.getValue();
                minOccasionalDrinkerResistance = minResistanceSlider.getValue();
                maxOccasionalDrinkerResistance = maxResistanceSlider.getValue();
            }

            beerStrengths = new HashMap<>();

            for (String beerName : beerNames) {
                beerStrengths.put(beerName, beerStrengthSliders.get(beerName).getValue());
            }

            proceedButton.setEnabled(true);
        });
        add(saveButton);

        //-----------------/Save button-----------------


        //-----------------Proceed button-----------------

        proceedButton = new JButton("Proceed");
        proceedButton.setFocusable(false);
        proceedButton.setEnabled(false);
        add(proceedButton).setBounds(10, 755, 350, 30);
        proceedButton.addActionListener(e -> {
            dispose();
            if (manualMode.isSelected()) {
                new SimulationWindow(0, 0,
                        minRegResistance, minConnResistance, minDrunkardResistance, minOccasionalDrinkerResistance,
                        maxRegResistance, maxConnResistance, maxDrunkardResistance, maxOccasionalDrinkerResistance,
                        beerStrengths
                ).setVisible(true);
            } else {
                new HelpWindow(
                        minRegResistance, minConnResistance, minDrunkardResistance, minOccasionalDrinkerResistance,
                        maxRegResistance, maxConnResistance, maxDrunkardResistance, maxOccasionalDrinkerResistance,
                        beerStrengths
                ).setVisible(true);
            }
        });

        //-----------------/Proceed button-----------------


        //-----------------Customer type dropBox-----------------
        add(new JLabel("Customer type:")).setBounds(10, 650, 150, 30);
        customerTypeComboBox = new JComboBox<>(new String[]{"All", "Regular", "Connoisseur", "Drunkard", "Occasional Drinker"});
        add(customerTypeComboBox).setBounds(10, 680, 150, 30);
        customerTypeComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                minResistanceSlider.setValue(50);
                maxResistanceSlider.setValue(50);
            }
        });
        //-----------------/Customer type dropBox-----------------
    }


    public static void main(String[] args) {
        // Cleaning results directory before the program runs
        File directory = new File("simulation_results");
        if (directory.exists() && directory.isDirectory()) {
            deleteExistingCSVFiles(directory.getPath());
        }

        // This segment is responsible for reading parameters from config file and running program in fast mode
        // without GUI with ability to run multiple simulations in order to get results for research
        if (args.length > 0) {
            File configFile = new File(args[0]);
            if (configFile.exists() && configFile.isFile()) {
                try (Scanner scanner = new Scanner(configFile)) {
                    int simulationNumber = 1;
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        String[] parameters = line.split(" ");
                        if (parameters.length >= 8 + beerNames.length) {
                            minRegResistance = Integer.parseInt(parameters[0]);
                            minConnResistance = Integer.parseInt(parameters[1]);
                            minDrunkardResistance = Integer.parseInt(parameters[2]);
                            minOccasionalDrinkerResistance = Integer.parseInt(parameters[3]);
                            maxRegResistance = Integer.parseInt(parameters[4]);
                            maxConnResistance = Integer.parseInt(parameters[5]);
                            maxDrunkardResistance = Integer.parseInt(parameters[6]);
                            maxOccasionalDrinkerResistance = Integer.parseInt(parameters[7]);
                            HashMap<String, Integer> beerStrengths = new HashMap<>();
                            for (int i = 0; i < beerNames.length; i++) {
                                beerStrengths.put(beerNames[i], Integer.parseInt(parameters[8 + i]));
                            }

                            Beer.createBeers(beerStrengths);
                            List<Beer> beers = Beer.getBeers();

                            // I dunno how to make it better, but i know that is possible
                            List<Customer> customers = new ArrayList<>();
                            Random rand = new Random();
                            for (int i = 0; i < 6; i++) {
                                double resistance = minRegResistance + (maxRegResistance - minRegResistance) * rand.nextDouble();
                                customers.add(new Regular("Regular_" + (i + 1), resistance, 0, 0));
                                resistance = minConnResistance + (maxConnResistance - minConnResistance) * rand.nextDouble();
                                customers.add(new Connoisseur("Connoisseur_" + (i + 1), resistance, 0, 0));
                                resistance = minDrunkardResistance + (maxDrunkardResistance - minDrunkardResistance) * rand.nextDouble();
                                customers.add(new Drunkard("Drunkard_" + (i + 1), resistance, 0, 0));
                                resistance = minOccasionalDrinkerResistance + (maxOccasionalDrinkerResistance - minOccasionalDrinkerResistance) * rand.nextDouble();
                                customers.add(new OccasionalDrinker("OccasionalDrinker_" + (i + 1), resistance, 0, 0));
                            }

                            Simulation simulation = new Simulation(customers, beers, simulationNumber);

                            for (int i = 0; i < 20; i++) {
                                simulation.run();
                            }

                            simulationNumber++;

                            // The program will close the csv writer only after all rows are printed
                            simulation.closeCSVWriter();
                        }
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Config file not found.");
                }
            } else {
                System.out.println("Invalid file path.");
            }
        } else {
            SwingUtilities.invokeLater(() -> new SettingsWindow().setVisible(true));
        }
    }

    private static void deleteExistingCSVFiles(String directoryPath) {
        Path dir = Paths.get(directoryPath);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path path : stream) {
                if (path.toString().endsWith(".csv")) {
                    Files.delete(path);
                }
            }
        } catch (IOException e) {
            System.err.println("Error deleting CSV files: " + e.getMessage());
        }
    }

}
