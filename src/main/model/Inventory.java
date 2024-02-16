package model;

import model.items.Item;

import java.util.ArrayList;

// keeps track of inventory
public class Inventory {
    private int gold; // amount of gold in inventory
    private final ArrayList<Item> inventoryList; // keeps track of the inventory

    //Creates an inventory
    public Inventory() {
        inventoryList = new ArrayList<>();
    }

    //REQUIRES: item != null
    //MODIFIES: this, item
    //EFFECTS: if item is already inventory, adds a level to the item,
    // otherwise adds the item to inventory
    public void addInventory(Item item) {

        if (!getInventoryNames().contains(item.getItemName())) {
            inventoryList.add(item);
        } else {
            addItemLevel(item);
        }

    }

    //REQUIRES: item != null
    //MODIFIES: item
    //EFFECTS: add a level to an item if found in inventory list
    public void addItemLevel(Item item) {
        for (Item i:inventoryList) {
            if (i.getItemName().equals(item.getItemName())) {
                i.addLevel();
                break;
            }
        }
    }

    // REQUIRES: gold > 0
    // MODIFIES: this
    // EFFECTS: adds given amount to gold
    public void addGold(int gold) {
        this.gold += gold;
    }

    //EFFECTS: returns list of item names in inventory with added dialogue
    public ArrayList<String> getInventoryNames() {
        ArrayList<String> tempList = new ArrayList<>();
        for (Item i: inventoryList) {
            tempList.add(i.getItemName());
        }
        tempList.add("Gold: " + gold);
        tempList.add("Exit");
        return tempList;
    }

    //EFFECTS: returns list of ability names from items in inventory with punch
    public ArrayList<String> getAbilityNames() {
        ArrayList<String> tempList = new ArrayList<>();
        tempList.add("Punch");
        for (Item i: inventoryList) {
            tempList.add(i.getAbilityName());
        }
        return tempList;
    }

    //EFFECTS: returns all the levels of items in inventory with Level in front
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

    public ArrayList<Item> getInventory() {
        return inventoryList;
    }

    //setter
    public void setGold(int gold) {
        this.gold = gold;
    }
}
