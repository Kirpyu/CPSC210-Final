package model;

import model.items.Item;
import model.items.ItemList;

import java.util.ArrayList;

public class Shop {
    private final ArrayList<Item> shopList;
    private final ItemList itemList;

    //constructor
    public Shop() {
        shopList = new ArrayList<>();
        itemList = new ItemList();

        displayItem();

    }

    //MODIFIES: this
    //EFFECTS: adds items to the shop
    public ArrayList<Item> displayItem() {
        for (Item i : itemList.getListOfItems()) {
            shopList.add(i);
        }

        return shopList;
    }

    //EFFECTS: returns a list of all the costs of items in the shop
    public ArrayList<String> getShopListCosts() {
        ArrayList<String> tempList = new ArrayList<>();
        for (Item i : itemList.getListOfItems()) {
            tempList.add(i.getCost() + "G");
        }

        return tempList;
    }

    //EFFECTS: returns all item names currently in the shop
    public ArrayList<String> getShopListNames() {
        ArrayList<String> tempList = new ArrayList<>();
        for (Item i : itemList.getListOfItems()) {
            tempList.add(i.getItemName());
        }
        tempList.add("Exit");

        return tempList;
    }

    //EFFECTS: checks if gold is sufficient to purchase item
    public boolean canPurchase() {
        return false;
    }

    //EFFECTS: returns item at given integer in current shop
    public Item purchaseItem(int i) {
        return getShopList().get(i);
    }

    //EFFECTS: returns items in the shop
    public ArrayList<Item> getShopList() {
        return shopList;
    }
}
