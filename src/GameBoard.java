import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Katri Vidén
 * Date: 2020-10-21
 * Time: 15:00
 * Project: OOPInlämning3
 * Copyright: MIT
 */
public class GameBoard {

    private int[] gameBoard = new int[16];

    GameBoard(int[] gameBoard) {
        this.gameBoard = gameBoard;
    }

    GameBoard() {
        boolean solvable = false;
        while (!solvable) {
            List<Integer> randomNumbers = getRandomNumbers();
            for (int i = 0; i < gameBoard.length; i++) {
                gameBoard[i] = randomNumbers.get(i);
            }
            solvable = isSolvable(gameBoard);
        }
    }

    private List<Integer> getRandomNumbers() {
        ArrayList<Integer> randomNumbers = new ArrayList<>();
        Random random = new Random();
        int number;
        while (randomNumbers.size() != 16) {
            number = random.nextInt(16);
            if (!randomNumbers.contains(number)) {
                randomNumbers.add(number);
            }
        }
        return randomNumbers;
    }

    public boolean checkWin() {
        for (int i = 0; i < gameBoard.length - 1; i++) {
            if (gameBoard[i] != i + 1) {
                return false;
            }
        }
        return true;
    }

    public boolean moveTile(int buttonNr) {
        if (!isValidMove(buttonNr)) {
            return false;
        }
        int swap = gameBoard[buttonNr];
        for (int i = 0; i < gameBoard.length; i++) {
            if (gameBoard[i] == 0) {
                gameBoard[i] = swap;
                gameBoard[buttonNr] = 0;
            }
        }
        return true;
    }

    public boolean isValidMove(int buttonNr) {
        for (int i = 0; i < gameBoard.length; i++) {
            if (gameBoard[i] == 0) {
                if (i == 3 || i == 7 || i == 11) {
                    return (buttonNr == i - 1 || buttonNr == i + 4 || buttonNr == i - 4);
                } else if (i == 4 || i == 8 || i == 12) {
                    return (buttonNr == i + 1 || buttonNr == i + 4 || buttonNr == i - 4);
                }
                return (buttonNr == i + 1 || buttonNr == i - 1 ||
                        buttonNr == i + 4 || buttonNr == i - 4);
            }
        }
        return false;
    }

    public int[] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(int[] gameBoard) {
        this.gameBoard = gameBoard;
    }

    // Lösningen kopierad från internet
    public boolean isSolvable(int[] puzzle) {
        int parity = 0;
        int gridWidth = (int) Math.sqrt(puzzle.length);
        int row = 0; // the current row we are on
        int blankRow = 0; // the row with the blank tile

        for (int i = 0; i < puzzle.length; i++) {
            if (i % gridWidth == 0) { // advance to next row
                row++;
            }
            if (puzzle[i] == 0) { // the blank tile
                blankRow = row; // save the row on which encountered
                continue;
            }
            for (int j = i + 1; j < puzzle.length; j++) {
                if (puzzle[i] > puzzle[j] && puzzle[j] != 0) {
                    parity++;
                }
            }
        }

        if (gridWidth % 2 == 0) { // even grid
            if (blankRow % 2 == 0) { // blank on odd row; counting from bottom
                return parity % 2 == 0;
            } else { // blank on even row; counting from bottom
                return parity % 2 != 0;
            }
        } else { // odd grid
            return parity % 2 == 0;
        }
    }
}
