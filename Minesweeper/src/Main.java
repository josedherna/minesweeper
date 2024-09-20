import java.util.Scanner;

public class Main {
    // Main method to start the game
    public static void main(String[] args) {
        // Create a Minesweeper game with specific dimensions and number of mines
        Minesweeper game = new Minesweeper(10, 10, 10);

        // Game loop
        while (!game.getGameOver()) {
            game.displayBoard();
            Scanner input = new Scanner(System.in);
            System.out.println();
            System.out.println("Enter Action (reveal, flag, unflag): ");
            String action = input.nextLine();
            System.out.println("Enter row number: ");
            int row = input.nextInt();;
            System.out.println("Enter column number: ");
            int column = input.nextInt();;
            game.playerMove(row, column, action);
            // Check for win or loss conditions
            if (game.checkWin()) {
                System.out.println("Congratulations! You've won the game.");
                break;
            }
            if (game.checkLoss(row, column) && action.equals("reveal")) {
                game.displayBoard();
                System.out.println("Game Over! You hit a mine.");
                game.setGameOver(true);
            }
        }
    }
}