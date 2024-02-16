package model.tests;

import model.enemy.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WarriorTest {
    private Warrior warrior;

    @BeforeEach
    public void beforeEach() {
        warrior = new Warrior();
    }

    @Test
    public void warriorAttackLineTest() {
        assertEquals("Warrior slashes you for 2 damage", warrior.attackLine());
        warrior.setAttack(8);
        assertEquals("Warrior slashes you for 8 damage", warrior.attackLine());
    }

    @Test
    public void warriorDeathLineTest() {
        assertEquals("Warrior died", warrior.deathLine());
    }
}
