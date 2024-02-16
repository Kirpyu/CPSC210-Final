package model.enemy;

import model.items.Staff;

public class Mage extends Enemy {
    public Mage() {
        super("Mage", 5, 5, 5, new Staff());
    }

    //EFFECTS: returns the line mages state when they attack
    @Override
    public String attackLine() {
        return "Mage burns you for " + attack + " damage";
    }

    //EFFECTS: returns the line mages state when they die
    @Override
    public String deathLine() {
        return "Mage died";
    }
}
