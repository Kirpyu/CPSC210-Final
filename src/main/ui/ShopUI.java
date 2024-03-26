package ui;

import model.Inventory;
import model.Shop;
import model.items.Item;

import java.io.IOException;

public class ShopUI {
    private final Shop shop;
    private final Inventory inventory;
    private final Dialogue dialogue;
    private final TerminalGame terminalGame;


    //Creates screen for Shop
    public ShopUI(Shop shop, Inventory inventory, Dialogue dialogue, TerminalGame terminalGame) {
        this.shop = shop;
        this.inventory = inventory;
        this.terminalGame = terminalGame;
        this.dialogue = dialogue;
    }

    //MODIFIES: terminalGame, inventory
    //EFFECTS: swaps screen to options if cursor hovers exit, otherwise
    // adds a hovered item to inventory
    public void executeShop(int option) throws IOException {
        if (option == shop.getShopList().size()) {
            terminalGame.swapScreen("Options");
        } else {
            dialogue.resetDialogue();
            Item hoveredItem = shop.getItem(option);
            if (shop.canPurchase(inventory.getGold(), hoveredItem.getCost())) {
                inventory.addInventory(hoveredItem);
                inventory.setGold(inventory.getGold() - hoveredItem.getCost());
                dialogue.addDialogue("Purchased " + hoveredItem.getItemName() + ", "
                        + inventory.getGold() + "G remaining");

                terminalGame.swapScreen("Dialogue");
            } else {
                int missingGold = hoveredItem.getCost() - inventory.getGold();
                dialogue.addDialogue("Can't purchase " + hoveredItem.getItemName() + ", missing " + missingGold + "G");
                terminalGame.refresh();
            }
        }
    }
}
