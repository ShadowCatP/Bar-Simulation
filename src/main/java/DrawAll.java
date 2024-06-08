import javax.swing.*;
import java.awt.*;
import java.util.List;

class DrawAll extends JPanel {
    List<Customer> customers;
    DrawAll(List<Customer> customers_) {
        customers = customers_;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        int width = 50, height = 50;

        for (Customer el : customers) {
            g2D.setPaint(el.getCurrColor());
            g2D.fillRect(el.getX(), el.getY(), width, height);
        }
    }
}