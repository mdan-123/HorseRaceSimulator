import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CustomiseHorse extends JFrame implements ActionListener {

    JLabel title;
    ArrayList<Horse> horses;
    JComboBox<String> horseComboBox;
    JLabel nameLabel;
    JLabel symbolLabel;
    JLabel confidenceLabel;
    JTextField nameField;
    JTextField symbolField;
    JTextField confidenceField;
    JButton saveButton;
    JPanel panel;

    JMenuBar menuBar;


    CustomiseHorse(ArrayList<Horse> horses) {
        this.horses = horses;

        this.setBackground(Color.green);
        this.getContentPane().setBackground(Color.green);
        this.setSize(750, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Create the main menu bar

        menuBar = new JMenuBar();

        // Add menu items for different options
        JMenuItem mainMenu = new JMenuItem("Main Menu");
        JMenuItem addHorseItem = new JMenuItem("Add Horse");
        JMenuItem viewStatsItem = new JMenuItem("View Stats");
        JMenuItem startRaceItem = new JMenuItem("Start Race");
        JMenuItem deleteHorse = new JMenuItem("Delete Horse");


        // Add action listeners to menu items
        mainMenu.addActionListener(this);
        addHorseItem.addActionListener(this);
        viewStatsItem.addActionListener(this);
        startRaceItem.addActionListener(this);
        deleteHorse.addActionListener(this);

        // Add menu items to the navigation menu
        menuBar.add(mainMenu);
        menuBar.add(addHorseItem);
        menuBar.add(viewStatsItem);
        menuBar.add(startRaceItem);
        menuBar.add(deleteHorse);

        // Set the menu bar for the frame
        setJMenuBar(menuBar);

        horseComboBox = new JComboBox<>();
        horseComboBox.setForeground(Color.RED);
        for (Horse horse : horses) {
            horseComboBox.addItem(horse.getName());
        }

        horseComboBox.addActionListener(this);

        title = new Whitelabel("Customise Horse");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel = new Whitelabel("     New Name:");
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        symbolLabel = new Whitelabel("     New Symbol:");
        symbolLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        confidenceLabel = new Whitelabel("     New Confidence:");
        confidenceLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        nameField = new RedTextField("",20);
        symbolField = new RedTextField("",20);
        confidenceField = new RedTextField("",20);

        if (!horses.isEmpty()) {
            gethorsedetails(horses.getFirst());
        }

        saveButton = new RedButton("Save");
        saveButton.addActionListener(this);



        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.green);

        topPanel.add(title);
        topPanel.add(horseComboBox);

        this.add(topPanel, BorderLayout.NORTH);

        panel = new JPanel();
        panel.setBackground(Color.green);
        panel.setLayout(new GridLayout(6, 2, 5, 5));
        panel.add(symbolLabel);
        panel.add(symbolField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(confidenceLabel);
        panel.add(confidenceField);
        panel.add(new JLabel());
        panel.add(saveButton);

        this.add(panel, BorderLayout.CENTER);

        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public void gethorsedetails(Horse horse){
        nameField.setText(horse.getName());
        confidenceField.setText(String.valueOf(horse.getConfidence()));
        String symbol = String.valueOf(horse.getSymbol());
        symbolField.setText(symbol);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Main Menu")){
            new Menu(horses);
            dispose();
        } else if(e.getActionCommand().equals("Add Horse")){
            new addhorse(horses);
            dispose();
        } else if(e.getActionCommand().equals("Start Race")){
            if(horses.size()< 2) {
                JOptionPane.showMessageDialog(this, "You need at least 2 horses to start a race");
                return;
            }
            new StartRace(horses);
            dispose();
        } else if(e.getActionCommand().equals("Delete Horse")){
            if(horses.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "You need to add a horse before you can delete it");
                return;
            }
            new DeleteHorse(horses);
            dispose();
        } else if(e.getActionCommand().equals("View Stats")){
            if(horses.isEmpty()){
                JOptionPane.showMessageDialog(this, "You need to add a horse before you can view its stats");
                return;
            }
            new StatFrame(horses);
            dispose();
        }



        String selectedHorse = (String) horseComboBox.getSelectedItem();
        for(Horse horse: horses){
            if(horse.getName().equals(selectedHorse)){
                gethorsedetails(horse);
                break;
            }
        }

        if(e.getSource() == saveButton)
        {
            String name = nameField.getText();
            String decimalStr = confidenceField.getText();
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
                if (decimal < 0 || decimal > 1) {
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
    }
}

