package model.enemy;

import model.items.Axe;
import model.items.Shield;

public class Berserker extends Enemy {
    public Berserker() {
        super("Berserker", 5,3,5, new Axe());
    }


    @Override
    public String attackLine() {
        return "Berseker cuts you for " + attack + " damage";
    }

    @Override
    public String deathLine() {
        return "Berserker died";
    }


}
