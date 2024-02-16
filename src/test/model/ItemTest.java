package model;

import model.items.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemTest {
    private Item item;

    @Test
    public void itemTest() {
        item = new Item("Axe", "Cut", 0, 5);
        assertEquals("Axe", item.getItemName());
        assertEquals("Cut", item.getAbilityName());
        assertEquals(0, item.getLevel());
        assertEquals(5, item.getDamage());
    }




}