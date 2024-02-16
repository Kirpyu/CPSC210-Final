package model;

import model.enemy.Enemy;
import model.items.Item;

import java.util.ArrayList;

public class Player {
    private final int maxHealth;
    private int currentHealth;
    private int attack;
    private int damage;
    private Item equippedItem;

    public Player() {
        maxHealth = 20;
        currentHealth = maxHealth;
        attack = 5;
        damage = attack;
        equippedItem = null;
    }

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

    //MODIFIES: this
    //EFFECTS: deducts specified integer from current health
    public void damagePlayer(int i) {
        currentHealth -= i;
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
}
