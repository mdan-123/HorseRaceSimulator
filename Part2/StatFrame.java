import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StatFrame extends JFrame implements ActionListener {


    ArrayList<Horse> horses;
    JComboBox<String> horseComboBox;
    JLabel avTimeLabel;
    JLabel winRatioLabel;
    JLabel avspeedLabel;
    JLabel avDistanceLabel;
    JPanel panel;
    JLabel title;
    JLabel avTime;
    JLabel winRatio;
    JLabel avSpeed;
    JLabel avDistance;

    JMenuBar menuBar;


    StatFrame(ArrayList<Horse> horses){
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
        JMenuItem startRaceItem = new JMenuItem("Start Race");
        JMenuItem CustomiseHorse = new JMenuItem("Customise Horse");
        JMenuItem deleteHorse = new JMenuItem("Delete Horse");

        // Add action listeners to menu items
        mainMenu.addActionListener(this);
        addHorseItem.addActionListener(this);
        startRaceItem.addActionListener(this);
        CustomiseHorse.addActionListener(this);
        deleteHorse.addActionListener(this);

        // Add menu items to the navigation menu
        menuBar.add(mainMenu);
        menuBar.add(addHorseItem);
        menuBar.add(startRaceItem);
        menuBar.add(CustomiseHorse);
        menuBar.add(deleteHorse);

        // Set the menu bar for the frame
        setJMenuBar(menuBar);


        horseComboBox = new JComboBox<>();
        horseComboBox.setForeground(Color.RED);
        for (Horse horse : horses) {
            horseComboBox.addItem(horse.getName());
        }
        horseComboBox.addActionListener(this);

        avTimeLabel = new Whitelabel("     Average time in seconds: ");
        avTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        winRatioLabel = new Whitelabel("     Win ratio: ");
        winRatioLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        avspeedLabel = new Whitelabel("     Average speed: ");
        avspeedLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        avDistanceLabel = new Whitelabel("     Average distance: ");
        avDistanceLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        avTime = new Whitelabel("");
        winRatio = new Whitelabel("");
        avSpeed = new Whitelabel("");
        avDistance = new Whitelabel("");


        title = new JLabel("Stats");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);


        JPanel toppanel = new JPanel();
        toppanel.add(title);
        toppanel.add(horseComboBox);
        toppanel.setBackground(Color.green);

        this.add(toppanel, BorderLayout.NORTH);

        panel = new JPanel();
        panel.setBackground(Color.green);

        panel.setLayout(new GridLayout(5, 2 , 5, 5));
        panel.add(avTimeLabel);
        panel.add(avTime);
        panel.add(winRatioLabel);
        panel.add(winRatio);
        panel.add(avspeedLabel);
        panel.add(avSpeed);
        panel.add(avDistanceLabel);
        panel.add(avDistance);
        this.add(panel, BorderLayout.CENTER);

        if(!horses.isEmpty())
        {
            System.out.println(horses.get(0).getName());
            updateStats(horses.get(0));
        }



        this.setLocationRelativeTo(null);
        this.setVisible(true);



    }

    private void updateStats(Horse horse)
    {
        Stats stats = new Stats(horse);
        stats.workOutAvTime();
        stats.workOutWinRatio();
        stats.workOutAvSpeed();
        stats.workOutAvDistance();


        if(horse.getTotalRaces() ==0)
        {
            avTime.setText("0");
            winRatio.setText("0");
            avSpeed.setText("0");
            avDistance.setText("0");

        }
        else {
            avTime.setText(String.valueOf(stats.getAvTime()));
            winRatio.setText(String.valueOf(stats.getWinRatio()));
            avSpeed.setText(String.valueOf(stats.getAvSpeed()) + " m/s");
            avDistance.setText(String.valueOf(stats.getAvDistance()));

        }

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
            if (horses.size() < 2) {
                JOptionPane.showMessageDialog(this, "You need at least 2 horses to start a race");
                return;
            }
            new StartRace(horses);
            dispose();
        }
        else if(e.getActionCommand().equals("Customise Horse"))
        {
            if (horses.isEmpty()) {
                JOptionPane.showMessageDialog(this, "You need to add a horse before you can customise it");
                return;
            }
            new CustomiseHorse(horses);
            dispose();
        }
        else if(e.getActionCommand().equals("Delete Horse"))
        {
            if (horses.isEmpty()) {
                JOptionPane.showMessageDialog(this, "You need to add a horse before you can delete it");
                return;
            }
            new DeleteHorse(horses);
            dispose();
        }


        String selectedHorse = (String) horseComboBox.getSelectedItem();
        for (Horse horse : horses) {
            if (horse.getName().equals(selectedHorse)) {
                updateStats(horse);
                break;
            }
        }
    }
}
