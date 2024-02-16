package model.items;

import java.util.ArrayList;

public class ItemList {
    private final ArrayList<Item> listOfItems; // a list of all items that exist

    //Creates a list of all weapons that exist
    public ItemList() {
        listOfItems = new ArrayList<>();
        listOfItems.add(new Axe());
        listOfItems.add(new Dagger());
        listOfItems.add(new Shield());
        listOfItems.add(new Staff());
        listOfItems.add(new Sword());
    }

    //REQUIRES: i < listOfItems.size()
    //EFFECTS: returns item at given index
    public Item getItem(int i) {
        return listOfItems.get(i);
    }

    //getter
    public ArrayList<Item> getListOfItems() {
        return listOfItems;
    }


}
