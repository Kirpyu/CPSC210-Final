package model.enemy;

import model.items.Dagger;

public class Rogue extends Enemy {
    public Rogue() {
        super("Rogue", 5, 5, 5, new Dagger());
    }

    @Override
    public String attackLine() {
        return "Rogue stabs you for " + attack + " damage";
    }

    @Override
    public String deathLine() {
        return "Rogue died";
    }
}
