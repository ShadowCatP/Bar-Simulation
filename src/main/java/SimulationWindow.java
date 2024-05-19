import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SimulationWindow().setVisible(true);
            }
        });
    }
}