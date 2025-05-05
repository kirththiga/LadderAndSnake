import java.util.InputMismatchException;
import java.util.Scanner;

public class PlayLadderAndSnake {
    LadderAndSnake game;

    public PlayLadderAndSnake() {
        int numberOfPlayers = getNumberOfPlayersFromUser();
        game = new LadderAndSnake(numberOfPlayers);
    }

    private int getNumberOfPlayersFromUser() {
        welcomeMessage();

        System.out.println("Enter the # of players for your game – The number must be between 2 and 4 inclusively: ");
        Scanner input = new Scanner(System.in);
        int numberOfPlayers = 0;
        int count = 0;

        while (count < 4) {
            // Use a try catch block, so that the user enters a valid integer and no other characters.
            try {
                numberOfPlayers = input.nextInt();

                if(numberOfPlayers >= 2 && numberOfPlayers <= 4){
                    return numberOfPlayers;
                }
                else {
                    count++;

                    if(count < 4) {
                        System.out.println("Bad Attempt " + count + " - Invalid # of players. Please enter a # between 2 and 4 inclusively: ");
                    }

                    if(count == 3) {
                        System.out.println("This is your last attempt: ");
                    }
                }
            } catch (InputMismatchException e) {
                // Discard input
                input.nextLine();
                count++;
                System.out.println("Bad Attempt " + count +" - Invalid input. Please enter a # between 2 and 4 inclusively: ");

                if(count == 3) {
                    System.out.println("This is your last attempt: ");
                }
            }
        }

        if(count == 4) {
            System.out.println("Bad Attempt 4! You have exhausted all your chances. The program will terminate!");
            System.exit(0);
        }

        return numberOfPlayers;
    }

    private void welcomeMessage() {
        System.out.println("##################################################################");
        System.out.println("#  Welcome to the Ladder And Snake game by Amin and Kirththiga!  #");
        System.out.println("##################################################################\n");
    }

    public static void main(String[] args) {
        new PlayLadderAndSnake();
    }
}
