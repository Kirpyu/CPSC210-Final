package persistence;

import model.Inventory;
import model.Player;
import model.enemy.EnemyList;
import model.items.Item;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads enemylist, player, and inventory from JSON data stored in file
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads inventory from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Inventory readInventory(Inventory i) throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return (Inventory) parse(i, jsonObject);
    }

    // EFFECTS: reads enemylist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public EnemyList readEnemyList(EnemyList e) throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return (EnemyList) parse(e, jsonObject);
    }

    // EFFECTS: reads player from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Player readPlayer(Player p) throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return (Player) parse(p, jsonObject);
    }


    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: sorts and parses an object from JSON object and returns it
    private Object parse(Object o, JSONObject jsonObject) {
        sortKey(o, jsonObject);
        return o;
    }

    // MODIFIES: o
    // EFFECTS: casts object to right object type, then
    // parses object from JSON object and adds them to appropriate object
    private void sortKey(Object o, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("save");

        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;

            if ((nextItem.has("inventory")) && (o instanceof Inventory)) {
                addInventory((Inventory) o, nextItem);
                break;
            } else if ((nextItem.has("enemies")) && (o instanceof EnemyList)) {
                addEnemyList((EnemyList) o, nextItem);
                break;
            } else if (nextItem.has("player")) {
                addPlayer((Player) o, nextItem);
                break;
            }
        }
    }

    // MODIFIES: i
    // EFFECTS: parses items from JSON object and adds them to inventory
    private void addInventory(Inventory i, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("inventory");
        i.clearInventory();
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItem(i, nextItem);
        }
        
        int gold = jsonObject.getInt("gold");
        i.setGold(gold);
    }

    // MODIFIES: e
    // EFFECTS: parses enemies from JSON object and adds them to enemylist
    private void addEnemyList(EnemyList e, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("enemies");
        e.removeAllEnemies();
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addEnemy(e, nextItem);
        }
    }

    // MODIFIES: p
    // EFFECTS: parses stats from JSON object and adds them to player
    private void addPlayer(Player p, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("player");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addStats(p, nextItem);
        }
    }

    // MODIFIES: i
    // EFFECTS: parses item from JSON object and adds item to inventory
    private void addItem(Inventory i, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String ability = jsonObject.getString("ability");
        int level = jsonObject.getInt("level");
        int damage = jsonObject.getInt("damage");

        Item item = new Item(name, ability, level, damage);
        i.addInventory(item);
    }

    //MODIFIES: e
    //EFFECTS: parses enemy from JSON object and adds enemy to enemylist
    private void addEnemy(EnemyList e, JSONObject jsonObject) {
        String name = jsonObject.getString("name");

        e.addEnemy(name);
    }

    //MODIFIES: p
    //EFFECTS: parses players stats from JSON object and adds them to player
    private void addStats(Player p, JSONObject jsonObject) {
        int currentHP = jsonObject.getInt("health");
        try {
            JSONArray equippedItem = jsonObject.getJSONArray("item");

            for (Object json: equippedItem) {
                JSONObject item = (JSONObject) json;
                addEquippedItem(p, item);
            }
        } catch (JSONException e) {
            // no item
        }


        p.setCurrentHealth(currentHP);
    }

    private void addEquippedItem(Player p, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String ability = jsonObject.getString("ability");
        int level = jsonObject.getInt("level");
        int damage = jsonObject.getInt("damage");

        Item item = new Item(name, ability, level, damage);
        p.setEquippedItem(item);
    }
}
