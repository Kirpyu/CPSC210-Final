package model.enemy;

import model.items.Dagger;

//creates a default rogue enemy
public class Rogue extends Enemy {
    public Rogue() {
        super("Rogue", 5, 5, 5, new Dagger());
    }

    //EFFECTS: returns the line rogues state when they attack
    @Override
    public String attackLine() {
        return "Rogue stabs you for " + attack + " damage";
    }

    //EFFECTS: returns the line rogues state when they die
    @Override
    public String deathLine() {
        return "Rogue died";
    }
}
