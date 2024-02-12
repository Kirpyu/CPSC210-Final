package ui;

import com.googlecode.lanterna.TerminalPosition;
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

import java.io.IOException;
import java.util.ArrayList;

//RPG Game
public class TerminalGame {
    private Screen screen;
    private int option;
    private ArrayList<String> listOfOptions;

    public TerminalGame() {

        ArrayList<String> listOfOptions = new ArrayList<>();
        listOfOptions.add("Up");
        listOfOptions.add("Down");
        listOfOptions.add("Left");
        this.listOfOptions = listOfOptions;

    }

    // EFFECTS: starts the game
    public void start() throws IOException {

        screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();


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
        boolean keepGoing = true;

        init();

        KeyStroke input;

        while (keepGoing) {
            input = screen.readInput();
            if (input.getKeyType() == KeyType.Escape) {
                keepGoing = false;
            } else {
                processInput(input);
            }
        }

        drawEndScreen();

    }

    //EFFECTS: checks input, then renders the game according to input
    private void processInput(KeyStroke type) throws IOException {
        switch (type.getKeyType()) {
            case ArrowUp:
                render("up");

                break;
            case ArrowDown:
                render("down");

                break;

            case Character:
                if (type.getCharacter() == ' ') {
                    System.out.println("worked");
                    //make this find cursor, then go to the page holding the right stuff
                }
                break;
        }
    }

    // EFFECTS: render depending on the direction of button pressed
    private void render(String direction) throws IOException {
        screen.clear();

        drawArrow(direction, listOfOptions);
        drawOptions(listOfOptions);

        screen.refresh();
    }

    // initializes stuff
    private void init() {

    }

    //EFFECTS: draws the pointer next to options
    private void drawArrow(String direction, ArrayList<String> options) {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(TextColor.ANSI.WHITE);
        switch (direction) {
            case "up":
                if (option != 0) {
                    option--;
                }
                break;

            case "down":
                if (option != options.size() - 1) {
                    option++;
                }
                break;
        }

        text.putString(1, option, ">");

    }

    // EFFECTS: displays all options
    private void drawOptions(ArrayList<String> options) {
        // can use for loop here, where up down left are in an array
        // can make an array become a parameter
        System.out.println(options);
        for (int i = 0; i < options.size(); i++) {
            TextGraphics text = screen.newTextGraphics();
            text.setForegroundColor(TextColor.ANSI.WHITE);
            text.putString(2, i, options.get(i));
        }

//        TextGraphics text = screen.newTextGraphics();
//        text.setForegroundColor(TextColor.ANSI.WHITE);
//        text.putString(2, 0, "Up");
//
//        text = screen.newTextGraphics();
//        text.setForegroundColor(TextColor.ANSI.WHITE);
//        text.putString(2, 1, "Down");
//
//        text = screen.newTextGraphics();
//        text.setForegroundColor(TextColor.ANSI.WHITE);
//        text.putString(2, 2, "Left");
    }

    //MODIFIES: this
    //EFFECTS: displays end screen
    private void drawEndScreen() {
        WindowBasedTextGUI endGui = new MultiWindowTextGUI(screen);

        new MessageDialogBuilder()
                .setTitle("Done")
                .addButton(MessageDialogButton.Close)
                .build()
                .showDialog(endGui);
    }


}
