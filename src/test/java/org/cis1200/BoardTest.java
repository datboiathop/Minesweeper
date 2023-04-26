package org.cis1200;

import org.junit.jupiter.api.*;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * You can use this file (and others) to test your
 * implementation.
 */

public class BoardTest {

    @Test
    public void testNumMines() {
        Board b = new Board(new JLabel(""));
        int[] minePos = b.getMinePositions();
        assertEquals(14, minePos.length, "Number of mines on the board");
        for (int i: minePos) {
            int col = i % 10;
            int row = i / 10;
            if (row < 0 || row > 9 || col < 0) {
                assertFalse(false);
            }
        }
    }

    @Test
    public void testCreatingNewGameAndAddingNewMines() {
        Board b = new Board(new JLabel(""));
        int[] minePos = b.getMinePositions();
        assertEquals(14, minePos.length, "Number of mines on the board");
        b.newGame();
        int[] newMinePos = b.getMinePositions();
        assertNotEquals(newMinePos, minePos, "Testing new mine positions after add mines.");
        Cell[][] grid = b.getGrid();
        int count = 0;
        for (int i = 0; i < b.getNumRow(); i++) {
            for (int j = 0; j < b.getNumCol(); j++) {
                if (grid[i][j].isMine()) {
                    count++;
                }
            }
        }
        assertEquals(14, count, "Same number of mines after adding new mines.");
    }

    @Test
    public void testPositionsOfMines() {
        Board b = new Board(new JLabel(""));
        int[] minePos = b.getMinePositions();
        Cell[][] grid = b.getGrid();
        for (int i: minePos) {
            int col = i % 10;
            int row = i / 10;
            assertTrue(grid[row][col].isMine(), "Testing mines' positions in the grid.");
        }
    }

    @Test
    public void testSurroundingCells() {
        Board b = new Board(new JLabel(""));
        int[] minePos = b.getMinePositions();
        Cell[][] grid = b.getGrid();
        for (int i: minePos) {
            int col = i % 10;
            int row = i / 10;
            for (int j = -1; j <= 1; j++) {
                for (int k = -1; k <= 1; k++) {
                    if ((row + j) < 0 || (col + k) < 0 ||
                            (row + j) >= b.getNumRow() || (col + k) >= b.getNumCol()) {
                        continue;
                    }
                    if (!grid[row + j][col + k].isMine()) {
                        assertTrue(grid[row + j][col + k].getSurroundingMines() > 0,
                                "Testing if surrounding cells notice the mine.");
                    }
                }
            }
        }
    }

    @Test
    public void testRevealCell() {
        Board b = new Board(new JLabel(""));
        Cell[][] grid = b.getGrid();
        for (int i = 0; i < b.getNumRow(); i++) {
            for (int j = 0; j < b.getNumCol(); j++) {
                grid[i][j].reveal();
                assertFalse(grid[i][j].isCovered(), "Testing if cell is uncovered after reveal.");
            }
        }

    }

}

