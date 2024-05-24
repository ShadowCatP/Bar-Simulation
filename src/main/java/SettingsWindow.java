import javax.swing.*;

public class SettingsWindow extends JFrame {
    private JTextField iterationsField;

    SettingsWindow() {
        setTitle("Settings");
        setSize(500, 400);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new JLabel("Number of iterations:")).setBounds(10, 10, 150, 30);

        iterationsField = new JTextField();
        add(iterationsField).setBounds(160, 10, 150, 30);
    }
}
