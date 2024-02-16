package model.tests;

import model.enemy.Rogue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RogueTest {
    private Rogue rogue;

    @BeforeEach
    public void beforeEach() {
        rogue = new Rogue();
    }

    @Test
    public void rogueAttackLineTest() {
        assertEquals("Rogue stabs you for 5 damage", rogue.attackLine());
        rogue.setAttack(9);
        assertEquals("Rogue stabs you for 9 damage", rogue.attackLine());
    }

    @Test
    public void rogueDeathLineTest() {
        assertEquals("Rogue died", rogue.deathLine());
    }
}
