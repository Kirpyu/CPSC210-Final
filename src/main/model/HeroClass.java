package model;

import model.items.Item;

public abstract class HeroClass {
    protected int health;
    protected int attack;

    public HeroClass(int health, int attack) {
        this.health = health;
        this.attack = attack;
    }

    // getters
    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }

    // setters
    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    //EFFECTS: false if hero is alive, else drops gold and item if hero is dead
    public boolean dead() {
        boolean state = false;
        if (health <= 0) {
            state = true;
            droppedItem();
            droppedGold();
        }
        return state;
    }

    //EFFECTS: returns amount of gold dropped by enemy
    protected abstract int droppedGold();

    //EFFECTS: returns item dropped by enemy
    protected abstract Item droppedItem();


}

