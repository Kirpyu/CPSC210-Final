package ui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import model.*;
import model.enemy.EnemyList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

//RPG Game
public class TerminalGame extends JFrame {

    private Screen screen; // displays the screen
    private String currentScreen; // holds a string for what the current screen is
    private int option; // determines where the cursor or > is

//    private int waveNumber; // determines the numbers of waves that has passed
    private final ArrayList<String> listOfOptions; // determines list of options in options screen
    private final Shop shop; // holds a shop for players to purchase items in
    private Player player; // holds the main player
    private Inventory inventory; // holds the players inventory
    private EnemyList enemyList; // determines enemies that exist
    private final Dialogue dialogue; // determines dialogue behavior

    private final InventoryUI inventoryUI; // holds behavior of inventory screen
    private final ShopUI shopUI; // holds behavior of shop screen
    private final AttackUI attackUI; // holds behavior of attack screen

    // json
    private static final String JSON_STORE = "./data/save.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    //panels
    private JPanel graphicPanel; // this will be a card panel that changes depending on current screen
    private JPanel textPanel;
    private JPanel hudPanel;
    private GridBagConstraints constraints;
    private JPanel mainPanel;

    //EFFECTS: Starts the game, shows current screen and creates list of options
    public TerminalGame() {
        listOfOptions = new ArrayList<>();
        currentScreen = "Options";

        player = new Player();
        shop = new Shop();
        inventory = new Inventory();
        enemyList = new EnemyList();

        dialogue = new Dialogue(player, enemyList);
        inventoryUI = new InventoryUI(inventory, this);
        shopUI = new ShopUI(shop, inventory, dialogue, this);
        attackUI = new AttackUI(player, enemyList, inventory, dialogue, this);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        graphicPanel = new JPanel();
        hudPanel = new JPanel();
        textPanel = new JPanel();
        constraints = new GridBagConstraints();
        mainPanel = null;
    }

    // MODIFIES: this
    // EFFECTS: starts the game
    public void start() throws IOException {
        initOptions();
        createAndShowGUI();
        attackUI.createEnemies(2);
//        dialogue.start();
    }

    private void initOptions() {
        listOfOptions.add("Attack");
        listOfOptions.add("Inventory");
        listOfOptions.add("Shop");
        listOfOptions.add("Stats");
        listOfOptions.add("Save");
        listOfOptions.add("Load");
    }

    //EFFECTS: creates graphic panel box and sets it to proper grid position
    private void createGraphicPanel() {
        constraints = new GridBagConstraints();
        graphicPanel.setPreferredSize(new Dimension(600,300));
        graphicPanel.setBackground(Color.black);
        graphicPanel.setLayout(new BoxLayout(graphicPanel, BoxLayout.PAGE_AXIS));
        graphicPanel.setBorder(BorderFactory.createLineBorder(Color.white));
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        JLabel label = new JLabel("HP");
        label.setForeground(Color.white);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        graphicPanel.add(label);
        mainPanel.add(graphicPanel, constraints);
    }

    private void createHudPanel() {
        constraints = new GridBagConstraints();
        // make a for loop of all panels, initialize them and add them
        hudPanel.setPreferredSize(new Dimension(600,50));
        hudPanel.setBackground(Color.black);
        hudPanel.setLayout(new BoxLayout(hudPanel, BoxLayout.PAGE_AXIS));
        hudPanel.setBorder(BorderFactory.createLineBorder(Color.white));
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 3;
        JLabel label = new JLabel("HP");
        label.setForeground(Color.white);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        hudPanel.add(label);
        mainPanel.add(hudPanel, constraints);
    }

