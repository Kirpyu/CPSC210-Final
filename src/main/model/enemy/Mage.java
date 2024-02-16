package model.enemy;

import model.items.Staff;

public class Mage extends Enemy {
    public Mage() {
        super("Mage", 5, 5, 5, new Staff());
    }

    @Override
    public String attackLine() {
        return "Mage burns you for " + attack + " damage";
    }

    @Override
    public String deathLine() {
        return "Mage died";
    }
}
