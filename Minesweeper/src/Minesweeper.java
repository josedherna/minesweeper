public class Minesweeper {

    // Data members
    private char[][] board;   // The game board where cells will be displayed
    private boolean[][] mines; // Array to track the locations of mines
    private boolean[][] revealed; // Array to track which cells have been revealed
    private int rows; // Number of rows in the board
    private int cols; // Number of columns in the board
    private int numMines; // Number of mines in the game
    private boolean gameOver; // Boolean to check if the game is over

    // Constructor to initialize the board with the specified dimensions and number of mines
    public Minesweeper(int rows, int cols, int numMines) {
        this.rows = rows;
        this.cols = cols;
        this.numMines = numMines;
        this.board = new char[rows][cols];
        this.mines = new boolean[rows][cols];
        this.revealed = new boolean[rows][cols];
        this.gameOver = false;
        initializeBoard();
        // Call methods to initialize the board and place mines
    }
    public boolean getGameOver(){
        return this.gameOver;
    }
    public void setGameOver(boolean status)
    {
        this.gameOver = status;

    }
    // Method to initialize the game board with empty values
    private void initializeBoard() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                board[row][col] = '-';
                revealed[row][col] = false;
            }
        }
        placeMines();
    }

    // Method to randomly place mines on the board
    private void placeMines() {
        int count = 0;
        while (count < 10) {
            int row = (int)(Math.random()*10);
            int col = (int)(Math.random()*10);
            if (!mines[row][col]) {
                mines[row][col] = true;
                count++;
            }
        }
    }

    // Method to calculate numbers on the board for non-mine cells
    private int calculateNumbers(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < 10 && j >= 0 && j < 10 && mines[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    // Method to display the current state of the board
    public void displayBoard() {
        int columnLabel = 0;
        System.out.println("Game Board: ");
        for (int rowLabel = 0; rowLabel < 10; rowLabel++) {
            System.out.print(" " + rowLabel + " ");
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print("["+ board[i][j] +"]");
            }
            System.out.print(" " + columnLabel);
            System.out.println();
            columnLabel++;
        }
        System.out.println();
    }

    // Method to handle a player's move (reveal a cell or place a flag)
    public void playerMove(int row, int col, String action) {
        switch(action) {
            case "reveal":
                revealCell(row, col);
                break;
            case "flag":
                flagCell(row, col);
                break;
            case "unflag":
                unflagCell(row, col);
            default:
                System.out.println("Please enter a valid command");
                break;
        }
    }

    // Method to check if the player has won the game
    public boolean checkWin() {
        for (int row = 0; row < 10; row++){
            for (int col = 0; col < 10; col++) {
                System.out.println(mines[row][col] && !revealed[row][col]);
                if (mines[row][col] == revealed[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    // Method to check if the player has lost the game
    public boolean checkLoss(int row, int col) {
        if (row < 0 || row > 9 && col < 0 || col > 9) {
            System.out.println("Please enter valid row and column number (0-9)");
            return false;
        }
        if(revealed[row][col] == mines[row][col]) {
            return true;
        }
        else {
            return false;
        }
    }

    // Method to reveal a cell (and adjacent cells if necessary)
    private void revealCell(int row, int col) {
        if (row < 0 || row > 9 || col < 0 || col > 9) {
            return;
        }
        if (revealed[row][col]) {
            return;
        }
        if (mines[row][col]) {
            board[row][col] = '*';
            revealed[row][col] = true;
            return;
        }
        if (board[row][col] == '>') {
            return;
        }
        int adjacentMines = calculateNumbers(row, col);
        revealed[row][col] = true;
        if (adjacentMines > 0) {
            board[row][col] = (char) (adjacentMines + '0');
        } else {
            board[row][col] = '_';
            revealCell(row - 1, col - 1);
            revealCell(row - 1, col);
            revealCell(row - 1, col + 1);
            revealCell(row, col - 1);
            revealCell(row, col + 1);
            revealCell(row + 1, col - 1);
            revealCell(row + 1, col);
            revealCell(row + 1, col + 1);
        }
    }

    // Method to flag a cell as containing a mine
    private void flagCell(int row, int col) {
        board[row][col] = '>';
    }

    // Method to unflag a cell
    private void unflagCell(int row, int col) {
        board[row][col] = '_';
    }
}
