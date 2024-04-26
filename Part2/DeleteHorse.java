import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeleteHorse extends JFrame implements ActionListener {

    ArrayList<Horse> horses;
    JComboBox<String> horseComboBox;
    JLabel Delete;
    JButton deleteButton;

    JMenuBar menuBar;

    DeleteHorse(ArrayList<Horse> horses){
        this.horses = horses;

        this.setSize(750, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.setBackground(Color.green);


        // Create the main menu bar
        menuBar = new JMenuBar();

        // Add menu items for different options
        JMenuItem mainMenu = new JMenuItem("Main Menu");
        JMenuItem addHorseItem = new JMenuItem("Add Horse");
        JMenuItem viewStatsItem = new JMenuItem("View Stats");
        JMenuItem startRaceItem = new JMenuItem("Start Race");
        JMenuItem customiseHorse = new JMenuItem("Customise Horse");

        // Add action listeners to menu items
        mainMenu.addActionListener(this);
        addHorseItem.addActionListener(this);
        viewStatsItem.addActionListener(this);
        startRaceItem.addActionListener(this);
        customiseHorse.addActionListener(this);

        // Add menu items to the navigation menu
        menuBar.add(mainMenu);
        menuBar.add(addHorseItem);
        menuBar.add(viewStatsItem);
        menuBar.add(startRaceItem);
        menuBar.add(customiseHorse);


        // Set the menu bar for the frame
        setJMenuBar(menuBar);


        Delete = new JLabel("Delete a horse");
        Delete.setFont(new Font("Arial", Font.PLAIN, 30));
        Delete.setHorizontalAlignment(SwingConstants.CENTER);

        horseComboBox = new JComboBox<>();
        horseComboBox.setForeground(Color.RED);
        for (Horse horse : horses) {
            horseComboBox.addItem(horse.getName());
        }
        horseComboBox.addActionListener(this);

        this.add(Delete, BorderLayout.NORTH);


        deleteButton = new RedButton("Delete");
        deleteButton.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setBackground(Color.green);
        panel.setLayout(new GridLayout(2, 4, 5, 5));
        panel.add(horseComboBox);
        panel.add(deleteButton);

        this.add(panel, BorderLayout.CENTER);


        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Main Menu"))
        {
            new Menu(horses);
            dispose();
        }
        else if(e.getActionCommand().equals("Add Horse"))
        {
            new addhorse(horses);
            dispose();
        }
        else if(e.getActionCommand().equals("Start Race"))
        {
            if(horses.size()< 2)
            {
                JOptionPane.showMessageDialog(this, "You need at least 2 horses to start a race");
                return;
            }
            new StartRace(horses);
            dispose();
        }
        else if(e.getActionCommand().equals("Customise Horse"))
        {
            if(horses.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "You need to add a horse before you can customise it");
                return;
            }
            new CustomiseHorse(horses);
            dispose();
        } else if(e.getActionCommand().equals("View Stats"))
        {
            if(horses.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "You need to add a horse before you can view stats");
                return;
            }
            new StatFrame(horses);
            dispose();
        }
        else if(e.getSource() == deleteButton)
        {
            for (Horse horse : horses) {
                if(horse.getName().equals(horseComboBox.getSelectedItem()))
                {
                    horses.remove(horse);
                    new Menu(horses);
                    dispose();
                    break;
                }
            }
        }
    }
}
