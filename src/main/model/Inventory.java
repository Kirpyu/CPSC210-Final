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
        if (getInventoryNames().contains(item.getItemName())) {
            for (Item i:inventoryList) {
                if (i.getItemName().equals(item.getItemName())) {
                    i.addLevel();
                    break;
                }
            }
        } else {
            inventoryList.add(item);
        }
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

    public ArrayList<String> getAbilityNames() {
        ArrayList<String> tempList = new ArrayList<>();
        for (Item i: inventoryList) {
            tempList.add(i.getAbilityName());
        }
        tempList.add("Exit");
        return tempList;
    }

    public ArrayList<String> getInventoryLevels() {
        ArrayList<String> tempList = new ArrayList<>();
        for (Item i: inventoryList) {
            tempList.add("Level " + i.getLevel());
        }
        return tempList;
    }
}
