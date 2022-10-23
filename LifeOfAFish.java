import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class LifeOfAFish {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.print("Welcome to Life of a Fish\nPlease enter your name:");
        String nameUser = console.nextLine().trim();

        System.out.print("Welcome "+ nameUser + " the salmon!\nYou're a Sockeye born in Washington. You'll start with 5 hearts. \nYou've survived the treacherous first year, and journeyed upstream to the ocean. \nSwim East('E'), North('N'), South('S'), or stay on the West Coast('W'):");
        int hearts=5;
        int choice = inputChoice(console);
        System.out.println(choice);
        if(choice==3){
            hearts-=1;
            System.out.println("Along the coast, you're harmed by toxic chemicals released by humans. Lose 1 heart.");
        }
        if(choice==2){
            hearts-=2;
            System.out.println("Rising ocean temperatures due to climate change have made the southern Pacific nearly uninhabitable for you. Lose 2 hearts.");
        }
        if(choice<=1){
            System.out.println("Good choice! In today's warmer climate, you'll fare best in the cold northern Pacific.");
        }

        





    }

    public static int inputChoice(Scanner console) {
        boolean keepGoing = true;
        int choice = 0;

        while (true) {
            String inputLine = console.nextLine();

            if (inputLine.length() != 0) {
                String directions = "ENSW";
                char direction = inputLine.charAt(0);
                int num=directions.indexOf(direction);
                if(num!=-1){
                    return num;
                }else {
                System.out.print("Direction must be 'U' or 'D', try again: ");
            }
        }
    }
    }

}
