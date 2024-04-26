import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.Scanner;


/**
 * A three-horse race, each horse running in its own lane
 * for a given distance
 *
 * @author McFarewell
 * @version 1.0
 */
public class Race{
    private int raceLength;
    private ArrayList<Horse> horsesList;
    private ArrayList<Horse> winningHorses;
    private long startTime;
    /**
     * Constructor for objects of class Race
     * Initially there are no horses in the lanes
     *
     * @param distance the length of the racetrack (in metres/yards...)
     */
    public Race(int distance, ArrayList<Horse> horsesList) {
        // initialise instance variables

        if (distance <= 0) {
            throw new IllegalArgumentException("Error: The value you have entered is not correct");
        }
        raceLength = distance;

        this.horsesList = horsesList;

        winningHorses = new ArrayList<>();

    }


    /**
     * Adds a horse to the race in a given lane
     *
     * @param theHorse   the horse to be added to the race

     */

    // add input validation

    public void addHorse(Horse theHorse) {
        if (theHorse == null) {
            throw new IllegalArgumentException("Error: The value you have entered is not correct");
        }

        while (theHorse.getConfidence() < 0 || theHorse.getConfidence() > 1) {

            theHorse.setConfidence(correctConfidence(theHorse.getConfidence()));

            // throw new IllegalArgumentException(
            //         "Error: The value you have entered is not correct it should be between 0 and 1");
        }

        if (theHorse.getName() == null) {
            throw new IllegalArgumentException(
                    "Error: The value you have entered is not correct you have not entered a name for the horse");
        }

        if (theHorse.getSymbol() == '\u0000') {
            throw new IllegalArgumentException(
                    "Error: The value you have entered is not correct you have not entered a symbol for the horse");
        }

        if (horsesList.contains(theHorse)) {
            throw new IllegalArgumentException("Error: The value you have entered already exists in the list");
        }

        horsesList.add(theHorse);

    }


    public double correctConfidence(double confidence) {
        Scanner scanner = new Scanner(System.in);

        while (confidence < 0 || confidence > 1) {
            System.out.println("The confidence level must be between 0 and 1. Please enter a new value:");
            confidence = scanner.nextDouble();
        }
        return confidence;
    }


    public void removeHorse(Horse theHorse) {

        if (theHorse == null) {
            throw new IllegalArgumentException("Error: The value you have entered is not correct");
        }

        if (!horsesList.contains(theHorse)) {
            throw new IllegalArgumentException("Error: The value you have entered is not correct");
        }

        horsesList.remove(theHorse);

    }

    private void updatewinningHorses(Horse theHorse) {
        if (!winningHorses.isEmpty()) {
            return;
        }
        if (raceWonBy(theHorse)) {
            winningHorses.add(theHorse);
        }
    }

    public void resetLane() {
        for (Horse horse : horsesList) {
            horse.goBackToStart();
        }
    }

    public void moveHorses() {
        if (winningHorses.isEmpty() || !HorsesFallen()) {
            for (Horse horse : horsesList) {
                moveHorse(horse);
            }
        }

    }

    /**
     * Start the race
     * The horse are brought to the start and
     * then repeatedly moved forward until the
     * race is finished
     */


//    public void updateHorseData(long raceDuration)
//    {
//        for(Horse horse: horsesList)
//        {
//            horse.incrementTotalRaces();
//            horse.addRaceDistance(horse.getDistanceTravelled());
//            if(winningHorses.contains(horse))
//            {
//                horse.addFinish(1);
//            }
//            else if(horse.hasFallen())
//            {
//                horse.addFinish(-1);
//            }
//            else
//            {
//                horse.addFinish(0);
//            }
//            horse.addFinishTime((int)raceDuration);
//
//        }
//
//        while(winningHorses.size() > 0)
//        {
//            winningHorses.remove(0);
//        }
//    }

    public void updateHorseData(long raceDuration) {
        for (Horse horse : horsesList) {
            horse.incrementTotalRaces();
            horse.addRaceDistance(horse.getDistanceTravelled());

            // Check if the horse has won or fallen
            if (winningHorses.contains(horse)) {
                horse.addFinish(1);
                horse.addFinishTime((int) raceDuration);
            } else if (horse.hasFallen()) {
                // If the horse has fallen, its finish time is when it fell
                long finishTime = horse.getFallTime(); // Get the fall time of the horse
                horse.addFinish(-1); // Mark the fall as a negative finish
                horse.addFinishTime((int) (finishTime - startTime)); // Record the finish time
            } else {
                horse.addFinish(0); // Not a win or fall
                horse.addFinishTime((int) raceDuration);
            }
        }

        // Clear the list of winning horses for the next race
        winningHorses.clear();
    }




    public String inputString(String message)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine();
    }

