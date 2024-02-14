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

    public void addInventory(Item item) {
        inventoryList.add(item);
    }


    public ArrayList<Item> getInventory() {
        return inventoryList;
    }

    public ArrayList<String> getInventoryNames() {
        ArrayList<String> tempList = new ArrayList<>();
        for (Item i: inventoryList) {
            tempList.add(i.getItemName());
        }
        tempList.add("Exit");
        return tempList;
    }

}
