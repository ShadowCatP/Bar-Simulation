import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Settings window should have settings for the simulations such as the number of iterations,
// a range slider for minimal and maximal resistance for each type of customer, a fitness proportionate selection slider for distribution of chances
// to pick a beer (will do later) for a customer, a dropBox if user wants to filter the output to show only selected type of customer
// and a button to proceed to the simulation window with settings applied.

public class SettingsWindow extends JFrame {
    private JTextField iterationsField;
    private JSlider minResistanceSlider;
    private JSlider maxResistanceSlider;
    private JButton saveButton;
    private JButton proceedButton;
    private JComboBox<String> customerTypeComboBox;
    private int minRegResistance = 50, minConnResistance = 50, minDrunkardResistance = 50;
    private int maxRegResistance = 50, maxConnResistance = 50, maxDrunkardResistance = 50;

    SettingsWindow() {
        setTitle("Settings");
        setSize(1000, 800);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //-----------------Iteration field-----------------
        add(new JLabel("Number of iterations:")).setBounds(10, 10, 150, 30);
        iterationsField = new JTextField();
        iterationsField.setText("10");
        add(iterationsField).setBounds(160, 10, 150, 30);
        //-----------------/Iteration field-----------------

        //-----------------Min resistance slider-----------------
        add(new JLabel("Min resistance:")).setBounds(10, 40, 150, 30);
        minResistanceSlider = new JSlider(0, 100);
        minResistanceSlider.setMajorTickSpacing(25);
        minResistanceSlider.setMinorTickSpacing(5);
        minResistanceSlider.setPaintTicks(true);
        minResistanceSlider.setPaintLabels(true);
        minResistanceSlider.setSnapToTicks(true);
        add(minResistanceSlider).setBounds(10, 70, 300, 50);
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
        add(new JLabel("Max resistance:")).setBounds(10, 120, 150, 30);
        maxResistanceSlider = new JSlider(0, 100);
        maxResistanceSlider.setMajorTickSpacing(25);
        maxResistanceSlider.setMinorTickSpacing(5);
        maxResistanceSlider.setPaintTicks(true);
        maxResistanceSlider.setPaintLabels(true);
        maxResistanceSlider.setSnapToTicks(true);
        add(maxResistanceSlider).setBounds(10, 150, 300, 50);
        maxResistanceSlider.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e) {
                if (minResistanceSlider.getValue() > maxResistanceSlider.getValue()) {
                    maxResistanceSlider.setValue(minResistanceSlider.getValue());
                }
            }
        });
        //-----------------/Max resistance slider-----------------

        //-----------------Save button-----------------
        saveButton = new JButton("Save");
        saveButton.setBounds(150, 210, 100, 30);
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
            else {
                minRegResistance      = minResistanceSlider.getValue();
                maxRegResistance      = maxResistanceSlider.getValue();
                minConnResistance      = minResistanceSlider.getValue();
                maxConnResistance      = maxResistanceSlider.getValue();
                minDrunkardResistance = minResistanceSlider.getValue();
                maxDrunkardResistance = maxResistanceSlider.getValue();
            }
        });
        add(saveButton);

        //-----------------/Save button-----------------


        //-----------------Proceed button-----------------

        proceedButton = new JButton("Proceed");
        proceedButton.addActionListener(e -> {
            new SimulationWindow(
                    Integer.parseInt(iterationsField.getText()),
                    minRegResistance, minConnResistance, minDrunkardResistance,
                    maxRegResistance, maxConnResistance, maxDrunkardResistance,
                    (String) customerTypeComboBox.getSelectedItem()
            ).setVisible(true);
            dispose();
        });
        proceedButton.setFocusable(false);
        add(proceedButton).setBounds(10, 210, 100, 30);

        //-----------------/Proceed button-----------------


        //-----------------Customer type dropBox-----------------
        add(new JLabel("Customer type:")).setBounds(10, 240, 150, 30);
        customerTypeComboBox = new JComboBox<>(new String[]{"All", "Regular", "Connoisseur", "Drunkard"});
        add(customerTypeComboBox).setBounds(10, 270, 150, 30);
        customerTypeComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                minResistanceSlider.setValue(50);
                maxResistanceSlider.setValue(50);
            }
        });
        //-----------------/Customer type dropBox-----------------
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SettingsWindow().setVisible(true));
    }
}
