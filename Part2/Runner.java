import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Runner extends JFrame implements ActionListener {

    JButton AddHorseButton;
    JLabel welcomeLabel;
    JLabel gameLabel;
    ArrayList<Horse> horses;

    Runner() {
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null); // Set layout to null to use absolute positioning

        // Set background color
        this.getContentPane().setBackground(Color.green);

        AddHorseButton = new RedButton("Add Horse");

        AddHorseButton.addActionListener(this);
        AddHorseButton.setSize(150, 50); // Set button size
        AddHorseButton.setLocation(160, 200); // Set button location
        AddHorseButton.setFocusable(false);







        welcomeLabel = new JLabel("Welcome to the");
        welcomeLabel.setFont(new Font("Verdana", Font.BOLD, 30));
        welcomeLabel.setForeground(Color.white);
        welcomeLabel.setSize(300, 50); // Set label size
        welcomeLabel.setLocation(75, 75); // Set label location
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the text

        gameLabel = new JLabel("Horse Racing Game");
        gameLabel.setFont(new Font("Verdana", Font.BOLD, 30));
        gameLabel.setForeground(Color.white);
        gameLabel.setSize(350, 50); // Set label size
        gameLabel.setLocation(75, 125); // Set label location
        gameLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the text

        this.add(AddHorseButton);
        this.add(welcomeLabel);
        this.add(gameLabel);

        this.setVisible(true);
        this.setLocationRelativeTo(null); // Center the JFrame on the screen
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == AddHorseButton) {
            horses = new ArrayList<>();

            new addhorse(horses);
            dispose();
        }
    }


}
