package model;

import model.enemy.Enemy;
import model.enemy.Paladin;
import model.items.Axe;
import model.items.Dagger;
import model.items.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {
    private Player player;

    @BeforeEach
    public void beforeEach() {
        player = new Player();

    }

    @Test
    public void playerTest() {
        assertEquals(20, player.getMaxHealth());
        assertEquals(20, player.getCurrentHealth());
        assertEquals(5, player.getAttack());
        assertEquals(5, player.getDamage());
        assertEquals(null, player.getEquippedItem());

    }

    @Test
    public void damageEnemyTest() {
        Enemy dummy = new Paladin();
        assertEquals(10, dummy.getHealth());
        player.damageEnemy(dummy, 7);
        assertEquals(3, dummy.getHealth());
        player.damageEnemy(dummy, 1);
        assertEquals(2, dummy.getHealth());
    }

    @Test
    public void deadTest() {
        assertEquals(false, player.dead());
        player.damagePlayer(6);
        assertEquals(false, player.dead());
        player.damagePlayer(13);
        assertEquals(false, player.dead());
        player.damagePlayer(1);
        assertEquals(true, player.dead());
    }

    @Test
    public void getStatsTest() {
        ArrayList<String> tempList = new ArrayList<>();
        tempList.add("Health: 20/20");
        tempList.add("Attack: 5");
        tempList.add("Equipped Item: None");
        tempList.add("Exit");

        assertEquals(tempList, player.getStats());
        tempList.removeAll(tempList);
        tempList.add("Health: 15/20");
        tempList.add("Attack: 7");
        tempList.add("Equipped Item: Axe");
        tempList.add("Exit");
        player.damagePlayer(5);
        player.setAttack(7);
        player.setEquippedItem(new Axe());
        assertEquals(tempList, player.getStats());

    }

    @Test
    public void damagePlayerTest() {
        assertEquals(20, player.getCurrentHealth());
        player.damagePlayer(6);
        assertEquals(14, player.getCurrentHealth());
        player.damagePlayer(13);
        assertEquals(1, player.getCurrentHealth());
        player.damagePlayer(1);
        assertEquals(0, player.getCurrentHealth());
    }

    @Test
    public void getCurrentHealthTest() {
        assertEquals(20, player.getCurrentHealth());
        player.damagePlayer(6);
        assertEquals(14, player.getCurrentHealth());
        player.damagePlayer(13);
        assertEquals(1, player.getCurrentHealth());
        player.damagePlayer(1);
        assertEquals(0, player.getCurrentHealth());
    }

    @Test
    public void getMaxHealthTest() {
        assertEquals(20, player.getMaxHealth());
        player.damagePlayer(6);
        assertEquals(20, player.getMaxHealth());
        player.damagePlayer(13);
        assertEquals(20, player.getMaxHealth());
        player.damagePlayer(1);
        assertEquals(20, player.getMaxHealth());

    }

    @Test
    public void getDamageTest() {
        assertEquals(5, player.getDamage());
        player.setDamage(7);
        assertEquals(7, player.getDamage());
        player.setDamage(3);
        assertEquals(3, player.getDamage());
    }

    @Test
    public void getEquippedItemTest() {
        assertEquals(null, player.getEquippedItem());

        Item axe = new Axe();
        player.setEquippedItem(axe);
        assertEquals(axe, player.getEquippedItem());

        Item dagger = new Dagger();
        player.setEquippedItem(dagger);
        assertEquals(dagger, player.getEquippedItem());
    }

    @Test
    public void getAttackTest() {
        assertEquals(5, player.getAttack());
        player.setAttack(8);
        assertEquals(8, player.getAttack());
        player.setAttack(1);
        assertEquals(1, player.getAttack());

    }

    @Test
    public void setEquippedItemTest() {
        assertEquals(null, player.getEquippedItem());

        Item axe = new Axe();
        player.setEquippedItem(axe);
        assertEquals(axe, player.getEquippedItem());

        Item dagger = new Dagger();
        player.setEquippedItem(dagger);
        assertEquals(dagger, player.getEquippedItem());

    }

    @Test
    public void setAttackTest() {
        assertEquals(5, player.getAttack());
        player.setAttack(8);
        assertEquals(8, player.getAttack());
        player.setAttack(1);
        assertEquals(1, player.getAttack());
    }

    @Test
    public void setDamageTest() {
        assertEquals(5, player.getDamage());
        player.setDamage(7);
        assertEquals(7, player.getDamage());
        player.setDamage(3);
        assertEquals(3, player.getDamage());

    }
}
