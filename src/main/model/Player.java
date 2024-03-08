package model;

import model.enemy.Enemy;
import model.items.Item;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Player {
    private final int maxHealth;
    private int currentHealth;
    private int attack; // base attack
    private int damage; // damage after considering equipped item, damage dealt
    private Item equippedItem;

    // Creates a player with health, damage, and item
    public Player() {
        maxHealth = 20;
        currentHealth = maxHealth;
        attack = 5;
        damage = attack;
        equippedItem = null;
    }

    //REQUIRES: int > 0
    //MODIFIES: enemy
    //EFFECTS: Deducts health from given enemy based on given damage
    public void damageEnemy(Enemy enemy, int damage) {
        enemy.setHealth(enemy.getHealth() - damage);
    }

    //EFFECTS: false if player is alive, else drops gold and item if hero is dead
    public boolean dead() {
        return currentHealth <= 0;
    }

    //EFFECTS: returns stats of player with added dialogue
    public ArrayList<String> getStats() {
        ArrayList<String> tempList = new ArrayList<>();
        tempList.add("Health: " + currentHealth + "/" + maxHealth);
        tempList.add("Attack: " + attack);
        if (equippedItem == null) {
            tempList.add("Equipped Item: None");
        } else {
            tempList.add("Equipped Item: " + equippedItem.getItemName());
        }

        tempList.add("Exit");

        return tempList;
    }

    //REQUIRES: i > 0
    //MODIFIES: this
    //EFFECTS: deducts specified integer from current health
    public void damagePlayer(int i) {
        currentHealth -= i;
    }

    //EFFECTS: creates a json object for player with player stats
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("player", statsToJson());

        return json;

    }

    // EFFECTS: returns player stats as json array
    public JSONArray statsToJson() {
        JSONArray jsonArray = new JSONArray();

        JSONObject jsonHealth = new JSONObject();
        jsonHealth.put("health", currentHealth);
        jsonArray.put(jsonHealth);

        return jsonArray;
    }

    //getters
    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getAttack() {
        return attack;
    }

    public int getDamage() {
        return damage;
    }

    public Item getEquippedItem() {
        return equippedItem;
    }

    //setter
    public void setEquippedItem(Item equippedItem) {
        this.equippedItem = equippedItem;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }
}
