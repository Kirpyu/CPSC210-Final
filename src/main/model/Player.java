package model;

import model.enemy.Enemy;

public class Player {
    private int health;
    private int attack;
    private int damage;

    public Player() {
        health = 20;
        attack = 5;
        damage = attack;
    }

    public void damageEnemy(Enemy enemy, int damage) {
        enemy.setHealth(enemy.getHealth() - damage);
    }

    //EFFECTS: false if player is alive, else drops gold and item if hero is dead
    public boolean dead() {
        boolean state = false;
        if (health <= 0) {
            state = true;
        }
        return state;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

}
