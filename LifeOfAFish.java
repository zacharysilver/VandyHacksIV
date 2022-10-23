import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class LifeOfAFish {
   /**
 * @param args
 */
public static void main(String[] args) {

	// Create a scanner console to read user input.
    Scanner console = new Scanner(System.in);
	Random random = new Random();
    boolean playing=true;
	while(playing){
	// Introduction to the system and the number of lives that the salmon has.
    int generation = 1;

    System.out.println("Welcome to Life of a Fish\n You'll navigate the challenges of fishhood as you seek to survive and reproduce. The first generation begins now.");
    boolean alive=true;
    while(alive){
        System.out.println("Your current generation is "+ generation+".");
        System.out.println("Time to name your fish.");
        String nameUser = console.nextLine().trim();
        System.out.print("Welcome "+ nameUser + " the salmon!\nYou're a Sockeye born in Washington state. You'll start with 5 hearts.\n");
        if (generation != 1){
            System.out.println("Environmental harm and dwindling food supply means you'll start life with only " +Math.max(6-generation, 1)+" heart points.");
        }
        else{
            System.out.println("A healthy young fish, you'll start life with 5 hearts.");
        }
        int hearts =Math.max(6-generation, 1);

    // First decision made by user deciding the coast location.
    hearts += coastDecision(console);
    if(hearts<=0){
        System.out.println("Game Over");
        break;
    }
    
    int timesSick = 0;
    for(int i=1;i<4;i++){
        userStats(nameUser, hearts); 
        System.out.println("Encounter #" + i);
        int x = random.nextInt(6);
    
        if (x == 0){
            hearts += oilSpill(console);
        }else if(x==1){
            hearts+=fisherman(console);
        }
        else if (x == 2){
            hearts += chowTime(console, random);  	
        } else if (x==3){
            System.out.println("you tragically contracted furunculosis");
            if (timesSick != 0){
			    System.out.println("you have gotten sick before and resist the infection");
		    } else {
			    hearts -= 1; 
			    timesSick += 1;
			    System.out.println("the disease is new to you and you get sick, lose 1 heart");
            }
        } else if (x==4){
            hearts+=hideAndSeek(console, generation);
        }
        else if(x==5){
            hearts+=predSense(console);
        }
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            System.out.print("");
        }
        if (hearts <= 0){
            alive=false;
            System.out.println("You're all out of hearts. Game over");
            break;
        }
    }
    if(alive){
        System.out.println("You've survived your adulthood in the ocean. \nAt the age of 5, your body begins to change. You turn a deep red, and get a hooked jaw and a humped back.\nIt's time to return upstream and reproduce. Press 1 to return to where you were born, or 2 to seek out another spot.");
        int choice2=inputChoice(console, "12");
        if(choice2==0){
            System.out.println("Good choice! Salmon have the natural ability to navigate to the stream where they were born.\nHabitat protection laws have kept your stream in adequate shape for your next generation to grow.\n You'll start the life cycle over as your child.");
            generation++;
        }
        else{
            System.out.println("Bad idea. Lost in Washington's vast network of rivers and streams, you die alone in a rural stream. Game over.");
            alive=false;
        }
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            System.out.print("");
        }
    }
}
    System.out.println("You died during generation "+generation);
    System.out.println("Do you want to play again? (Y/N).");
    int choice3=inputChoice(console,"YN");
    if(choice3==1){
        System.out.println("Thanks for playing! We hope to see you again");
        playing=false;
    }
    
    }
    
   }
 
 
    /**
     * Method for determining the coastal path that is taken by the fish.
     *
     * @param Scanner console to take input from the user.
     * @return int of the number of lives lost or gained.
     */
    public static int coastDecision(Scanner console) {
        System.out.print("You've survived the treacherous first year, and journeyed upstream to the ocean. \nSwim East('E'), North('N'), South('S'), or stay on the West Coast('W'):");
        int choice = inputChoice(console,"ENSW");
        System.out.println(choice);
        if(choice==3){
            System.out.println("Along the coast, you're harmed by toxic chemicals released by humans. Lose 1 heart.");
           return -1; 
       }
       if(choice==2){
            System.out.println("Rising ocean temperatures due to climate change have made the southern Pacific nearly uninhabitable for you. Lose 2 hearts.");
           return -2;
       }
       if(choice<=1){
           System.out.println("Good choice! In today's warmer climate, you'll fare best in the cold northern Pacific.");
	        return 0; 
       }
       return 0;
   }
 
    /**
     * Method for determing the what happens after an oil spill.
     *
     * @param Scanner console as one of the parameters.
     * @return int Returns the value of the number of lives lost or gained.
     */
    public static int oilSpill(Scanner console){
	    System.out.print("You see that your school is heading towards an oil spill. Split off from the group and face predators alone, or play it safe and follow the school?\n Enter S for split or L for leave");
	    int choice=inputChoice(console, "SL");
	    if(choice==0){
	        return predGame(console);
        } else{
	        System.out.println("Traveling through the spill, you ingest oil, causing health problems.\nLose a heart");
            return -1;
        }
     }
	
    /**
     * Method for determing what happens during a predator encounter.
     *
     * @param Scanner console as one of the parameters.
     * @return int Returns the value of the number of lives lost or gained.
     */
	public static int predGame(Scanner console){
		System.out.println("A shark appears. Dodge it by pressing L or R. The wrong choice will lead to instant death.");
		int choice=inputChoice(console, "LR");
		Random rand = new Random();
		int rNum=rand.nextInt(2);
		if(choice==rNum){
			System.out.println("You’re caught! The shark eats you.");
			return -10;
		}else{
			System.out.println("Safe! For now.");
			return 0;
        }
    }
    
    /**
     * Method for determing what happens during a predator encounter.
     *
     * @param Scanner console as one of the parameters.
     * @return int Returns the value of the number of lives lost or gained.
     */
    public static int predSense(Scanner console){
        System.out.print("You sense a predator around you but can’t tell which direction they are coming from. Which sense do you rely on:\nH for hearing, S for Smell, V for vision, R for just run away");
        int choice=inputChoice(console, "HSVR");
        if(choice==0){
            System.out.println("Salmon hear using low frequency sound waves which vibrate through the water to a row of sensory pores called lateral lines on the sides of the salmon\nyou escape the predator but not without a scar, lose 1 heart (hearing is not their best sense)");
            return  -1;
        } else if (choice ==1){
            System.out.println("Salmon have an incredibly good sense of smell which they use to return to the same stream to spawn. It is 1000x better than a dog’s sense of smell.\nYou escape successfully");
            return 0;
        }else if (choice == 2){
            System.out.println("Salmon have terrible vision, while you weren’t rash you were not perceptive. Lose 2 hearts");
            return -2;
        }else{
            System.out.println("You were rash and hasty and the Otter ate you whole");
            return -10;
        }
   }

 
    /**
     * This method determine what happens with the fish’s encounter with a fisherman.
     *
     * @param Scanner console to take the user input
     * @return int Hearts of the user are returned back to the individual
     */
     public static int fisherman(Scanner console) {
        System.out.print("Oh no! You were suddenly caught by the fisherman's hook. Let's hope for the best!\n");
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            System.out.print("");
        }
        Random rand = new Random();
        int rNum = rand.nextInt(2);
        if (rNum == 0) {
            System.out.print("You were able to escape from the hook. Great!\n");
            return 0;
        } else {
            System.out.print("The fisherman reeled you up above water, unfortunately. Let's see what will happen next.");
            try{
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                System.out.print("");
            }
            rNum = rand.nextInt(2);
            if (rNum == 0) {
                System.out.println("You were released from the hook. Catch and release, lucky. ");
                return 0;
            } else {
                System.out.println("The fisherman decided to cook you and made delicious cooked salmon. Thanks for your contribution.");
                return -10;
            }
        }
 
     }
 
	
   
    /**
     * Method that determines hearts gained or lost from fish.
     *
     * @param Scanner console to take input from the user and random object from main
     * @return int of the number of hearts lost or gained.
     */
    public static int chowTime(Scanner console, Random random) {
        System.out.println("You decide to rest your fins for a meal. You can't eat just yet though. You come across 3 rocks, and you know one of them has a delicious nutricious meal waiting for you."); 
        String[] foodType = new String[3];
        
        foodType[0] = "Herring";
        foodType[1] = "Shrimp";
        foodType[2] = "Grasshopper";
         
        System.out.println("What rock do you choose? Enter 1, 2, or 3.");
        int userChoice = inputChoice(console, "123");
        String realChoice = foodType[random.nextInt(3)];
        if (realChoice.equals("Herring")) {
            System.out.println("You chose a rock with a " + realChoice + " underneath. You gain 2 lives"); 
            return 2;
        } else if (realChoice.equals("Shrimp")) {
            System.out.println("You chose a rock with a " + realChoice + " underneath. You gain 1 life");
            return 1;
        } else if (realChoice.equals("Grasshopper")) {
            System.out.println("You chose a rock with a " + realChoice + " underneath. You don't get anything from that");
            return 0;
        }
        return 0;
    }

   /**
    * Method for determining input choice to be used for each salmon scenario.
    *
    * @param Scanner console to take input from the user.
    * @param String options to take have a sequence of letters that can be used.
    * @return int of the number of lives lost or gained.
    */
    public static int inputChoice(Scanner console, String options) {
        boolean keepGoing = true;
        int choice = 0;
 
        while (true) {
            String inputLine = console.nextLine();
            if (inputLine.length() != 0) {
                char direction = inputLine.charAt(0);
                int num=options.indexOf(Character.toString(direction).toUpperCase());
                if(num!=-1){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e){
                        return num;
                    }
                    return num;
                } else {
                    System.out.print("Not an option, try again: ");
                }
            }
       }
   }	
 
    /**
    * Method for determining input choice to be used for each salmon scenario.
    *
    * @param Scanner console to take input from the user.
    * @param String options to take have a sequence of letters that can be used.
    * @return int of the number of lives lost or gained.
    */
   public static int hideAndSeek(Scanner console, int generation){
	System.out.print("A predator approaches. Pick your hiding spot:\n1. In sea anemones. \n2. Between rocks. \n3. In coral.\nEnter 1, 2, or 3.");
	int choice=inputChoice(console, "123");
	if(choice==0){
		System.out.println("You're stung. Lose a heart");
		return -1;
	}
	if(choice==1){
		System.out.println("Your tail is stuck in the rocks. lose 2 hearts");
        return -2;
    }
    else{
        if(generation<3){
            System.out.println("Nice choice! You're safe.");
            return 0;
        }
        else{
            System.out.println("Climate change has eroded the coral. Lose 2 hearts.");
            return -2;
        }
    }
    }
 
   /*
    * Method for printing the statistics of the user through the simulation.
    *
    * @param String nameUser for the name of the fish player.
    * @param int hearts for the number of hearts to be given by the player.
    */
    public static void userStats(String userName, int hearts) {
        System.out.print("\n===============\n");
        System.out.println("Statistics: ");
        System.out.println("Salmon " + userName + " has " + hearts + " heart(s) remaining.");
 System.out.println("\n===============\n");
}
}
