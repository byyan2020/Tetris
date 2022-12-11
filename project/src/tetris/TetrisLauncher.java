package tetris;

import javax.swing.*;

public class TetrisLauncher {
    public static void main(String[] args) {
        // Create a JFrame.
        JFrame frame = new JFrame("Tetris");
        // Create game panel.
        Tetris panel = new Tetris();
        // Add panel to frame
        frame.add(panel);
        // Make JFrame visible.
        frame.setVisible(true);
        // Set window size.
        frame.setSize(810, 940);
        // Make the window in the middle of the screen.
        frame.setLocationRelativeTo(null);
        // Terminate program when window is closed.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.start();
    }
}
