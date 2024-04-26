import javax.swing.*;
import java.awt.*;

public class RedTextField extends JTextField {
    public RedTextField(String text, int columns) {
        super(text, columns);
        this.setForeground(Color.RED);
        this.setFont(new Font("sans serif", Font.BOLD, 16));

    }
}
