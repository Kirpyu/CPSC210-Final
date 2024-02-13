package model;

import model.items.Axe;
import model.items.Item;
import model.items.ItemList;

import java.util.ArrayList;

public class Shop {
    private ArrayList<Item> shopList;
    private ItemList itemList;

    public Shop() {
        shopList = new ArrayList<>();
        itemList = new ItemList();
        displayItem();

    }

    //placeholder, gets 3 items out of given list in final ver
    public ArrayList<Item> displayItem() {
        for (Item i : itemList.getListOfItems()) {
            shopList.add(i);
        }

        return shopList;
    }

    public ArrayList<String> getShopListNames() {
        ArrayList<String> tempList = new ArrayList<>();
        for (Item i : itemList.getListOfItems()) {
            tempList.add(i.getAbilityName());
        }
        tempList.add("Exit");

        return tempList;
    }

    public ArrayList<Item> getShopList() {
        return shopList;
    }
}
