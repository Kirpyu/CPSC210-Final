package model.persistence;

import model.Inventory;
import model.enemy.Enemy;
import model.enemy.EnemyList;
import model.items.Item;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Inventory readInventory(Inventory inventory) throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseInventory(inventory, jsonObject);
    }

    public EnemyList readEnemyList(EnemyList enemyList) throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseEnemyList(enemyList, jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses inventory from JSON object and returns it
    private Inventory parseInventory(Inventory i, JSONObject jsonObject) {
        addInventory(i, jsonObject);
        return i;
    }

    // EFFECTS: parses enemylist from JSON object and returns it
    private EnemyList parseEnemyList(EnemyList e, JSONObject jsonObject) {
        addEnemyList(e, jsonObject);
        return e;
    }

    // MODIFIES: i
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addInventory(Inventory i, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("inventory");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItem(i, nextItem);
        }
    }

    // MODIFIES: i
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addEnemyList(EnemyList e, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("enemies");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addEnemy(e, nextItem);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addItem(Inventory i, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String ability = jsonObject.getString("ability");
        int level = jsonObject.getInt("level");
        int damage = jsonObject.getInt("damage");

        Item item = new Item(name, ability, level, damage);
        i.addInventory(item);
    }

    //MODIFIES: e
    //
    private void addEnemy(EnemyList e, JSONObject jsonObject) {
        String name = jsonObject.getString("name");

        e.addEnemy(name);
    }
}
