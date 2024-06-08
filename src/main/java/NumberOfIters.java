import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.Font;

public class NumberOfIters extends JFrame {
    private JButton proceedButton;
    private JTextField numberOfItersField;
    NumberOfIters(int minRegResistance, int minConnResistance, int minDrunkardResistance, int minOccasionalDrinkerResistance,
                  int maxRegResistance, int maxConnResistance, int maxDrunkardResistance, int maxOccasionalDrinkerResistance,
                  String customerType, HashMap<String, Integer> beerQuantities, HashMap<String, Integer> beerStrengths){

        setTitle("Number of iterations window");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        //----------------------numberOfItersLabel-----------------------
        JLabel numberOfItersLabel = new JLabel("Enter number of iterations:");
        numberOfItersLabel.setBounds(50, 70, 300, 30);
        numberOfItersLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(numberOfItersLabel);
        //----------------------/numberOfItersLabel-----------------------

        //----------------------numberOfItersField-----------------------
        numberOfItersField = new JTextField();
        numberOfItersField.setBounds(50, 100, 300, 30);
        numberOfItersField.setFont(new Font("Arial", Font.BOLD, 16));
        add(numberOfItersField);
        //----------------------/numberOfItersField-----------------------

        //----------------------numberOfItersField-----------------------
        proceedButton = new JButton("Proceed");
        proceedButton.setBounds(125, 200, 150, 50);
        proceedButton.setFont(new Font("Arial", Font.BOLD, 16));
        proceedButton.setFocusable(false);

        proceedButton.addActionListener(e-> {
            new SimulationWindow(
                    getNumberOfItersField(), minRegResistance, minConnResistance, minDrunkardResistance, minOccasionalDrinkerResistance,
                    maxRegResistance, maxConnResistance, maxDrunkardResistance, maxOccasionalDrinkerResistance,
                    customerType, beerQuantities, beerStrengths).setVisible(true);
            dispose();
        });
        add(proceedButton);
        //----------------------/numberOfItersField-----------------------

        setVisible(true);
    }

    private int getNumberOfItersField(){
        try {
            int iterations = Integer.parseInt(numberOfItersField.getText());
            return iterations;
        } catch(NumberFormatException e){
            return 1;
        }
    }
}
