import javax.swing.*;
import java.awt.*;
import java.util.List;

class DrawCustomers extends JPanel {
    List<Customer> customers;
    private Image backgroundImage;

    DrawCustomers(List<Customer> customers_, String imagePath) {
        customers = customers_;
        backgroundImage = new ImageIcon(imagePath).getImage();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);

        for (Customer el : customers) {
            g2D.drawImage(el.getImage(), el.getX(), el.getY(), null);
        }
    }
}