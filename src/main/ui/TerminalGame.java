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
import model.*;
import model.enemy.Enemy;
import model.enemy.EnemyList;

import java.io.IOException;
import java.util.ArrayList;

//RPG Game
public class TerminalGame {
    private Screen screen;
    private int option;
    private final ArrayList<String> listOfOptions;
    private String currentScreen;
    private final Shop shop;
    private Player player;
    private final Inventory inventory;
    private final EnemyList enemyList;
    private Dialogue dialogue;

    //EFFECTS: Constructor
    public TerminalGame() {
        listOfOptions = new ArrayList<>();

        listOfOptions.add("Attack");
        listOfOptions.add("Inventory");
        listOfOptions.add("Shop");
        listOfOptions.add("Stats");

        currentScreen = "Options";
        player = new Player();
        shop = new Shop();
        inventory = new Inventory();
        enemyList = new EnemyList();
        dialogue = new Dialogue(player, enemyList);
    }

    // MODIFIES: this
    // EFFECTS: starts the game
    public void start() throws IOException {

        screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();
        screen.setCursorPosition(new TerminalPosition(0, 0));
        screen.clear();

        option = 1;

        enemyList.createEnemies();
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
                    executeCurrentScreen();
                }
                break;
        }
    }

    // EFFECTS: executes command given depending on the current page
    public void executeCurrentScreen() throws IOException {
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
            case "Attack":
                executeAttack();
                break;
            case "Stats":
                executeStats();
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: render depending on the direction of button pressed and
    // changes space bar key behavior depending on current screen
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void render(String direction) throws IOException {
        screen.clear();
        switch (currentScreen) {
            case "Options":
                drawArrow(direction, listOfOptions);
                drawOptions(listOfOptions);
                break;

            case "Attack":
                drawArrow(direction, enemyList.getEnemyNames());
                drawOptions(enemyList.getEnemyNames());
                drawMoreOptions(enemyList.getEnemiesHealth());
                break;

            case "Inventory":
                drawArrow(direction, inventory.getInventoryNames());
                drawOptions(inventory.getInventoryNames());
                drawMoreOptions(inventory.getInventoryLevels());
                break;

            case "Shop":
                drawArrow(direction, shop.getShopListNames());
                drawOptions(shop.getShopListNames());
                drawMoreOptions(shop.getShopListCosts());
                break;

            case "Stats":
                drawArrow(direction, player.getStats());
                drawOptions(player.getStats());
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

    // MODIFIES: this
    // EFFECTS: if the cursor hovers exit, then returns to option, else
    // adds item to inventory
    public void executeShop() throws IOException {
        if (option == shop.getShopList().size()) {
            swapScreen("Options");
        } else {
            inventory.addInventory(shop.purchaseItem(option));
        }
    }

    //MODIFIES: this
    //EFFECTS: executes the attack if cursor does not hover exit
    public void executeAttack() throws IOException {
        if (option == enemyList.getEnemyList().size()) {
            swapScreen("Options");
        } else {
            attackEnemy(enemyList.getEnemy(option));
            swapScreen("Options");
        }
    }

    public void attackEnemy(Enemy targetEnemy) {
        dialogue.resetDialogue();
        player.damageEnemy(targetEnemy, player.getDamage());

        dialogue.addDialogue("You damaged " + targetEnemy.getName() + " for " + player.getDamage());

        if (targetEnemy.dead()) {
            dialogue.addDialogue("You killed " + targetEnemy.getName());
            enemyList.removeEnemy(targetEnemy);
            inventory.addGold(targetEnemy.getGoldDropped());
            inventory.addInventory(targetEnemy.getItemDropped());
            dialogue.addDialogue("You gained " + targetEnemy.getGoldDropped() + "G");
            dialogue.addDialogue("You gained " + targetEnemy.getItemDropped().getItemName());
        } else {
            dialogue.addDialogue(targetEnemy.getName() + " has " + targetEnemy.getHealth() + " health left");
        }

        if (enemyList.getEnemyList().isEmpty()) {
            dialogue.addDialogue("You have killed all enemies");
            dialogue.addDialogue("Shop has refreshed!");
            dialogue.addDialogue("Next Enemy Wave Incoming");
        } else {
            attackPlayer(player);
        }
    }

    public void attackPlayer(Player player) {
        for (Enemy e: enemyList.getEnemyList()) {
            player.damagePlayer(e.getAttack());
            dialogue.addDialogue(e.getName() + " has hit you for " + e.getAttack() + "HP");
        }

        if (player.dead()) {
            drawEndScreen();
        } else {
            dialogue.addDialogue("You have " + player.getCurrentHealth() + "HP left");
        }
    }

    //MODIFIES: this
    //EFFECTS: pressing exit exits the inventory
    public void executeInventory() throws IOException {
        if (option == inventory.getInventory().size() + 1) {
            swapScreen("Options");
        } else {
            System.out.println("lol");
        }
    }

    public void executeStats() throws IOException {
        if (option == player.getStats().size() - 1) {
            swapScreen("Options");
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

    // EFFECTS: displays all options
    private void drawMoreOptions(ArrayList<String> options) {
        for (int i = 0; i < options.size(); i++) {
            TextGraphics text = screen.newTextGraphics();
            text.setForegroundColor(TextColor.ANSI.WHITE);
            text.putString(15, i, options.get(i));
        }
    }

    private void drawDialogue(ArrayList<String> options) {
        for (int i = 0; i < options.size(); i++) {
            TextGraphics text = screen.newTextGraphics();
            text.setForegroundColor(TextColor.ANSI.WHITE);
            text.putString(1, 10 + i, options.get(i));
        }
    }

    //MODIFIES: this
    //EFFECTS: displays end screen
    private void drawEndScreen() {
        WindowBasedTextGUI endGui = new MultiWindowTextGUI(screen, TextColor.ANSI.BLACK);

        new MessageDialogBuilder()
                .setTitle("You Died")
                .addButton(MessageDialogButton.Close)
                .setText("Get Good")
                .build()
                .showDialog(endGui);
    }
}
