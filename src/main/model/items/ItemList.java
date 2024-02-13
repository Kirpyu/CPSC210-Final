package model.items;

import java.util.ArrayList;

public class ItemList {
    private ArrayList<Item> listOfItems;
    private Item axe;
    private Item dagger;
    private Item shield;
    private Item staff;
    private Item sword;


    public ItemList() {
        listOfItems = new ArrayList<>();

        axe = new Axe();
        dagger = new Dagger();
        shield = new Shield();
        staff = new Staff();
        sword = new Sword();

        listOfItems.add(axe);
        listOfItems.add(dagger);
        listOfItems.add(shield);
        listOfItems.add(staff);
        listOfItems.add(sword);
    }

    public ArrayList<Item> getListOfItems() {
        return listOfItems;
    }
}
