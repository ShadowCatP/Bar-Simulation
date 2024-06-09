import javax.swing.*;
import java.util.HashMap;
import java.awt.Font;

//that window using only in automatic mode
public class HelpWindow extends JFrame {
    JTextField numberOfItersField;
    JTextField delayingBetweenItersField;
    HelpWindow(int minRegResistance, int minConnResistance, int minDrunkardResistance, int minOccasionalDrinkerResistance,
               int maxRegResistance, int maxConnResistance, int maxDrunkardResistance, int maxOccasionalDrinkerResistance,
               HashMap<String, Integer> beerStrengths){

        setTitle("Number of iterations window");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        //----------------------numberOfItersLabel-----------------------
        JLabel numberOfItersLabel = new JLabel("Enter number of iterations:");
        numberOfItersLabel.setBounds(150, 70, 300, 30);
        numberOfItersLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(numberOfItersLabel);
        //----------------------/numberOfItersLabel-----------------------

        //----------------------numberOfItersField-----------------------
        numberOfItersField = new JTextField();
        numberOfItersField.setBounds(150, 100, 300, 30);
        numberOfItersField.setFont(new Font("Arial", Font.BOLD, 16));
        add(numberOfItersField);
        //----------------------/numberOfItersField-----------------------

        //----------------------delayBetweenItersLabel-----------------------
        JLabel delayBetweenItersLabel = new JLabel("Enter delay between iterations (in seconds):");
        delayBetweenItersLabel.setBounds(150, 170, 400, 30);
        delayBetweenItersLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(delayBetweenItersLabel);
        //----------------------/numberOfItersLabel-----------------------

        //----------------------delayingBetweenItersField-----------------------
        delayingBetweenItersField = new JTextField();
        delayingBetweenItersField.setBounds(150, 200, 300, 30);
        delayingBetweenItersField.setFont(new Font("Arial", Font.BOLD, 16));
        add(delayingBetweenItersField);
        //----------------------/delayingBetweenItersField-----------------------

        //----------------------proceedButton-----------------------
        JButton proceedButton = new JButton("Proceed");
        proceedButton.setBounds(225, 300, 150, 50);
        proceedButton.setFont(new Font("Arial", Font.BOLD, 16));
        proceedButton.setFocusable(false);

        proceedButton.addActionListener(e-> {
            new SimulationWindow(
                    getNumberOfItersField(), getDelayBetweenIters(), minRegResistance, minConnResistance, minDrunkardResistance, minOccasionalDrinkerResistance,
                    maxRegResistance, maxConnResistance, maxDrunkardResistance, maxOccasionalDrinkerResistance, beerStrengths).setVisible(true);
            dispose();
        });
        add(proceedButton);
        //----------------------/proceedButton-----------------------

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
    private int getDelayBetweenIters(){
        try {
            int iterations = Integer.parseInt(delayingBetweenItersField.getText());

            if (iterations > 10 || iterations < 1) {
                return 10;
            } else {
                return iterations;
            }
        } catch(NumberFormatException e){
            return 3;
        }
    }
}
