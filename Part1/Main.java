import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        int distance = 0;
    
        // the String to int conversion happens here
        distance = Integer.parseInt(inputString("Enter the distance"));
    
      
        Race race = new Race(distance);

        int numberOfHorses = 0;

        
        
        // the String to int conversion happens here
        numberOfHorses = Integer.parseInt(inputString("Enter the number of horses (>2)"));
        
        while (numberOfHorses < 2) {
            numberOfHorses = Integer.parseInt(inputString("Enter the number of horses (>2)"));
        }
  


        for(int i = 0 ; i < numberOfHorses; i++){
            String name = inputString("Enter the name of the horse");
            char symbol = inputString("Enter the symbol of the horse").charAt(0);
            double confidence = 0;
        
            
                // the String to int conversion happens here
            confidence = Double.parseDouble(inputString("Enter the confidence of the horse"));
    

            Horse horse = new Horse(symbol, name, confidence);

            race.addHorse(horse);
        }

        race.startRace();




    }



    public static String inputString(String message) {
        // Add the missing closing parenthesis
        // to fix the "illegal start of expression" error.
        // Return the input string.
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine();
    }
}