//    public void rerun()
//    {
//        String input = inputString("Would you like to start the race");
//        while(input.equalsIgnoreCase("yes")) {
//            startRace();
//            input = inputString("Would you like to start the race again");
//        }
//    }

    public void startRace() {
        startTime = System.currentTimeMillis();
        long endTime = 0;
        long raceDuration = 0;
        boolean finished = false;


            // Check if any horse lane is empty
            if ((horsesList.isEmpty())) {
                System.out.println("No horses in the race.");
                return;
            }

            resetLane();

        while (!finished && (winningHorses.isEmpty() || !HorsesFallen())) {
            moveHorses();
            printRace();

            // Check if any horse has won the race
            for (Horse horse : horsesList) {
                if (!winningHorses.isEmpty()) {
                    printWinner();
                    finished = true;
                    endTime = System.currentTimeMillis();
                    raceDuration = endTime - startTime;
                    updateHorseData(raceDuration);
                    break;
                } else {
                    updatewinningHorses(horse);
                }

            }

            // Check if all horses have fallen
            if (HorsesFallen()) {
                System.out.println("All horses have fallen. No winner this time.");
                finished = true;
                endTime = System.currentTimeMillis();
                raceDuration = endTime - startTime;
                updateHorseData(raceDuration);
            }




            try {
                TimeUnit.MILLISECONDS.sleep(100);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        new Menu(horsesList);



    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    public void printWinner() {
        Collections.sort(horsesList, Comparator.comparingInt(Horse::getDistanceTravelled).reversed());

        System.out.println();
        System.out.println();
        System.out.println("The winner is " + winningHorses.get(0).getName());

        System.out.println();

        for (int i = 1; i < horsesList.size(); i++) {
            if (i == 1) {
                System.out.println(horsesList.get(i).getName() + " came in " + (i + 1)
                        + "nd place and the distance travelled is " + horsesList.get(i).getDistanceTravelled());
            } else if (i == 2) {
                System.out.println(horsesList.get(i).getName() + " came in " + (i + 1)
                        + "rd place and the distance travelled is " + horsesList.get(i).getDistanceTravelled());
            } else {
                System.out.println(horsesList.get(i).getName() + " came in " + (i + 1)
                        + "th place and the distance travelled is " + horsesList.get(i).getDistanceTravelled());
            }
        }

    }

    public boolean HorsesFallen() {
        boolean everyoneHasFallen = false;

        for (Horse horse : horsesList) {
            if (horse.hasFallen()) {
                everyoneHasFallen = true;
            } else {
                everyoneHasFallen = false;
                break;
            }
        }
        return everyoneHasFallen;
    }

    /**
     * Randomly make a horse move forward or fall depending
     * on its confidence rating
     * A fallen horse cannot move
     *
     * @param theHorse the horse to be moved
     */
    private void moveHorse(Horse theHorse) {
        // if the horse has fallen it cannot move,
        // so only run if it has not fallen

        if (!theHorse.hasFallen()) {
            // the probability that the horse will move forward depends on the confidence;
            if (Math.random() < theHorse.getConfidence()) {
                theHorse.moveForward();
            }

            // the probability that the horse will fall is very small (max is 0.1)
            // but will also will depends exponentially on confidence
            // so if you double the confidence, the probability that it will fall is *2
            if (Math.random() < (probabilityOfFalling(theHorse))) {
                theHorse.fall();
            }
        }
    }

    private double probabilityOfFalling(Horse theHorse) {
        return (0.1 * theHorse.getConfidence() * theHorse.getConfidence());
    }

    /**
     * Determines if a horse has won the race
     *
     * @param theHorse The horse we are testing
     * @return true if the horse has won, false otherwise.
     */
    private boolean raceWonBy(Horse theHorse) {

        return theHorse.getDistanceTravelled() == raceLength;
    }

    /***
     * Print the race on the terminal
     */
    private void printRace() {
        System.out.print('\u000C'); // clear the terminal window
        clearScreen();

        multiplePrint('=', raceLength + 3); // top edge of track
        System.out.println();

        printLaneHorses();

        multiplePrint('=', raceLength + 3); // bottom edge of track
        System.out.println();
    }

    public void printLaneHorses() {
        for (Horse horse : horsesList) {
            printLane(horse);
            System.out.println();
        }
    }

    /**
     * print a horse's lane during the race
     * for example
     * | X |
     * to show how far the horse has run
     */
    private void printLane(Horse theHorse) {
        // calculate how many spaces are needed before
        // and after the horse
        int spacesBefore = theHorse.getDistanceTravelled();
        int spacesAfter = raceLength - theHorse.getDistanceTravelled();

        // print a | for the beginning of the lane
        System.out.print('|');

        // print the spaces before the horse
        multiplePrint(' ', spacesBefore);

        // if the horse has fallen then print dead
        // else print the horse's symbol
        if (theHorse.hasFallen()) {
            System.out.print('\u2322');
        } else {
            System.out.print(theHorse.getSymbol());
        }

        // print the spaces after the horse
        multiplePrint(' ', spacesAfter);

        // print the | for the end of the track
        System.out.print('|');

        System.out
                .print("      " + theHorse.getName() + "      (Current Confidence: " + theHorse.getConfidence() + ")");
    }

    /***
     * print a character a given number of times.
     * e.g. printmany('x',5) will print: xxxxx
     *
     * @param aChar the character to Print
     */
    private void multiplePrint(char aChar, int times) {
        int i = 0;
        while (i < times) {
            System.out.print(aChar);
            i = i + 1;
        }
    }
}
