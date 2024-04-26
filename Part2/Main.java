import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Horse horse1 = new Horse('1', "Horse 1", 0.2);
        Horse horse2 = new Horse('2', "Horse 2", 0.3);
        Horse horse3 = new Horse('3', "Horse 3", 0.4);
        ArrayList<Horse> horses = new ArrayList<>();
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);
        Race race = new Race(10, horses);
//        race.startRace();

        new Runner();
    }
}