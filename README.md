# HorseRaceSimulator
Horse Race Simulation Game

# Getting started

This is what you would need installed to run the project locally. To get a local copy up and running follow these steps.

# Dependencies 

The only thing that you would need installed is Java Development Kit installed on the system. This will include the Java RunTime Environment which is necessary for running Java applications and other tools such as the Java RunTime Environment which is necessary for running java applications and other tools such as the Java compiler.

JDk download for mac

https://www.oracle.com/uk/java/technologies/downloads/#jdk22-mac

JDK download for windows

https://www.oracle.com/uk/java/technologies/downloads/#jdk22-windows

JDK download for linux

https://www.oracle.com/uk/java/technologies/downloads/#jdk22-linux


# Installation

Clone the repository using the link  https://github.com/mdan-123/HorseRaceSimulator

If this does not work mail the author

# Set up 

Part1:

To run the code for part1 navigate to the Part1 folder using the terminal or a IDE of your choice.

If on a terminal compile the file called Race.java by typing javac Race.java

Then once successfully compiled run the file by typing java Race.java.

If on IDE then just run Race.java.

Race.java will create an object of Main and then run the main method of Main which will call the StartRace method in Race.

When the file is run it will ask the user to enter a distance.

If this distance is 0 or less it will force the user to keep entering until they enter a number greater than 0.

After this is done it will then ask you for the amount of horses you wish to race. This number must be greater than 2 and will force the the user to keep re-entering if not.

Then it will ask for the name, symbol and confidence of each horse that you have entered.

After this it will start the race in the terminal.

After the race it will then print the winner and ask if you wish to race again and will only accept yes or y to restart the race.

If yes then it will ask for distance again and after will start the race again.

Part2:

To run the code for part2 navigate to the part2 folder using the terminal or a IDE of your choice.

If on terminal compile the file called Race.java by typing javac Race.java

Then once successfully compiled run the file by typing java Race.java.

If on IDE then just run Race.java.

This will create an object of Main.java then will run the main method of this class.

This main method will create a frame called Runner(); 

This frame starts the code.

The code will then display a frame that has a welcome message with a button called 'add horse'.

Once you press this button you are sent to a different frame where you will be asked to enter the name, symbol and confidence of the horse.

Any invalid inputs will not be accepted and a text dialog will appear on the screen.

Once the horse is added you will be sent to a different frame called main menu. Where there is a button to go anywhere you want.

The frames will not allow you to start a race while the amount of horses is less than 2 so you must enter another horse.

Once there is two horses you can press the start race in the menu bar or in the menu by pressing the start race button.

Once pressed it will send you to another frame asking for distance. 

Distance must be greater than 0 or it will not start the race.

Then it will start the race in the terminal.

Once the race finishes and prints the winner and then re-opens the main menu. Allowing you to do whatever you wish after.

#Guideline

For Part1:

Any irrenous data will be thrown with a suitable exception message.

If you wish to run the race again then you must enter yes or y. Anything else will end the running of the code.

For part2:

For the menu bar, if the amount of horses is less than 2, then if start race is pressed it will show a message dialog asking you to have at least two horses.

The same is for customise, stats and delete horses. If there are no horses in the game then it will return a message dialog asking for at least one horse.

To customise a horse, you must a select a horse using a drop down menu and then are able to change the contents of that horse.

To view stats, you must select a horse then the stats will show up on the screen.

To delete a horse, you must select a horse using a drop down menu then press the delete button.

In terms of irrenous data any data that is not allowed will be dealt with by a message box that pops onto the screen. For example in add horse and customise horse if you try to enter a symbol with length greater than 2 then a message box will pop up asking you to enter something of length one. And will not allow you to add or change the horse.

#Author

Name: Muhammad Danish Waheed

Email: danishwaheed732@outlook.com


