package model.enemy;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

//creates an enemylist class that holds enemies
public class EnemyList {
    private final ArrayList<Enemy> currentEnemies; // holds enemies currently in the wave
    private final ArrayList<String> listOfEnemies; // holds all enemies that exist

    //Adds a string of all enemies that exists to listOfEnemies
    public EnemyList() {
        currentEnemies = new ArrayList<>();
        listOfEnemies = new ArrayList<>();
        listOfEnemies.add("Paladin");
        listOfEnemies.add("Rogue");
        listOfEnemies.add("Mage");
        listOfEnemies.add("Warrior");
        listOfEnemies.add("Berserker");
    }

    //REQUIRES: enemy is one of enemies that exist
    //MODIFIES: this
    //EFFECTS: instantiates a given enemy
    public void addEnemy(String enemy) {
        switch (enemy) {
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

    //REQUIRES: enemy != null and is in currentEnemies
    //MODIFIES: this
    //EFFECTS: removes a selected enemy
    public void removeEnemy(Enemy enemy) {
        currentEnemies.remove(enemy);
    }

    //MODIFIES: this
    //EFFECTS: removes all enemies in list
    public void removeAllEnemies() {
        currentEnemies.clear();
    }

    //REQUIRES: i < currentEnemies.size
    //EFFECTS: returns selected enemy
    public Enemy getCurrentEnemy(int i) {
        return currentEnemies.get(i);
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

    // EFFECTS: returns all enemies current hp with HP added to the end
    public ArrayList<String> getEnemiesHealth() {
        ArrayList<String> tempList = new ArrayList<>();

        for (Enemy e: currentEnemies) {
            tempList.add(e.getHealth() + "HP");
        }

        return tempList;
    }

    // EFFECTS: returns all enemies current hp and attack with HP and ATK string added to the end
    public ArrayList<String> getEnemiesStats() {
        ArrayList<String> tempList = new ArrayList<>();

        for (Enemy e: currentEnemies) {
            tempList.add(e.getHealth() + "HP " + e.getAttack() + "ATK");
        }

        return tempList;
    }

    // EFFECTS: returns an json object with current enemies
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("enemies", enemyToJson());
        return json;

    }

    //EFFECTS: returns all current enemies as json array
    public JSONArray enemyToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Enemy e : currentEnemies) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }

    //getter
    public ArrayList<Enemy> getCurrentEnemies() {
        return currentEnemies;
    }

    public ArrayList<String> getListOfEnemies() {
        return listOfEnemies;
    }

    public String getEnemy(int i) {
        return listOfEnemies.get(i);
    }
}
