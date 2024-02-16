package model;

import model.items.Item;

import java.util.ArrayList;

// keeps track of inventory
public class Inventory {
    private int gold;
    private final ArrayList<Item> inventoryList;

    //constructor
    public Inventory() {
        inventoryList = new ArrayList<>();
    }

    //MODIFIES: this, Item
    //EFFECTS: if item is already inventory, adds a level to the item,
    // otherwise adds the item to inventory
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

    public void addGold(int gold) {
        this.gold += gold;
    }

    //EFFECTS: returns list of items currently in inventory
    public ArrayList<Item> getInventory() {
        return inventoryList;
    }

    //EFFECTS: returns list of item names in inventory
    public ArrayList<String> getInventoryNames() {
        ArrayList<String> tempList = new ArrayList<>();
        for (Item i: inventoryList) {
            tempList.add(i.getItemName());
        }
        tempList.add("Gold: " + gold);
        tempList.add("Exit");
        return tempList;
    }

    //EFFECTS: returns list of ability names from items in inventory
    public ArrayList<String> getAbilityNames() {
        ArrayList<String> tempList = new ArrayList<>();
        tempList.add("Punch");
        for (Item i: inventoryList) {
            tempList.add(i.getAbilityName());
        }
        return tempList;
    }

    //EFFECTS: returns all the levels of items in inventory
    public ArrayList<String> getInventoryLevels() {
        ArrayList<String> tempList = new ArrayList<>();
        for (Item i: inventoryList) {
            tempList.add("Level " + i.getLevel());
        }
        return tempList;
    }

    //getter
    public int getGold() {
        return gold;
    }

    //setter
    public void setGold(int gold) {
        this.gold = gold;
    }
}
