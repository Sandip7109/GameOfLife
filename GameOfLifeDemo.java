public class GameOfLifeDemo {
    static int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
    static int live = 1;
    static int dead = 0;
    int soonToBeLive = 3;
    int soonToBeDead = -2;
    int underPopulation = 2;
    int overPopulation = 3;

    public int[][] getNextGenBoard(int[][] inputBoard) {
        if (inputBoard.length == 0 || inputBoard[0].length == 0) {
            throw new IllegalArgumentException("Board must be valid");
        }

        for (int row = 0; row < inputBoard.length; row++) {
            for (int column = 0; column < inputBoard[row].length; column++) {
                if (inputBoard[row][column] == live) {
                    int activeCellCount = findActiveCell(inputBoard, row, column);
                    if (activeCellCount < underPopulation || activeCellCount > overPopulation) {
                        inputBoard[row][column] = soonToBeDead;
                    }
                } else if (inputBoard[row][column] == dead) {
                    int activeCellCount = findActiveCell(inputBoard, row, column);
                    if (activeCellCount == overPopulation) {
                        inputBoard[row][column] = soonToBeLive;
                    }
                }
            }
        }

        updateBoard(inputBoard);

        return inputBoard;
    }

    private void updateBoard(int[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                if (board[row][column] == soonToBeDead) {
                    board[row][column] = dead;
                }
                if (board[row][column] == soonToBeLive) {
                    board[row][column] = live;
                }
            }
        }
    }

    private int findActiveCell(int[][] board, int row, int column) {
        int activeCount = 0;

        for (int direction[] : directions) {
            int newRow = row + direction[0];
            int newColumn = column + direction[1];

            if (newRow >= 0 && newRow < board.length && newColumn >= 0 && newColumn < board[0].length && (board[newRow][newColumn] == 1 || board[newRow][newColumn] == soonToBeDead)) {
                activeCount++;
            }

        }

        return activeCount;
    }

    private static void printBoard(int[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                if (board[row][column] == live) {
                    System.out.print("Live    ");
                } else {
                    System.out.print("Dead    ");
                }
            }
            System.out.println();
        }
        System.out.println("##################################");
    }

    public static void main(String[] args) {
        int[][] newGenBoard = {{dead, live, dead}, {dead, dead, live}, {live, live, live}, {dead, dead, dead}};
        int numberOfGen = 5;
        while (numberOfGen > 0) {
            newGenBoard = new GameOfLifeDemo().getNextGenBoard(newGenBoard);
            printBoard(newGenBoard);
            numberOfGen--;
        }

    }

}
