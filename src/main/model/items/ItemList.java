package model.items;

import java.util.ArrayList;

public class ItemList {
    private final ArrayList<Item> listOfItems;

    //constructor
    public ItemList() {
        listOfItems = new ArrayList<>();
        listOfItems.add(new Axe());
        listOfItems.add(new Dagger());
        listOfItems.add(new Shield());
        listOfItems.add(new Staff());
        listOfItems.add(new Sword());
    }

    //EFFECTS: returns all list of items that exists
    public ArrayList<Item> getListOfItems() {
        return listOfItems;
    }
}
