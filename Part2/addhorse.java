import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class addhorse extends JFrame implements ActionListener {

    JTextField nameField, decimalField, symbolField;
    JButton submitButton;
    ArrayList<Horse> horses;
    JMenuBar menuBar;
    JLabel titleLabel;

    addhorse(ArrayList<Horse> horses) {
        this.horses = horses;

        this.setSize(750, 750); // Set initial size
        this.setBackground(Color.green); // Set background color
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout()); // Use BorderLayout for better component arrangement

        menuBar = new JMenuBar();

        JMenuItem mainMenu = new JMenuItem("Main Menu");
        JMenuItem startRaceItem = new JMenuItem("Start a race");
        JMenuItem viewStatsItem = new JMenuItem("View Stats");
        JMenuItem customiseHorse = new JMenuItem("Customise Horse");
        JMenuItem deleteHorse = new JMenuItem("Delete Horse");

        mainMenu.addActionListener(this);
        startRaceItem.addActionListener(this);
        viewStatsItem.addActionListener(this);
        customiseHorse.addActionListener(this);
        deleteHorse.addActionListener(this);

        menuBar.add(mainMenu);
        menuBar.add(startRaceItem);
        menuBar.add(viewStatsItem);
        menuBar.add(customiseHorse);
        menuBar.add(deleteHorse);


        setJMenuBar(menuBar);

        titleLabel = new JLabel("Add a horse");
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBackground(Color.green);
        titleLabel.setForeground(Color.BLACK);
        this.add(titleLabel, BorderLayout.NORTH); // Add label to the top of the frame

        JPanel inputPanel = new JPanel(); // Panel to hold input components
        inputPanel.setLayout(new GridLayout(6, 2, 5, 5)); // Use GridLayout for vertical alignment

        JLabel symbolLabel = new Whitelabel("     Symbol:");
        symbolField = new RedTextField("", 20);
//        symbolField.setForeground(Color.RED);
        symbolLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel nameLabel = new Whitelabel("     Name:");
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        nameField = new RedTextField("", 20);
//        nameField.setForeground(Color.RED);
        JLabel decimalLabel = new Whitelabel("     Decimal Number:");
        decimalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        decimalField = new RedTextField("", 20);
//        decimalField.setForeground(Color.RED);

        submitButton = new RedButton("Submit");

        inputPanel.add(symbolLabel);
        inputPanel.add(symbolField);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(decimalLabel);
        inputPanel.add(decimalField);
        inputPanel.add(new JLabel()); // Empty label to occupy the space
        inputPanel.add(submitButton);

        inputPanel.setBackground(Color.green);

        this.add(inputPanel, BorderLayout.CENTER); // Add input panel to the center of the frame

        submitButton.addActionListener(this);



        this.setVisible(true); // Make the frame visible
        this.setLocationRelativeTo(null); // Center the JFrame on the screen

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String name = nameField.getText();
            String decimalStr = decimalField.getText();
            char symbol = ' '; // Default value for symbol

            // Validation for name
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a name for the horse", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validation for symbol
            try {
                String symbolStr = symbolField.getText();
                if (symbolStr.length() != 1) {
                    JOptionPane.showMessageDialog(this, "Please enter a single character for the symbol", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                symbol = symbolStr.charAt(0);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid symbol", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validation for decimal
            try {
                double decimal = Double.parseDouble(decimalStr);
                if (decimal <= 0 || decimal >= 1) {
                    JOptionPane.showMessageDialog(this, "Please enter a decimal number between 0 and 1", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Check if the horse already exists
                Horse newHorse = new Horse(symbol, name, decimal);
                if (horses.contains(newHorse)) {
                    JOptionPane.showMessageDialog(this, "The horse already exists", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // If all validations pass, add the horse
                horses.add(newHorse);
                new Menu(horses);
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid decimal number", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if(e.getActionCommand().equals("Main Menu")){
            new Menu(horses);
            dispose();
        } else if(e.getActionCommand().equals("Start a race")){
            if(horses.size() < 2) {
                JOptionPane.showMessageDialog(this, "Please add at least 2 horses to start", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            new StartRace(horses);
            dispose();
        } else if(e.getActionCommand().equals("View Stats")){
            if(horses.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Please add at least 1 horse to view stats", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            new StatFrame(horses);
            dispose();
        } else if(e.getActionCommand().equals("Customise Horse")){
            if(horses.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Please add at least 1 horse to customise", "Error", JOptionPane.ERROR_MESSAGE);
                return;

            }
            new CustomiseHorse(horses);
            dispose();
        } else if(e.getActionCommand().equals("Delete Horse")){
            if(horses.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Please add at least 1 horse to delete", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            new DeleteHorse(horses);
            dispose();


        }
    }




}
