package model.enemy;

import model.items.Dagger;

public class Rogue extends Enemy {
    public Rogue() {
        super("Rogue", 5, 5, 5, new Dagger());
    }

    @Override
    public String attackLine() {
        return "Rogue stabbed you for " + attack + " damage";
    }

    @Override
    public String deathLine() {
        return "Rogue Died";
    }
}
