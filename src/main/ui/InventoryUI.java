package ui;

import com.googlecode.lanterna.screen.Screen;
import model.Inventory;

import java.io.IOException;

public class InventoryUI {
    private Inventory inventory;
    private TerminalGame terminalGame;

    public InventoryUI(Inventory inventory, TerminalGame terminalGame) {
        this.inventory = inventory;
        this.terminalGame = terminalGame;
    }

    public void executeInventory(int option) throws IOException {
        if (option == inventory.getInventory().size() + 1) {
            terminalGame.swapScreen("Options");
        } else {
            System.out.println("lol");
        }
    }
}
