import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StartRace extends JFrame implements ActionListener {
    ArrayList<Horse> horses;
    JButton startButton;
    JTextField distanceButton;
    JPanel panel;
    JLabel label;
    JMenuBar menuBar;

    StartRace(ArrayList<Horse> horses)
    {
        this.setBackground(Color.green);
        this.getContentPane().setBackground(Color.green);
        this.horses = horses;
        this.setSize(750, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        menuBar = new JMenuBar();

        JMenuItem mainMenu = new JMenuItem("Main Menu");
        JMenuItem addHorseItem = new JMenuItem("Add Horse");
        JMenuItem viewStatsItem = new JMenuItem("View Stats");
        JMenuItem customiseHorse = new JMenuItem("Customise Horse");
        JMenuItem deleteHorse = new JMenuItem("Delete Horse");

        mainMenu.addActionListener(this);
        addHorseItem.addActionListener(this);
        viewStatsItem.addActionListener(this);
        customiseHorse.addActionListener(this);
        deleteHorse.addActionListener(this);

        menuBar.add(mainMenu);
        menuBar.add(addHorseItem);
        menuBar.add(viewStatsItem);
        menuBar.add(customiseHorse);
        menuBar.add(deleteHorse);

        setJMenuBar(menuBar);




        label = new Whitelabel("Start the race");

        startButton = new RedButton("Start");
        distanceButton = new JTextField("Set the distance");
        distanceButton.setForeground(Color.RED);
        panel = new JPanel();
        startButton.addActionListener(this);
        panel.setBackground(Color.green);

        label.setFont(new Font("Arial", Font.PLAIN, 30));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(label, BorderLayout.NORTH);

        panel.add(distanceButton);
        panel.add(startButton);

        this.add(panel, BorderLayout.CENTER);


        this.setLocationRelativeTo(null);
        this.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton)
        {

            String distance = distanceButton.getText();
            try{
                int distanceInt = Integer.parseInt(distance);
                if(distanceInt <= 0)
                {
                    JOptionPane.showMessageDialog(this, "Please enter a positive number", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    Race race = new Race(distanceInt, horses);
                    dispose();
                    race.startRace();

                }
            } catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this, "Please enter a number", "Error", JOptionPane.ERROR_MESSAGE);
            }


        }

        else if(e.getActionCommand().equals("Main Menu"))
        {
            new Menu(horses);
            dispose();
        }
        else if(e.getActionCommand().equals("Add Horse"))
        {
            new addhorse(horses);
            dispose();
        }
        else if(e.getActionCommand().equals("View Stats"))
        {
            new StatFrame(horses);
            dispose();
        }
        else if(e.getActionCommand().equals("Customise Horse"))
        {
            new CustomiseHorse(horses);
            dispose();
        }
        else if(e.getActionCommand().equals("Delete Horse"))
        {
            new DeleteHorse(horses);
            dispose();
        }

    }
}


