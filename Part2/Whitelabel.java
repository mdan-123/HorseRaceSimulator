import javax.swing.*;
import java.awt.*;

public class Whitelabel extends JLabel {
    public Whitelabel(String text) {
        super(text);
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 16));
    }
}
