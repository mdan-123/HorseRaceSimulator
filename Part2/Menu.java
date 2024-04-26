import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Menu extends JFrame implements ActionListener {

    JButton AddHorseButton;
    JButton startRaceButton;
    JButton viewStatsButton;
    JButton CustomiseHorseButton;
    JButton deleteHorseButton;
    JLabel label;
    ArrayList<Horse> horses;

    Menu(ArrayList<Horse> horses){
        this.horses = horses;
        this.setSize(750,750);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.GREEN);
        this.getContentPane().setBackground(Color.GREEN);

        // Set location for the label slightly above the center
        label = new JLabel("Main Menu");
        label.setFont(new Font("Arial", Font.PLAIN, 30));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        this.add(label, BorderLayout.NORTH); // Add label to the top of the frame


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 4, 5, 5));
        panel.setBackground(Color.GREEN);



//        Dimension labelSize = label.getPreferredSize();
//        label.setBounds(300, 100, labelSize.width, labelSize.height);

        AddHorseButton = new RedButton("Add Horse");
        AddHorseButton.addActionListener(this);
        startRaceButton = new RedButton("Start Race");
        startRaceButton.addActionListener(this);
        viewStatsButton = new RedButton("View Stats");
        viewStatsButton.addActionListener(this);
        CustomiseHorseButton = new RedButton("Customise Horse");
        CustomiseHorseButton.addActionListener(this);
        deleteHorseButton = new RedButton("Delete Horse");
        deleteHorseButton.addActionListener(this);


        panel.add(AddHorseButton);
        panel.add(startRaceButton);
        panel.add(viewStatsButton);
        panel.add(CustomiseHorseButton);
        panel.add(deleteHorseButton);

        this.add(panel, BorderLayout.CENTER);



        if(this.horses.size() < 2){
            startRaceButton.setEnabled(false);
        }

        if(this.horses.isEmpty()){
            viewStatsButton.setEnabled(false);
            CustomiseHorseButton.setEnabled(false);
        }


        this.setVisible(true);
        this.setLocationRelativeTo(null); // Center the JFrame on the screen

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == AddHorseButton){
            new addhorse(horses);
            dispose();
        } else if (e.getSource() == startRaceButton) {
            if(horses.size()< 2)
            {
                JOptionPane.showMessageDialog(this, "You need at least 2 horses to start a race");
                return;
            }
            new StartRace(horses);
            dispose();
        } else if(e.getSource() == viewStatsButton) {
            if(horses.isEmpty()){
                JOptionPane.showMessageDialog(this, "You need to add a horse before you can view stats");
                return;
            }
            new StatFrame(horses);
            dispose();
        } else if(e.getSource() == CustomiseHorseButton) {
            if (horses.isEmpty()) {
                JOptionPane.showMessageDialog(this, "You need to add a horse before you can customise it");
                return;
            }
            new CustomiseHorse(horses);
            dispose();
        } else if(e.getSource() == deleteHorseButton) {
            if (horses.isEmpty()) {
                JOptionPane.showMessageDialog(this, "You need to add a horse before you can delete it");
                return;
            }
            new DeleteHorse(horses);
            dispose();
        }
    }
}

