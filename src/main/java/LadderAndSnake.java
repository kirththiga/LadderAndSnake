import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class LadderAndSnake {
    private int[] board;
    private ArrayList<Player> players;
    private int numberOfPlayers;

    public LadderAndSnake(int numberOfPlayers) {
        board = new int[101];
        players = new ArrayList<Player>();
        this.numberOfPlayers = numberOfPlayers;
        initializeBoardWithLadderAndSnake();
        play();
    }

    public LadderAndSnake(int[] board) {
        this.board = board;
    }

    public void initializeBoardWithLadderAndSnake() {
        // To update the location of the players when moving up the ladder
        board[1] = 38;
        board[4] = 14;
        board[9] = 31;
        board[21] = 42;
        board[28] = 84;
        board[36] = 44;
        board[51] = 67;
        board[71] = 91;
        board[80] = 100;

        // To update the location of the players when moving down the snake
        board[16] = 6;
        board[48] = 30;
        board[64] = 60;
        board[79] = 19;
        board[93] = 68;
        board[95] = 24;
        board[97] = 76;
        board[98] = 78;
    }

    public int flipDice() {
        return (int)(Math.random() * 6) + 1;
    }

    public void play() {
        System.out.println("---Game is Played by " + numberOfPlayers + " players");
        createPlayers();

        System.out.println("---Now deciding which player will start playing:");
        orderOfPlay(players);

        System.out.print("---Reached final decision on the order of playing: ");
        for(int i=0; i < players.size(); i++) {
            if(i < numberOfPlayers - 1) {
                System.out.print(players.get(i).getName() + ", ");
            }
            else {
                System.out.println(players.get(i).getName());
            }
        }
        playUntilGameOver();
    }

    private void createPlayers() {
        for (int i = 0; i < numberOfPlayers; i++) {
            Player player = new Player(i+1);
            players.add(player);
        }
    }

    private void orderOfPlay(ArrayList<Player> orderPlayers) {
        for(Player player : orderPlayers) {
            int roll = flipDice();
            player.setLocation(roll);
            System.out.println(player.getName() + " got a dice value of " + roll);
        }

        Collections.sort(orderPlayers);

        ArrayList<Player> tiedPlayers = getTiedPlayers(orderPlayers);

        if(tiedPlayers.size() > 1 && tiedPlayers.size() <=4) {
            System.out.print("A tie was achieved between ");
            for (int i = 0; i < tiedPlayers.size(); i++) {
                if(i == 1 && tiedPlayers.size() == 2) {
                    System.out.print(" and ");
                }
                else if (i >= 1 && i <= tiedPlayers.size() - 1) {
                    System.out.print(", ");
                }

                System.out.print(tiedPlayers.get(i).getName());
            }

            System.out.println(". Attempting to break the tie");
            this.players.removeAll(tiedPlayers);
            orderOfPlay(tiedPlayers);
        }
        else {
            this.players.addAll(orderPlayers);
            resetAllPlayerLocation();
        }
    }

    private ArrayList<Player> getTiedPlayers(ArrayList<Player> tied) {
        ArrayList<Player> tiedPlayers = new ArrayList<Player>();

        boolean tie = false;
        for(int i=0; i<tied.size(); i++) {
            for(int j=i+1; j<tied.size(); j++) {
                if(tied.get(i).getLocation() != tied.get(j).getLocation()) {
                    break;
                }
                else if(!tiedPlayers.contains(tied.get(j))) {
                    tiedPlayers.add(tied.get(j));
                    tie = true;
                }
            }

            if(tie) {
                tiedPlayers.add(tied.get(i));
                tie = false;
            }
            if(tiedPlayers.size() >= tied.size()) {
                break;
            }
        }

        return tiedPlayers;
    }

    private void resetAllPlayerLocation() {
        for(Player player : players) {
            player.setLocation(1);
        }
    }

    private void playUntilGameOver() {
        boolean gameOver = false;

        while (!gameOver) {
            for(Player player : players) {
                gameOver = isGameOver(player);

                if (gameOver) {
                    break;
                }
            }

            if(!gameOver) {
                System.out.println("---Game is not over; flipping again");
            }
        }
    }

    private boolean isGameOver(Player player) {
        int roll = flipDice();
        player.moveForward(roll);

        int currentLocation = player.getLocation();

        if(currentLocation > 100) {
            goBackExcessSquares(player, currentLocation, roll);
        }
        else if(board[currentLocation] != 0) {
            moveOnLadderAndSnake(player, currentLocation, roll);
        }
        else {
            System.out.println("-" + player.getName() + " got a dice value of " + roll + "; now in square " + currentLocation);
        }

        if(player.getLocation() == 100) {
            System.out.println("-----" + player.getName() + " won the game!!! The program will terminate!");
            return true;
        }
        return false;
    }

    private void moveOnLadderAndSnake(Player player, int currentLocation, int roll) {
        int newLocation = board[currentLocation];
        String move = (newLocation > currentLocation) ? "up" : "down";

        System.out.println("-" + player.getName() + " got a dice value of " + roll + "; went to square " + currentLocation + " then " + move + " to square " + newLocation);
        player.setLocation(newLocation);
    }

    private void goBackExcessSquares(Player player, int currentLocation, int roll) {
        int goBack = currentLocation-100;
        player.setLocation(100-goBack);
        System.out.println("-" + player.getName() + " got a dice value of " + roll + "; now in square " + player.getLocation());
    }

    public int[] getBoard() {
        return board;
    }

    public void setBoard(int[] board) {
        this.board = board;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    @Override
    public String toString() {
        return "LadderAndSnake{" +
                "board=" + Arrays.toString(board) +
                ", players=" + players +
                ", numberOfPlayers=" + numberOfPlayers +
                '}';
    }
}
