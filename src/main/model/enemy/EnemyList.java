package model.enemy;

import java.util.ArrayList;

public class EnemyList {
    private ArrayList<Enemy> enemyList;

    public EnemyList() {
        enemyList = new ArrayList<>();
        randomizeEnemyStats();
    }

    //MODIFIES: this
    //EFFECTS: instantiates enemies
    public void createEnemies() {
        enemyList.add(new Paladin());
        enemyList.add(new Rogue());
    }

    //MODIFIES: this
    //EFFECTS: removes a selected enemy
    public void removeEnemy(Enemy enemy) {
        enemyList.remove(enemy);
    }

    //EFFECTS: returns selected enemy
    public Enemy getEnemy(int i) {
        return enemyList.get(i);
    }

    //EFFECTS: randomizes all enemy stats
    public void randomizeEnemyStats() {

    }

    //EFFECTS: returns a list of string of all enemies
    public ArrayList<String> getEnemyNames() {
        ArrayList<String> tempList = new ArrayList<>();

        for (Enemy e: enemyList) {
            tempList.add(e.getName());
        }

        tempList.add("Exit");
        return tempList;
    }

    //getter
    public ArrayList<Enemy> getEnemyList() {
        return enemyList;
    }


}
