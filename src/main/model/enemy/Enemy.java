package model.enemy;

import model.Player;
import model.items.Item;

public abstract class Enemy {
    protected String name;
    protected int health;
    protected int attack;
    protected int goldDropped;
    protected Item itemDropped;

    public Enemy(String name, int health, int attack, int goldDropped, Item itemDropped) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.goldDropped = goldDropped;
        this.itemDropped = itemDropped;
    }

    public void attackPlayer(Player player, int damage) {

    }

    //randomizes gold dropped, attack, and health
    public void randomizeStats(int wave) {

    }

    public abstract String attackLine();

    public abstract String deathLine();



    //EFFECTS: false if hero is alive, else drops gold and item if hero is dead
    public boolean dead() {
        boolean state = false;
        if (health <= 0) {
            state = true;

            getGoldDropped();
        }
        return state;
    }

    public String getName() {
        return name;
    }

    //setters
    public void setHealth(int health) {
        this.health = health;
    }

    // getters
    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }

    //EFFECTS: returns amount of gold dropped by enemy
    public int getGoldDropped() {
        return goldDropped;
    }

    //EFFECTS: returns item dropped by enemy
    public Item getItemDropped() {
        return itemDropped;
    }


}

