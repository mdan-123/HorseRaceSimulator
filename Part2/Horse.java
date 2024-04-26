import java.util.ArrayList;

/**
 * Write a description of class Horse here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Horse
{
    //Fields of class Horse
    private String name;
    private char symbol;
    private int distanceTravelled;
    private double confidence;
    private boolean fallen;
    private ArrayList<Integer> finishTime;
    private int totalRaces;
    private ArrayList<Integer> finshes; //if value is -1 horse fell, if value is 0 horse
    // did not win and if value is 1 horse won
    private ArrayList<Integer> RaceDistances;
    private long falltime;


    //Constructor of class Horse
    /**
     * Constructor for objects of class Horse
     */
    public Horse(char horseSymbol, String horseName, double horseConfidence)
    {
        this.name = horseName;
        this.symbol = horseSymbol;
        this.confidence = horseConfidence;
        this.distanceTravelled = 0;
        this.fallen = false;
        this.finishTime = new ArrayList<>();
        this.totalRaces = 0;
        this.finshes = new ArrayList<>(); // if value is -1 horse fell, if value is 0 horse did not win and if value is 1 horse won
        this.RaceDistances = new ArrayList<>();

    }




    //Other methods of class Horse

    public void addRaceDistance(int distance)
    {
        this.RaceDistances.add(distance);
    }

    public ArrayList<Integer> getRaceDistances()
    {
        return this.RaceDistances;
    }

    public ArrayList<Integer> getFinishTime()
    {
        return this.finishTime;
    }

    public ArrayList<Integer> getFinshes()
    {
        return this.finshes;
    }

    public int getTotalRaces()
    {
        return this.totalRaces;
    }

    public void addFinishTime(int time)
    {
        this.finishTime.add(time);
    }

    public void incrementTotalRaces()
    {
        this.totalRaces++;
    }

    public void addFinish(int finish)
    {
        this.finshes.add(finish);
    }

    public double getConfidence()
    {
        return this.confidence;
    }

    public int getDistanceTravelled()
    {
        return this.distanceTravelled;
    }

    public String getName()
    {
        return this.name;
    }

    public char getSymbol()
    {
        return this.symbol;
    }

    public boolean hasFallen()
    {
        return this.fallen;
    }

    public void fall()
    {
        this.fallen = true;
        this.falltime = System.currentTimeMillis();
    }

    public void goBackToStart()
    {
        this.distanceTravelled = 0;
        this.fallen = false;
    }

    public long getFallTime()
    {
        return this.falltime;
    }


    public void moveForward()
    {
        this.distanceTravelled++;
    }

    public void setConfidence(double newConfidence)
    {
        this.confidence = newConfidence;
    }

    public void setSymbol(char newSymbol)
    {
        this.symbol = newSymbol;
    }

}
