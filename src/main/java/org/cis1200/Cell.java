package org.cis1200;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class Cell extends JButton {
    private final int col;
    private final int row;
    private boolean covered;
    private int surroundingMines;

    public Cell(int row, int col, final ActionListener actionListener, int value) {
        super();
        this.row = row;
        this.col = col;
        this.covered = true;
        addActionListener(actionListener);
        surroundingMines = value;
        setText("");
    }

    public abstract boolean isMine();

    public abstract void reveal();

    public void reset() {
        setEnabled(true);
        surroundingMines = 0;
        setText("");
        covered = true;
    }
    public int getSurroundingMines() {
        return surroundingMines;
    }

    public void increaseMines() {
        surroundingMines++;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isCovered() {
        return covered;
    }

    public void setCovered(boolean b) {
        covered = b;
    }



}
