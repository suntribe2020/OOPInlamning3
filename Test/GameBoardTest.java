import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Katri Vidén
 * Date: 2020-10-24
 * Time: 15:23
 * Project: OOPInlamning3
 * Copyright: MIT
 */
public class GameBoardTest {

    @Test
    public final void allowedMoveTileTest() {
        int[] testBoard = new int[]{1, 2, 3, 4,
                5, 0, 6, 7,
                8, 9, 10, 11,
                12, 13, 14, 15};
        GameBoard gameBoard = new GameBoard(testBoard);
        // Testa flytta neråt
        assertTrue(gameBoard.moveTile(1));
        assertEquals(0, gameBoard.getGameBoard()[1]);
        assertEquals(2, gameBoard.getGameBoard()[5]);

        // Testa flytta uppåt
        assertTrue(gameBoard.moveTile(5));
        assertEquals(0, gameBoard.getGameBoard()[5]);
        assertEquals(2, gameBoard.getGameBoard()[1]);

        // Testa flytta till höger
        assertTrue(gameBoard.moveTile(4));
        assertEquals(5, gameBoard.getGameBoard()[5]);
        assertEquals(0, gameBoard.getGameBoard()[4]);

        // Testa flytta till vänster
        assertTrue(gameBoard.moveTile(5));
        assertEquals(5, gameBoard.getGameBoard()[4]);
        assertEquals(0, gameBoard.getGameBoard()[5]);
    }

    @Test
    public final void notAllowedEdgeTileMoveTest() {
        GameBoard gameBoard = new GameBoard();
        // Testar 0:an på höger kant
        // Sätter 0:an i element 7
        gameBoard.setGameBoard(createCustomGameboard(7));
        assertFalse(gameBoard.moveTile(8));
        // Sätter 0:an i element 3
        gameBoard.setGameBoard(createCustomGameboard(3));
        assertFalse(gameBoard.moveTile(4));
        // Sätter 0:an i element 11
        gameBoard.setGameBoard(createCustomGameboard(11));
        assertFalse(gameBoard.moveTile(12));

        // Testar 0:an på vänster kant
        // Sätter 0:an i element 4
        gameBoard.setGameBoard(createCustomGameboard(4));
        assertFalse(gameBoard.moveTile(3));
        // Sätter 0:an i element 8
        gameBoard.setGameBoard(createCustomGameboard(8));
        assertFalse(gameBoard.moveTile(7));
        // Sätter 0:an i element 12
        gameBoard.setGameBoard(createCustomGameboard(12));
        assertFalse(gameBoard.moveTile(11));
    }

    @Test
    public final void notAllowedMoveTileTest() {
        int[] testBoard = new int[]{0, 1, 2, 3,
                4, 5, 6, 7,
                8, 9, 10, 11,
                12, 13, 14, 15};
        GameBoard gameBoard = new GameBoard(testBoard);
        assertFalse(gameBoard.moveTile(2));
        // kolla att värdet inte har bytt plats
        assertEquals(2, gameBoard.getGameBoard()[2]);
        assertEquals(0, gameBoard.getGameBoard()[0]);
    }

    @Test
    public final void checkWinTest() {
        GameBoard gameBoard = new GameBoard();
        gameBoard.setGameBoard(createCustomGameboard(15));
        assertTrue(gameBoard.checkWin());
        gameBoard.setGameBoard(createCustomGameboard(11));
        assertFalse(gameBoard.checkWin());
    }

    private int[] createCustomGameboard(int elementWithZero) {
        int[] testBoard = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        testBoard[elementWithZero] = 0;
        return testBoard;
    }
}
