package ui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import model.Game;

import javax.swing.*;
import java.io.IOException;

public class TerminalGame {
    private Game game;
    private Screen screen;
    private WindowBasedTextGUI endGui;
    private int option;

    // EFFECTS: starts the game
    public void start() throws IOException {
        screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();

        TerminalSize terminalSize = screen.getTerminalSize();

        game = new Game();

        screen.setCursorPosition(new TerminalPosition(0, 0));
        screen.clear();

        option = 1;
        render("up");

        screen.refresh();



        handleUserInput();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void handleUserInput() throws IOException {
        System.out.println("started");
        boolean keepGoing = true;

        init();

        KeyStroke input;

        while (keepGoing) {
            input = screen.readInput();
            if (input.getKeyType() == KeyType.Escape) {
                keepGoing = false;
            } else {
                processInput(input.getKeyType());
            }
        }

        System.out.println("Game Ended");
        drawEndScreen();

    }

    private void processInput(KeyType type) throws IOException {
        switch (type) {
            case ArrowUp:
                render("up");

                break;
            case ArrowDown:
                render("down");

                break;
            case ArrowLeft:
                System.out.println("Pressed Left");

                break;
        }
    }

    private void render(String direction) throws IOException {
        screen.clear();
        drawArrow(direction);
        drawOptions();
        screen.refresh();
    }

    // initializes stuff
    private void init() {

    }

    private void drawArrow(String direction) {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(TextColor.ANSI.WHITE);
        switch (direction) {
            case "up":
                if (option != 0) {
                    option--;
                }
                break;

            case "down":
                if (option != 2) {
                    option++;
                }
                break;
        }

        text.putString(1, option, ">");

    }

    private void drawOptions() {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(TextColor.ANSI.WHITE);
        text.putString(2, 0, "Up");

        text = screen.newTextGraphics();
        text.setForegroundColor(TextColor.ANSI.WHITE);
        text.putString(2, 1, "Down");

        text = screen.newTextGraphics();
        text.setForegroundColor(TextColor.ANSI.WHITE);
        text.putString(2, 2, "Left");
    }


    private void drawEndScreen() {
        endGui = new MultiWindowTextGUI(screen);

        new MessageDialogBuilder()
                .setTitle("Done")
                .addButton(MessageDialogButton.Close)
                .build()
                .showDialog(endGui);
    }


}
