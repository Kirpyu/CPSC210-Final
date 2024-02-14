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
import model.Inventory;
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
    private Inventory inventory;

    //EFFECTS: Constructor
    public TerminalGame() {
        listOfOptions = new ArrayList<>();

        listOfOptions.add("Fight");
        listOfOptions.add("Inventory");
        listOfOptions.add("Shop");
        listOfOptions.add("Exit");

        currentScreen = "Options";
        shop = new Shop();
        inventory = new Inventory();
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
                    switch (currentScreen) {
                        case "Options":
                            executeOption();
                            break;
                        case "Inventory":
                            executeInventory();
                            break;
                        case "Shop":
                            executeShop();
                            break;
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

            case "Fight":

                break;

            case "Inventory":
                drawArrow(direction, inventory.getInventoryNames());
                drawOptions(inventory.getInventoryNames());
                break;

            case "Shop":
                drawArrow(direction, shop.getShopListNames());
                drawOptions(shop.getShopListNames());
                break;

        }


        screen.refresh();
    }

    // MODIFIES: currentScreen
    // EFFECTS: finds cursors position, then executes whatever task is given
    public void executeOption() throws IOException {
        switch (option) {
            case 0:
                swapScreen("Fight");
                break;
            case 1:
                swapScreen("Inventory");
                break;
            case 2:
                swapScreen("Shop");
                break;
        }
    }

    // MODIFIES: currentScreen
    // EFFECTS: changes the current screen to screen name, then refreshes the screen
    public void swapScreen(String screenName) throws IOException {
        currentScreen = screenName;
        option = 1;
        render("up");
    }

    // MODIFIES: currentScreen
    // EFFECTS: finds cursors position, then executes whatever task is given
    public void executeShop() throws IOException {
        if (option == shop.getShopList().size()) {
            swapScreen("Options");
        } else {
            inventory.addInventory(shop.purchaseItem(option));
        }
    }

    public void executeInventory() throws IOException {
        if (option == inventory.getInventory().size()) {
            swapScreen("Options");
        } else {
            System.out.println("lol");
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
