package ui;

import model.*;
import model.enemy.EnemyList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

//RPG Game
public class TerminalGame extends JFrame {

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

    //hud
    private JLabel hudHP;

    //panels
    private final JPanel graphicPanel; // this will be a card panel that changes depending on current screen
    private final JPanel textPanel;
    private final JPanel hudPanel;
    private GridBagConstraints constraints;
    private final JPanel moreTextPanel;
    private final JPanel mainPanel;
    private final JPanel characterPanel;

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

        hudHP = new JLabel();

        graphicPanel = new JPanel();
        hudPanel = new JPanel();
        textPanel = new JPanel();
        moreTextPanel = new JPanel();
        characterPanel = new JPanel();
        constraints = new GridBagConstraints();
        mainPanel = new JPanel(new GridBagLayout());

    }

    // MODIFIES: this
    // EFFECTS: starts the game
    public void start() {
        initOptions();
        createAndShowGUI();
        attackUI.createEnemies(2);
        //test, remove after
        dialogue.start();
        swapScreen("Dialogue");
    }

    // EFFECTS: loads font for use
    public static Font loadFont(String path, float size) {
        try {
            InputStream fileStream = TerminalGame.class.getResourceAsStream(path);
            Font myFont = Font.createFont(Font.TRUETYPE_FONT, fileStream);
            return myFont.deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: creates all options for the main menu
    public void initOptions() {
        listOfOptions.add("Attack");
        listOfOptions.add("Inventory");
        listOfOptions.add("Shop");
        listOfOptions.add("Stats");
        listOfOptions.add("Save");
        listOfOptions.add("Load");
    }

    //MODIFIES: this
    //EFFECTS: creates graphic panel box and sets it to proper grid position
    public void createGraphicPanel() {
        constraints = new GridBagConstraints();
        graphicPanel.setPreferredSize(new Dimension(600,300));
        graphicPanel.setBackground(Color.black);
        graphicPanel.setLayout(new BorderLayout());
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 3;
        mainPanel.add(graphicPanel, constraints);

        characterPanel.setBackground(Color.black);
        characterPanel.setLayout(new FlowLayout());

        graphicPanel.add(characterPanel, BorderLayout.SOUTH);
    }

    //MODIFIES: this
    //EFFECTS: creates hud panel box and sets it to proper grid position
    public void createHudPanel() {
        constraints = new GridBagConstraints();
        hudPanel.setPreferredSize(new Dimension(600,50));
        hudPanel.setBackground(Color.black);
        hudPanel.setLayout(new BoxLayout(hudPanel, BoxLayout.PAGE_AXIS));
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;

        hudHP = new JLabel("HP: " + player.getCurrentHealth() + "/" + player.getMaxHealth());
        hudHP.setForeground(Color.white);
        hudHP.setFont(loadFont("/fonts/PixelifySans-Bold.ttf", 24f));
        hudPanel.add(hudHP);

        mainPanel.add(hudPanel, constraints);
    }

    //MODIFIES: this
    //EFFECTS: updates the hud panel with the correct stats
    public void updateHudPanel() {
        hudHP.setText("HP: " + player.getCurrentHealth() + "/" + player.getMaxHealth());
    }

    //EFFECTS: creates text panel box and sets it to proper grid position
    public void createTextPanel() {
        constraints = new GridBagConstraints();
        textPanel.setPreferredSize(new Dimension(400,150));
        textPanel.setBackground(Color.black);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));
        textPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,0,Color.white));
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;

        mainPanel.add(textPanel, constraints);
    }

    //MODIFIES: this
    //EFFECTS: creates second text panel box and sets it to proper grid position
    public void createMoreTextPanel() {
        constraints = new GridBagConstraints();
        moreTextPanel.setPreferredSize(new Dimension(200,150));
        moreTextPanel.setBackground(Color.black);
        moreTextPanel.setLayout(new BoxLayout(moreTextPanel, BoxLayout.PAGE_AXIS));
        moreTextPanel.setBorder(BorderFactory.createMatteBorder(1,0,1,1,Color.white));
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 1;

        mainPanel.add(moreTextPanel, constraints);
    }

    //MODIFIES: this
    //EFFECTS: creates a picture using the given file path, then adds it to the graphic panel
    public void createImage(String path) {

        ImageIcon imageIcon = new ImageIcon(new ImageIcon(this.getClass().getResource(path))
                .getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));
        JLabel picLabel = new JLabel(imageIcon);
        picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        characterPanel.add(picLabel);
    }


    // EFFECTS: creates all panels and adds them to mainpanel
    public void addComponentsToPane() {
        createGraphicPanel();
        createHudPanel();
        createTextPanel();
        createMoreTextPanel();
    }

    // MODIFIES: this
    // EFFECTS: removes an enemy from the graphicPanel
    public void removeEnemyGraphic() {
        // make sure its not visible
        characterPanel.getComponent(0).setVisible(false);
        characterPanel.remove(0);
    }

    // MODIFIES: this
    // EFFECTS: creates the frame and panels for the games, then shows them
    public void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Game");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);

        mainPanel.setBackground(Color.black);
        frame.add(mainPanel);

        //Set up the content pane.
        addComponentsToPane();

        //Display the window.
        frame.pack();
        setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: removes and creates panels again in order to avoid overlapping
    public void refreshPanels() {
        textPanel.removeAll();
        moreTextPanel.removeAll();

        createMoreTextPanel();
        updateHudPanel();
        createTextPanel();
    }

    // MODIFIES: this
    // EFFECTS: executes command given depending on the current page
    public void executeCurrentScreen() {
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
            case "Dialogue":
                swapScreen("Options");
                break;
        }
    }

    // REQUIRES: dialogue is not empty
    // MODIFIES: this
    // EFFECTS: refreshes the screen with new lines
    public void refresh() {
        switch (currentScreen) {
            case "Options":
                drawArrow(listOfOptions);
                break;

            case "Attack":
                drawArrow(enemyList.getEnemyNames());
                drawMoreOptions(enemyList.getEnemiesStats());
                break;

            case "Inventory":
                drawArrow(inventory.getInventoryNames());
                drawMoreOptions(inventory.getInventoryLevels());
                break;

            case "Shop":
                drawArrow(shop.getShopListNames());
                drawMoreOptions(shop.getShopListCosts());
                break;

            case "Stats":
                drawArrow(player.getStats());
                break;

            case "Dialogue":
                drawArrow(dialogue.displayDialogue());
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: finds cursors position, then executes whatever task is given
    public void executeOption() {
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

    // REQUIRES: valid screen name (options, shop, etc.)
    // MODIFIES: this
    // EFFECTS: changes the current screen to screen name, then refreshes the screen
    public void swapScreen(String screenName) {
        currentScreen = screenName;
        option = 0;
        refresh();
    }

    //MODIFIES: this
    //EFFECTS: creates statistics panel for player
    public void executeStats() {
        if (option == player.getStats().size() - 1) {
            swapScreen("Options");
        }
    }

    //REQUIRES: options.size() >= 0
    //MODIFIES: this
    //EFFECTS: draws the pointer next to options, then draws options afterward
    public void drawArrow(ArrayList<String> options) {
        drawOptions(options);
        JButton currentButton = (JButton) textPanel.getComponent(option);
        currentButton.requestFocus();
    }

    //REQUIRES: options >= 0
    //MODIFIES: this
    //EFFECTS: draws the pointer next to options depending on key pressed, then draws options afterwards
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

    // EFFECTS: requests the focus of current button through the option field
    public void executeKey() {
        JButton currentButton = (JButton) textPanel.getComponent(option);
        currentButton.requestFocus();
    }

    // REQUIRES: option >= 0
    // EFFECTS: displays all given options as buttons
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void drawOptions(ArrayList<String> options) {
        refreshPanels();

        for (int i = 0; i < options.size(); i++) {
            JButton button = new JButton(options.get(i));

            button.setForeground(Color.white);
            button.setBackground(Color.black);
            button.setBorder(BorderFactory.createLineBorder(Color.black));
            button.setOpaque(true);
            button.setFont(loadFont("/fonts/PixelifySans-VariableFont_wght.ttf", 14f));

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

    // EFFECTS: displays all extra options through labels
    public void drawMoreOptions(ArrayList<String> options) {
        for (String s : options) {
            JLabel button = new JLabel(s);
            button.setForeground(Color.white);
            button.setFont(loadFont("/fonts/PixelifySans-VariableFont_wght.ttf", 14f));
            moreTextPanel.add(button);
        }
    }

    //EFFECTS: saves inventory, enemylist, and player to the jSon file
    public void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(inventory, enemyList, player);
            jsonWriter.close();
            dialogue.resetDialogue();
            dialogue.addDialogue("Saved File!");
            swapScreen("Dialogue");

        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
        refresh();
    }

    // MODIFIES: this
    // EFFECTS: loads all objects from save file
    public void load() {
        try {
            this.inventory = jsonReader.readInventory(inventory);
            this.enemyList = jsonReader.readEnemyList(enemyList);
            this.player = jsonReader.readPlayer(player);

        } catch (IOException e) {
            System.out.println("Fail");
        }

        dialogue.resetDialogue();
        dialogue.addDialogue("Loaded Save File!");
        swapScreen("Dialogue");
        refresh();
    }

    //EFFECTS: exits game
    public void drawEndScreen() {
        System.exit(1);
    }
}
