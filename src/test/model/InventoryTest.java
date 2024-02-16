package model;

import model.items.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class InventoryTest {
    Inventory inventory;

    @BeforeEach
    public void beforeEach() {
        inventory = new Inventory();

    }

    @Test
    public void inventoryTest() {
        ArrayList<Item> tempList = new ArrayList<>();
        assertEquals(tempList, inventory.getInventory());
    }

    @Test
    public void addInventoryTest() {
        inventory.addInventory(new Axe());
        assertEquals("Axe", inventory.getInventory().get(0).getItemName());
        assertEquals(1, inventory.getInventory().size());

        inventory.addInventory(new Dagger());
        assertEquals("Dagger", inventory.getInventory().get(1).getItemName());
        assertEquals(2, inventory.getInventory().size());
    }

    @Test
    public void addGoldTest() {
        assertEquals(0, inventory.getGold());

        inventory.addGold(5);
        assertEquals(5, inventory.getGold());

        inventory.addGold(20);
        assertEquals(25, inventory.getGold());
    }

    @Test
    public void getInventoryTest() {
        ArrayList<Item> tempList = new ArrayList<>();
        assertEquals(tempList, inventory.getInventory());

        inventory.addInventory(new Axe());
        assertEquals("Axe", inventory.getInventory().get(0).getItemName());
        assertEquals(1, inventory.getInventory().size());

        inventory.addInventory(new Dagger());
        assertEquals("Dagger", inventory.getInventory().get(1).getItemName());
        assertEquals(2, inventory.getInventory().size());
    }

    @Test
    public void getInventoryNamesTest() {
        ArrayList<String> tempList = new ArrayList<>();
        tempList.add("Gold: 0");
        tempList.add("Exit");
        assertEquals(tempList, inventory.getInventoryNames());

        tempList.remove("Gold: 0");
        tempList.remove("Exit");
        inventory.addGold(5);
        tempList.add("Gold: 5");
        tempList.add("Exit");
        assertEquals(tempList, inventory.getInventoryNames());

        tempList.remove("Gold: 5");
        tempList.remove("Exit");
        inventory.addInventory(new Axe());
        tempList.add("Axe");
        tempList.add("Gold: 5");
        tempList.add("Exit");
        assertEquals(tempList, inventory.getInventoryNames());

        tempList.remove("Gold: 5");
        tempList.remove("Exit");
        inventory.addInventory(new Staff());
        tempList.add("Staff");
        tempList.add("Gold: 5");
        tempList.add("Exit");
        assertEquals(tempList, inventory.getInventoryNames());

        inventory.addGold(10);
        tempList.remove("Gold: 5");
        tempList.remove("Exit");
        tempList.add("Gold: 15");
        tempList.add("Exit");
        assertEquals(tempList, inventory.getInventoryNames());
    }

    @Test
    public void getAbilityNamesTest() {
        ArrayList<String> tempList = new ArrayList<>();
        tempList.add("Punch");
        assertEquals(tempList, inventory.getAbilityNames());

        inventory.addInventory(new Sword());
        tempList.add("Strike");
        assertEquals(tempList, inventory.getAbilityNames());

        inventory.addInventory(new Staff());
        tempList.add("Cast");
        assertEquals(tempList, inventory.getAbilityNames());

    }

    @Test
    public void getInventoryLevelsTest() {
        ArrayList<String> tempList = new ArrayList<>();
        tempList.add("Level 1");
        inventory.addInventory(new Shield());
        assertEquals(tempList, inventory.getInventoryLevels());

        Item dagger = new Dagger();
        inventory.addInventory(dagger);
        tempList.add("Level 1");
        assertEquals(tempList, inventory.getInventoryLevels());

        tempList.remove("Level 1");
        dagger.addLevel();
        tempList.add("Level 2");
        assertEquals(tempList, inventory.getInventoryLevels());
        assertEquals(2, inventory.getInventoryLevels().size());
    }

    @Test
    public void getGoldTest() {
        assertEquals(0, inventory.getGold());
        inventory.addGold(10);
        assertEquals(10, inventory.getGold());
        inventory.addGold(25);
        assertEquals(35, inventory.getGold());
    }

    @Test
    public void setGoldTest() {
        assertEquals(0, inventory.getGold());
        inventory.setGold(15);
        assertEquals(15, inventory.getGold());
        inventory.setGold(39);
        assertEquals(39, inventory.getGold());
    }
}
