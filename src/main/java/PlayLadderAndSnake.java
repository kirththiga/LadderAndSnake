import java.util.Scanner;

public class PlayLadderAndSnake {
    LadderAndSnake game;
    int numberOfPlayers;

    public PlayLadderAndSnake() {
        getNumberOfPlayersFromUser();
        game = new LadderAndSnake(numberOfPlayers);
        game.play();
    }

    public void getNumberOfPlayersFromUser() {
        System.out.println("Enter the # of players for your game – The number must be between 2 and 4 inclusively: ");

        Scanner input = new Scanner(System.in);
        numberOfPlayers = input.nextInt();

        int count = 1;
        while((numberOfPlayers < 2 || numberOfPlayers > 4) && count < 4) {
            System.out.println("Bad Attempt " + count +" - Invalid # of players. Please enter a # between 2 and 4 inclusively: ");

            if(count == 3) {
                System.out.println("This is your last attempt: ");
            }

            numberOfPlayers = input.nextInt();
            count++;
        }

        if(count == 4) {
            System.out.println("Bad Attempt 4! You have exhausted all your chances. The program will terminate!");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new PlayLadderAndSnake();
    }
}
