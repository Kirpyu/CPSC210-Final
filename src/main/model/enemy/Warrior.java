package model.enemy;

import model.items.Sword;

public class Warrior extends Enemy {
    public Warrior() {
        super("Warrior", 10,2,5, new Sword());
    }


    //EFFECTS: returns the lines warriors state when they attack
    @Override
    public String attackLine() {
        return "Warrior slashes you for " + attack + " damage";
    }

    //EFFECTS: returns the line warriors state when they die
    @Override
    public String deathLine() {
        return "Warrior died";
    }


}
