package model.tests;

import model.enemy.Berserker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BerserkerTest {
    private Berserker berserker;

    @BeforeEach
    public void beforeEach() {
        berserker = new Berserker();
    }

    @Test
    public void berserkerAttackLineTest() {
        assertEquals("Berseker cuts you for 3 damage", berserker.attackLine());
        berserker.setAttack(7);
        assertEquals("Berseker cuts you for 7 damage", berserker.attackLine());
    }

    @Test
    public void berserkerDeathLineTest() {
        assertEquals("Berserker died", berserker.deathLine());
    }
}
