package model.enemy;

import java.util.ArrayList;
import java.util.Random;

public class EnemyList {
    private ArrayList<Enemy> currentEnemies;
    private ArrayList<String> listOfEnemies;

    //constructor
    public EnemyList() {
        currentEnemies = new ArrayList<>();
        listOfEnemies = new ArrayList<>();
        listOfEnemies.add("Paladin");
        listOfEnemies.add("Rogue");
        listOfEnemies.add("Mage");
        listOfEnemies.add("Warrior");
        listOfEnemies.add("Berserker");
    }

    //MODIFIES: this
    //EFFECTS: instantiates a certain amount of random enemies
    public void createEnemies(int amount) {
        for (int i = amount; i > 0; i--) {
            Random random = new Random();
            String randomEnemy = listOfEnemies.get(random.nextInt(5));

            switch (randomEnemy) {
                case "Paladin":
                    currentEnemies.add(new Paladin());
                    break;
                case "Rogue":
                    currentEnemies.add(new Rogue());
                    break;
                case "Mage":
                    currentEnemies.add(new Mage());
                    break;
                case "Warrior":
                    currentEnemies.add(new Warrior());
                    break;
                case "Berserker":
                    currentEnemies.add(new Berserker());
                    break;
            }
        }
        randomizeEnemyStats();
    }

    //MODIFIES: this
    //EFFECTS: removes a selected enemy
    public void removeEnemy(Enemy enemy) {
        currentEnemies.remove(enemy);
    }

    //EFFECTS: returns selected enemy
    public Enemy getEnemy(int i) {
        return currentEnemies.get(i);
    }

    //EFFECTS: randomizes all enemy stats
    public void randomizeEnemyStats() {

    }

    //EFFECTS: returns a list of string of all enemies
    public ArrayList<String> getEnemyNames() {
        ArrayList<String> tempList = new ArrayList<>();

        for (Enemy e: currentEnemies) {
            tempList.add(e.getName());
        }

        tempList.add("Exit");
        return tempList;
    }

    public ArrayList<String> getEnemiesHealth() {
        ArrayList<String> tempList = new ArrayList<>();

        for (Enemy e: currentEnemies) {
            tempList.add(e.getHealth() + "HP");
        }

        return tempList;
    }

    //getter
    public ArrayList<Enemy> getEnemyList() {
        return currentEnemies;
    }


}
