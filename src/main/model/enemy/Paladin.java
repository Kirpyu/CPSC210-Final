package model.enemy;

import model.items.Item;
import model.items.Shield;

public class Paladin extends Enemy {
    public Paladin() {
        super("Paladin", 10,1,5, new Shield());
    }

    //EFFECTS: returns the line paladins state when they attack
    @Override
    public String attackLine() {
        return "Paladin smites you for " + attack + " damage";
    }

    //EFFECTS: returns the line paladins state when they die
    @Override
    public String deathLine() {
        return "Paladin died";
    }


}
