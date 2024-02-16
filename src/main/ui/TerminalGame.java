package ui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import model.*;
import model.enemy.Enemy;
import model.enemy.EnemyList;

import java.io.IOException;
import java.util.ArrayList;

//RPG Game
public class TerminalGame {

    private Screen screen;
    private String currentScreen;
    private int option;

    private int waveNumber;

    private final ArrayList<String> listOfOptions;
    private final Shop shop;
    private final Player player;
    private final Inventory inventory;
    private final EnemyList enemyList;
    private final Dialogue dialogue;

    private InventoryUI inventoryUI;
    private ShopUI shopUI;
    private AttackUI attackUI;


    //EFFECTS: Constructor
    public TerminalGame() {
        listOfOptions = new ArrayList<>();

        listOfOptions.add("Attack");
        listOfOptions.add("Inventory");
        listOfOptions.add("Shop");
        listOfOptions.add("Stats");

        currentScreen = "Options";
        waveNumber = 0;

        player = new Player();
        shop = new Shop();
        inventory = new Inventory();
        enemyList = new EnemyList();



        dialogue = new Dialogue(player, enemyList);
        inventoryUI = new InventoryUI(inventory, this);
        shopUI = new ShopUI(shop, inventory, dialogue, this);
        attackUI = new AttackUI(player, enemyList, inventory, dialogue, this);
    }

    // MODIFIES: this
    // EFFECTS: starts the game
    public void start() throws IOException {

        screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();
        screen.setCursorPosition(new TerminalPosition(0, 0));
        screen.clear();

        option = 1;

        attackUI.createEnemies(2);
        dialogue.start();
        render("up");

        screen.refresh();
        handleUserInput();
    }

    // EFFECTS: processes user input
    private void handleUserInput() throws IOException {
        boolean keepGoing = true;
        KeyStroke input;

        while (keepGoing) {
            input = screen.readInput();
            if (currentScreen.equals("End")) {
                keepGoing = false;
            } else {
                processInput(input);
            }
        }

        drawEndScreen();

    }

    //MODIFIES: this
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
                    executeCurrentScreen();
                }
                break;
            case Escape:
                drawEndScreen();
        }
    }

    // MODIFIES: this
    // EFFECTS: executes command given depending on the current page
    public void executeCurrentScreen() throws IOException {
        switch (currentScreen) {
            case "Options":
                executeOption();
                break;
            case "Inventory":
                inventoryUI.executeInventory(option);
                break;
            case "Shop":
                shopUI.executeShop(option);
                break;
            case "Attack":
                attackUI.executeAttack(option);
                break;
            case "Stats":
                executeStats();
                break;
            case "End":
                System.exit(0);
        }
    }

    // MODIFIES: this
    // EFFECTS: render depending on the direction of button pressed and
    // changes space bar key behavior depending on current screen
    public void render(String direction) throws IOException {
        screen.clear();
        switch (currentScreen) {
            case "Options":
                drawArrow(direction, listOfOptions);
                break;

            case "Attack":
                drawArrow(direction, enemyList.getEnemyNames());
                drawMoreOptions(enemyList.getEnemiesHealth());
                break;

            case "Inventory":
                drawArrow(direction, inventory.getInventoryNames());
                drawMoreOptions(inventory.getInventoryLevels());
                break;

            case "Shop":
                drawArrow(direction, shop.getShopListNames());
                drawMoreOptions(shop.getShopListCosts());
                break;

            case "Stats":
                drawArrow(direction, player.getStats());
                break;
        }

        drawDialogue(dialogue.displayDialogue());
        screen.refresh();
    }

    // MODIFIES: this
    // EFFECTS: finds cursors position, then executes whatever task is given
    public void executeOption() throws IOException {
        switch (option) {
            case 0:
                swapScreen("Attack");
                break;
            case 1:
                swapScreen("Inventory");
                break;
            case 2:
                swapScreen("Shop");
                break;
            case 3:
                swapScreen("Stats");
        }
    }

    // MODIFIES: this
    // EFFECTS: changes the current screen to screen name, then refreshes the screen
    public void swapScreen(String screenName) throws IOException {
        currentScreen = screenName;
        option = 1;
        render("up");
    }

    //MODIFIES: this
    //EFFECTS: pressing exit exits the stats page
    public void executeStats() throws IOException {
        if (option == player.getStats().size() - 1) {
            swapScreen("Options");
        }
    }

    //MODIFIES: this
    //EFFECTS: draws the pointer next to options, then draws options afterwards
    public void drawArrow(String direction, ArrayList<String> options) {
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
        drawOptions(options);

    }

    // MODIFIES: this
    // EFFECTS: displays all options
    public void drawOptions(ArrayList<String> options) {
        for (int i = 0; i < options.size(); i++) {
            TextGraphics text = screen.newTextGraphics();
            text.setForegroundColor(TextColor.ANSI.WHITE);
            text.putString(2, i, options.get(i));
        }
    }

    // MODIFIES: this
    // EFFECTS: displays all options
    public void drawMoreOptions(ArrayList<String> options) {
        for (int i = 0; i < options.size(); i++) {
            TextGraphics text = screen.newTextGraphics();
            text.setForegroundColor(TextColor.ANSI.WHITE);
            text.putString(15, i, options.get(i));
        }
    }

    // MODIFIES: this
    // EFFECTS: draws dialogue at the bottom of the screen
    public void drawDialogue(ArrayList<String> options) {
        for (int i = 0; i < options.size(); i++) {
            TextGraphics text = screen.newTextGraphics();
            text.setForegroundColor(TextColor.ANSI.WHITE);
            text.putString(1, 10 + i, options.get(i));
        }
    }

    //EFFECTS: exits game
    public void drawEndScreen() {
        System.exit(0);
    }


}
