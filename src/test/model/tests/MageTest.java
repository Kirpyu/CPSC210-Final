package model.tests;

import model.enemy.Mage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MageTest {
    private Mage mage;

    @BeforeEach
    public void beforeEach() {
        mage = new Mage();
    }

    @Test
    public void mageAttackLineTest() {
        assertEquals("Mage burns you for 5 damage", mage.attackLine());
        mage.setAttack(6);
        assertEquals("Mage burns you for 6 damage", mage.attackLine());
    }

    @Test
    public void mageDeathLineTest() {
        assertEquals("Mage died", mage.deathLine());

    }
}
