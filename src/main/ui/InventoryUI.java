package ui;

import model.Inventory;
import model.Player;

//creates an inventory class to maintain and manage inventory
public class InventoryUI {
    private final Inventory inventory;
    private final TerminalGame terminalGame;
    private final Player player;

    //Creates screen for inventory
    public InventoryUI(Player player, Inventory inventory, TerminalGame terminalGame) {
        this.inventory = inventory;
        this.terminalGame = terminalGame;
        this.player = player;
    }

    //MODIFIES: terminalGame
    //EFFECTS: swaps screen to options if cursor hovers exit
    public void executeInventory(int option) {
        if (option == inventory.getInventory().size() + 1) {
            terminalGame.swapScreen("Options");
        } else if (option == inventory.getInventory().size()) {
            // nothing
        } else {
            player.setEquippedItem(inventory.getInventory().get(option));
        }

    }
}
