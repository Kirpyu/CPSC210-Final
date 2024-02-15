package model.enemy;

import model.items.Item;
import model.items.Shield;

public class Paladin extends Enemy {
    public Paladin() {
        super("Paladin", 10,1,5, new Shield());
    }


    @Override
    public String attackLine() {
        return "Paladin smited you for " + attack + " damage";
    }

    @Override
    public String deathLine() {
        return "Paladin Died";
    }


}
