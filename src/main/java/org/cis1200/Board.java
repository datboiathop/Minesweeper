package org.cis1200;

import javax.swing.*;
import java.awt.*;
import java.util.*;
public class Board extends JPanel {

    private final int numCol;
    private final int numRow;
    private final int numMines;
    private  Cell[][] grid;
    private final JLabel status;


    public Board(JLabel status) {
        super();
        this.status = status;
        numCol = 10;
        numRow = 10;
        numMines = 14;
        createBoard();
    }

    public void createBoard() {
        int buttonSize = 20;
        setLayout(new GridLayout(numRow, numCol));
        setPreferredSize(new Dimension(numCol * buttonSize, numRow * buttonSize));
        newGame();
    }
    private void revealBoardAfterLoss() {
        if (grid == null) {
            return;
        }
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                if (grid[i][j].isCovered()) {
                    grid[i][j].reveal();
                }
            }
        }
        setBackground(Color.red);
        status.setText("You lost!");
    }
    private void checkWin() {
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                Cell cell = grid[i][j];
                if (cell.isEnabled() && !cell.isMine()) {
                    return;
                }
            }
        }
        status.setText("You won!");
    }

    private void revealZeros(Cell cell) {
        int row = cell.getRow();
        int col = cell.getCol();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((row + i) < 0 || (col + j) < 0 || (row + i) >= numRow || (col + j) >= numCol) {
                    continue;
                }
                if (grid[row + i][col + j].getSurroundingMines() == 0 &&
                        grid[row + i][col + j].isCovered()) {
                    grid[row + i][col + j].reveal();
                    revealZeros(grid[row + i][col + j]);
                }

            }
        }
    }
    private void revealCell(Cell cell) {
        if (cell.isMine()) {
            cell.setForeground(Color.RED);
            cell.setBackground(Color.RED);
            cell.reveal();
            revealBoardAfterLoss();
            return;
        } else if (cell.getSurroundingMines() == 0) {
            revealZeros(cell);
        } else {
            cell.reveal();
        }
        checkWin();
    }

    private void resetGrid() {
        if (grid == null) {
            return;
        }
        for (int row = 0; row < numRow; row++) {
            for (int col = 0; col < numCol; col++) {
                grid[row][col].reset();
            }
        }
    }

    public void newGame() {
        revealBoardAfterLoss();
        resetGrid();
        removeAll();
        setBackground(Color.gray);
        status.setText("New Game!");
        grid = new Cell[numRow][numCol];
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                grid[i][j] = new Empty(i, j, e -> {
                    Object source = e.getSource();
                    revealCell((Cell) source); }, 0);
            }
        }
        addMines();
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                add(grid[i][j]);
            }
        }
    }

    public void addMines() {
        int numMinesPlaced = 0;
        while (numMinesPlaced < numMines) {
            var rand = new Random();
            int col = rand.nextInt(numCol);
            int row = rand.nextInt(numRow);
            if (grid[row][col].isMine()) {
                continue;
            }
            numMinesPlaced++;
            grid[row][col] = new Mine(row, col, e -> {
                Object source = e.getSource();
                revealCell((Cell) source); }, 9);

            for (int j = -1; j <= 1; j++) {
                for (int k = -1; k <= 1; k++) {
                    if ((row + j) < 0 || (col + k) < 0 ||
                            (row + j) >= numRow || (col + k) >= numCol) {
                        continue;
                    }
                    Cell cell = grid[row + j][col + k];
                    if (!cell.isMine()) {
                        cell.increaseMines();
                    }
                }
            }
        }
    }

    public Cell[][] getGrid() {
        return grid.clone();
    }

    public int[] getMinePositions() {
        int[] ret = new int[numMines];
        int index = 0;
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                if (grid[i][j].isMine()) {
                    ret[index] = (grid[i][j].getRow() * 10) + grid[i][j].getCol();
                    index++;
                }
            }
        }
        return ret;
    }

    public int getNumRow() {
        return numRow;
    }

    public int getNumCol() {
        return numCol;
    }


}
