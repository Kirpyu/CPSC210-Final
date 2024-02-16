package ui;

import model.Inventory;
import model.Shop;

import java.io.IOException;

public class ShopUI {
    private Shop shop;
    private Inventory inventory;
    private TerminalGame terminalGame;

    public ShopUI(Shop shop, Inventory inventory, TerminalGame terminalGame) {
        this.shop = shop;
        this.inventory = inventory;
        this.terminalGame = terminalGame;
    }

    public void executeShop() throws IOException {

    }
}
