import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class LifeOfAFish {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.print("Welcome to Life of a Fish\nPlease enter your name:");
        String nameUser = console.nextLine().trim();

        System.out.println("Welcome "+ nameUser + " the salmon!\nSwim upstream('U') or downstream('D'):");
        int choice = inputChoice(console);







    }

    public static int inputChoice(Scanner console) {
        boolean keepGoing = true;
        int choice = 0;

        while (keepGoing) {
            String inputLine = console.nextLine();

            if (inputLine.length() != 0) {
                char direction = inputLine.charAt(0);

                if (direction == 'U' )  {

                    choice = 1;
                    keepGoing = false;

                } else if (direction == 'D') {
                    choice = 2;
                    keepGoing = false;

                } else {
                    System.out.print("Direction must be 'U' or 'D', try again: ");
                }
            } else {
                System.out.print("Direction must be 'U' or 'D', try again: ");
            }
        }
        return choice;
    }

}
