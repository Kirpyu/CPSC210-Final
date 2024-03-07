package model.enemy;

import model.items.Item;
import org.json.JSONObject;

public abstract class Enemy {
    protected String name;
    protected int health;
    protected int attack;
    protected int goldDropped;
    protected Item itemDropped;

    //Creates an enemy
    public Enemy(String name, int health, int attack, int goldDropped, Item itemDropped) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.goldDropped = goldDropped;
        this.itemDropped = itemDropped;
    }

//    //randomizes gold dropped, attack, and health
//    public void randomizeStats(int wave) {
//
//    }

    //EFFECTS: returns the line enemies state when they attack
    public abstract String attackLine();

    //EFFECTS: returns the line enemies state when they die
    public abstract String deathLine();

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("health", health);
        json.put("attack", attack);
        json.put("gold", goldDropped);
        json.put("item", itemDropped);
        return json;
    }



    //EFFECTS: false if hero is alive, else drops gold and item if hero is dead
    public boolean dead() {
        return health <= 0;
    }

    //setters
    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setGoldDropped(int goldDropped) {
        this.goldDropped = goldDropped;
    }

    // getters
    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }

    public int getGoldDropped() {
        return goldDropped;
    }

    public Item getItemDropped() {
        return itemDropped;
    }

    public String getName() {
        return name;
    }


}

