package persistence;

import model.Inventory;
import model.Player;
import model.enemy.EnemyList;
import model.items.Axe;
import model.items.Dagger;
import model.items.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {
    private EnemyList e;
    private ArrayList<String> enemyList;
    private ArrayList<Item> itemList;
    private Player p;
    private Inventory i;
    private Axe axe;
    private Dagger dagger;



    @BeforeEach
    void beforeEach() {
        e = new EnemyList();
        p = new Player();
        i = new Inventory();
        axe = new Axe();
        dagger = new Dagger();

        enemyList = new ArrayList<>();
        itemList = new ArrayList<>();
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptySave() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptySave.json");
            writer.open();
            writer.write(i, e, p);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptySave.json");
            e = reader.readEnemyList(e);
            p = reader.readPlayer(p);
            i = reader.readInventory(i);
            enemyList.add("Exit");

            assertEquals(enemyList, e.getEnemyNames());
            assertEquals(itemList, i.getInventory());
            assertEquals(20, p.getCurrentHealth());
            assertEquals(0, i.getGold());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }

    @Test
    void testWriterGeneralSave() {
        e.addEnemy("Berserker");
        e.addEnemy("Mage");
        enemyList.add("Berserker");
        enemyList.add("Mage");
        enemyList.add("Exit");

        i.addInventory(axe);
        i.addInventory(dagger);
        itemList.add(axe);
        itemList.add(dagger);

        p.setCurrentHealth(15);
        i.setGold(5);

        try {
            JsonWriter writer = new JsonWriter("./data/testReaderSave.json");
            writer.open();
            writer.write(i, e, p);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderSave.json");
            e = reader.readEnemyList(e);
            i = reader.readInventory(i);
            p = reader.readPlayer(p);

            assertEquals(enemyList, e.getEnemyNames());
            checkEnemyList("Berserker", 5, e.getCurrentEnemy(0));
            checkEnemyList("Mage", 5, e.getCurrentEnemy(1));

            checkInventory(axe.getItemName(), axe.getLevel(), axe.getAbilityName(), axe.getDamage(), i.getInventory().get(0));
            checkInventory(dagger.getItemName(), dagger.getLevel(), dagger.getAbilityName(), dagger.getDamage(), i.getInventory().get(1));
            checkGold(5, i);

            assertEquals(15, p.getCurrentHealth());
            checkPlayer(15, p);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testEmptyFile() {
        try {
            JsonReader reader = new JsonReader("./data/testEmptySave.json");
            p = reader.readPlayer(p);
            checkPlayer(20, p);
        } catch (IOException e) {
            fail("Unhandled Exception");
        }
    }

}