    //EFFECTS: creates text panel box and sets it to proper grid position
    private void createTextPanel() {
        constraints = new GridBagConstraints();
        textPanel.setPreferredSize(new Dimension(600,150));
        textPanel.setBackground(Color.black);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));
        textPanel.setBorder(BorderFactory.createLineBorder(Color.white));
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;

        mainPanel.add(textPanel, constraints);
    }

    private void addComponentsToPane() {
        createGraphicPanel();
        createHudPanel();
        createTextPanel();
    }

    private void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Game");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);

        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.black);
        frame.add(mainPanel);

        //Set up the content pane.
        drawOptions(listOfOptions);
        addComponentsToPane();

        //Display the window.
        frame.pack();
        setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    private void refreshPanels() {
        textPanel.removeAll();
        graphicPanel.removeAll();
        hudPanel.removeAll();

        //Set up the content pane.
        addComponentsToPane();
    }



    // EFFECTS: processes user input and progresses per input user makes (actions instead of ticks)
    // until process ends
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

    //REQUIRES: type is one of: arrowup, down, or spacebar
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

    // REQUIRES: direction is either up or down
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
                drawMoreOptions(enemyList.getEnemiesStats());
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
    // EFFECTS: refreshes the screen with new lines
    public void refresh() throws IOException {
        switch (currentScreen) {
            case "Options":
                drawArrow(listOfOptions);
                break;

            case "Attack":
                drawArrow(enemyList.getEnemyNames());
                break;

            case "Inventory":
                drawArrow(inventory.getInventoryNames());
                break;

            case "Shop":
                drawArrow(shop.getShopListNames());
                break;

            case "Stats":
                drawArrow(player.getStats());
                break;
        }

//        drawDialogue(dialogue.displayDialogue());
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
                break;
            case 4:
                save();
                break;
            case 5:
                load();
                break;
        }
    }

    // REQUIRES: valid screenname (options, shop, etc.)
    // MODIFIES: this
    // EFFECTS: changes the current screen to screen name, then refreshes the screen
    public void swapScreen(String screenName) throws IOException {
        currentScreen = screenName;
        option = 0;
        refresh();
    }

    //MODIFIES: this
    //EFFECTS: pressing exit exits the stats page
    public void executeStats() throws IOException {
        if (option == player.getStats().size() - 1) {
            swapScreen("Options");
        }
    }

    //EFFECTS: draws the pointer next to options, then draws options afterwards
    public void drawArrow(ArrayList<String> options) {
        drawOptions(options);
        JButton currentButton = (JButton) textPanel.getComponent(option);
        currentButton.requestFocus();
    }

    public void drawArrow(KeyEvent e, ArrayList<String> options) throws IOException {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (option != 0) {
                    option--;
                    executeKey();
                }
                break;

            case KeyEvent.VK_DOWN:
                if (option != options.size() - 1) {
                    // get focus of button
                    option++;
                    executeKey();
                }
                break;
            case KeyEvent.VK_SPACE:
                executeCurrentScreen();
                break;
        }
    }

    //REQUIRES: direction is up or down
    //EFFECTS: draws the pointer next to options, then draws options afterwards
    public void drawArrow(String direction, ArrayList<String> options) {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(TextColor.ANSI.WHITE);
        switch (direction) {
            case "up":
                if (option != 0) {
                    option--;
                    executeKey();
                }
                break;

            case "down":
                if (option != options.size() - 1) {
                    option++;
                    executeKey();
                }
                break;
        }
    }

    public void executeKey() {
        JButton currentButton = (JButton) textPanel.getComponent(option);
        currentButton.requestFocus();
    }

    // REQUIRES: option >= 0
    // EFFECTS: displays all given options
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void drawOptions(ArrayList<String> options) {
        refreshPanels();
        for (int i = 0; i < options.size(); i++) {
            JButton button = new JButton(options.get(i));

            button.setForeground(Color.white);
            button.setBackground(Color.black);
            button.setBorder(BorderFactory.createLineBorder(Color.black));
            button.setOpaque(true);

            button.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    try {
                        drawArrow(e, options);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            button.addFocusListener(new FocusAdapter() {
                String text = null;
                @Override
                public void focusGained(FocusEvent e) {
                    text = button.getText();
                    button.setText(">" + text);
                }

                @Override
                public void focusLost(FocusEvent e) {
                    button.setText(text);
                }
            });

            textPanel.add(button);

        }
    }

    // REQUIRES: option >= 0
    // EFFECTS: displays more options towards the right of the screen
    public void drawMoreOptions(ArrayList<String> options) {
        for (int i = 0; i < options.size(); i++) {
            TextGraphics text = screen.newTextGraphics();
            text.setForegroundColor(TextColor.ANSI.WHITE);
            text.putString(15, i, options.get(i));
        }
    }

    // REQUIRES: option >= 0
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

    //EFFECTS: saves inventory, enemylist, and player to the jSon file
    private void save() throws IOException {
        try {
            jsonWriter.open();
            jsonWriter.write(inventory, enemyList, player);
            jsonWriter.close();
            dialogue.addDialogue("Saved File");

        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
        refresh();
    }

    // MODIFIES: this
    // EFFECTS: loads all objects from save file
    private void load() throws IOException {
        try {
            this.inventory = jsonReader.readInventory(inventory);
            this.enemyList = jsonReader.readEnemyList(enemyList);
            this.player = jsonReader.readPlayer(player);

        } catch (IOException e) {
            System.out.println("Fail");
        }

        dialogue.resetDialogue();
        dialogue.addDialogue("Loaded Save File");
        refresh();
    }
}
