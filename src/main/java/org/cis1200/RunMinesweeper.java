package org.cis1200;

// imports necessary libraries for Java swing
import java.awt.*;
import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class RunMinesweeper implements Runnable {
    public void run() {
        // NOTE : recall that the 'final' keyword notes immutability even for
        // local variables.

        // Top-level frame in which game components live.
        final JFrame frame = new JFrame("Minesweeper");
        frame.setLocation(600, 400);

        frame.setLayout(new BorderLayout());


        JLabel status = new JLabel("New Game!");
        Board b = new Board(status);
        JPanel modeToolbar = createModeToolbar(status, b);
        frame.add(modeToolbar, BorderLayout.PAGE_END);
        frame.add(b, BorderLayout.CENTER);

        JFrame popup = new JFrame("Instructions");

        // create a label
        JLabel l = new JLabel("<html> Instructions: Welcome to Minesweeper!<br><html> " +
                "<html> You start the game by clicking on a random cell. <br> <html>" +
                "<html>If the  cell reveals a number then that is <br> <html>" +
                "<html>the number of mines that surround that specific cell. <br> <html>" +
                "<html>The surrounding mines can be in " +
                "any of the 8 surrounding cells. <br> <html>" +
                "<html> If the cell reveals an X then you have " +
                "hit a mine and your game is over!<br> <html>");
        l.setForeground(Color.BLUE);
        popup.add(l);
        popup.setLocation(650, 450);
        popup.setSize(300, 250);

        // Put the frames on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        popup.setVisible(true);


    }
    private JPanel createModeToolbar(JLabel status, Board b) {
        // status of game
        JPanel modeToolbar = new JPanel();
        modeToolbar.add(status);

        // new game
        JButton newGame = new JButton("New Game");
        modeToolbar.add(newGame);
        newGame.addActionListener(e -> b.newGame());

        // exiting the program
        JButton quit = new JButton("Quit");
        modeToolbar.add(quit);
        quit.addActionListener(e -> System.exit(0));

        return modeToolbar;
    }


}