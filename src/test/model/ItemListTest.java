package model;

import model.items.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemListTest {
    ItemList itemList;

    @BeforeEach
    public void beforeEach() {
        itemList = new ItemList();
    }

    @Test
    public void itemListTest() {
        assertEquals("Axe", itemList.getItem(0).getItemName());
        assertEquals("Dagger", itemList.getItem(1).getItemName());
        assertEquals("Shield", itemList.getItem(2).getItemName());
        assertEquals("Staff", itemList.getItem(3).getItemName());
        assertEquals("Sword", itemList.getItem(4).getItemName());
    }

    @Test
    public void getItemTest() {
        assertEquals("Axe", itemList.getItem(0).getItemName());
        assertEquals("Staff", itemList.getItem(3).getItemName());
        assertEquals("Sword", itemList.getItem(4).getItemName());
    }

    @Test
    public void getListOfItemsTest() {
        assertEquals("Axe", itemList.getListOfItems().get(0).getItemName());
        assertEquals("Dagger", itemList.getListOfItems().get(1).getItemName());
        assertEquals("Shield", itemList.getListOfItems().get(2).getItemName());
        assertEquals("Staff", itemList.getListOfItems().get(3).getItemName());
        assertEquals("Sword", itemList.getListOfItems().get(4).getItemName());
    }
}
