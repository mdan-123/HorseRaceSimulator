
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
    }
    
    
    
    
    //Other methods of class Horse

    
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
    }
    
    public void goBackToStart()
    {
        this.distanceTravelled = 0;
        this.fallen = false;
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
