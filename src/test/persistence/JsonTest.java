package persistence;

import model.Inventory;
import model.Player;
import model.enemy.Enemy;
import model.enemy.EnemyList;
import model.items.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkEnemyList(String name, int health, Enemy enemy) {
        assertEquals(name, enemy.getName());
        assertEquals(health, enemy.getHealth());
    }

    protected void checkPlayer(int health, Player player) {
        assertEquals(health, player.getCurrentHealth());

    }

    protected void checkInventory(String name, int level, String ability, int damage, Item item) {
        assertEquals(name, item.getItemName());
        assertEquals(level, item.getLevel());
        assertEquals(ability, item.getAbilityName());
        assertEquals(damage, item.getDamage());
    }

    protected void checkGold(int gold, Inventory inventory) {
        assertEquals(gold, inventory.getGold());
    }
}
