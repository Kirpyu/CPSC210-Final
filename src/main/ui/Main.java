package ui;

import java.io.IOException;

// runs the game
public class Main {
    public static void main(String[] args) throws IOException {
        //creates and starts the game
        TerminalGame gameHandler = new TerminalGame();

        gameHandler.start();
    }
}
