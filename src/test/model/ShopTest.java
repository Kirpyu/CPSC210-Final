package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShopTest {
    Shop shop;

    @BeforeEach
    public void beforeEach() {
        shop = new Shop();
    }

    @Test
    public void shopTest() {
        assertEquals("Axe", shop.getShopList().get(0).getItemName());
        assertEquals("Dagger", shop.getShopList().get(1).getItemName());
        assertEquals("Shield", shop.getShopList().get(2).getItemName());
        assertEquals("Staff", shop.getShopList().get(3).getItemName());
        assertEquals("Sword", shop.getShopList().get(4).getItemName());

        assertEquals("Axe", shop.getItemList().getItem(0).getItemName());
        assertEquals("Dagger", shop.getItemList().getItem(1).getItemName());
        assertEquals("Shield", shop.getItemList().getItem(2).getItemName());
        assertEquals("Staff", shop.getItemList().getItem(3).getItemName());
        assertEquals("Sword", shop.getItemList().getItem(4).getItemName());
    }

    @Test
    public void displayItemTest() {
        assertEquals("Axe", shop.getShopList().get(0).getItemName());
        assertEquals("Dagger", shop.getShopList().get(1).getItemName());
        assertEquals("Shield", shop.getShopList().get(2).getItemName());
        assertEquals("Staff", shop.getShopList().get(3).getItemName());
        assertEquals("Sword", shop.getShopList().get(4).getItemName());

        shop.displayItem();

        assertEquals("Axe", shop.getShopList().get(0).getItemName());
        assertEquals("Dagger", shop.getShopList().get(1).getItemName());
        assertEquals("Shield", shop.getShopList().get(2).getItemName());
        assertEquals("Staff", shop.getShopList().get(3).getItemName());
        assertEquals("Sword", shop.getShopList().get(4).getItemName());

    }

    @Test
    public void getShopListCostsTest() {
        assertEquals("0G", shop.getShopListCosts().get(0));
        assertEquals("0G", shop.getShopListCosts().get(1));
        assertEquals("0G", shop.getShopListCosts().get(2));
        assertEquals("0G", shop.getShopListCosts().get(3));
        assertEquals("0G", shop.getShopListCosts().get(4));
    }

    @Test
    public void getShopListNamesTest() {
        assertEquals("Axe", shop.getShopListNames().get(0));
        assertEquals("Dagger", shop.getShopListNames().get(1));
        assertEquals("Shield", shop.getShopListNames().get(2));
        assertEquals("Staff", shop.getShopListNames().get(3));
        assertEquals("Sword", shop.getShopListNames().get(4));
    }

    @Test
    public void canPurchaseTest() {
        assertTrue(shop.canPurchase(5, 3));
        assertTrue(shop.canPurchase(7, 7));
        assertFalse(shop.canPurchase(4, 5));

    }

    @Test
    public void getItemTest() {
        assertEquals("Axe", shop.getItem(0).getItemName());
        assertEquals("Staff", shop.getItem(3).getItemName());
    }

    @Test
    public void getShopListTest() {
        assertEquals("Axe", shop.getShopList().get(0).getItemName());
        assertEquals("Dagger", shop.getShopList().get(1).getItemName());
        assertEquals("Shield", shop.getShopList().get(2).getItemName());
        assertEquals("Staff", shop.getShopList().get(3).getItemName());
        assertEquals("Sword", shop.getShopList().get(4).getItemName());
    }
}
