package org.cis1200;

import java.awt.event.ActionListener;

public class Empty extends Cell {
    public Empty(int row, int col, final ActionListener actionListener, int value) {
        super(row, col, actionListener, value);
    }

    @Override
    public void reveal() {
        setEnabled(false);
        setText(Integer.toString(super.getSurroundingMines()));
        super.setCovered(false);
    }

    @Override
    public boolean isMine() {
        return false;
    }
}
