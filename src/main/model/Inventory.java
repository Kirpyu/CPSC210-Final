package model;

import model.items.Item;

import java.util.ArrayList;

// keeps track of inventory
public class Inventory {
    private int gold;
    private ArrayList<Item> inventoryList;

    public Inventory() {
        inventoryList = new ArrayList<>();
    }

    public ArrayList<Item> getInventory() {
        return inventoryList;
    }

}
