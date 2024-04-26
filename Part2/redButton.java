import javax.swing.*;
import java.awt.*;

class RedButton extends JButton {
    public RedButton(String text) {
        super(text);
        setContentAreaFilled(false); // Make the content area transparent
        setForeground(Color.RED); // Set font color to green
        setFont(new Font("Arial", Font.BOLD, 16)); // Set font with modifications
        setBackground(Color.RED); // Set background color to red
    }

    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(Color.BLUE.darker());
        } else {
            g.setColor(Color.RED);
        }


        super.paintComponent(g);
    }

}