import javax.swing.*;

// Settings window should have settings for the simulations such as the number of iterations,
// a range slider for minimal and maximal resistance for each type of customer, a fitness proportionate selection slider for distribution of chances
// to pick a beer (will do later) for a customer, a dropBox if user wants to filter the output to show only selected type of customer
// and a button to proceed to the simulation window with settings applied.
public class SettingsWindow extends JFrame {
    private JTextField iterationsField;
    private JSlider minResistanceSlider;
    private JSlider maxResistanceSlider;
    private JButton proceedButton;
    private JComboBox<String> customerTypeComboBox;

    SettingsWindow() {
        setTitle("Settings");
        setSize(500, 400);
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
        //-----------------/Max resistance slider-----------------

        //-----------------Proceed button-----------------
        proceedButton = new JButton("Proceed");
        proceedButton.addActionListener(e -> {
            int iterations = Integer.parseInt(iterationsField.getText());
            int minResistance = minResistanceSlider.getValue();
            int maxResistance = maxResistanceSlider.getValue();
            String customerType = (String) customerTypeComboBox.getSelectedItem();
            new SimulationWindow(iterations, minResistance, maxResistance, customerType).setVisible(true);
            dispose();
        });
        proceedButton.setFocusable(false);
        add(proceedButton).setBounds(10, 210, 100, 30);
        //-----------------/Proceed button-----------------

        //-----------------Customer type dropBox-----------------
        add(new JLabel("Customer type:")).setBounds(10, 240, 150, 30);
        customerTypeComboBox = new JComboBox<>(new String[]{"All", "Regular", "Connoisseur", "Drunkard"});
        add(customerTypeComboBox).setBounds(10, 270, 150, 30);
        //-----------------/Customer type dropBox-----------------
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SettingsWindow().setVisible(true));
    }
}
