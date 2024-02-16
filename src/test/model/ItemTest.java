package model;

import model.items.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemTest {
    private Item item;

    @BeforeEach
    public void beforeEach() {
        item = new Item("Axe", "Cut", 0, 5);
    }

    @Test
    public void itemTest() {

        assertEquals("Axe", item.getItemName());
        assertEquals("Cut", item.getAbilityName());
        assertEquals(0, item.getLevel());
        assertEquals(5, item.getDamage());
    }

    @Test
    public void getDamage() {
        assertEquals(5, item.getDamage());
    }

    @Test
    public void getLevel() {
        assertEquals(0, item.getLevel());
    }

    @Test
    public void getAbilityName() {
        assertEquals("Cut", item.getAbilityName());
    }

    @Test
    public void getItemName() {
        assertEquals("Axe", item.getItemName());
    }

    @Test
    public void getCost() {
        item.setCost(5);
        assertEquals(5, item.getCost());
    }

    @Test
    public void setCost() {
        item.setCost(7);
        assertEquals(7, item.getCost());
    }

    @Test
    public void setAbilityName() {
        item.setAbilityName("Slash");
        assertEquals("Slash", item.getAbilityName());
    }

    @Test
    public void setDamage() {
        item.setDamage(20);
        assertEquals(20, item.getDamage());
    }




}