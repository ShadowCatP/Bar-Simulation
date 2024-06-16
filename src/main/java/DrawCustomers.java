import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

class DrawCustomers extends JPanel {
    List<Customer> customers;
    DrawCustomers(List<Customer> customers_) {
        customers = customers_;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        for (Customer el : customers) {
            g2D.drawImage(el.getImage(), el.getX(), el.getY(), null);
        }
    }
}