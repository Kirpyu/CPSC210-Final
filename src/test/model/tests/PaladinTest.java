package model.tests;

import model.enemy.Paladin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaladinTest {
    private Paladin paladin;

    @BeforeEach
    public void beforeEach() {
        paladin = new Paladin();
    }

    @Test
    public void paladinAttackLineTest() {
        assertEquals("Paladin smites you for 1 damage", paladin.attackLine());
        paladin.setAttack(2);
        assertEquals("Paladin smites you for 2 damage", paladin.attackLine());
    }

    @Test
    public void paladinDeathLineTest() {
        assertEquals("Paladin died", paladin.deathLine());
    }
}
