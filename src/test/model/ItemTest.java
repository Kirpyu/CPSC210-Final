package model;

import model.items.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemTest {
    private ItemList itemList;
    private ArrayList<Item> listOfWeapons;
    @BeforeEach
    public void runBefore() {
        itemList = new ItemList();
        listOfWeapons = new ArrayList<>();
        listOfWeapons.add(new Axe());
        listOfWeapons.add(new Dagger());
        listOfWeapons.add(new Shield());
        listOfWeapons.add(new Staff());
        listOfWeapons.add(new Sword());
    }

//    @Test
//    public void getListOfItemsTest() {
//        assertEquals(listOfWeapons, itemList.getListOfItems());
//    }
//
//    @Test
//    public void getItemNameTest() {
//        for (Item i: listOfWeapons) {
//            assertEquals(i.getItemName(), "Axe");
//        }
//    }
}