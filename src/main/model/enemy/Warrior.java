package model.enemy;

import model.items.Sword;

public class Warrior extends Enemy {
    public Warrior() {
        super("Warrior", 10,2,5, new Sword());
    }


    @Override
    public String attackLine() {
        return "Warrior slashes you for " + attack + " damage";
    }

    @Override
    public String deathLine() {
        return "Warrior died";
    }


}
