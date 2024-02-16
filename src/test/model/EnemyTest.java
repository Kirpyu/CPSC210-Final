package model;

import model.enemy.Enemy;
import model.enemy.Paladin;
import model.enemy.Rogue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {
    private Enemy enemy1;
    private Enemy enemy2;

    @BeforeEach
    public void beforeEach() {
        enemy1 = new Paladin();
        enemy2 = new Rogue();
    }

    @Test
    public void deadTest() {
        assertFalse(enemy1.dead());
        enemy1.setHealth(1);
        assertFalse(enemy1.dead());
        enemy1.setHealth(0);
        assertTrue(enemy1.dead());

        assertFalse(enemy2.dead());
        enemy2.setHealth(0);
        assertTrue(enemy2.dead());
    }

    @Test
    public void setHealth() {
        enemy1.setHealth(1);
        assertEquals(1, enemy1.getHealth());
        enemy1.setHealth(5);
        assertEquals(5, enemy1.getHealth());
    }

    @Test
    public void setAttackTest() {
        enemy1.setAttack(6);
        assertEquals(6, enemy1.getAttack());
        enemy1.setAttack(3);
        assertEquals(3, enemy1.getAttack());

    }

    @Test
    public void setGoldDroppedTest() {
        assertEquals(5, enemy1.getGoldDropped());
        enemy2.setGoldDropped(7);
        assertEquals(7, enemy2.getGoldDropped());
        enemy2.setGoldDropped(1);
        assertEquals(1, enemy2.getGoldDropped());
    }

    @Test
    public void getAttack() {
        assertEquals(1, enemy1.getAttack());
        assertEquals(5, enemy2.getAttack());

    }

    @Test
    public void getHealth() {
        assertEquals(10, enemy1.getHealth());
        assertEquals(5, enemy2.getHealth());
    }

    @Test
    public void getGoldDropped() {
        assertEquals(5, enemy1.getGoldDropped());
        enemy2.setGoldDropped(7);
        assertEquals(7, enemy2.getGoldDropped());
    }

    @Test
    public void getItemDropped() {
        assertEquals("Shield", enemy1.getItemDropped().getItemName());
        assertEquals("Dagger", enemy2.getItemDropped().getItemName());
    }

    @Test
    public void getName() {
        assertEquals("Paladin", enemy1.getName());
        assertEquals("Rogue", enemy2.getName());
    }
}
