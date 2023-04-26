package org.cis1200;

import java.awt.event.ActionListener;

public class Mine extends Cell {
    public Mine(int row, int col, final ActionListener actionListener, int value) {
        super(row, col, actionListener, value);
    }

    @Override
    public void reveal() {
        setEnabled(false);
        setText("X");
        super.setCovered(false);
    }

    @Override
    public boolean isMine() {
        return true;
    }
}
