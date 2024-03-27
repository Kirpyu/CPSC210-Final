package ui;

import model.Inventory;

//creates an inventory class to maintain and manage inventory
public class InventoryUI {
    private final Inventory inventory;
    private final TerminalGame terminalGame;

    //Creates screen for inventory
    public InventoryUI(Inventory inventory, TerminalGame terminalGame) {
        this.inventory = inventory;
        this.terminalGame = terminalGame;
    }

    //MODIFIES: terminalGame
    //EFFECTS: swaps screen to options if cursor hovers exit
    public void executeInventory(int option) {
        if (option == inventory.getInventory().size() + 1) {
            terminalGame.swapScreen("Options");
        } else {
            System.out.println("Does nothing... will equip item in the future");
        }
    }
}
