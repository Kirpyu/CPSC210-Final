package model;

import model.enemy.Enemy;
import model.enemy.EnemyList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnemyListTest {
    private EnemyList enemyList;

    @BeforeEach
    public void beforeEach() {
        enemyList = new EnemyList();
    }

    @Test
    public void enemyListTest() {
        assertEquals("Paladin", enemyList.getEnemy(0));
        assertEquals("Rogue", enemyList.getEnemy(1));
        assertEquals("Mage", enemyList.getEnemy(2));
        assertEquals("Warrior", enemyList.getEnemy(3));
        assertEquals("Berserker", enemyList.getEnemy(4));
    }

    @Test
    public void addEnemyTest() {
        enemyList.addEnemy("Paladin");
        assertEquals("Paladin", enemyList.getCurrentEnemy(0).getName());
        enemyList.addEnemy("Rogue");
        assertEquals("Rogue", enemyList.getCurrentEnemy(1).getName());
        enemyList.addEnemy("Mage");
        assertEquals("Mage", enemyList.getCurrentEnemy(2).getName());
        enemyList.addEnemy("Warrior");
        assertEquals("Warrior", enemyList.getCurrentEnemy(3).getName());
        enemyList.addEnemy("Berserker");
        assertEquals("Berserker", enemyList.getCurrentEnemy(4).getName());
    }


    @Test
    public void removeEnemyTest() {
        enemyList.addEnemy("Paladin");
        assertEquals("Paladin", enemyList.getCurrentEnemy(0).getName());
        enemyList.removeEnemy(enemyList.getCurrentEnemy(0));
        assertEquals(new ArrayList<Enemy>(), enemyList.getCurrentEnemies());

        enemyList.addEnemy("Rogue");
        enemyList.addEnemy("Mage");
        enemyList.removeEnemy(enemyList.getCurrentEnemy(1));
        assertEquals("Rogue", enemyList.getCurrentEnemy(0).getName());
        assertEquals(1, enemyList.getCurrentEnemies().size());
    }

    @Test
    public void getCurrentEnemyTest() {
        enemyList.addEnemy("Paladin");
        assertEquals("Paladin", enemyList.getCurrentEnemy(0).getName());
        enemyList.addEnemy("Rogue");
        assertEquals("Rogue", enemyList.getCurrentEnemy(1).getName());
    }

    @Test
    public void getEnemyNamesTest() {
        ArrayList<String> tempList = new ArrayList<>();
        tempList.add("Exit");
        assertEquals(tempList, enemyList.getEnemyNames());
        assertEquals(1, enemyList.getEnemyNames().size());

        tempList.remove(0);
        enemyList.addEnemy("Warrior");
        tempList.add("Warrior");
        tempList.add("Exit");
        assertEquals(tempList, enemyList.getEnemyNames());

        tempList.remove(1);
        enemyList.addEnemy("Berserker");
        tempList.add("Berserker");
        tempList.add("Exit");
        assertEquals(tempList, enemyList.getEnemyNames());
        assertEquals(3, enemyList.getEnemyNames().size());
    }

    @Test
    public void getEnemiesHealthTest() {
        ArrayList<String> tempList = new ArrayList<>();
        assertEquals(tempList, enemyList.getEnemiesHealth());

        enemyList.addEnemy("Warrior");
        tempList.add("10HP");
        assertEquals(tempList, enemyList.getEnemiesHealth());

        enemyList.addEnemy("Rogue");
        tempList.add("5HP");
        assertEquals(tempList, enemyList.getEnemiesHealth());
        assertEquals(2, enemyList.getEnemiesHealth().size());


    }

    @Test
    public void getCurrentEnemiesTest() {
        ArrayList<Enemy> tempList = new ArrayList<>();
        assertEquals(tempList, enemyList.getCurrentEnemies());
        assertEquals(0, enemyList.getCurrentEnemies().size());

        enemyList.addEnemy("Mage");
        assertEquals("Mage", enemyList.getCurrentEnemies().get(0).getName());
        assertEquals(1, enemyList.getCurrentEnemies().size());

        enemyList.addEnemy("Rogue");
        assertEquals("Mage", enemyList.getCurrentEnemies().get(0).getName());
        assertEquals("Rogue", enemyList.getCurrentEnemies().get(1).getName());
        assertEquals(2, enemyList.getCurrentEnemies().size());

    }

    @Test
    public void getListOfEnemiesTest() {
        ArrayList<String> tempList = new ArrayList<>();
        tempList.add("Paladin");
        tempList.add("Rogue");
        tempList.add("Mage");
        tempList.add("Warrior");
        tempList.add("Berserker");
        assertEquals(tempList, enemyList.getListOfEnemies());
    }

    @Test
    public void getEnemyTest() {
        assertEquals("Paladin", enemyList.getEnemy(0));
        assertEquals("Warrior", enemyList.getEnemy(3));
    }
}
