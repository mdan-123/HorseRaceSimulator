public class Stats {
    private static Integer avTime;
    private static double winRatio;
    private static double avSpeed;
    private static double avDistance;
    private static Horse horse;


    public Stats(Horse horse)
    {
        this.horse = horse;
        this.avTime = 0;
        this.winRatio = 0;
        this.avSpeed = 0;
        this.avDistance = 0;
    }

    public static void workOutAvSpeed() {
        double totalSpeed = 0;
        double av = 0;

        for (int i = 0; i < horse.getFinishTime().size(); i++) {
            double timeInMillis = horse.getFinishTime().get(i);
            double timeInSeconds = timeInMillis / 1000.0; // Convert milliseconds to seconds
            double distance = horse.getRaceDistances().get(i);

            // Calculate speed for each race
            double speed = distance / timeInSeconds;

            // Add to total speed
            totalSpeed += speed;
        }

        // Calculate average speed
        if (horse.getFinishTime().size() > 0) {
            av = totalSpeed / horse.getFinishTime().size();
            avSpeed = Math.round(av);
        } else {
            // Handle case when there are no finish times
            avSpeed = 0;
        }
    }



    public static void workOutAvDistance()
    {
        double totalDistance = 0;
        double distance = 0;
        for(int i = 0; i < horse.getRaceDistances().size(); i++)
        {
            totalDistance += horse.getRaceDistances().get(i);
        }
        distance =  totalDistance / horse.getRaceDistances().size();
        avDistance = distance;
    }

    public static void workOutWinRatio()
    {
        double totalWins = 0;
        double swinRatio = 0;
        for(int i = 0; i < horse.getFinshes().size(); i++)
        {
            if(horse.getFinshes().get(i) == 1)
            {
                totalWins++;
            }
        }
        swinRatio =  totalWins / horse.getTotalRaces();
        winRatio = swinRatio;
    }

    public Integer getAvTime()
    {
        return avTime;
    }

    public double getWinRatio()
    {
        return winRatio;
    }

    public double getAvSpeed()
    {
        return avSpeed;
    }

    public double getAvDistance()
    {
        return avDistance;
    }



    public static void workOutAvTime()
    {
        int totalTime = 0;
        double svTime = 0;
        double timeinseconds = 0;
        for(int i = 0; i < horse.getFinishTime().size(); i++)
        {
            totalTime += horse.getFinishTime().get(i);
            timeinseconds = totalTime / 1000.0;
        }
        svTime =  timeinseconds / horse.getFinishTime().size();
        avTime = Math.toIntExact(Math.round(svTime));
    }

    public static void main(String[] args)
    {
        workOutAvTime();
        workOutWinRatio();
        workOutAvSpeed();
        workOutAvDistance();
        System.out.println(avTime);
        System.out.println(winRatio);
        System.out.println(avSpeed);
        System.out.println(avDistance);
    }

}
