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
import model.Shop;

import java.io.IOException;
import java.util.ArrayList;

//RPG Game
public class TerminalGame {
    private Screen screen;
    // option can also be thought of the current cursor position
    private int option;
    private ArrayList<String> listOfOptions;
    private String currentScreen;
    private Shop shop;

    //EFFECTS: Constructor
    public TerminalGame() {
        listOfOptions = new ArrayList<>();

        listOfOptions.add("Right");
        listOfOptions.add("Up");
        listOfOptions.add("Down");
        listOfOptions.add("Left");

        currentScreen = "Options";
        shop = new Shop();
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
                    //make this find cursor, then go to the page holding the right stuff
                    //if current screen = blank, then do specific command
                    if (currentScreen.equals("Options")) {
                        System.out.println("Swapped");
                        executeOption();
                    } else if (currentScreen.equals("Shop")) {
                        System.out.println("Swapped");
                        executeShop();
                    }
                }
                break;
        }
    }

    // EFFECTS: render depending on the direction of button pressed and
    // changes spacebar key behavior depending on currentscreen
    private void render(String direction) throws IOException {
        screen.clear();
        switch (currentScreen) {
            case "Options":
                drawArrow(direction, listOfOptions);
                drawOptions(listOfOptions);
                break;

            case "Shop":
                drawArrow(direction, shop.getShopListNames());
                drawOptions(shop.getShopListNames());
                break;
        }


        screen.refresh();
    }

    // EFFECTS: finds cursors position, then executes whatever task is given
    public void executeOption() throws IOException {
        switch (option) {
            case 1:
                System.out.println("Cursor at 1");
                currentScreen = "Shop";
                render("up");
                break;
        }
    }

    // EFFECTS: finds cursors position, then executes whatever task is given
    public void executeShop() throws IOException {
        switch (option) {
            case 5:
                currentScreen = "Options";
                option = 1;
                render("up");
                break;
        }

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
        for (int i = 0; i < options.size(); i++) {
            TextGraphics text = screen.newTextGraphics();
            text.setForegroundColor(TextColor.ANSI.WHITE);
            text.putString(2, i, options.get(i));
        }
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

    //getters
    public int getOption() {
        return option;
    }

    //setters
    public void setCurrentScreen(String currentScreen) {
        this.currentScreen = currentScreen;
    }

    public void setOption(int option) {
        this.option = option;
    }
}
