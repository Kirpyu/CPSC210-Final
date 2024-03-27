package model.items;

import org.json.JSONObject;

// creates an item class that can be modified into a default weapon or custom weapon
public class Item {
    protected String itemName;
    protected String abilityName;
    protected int damage;
    protected int level;
    protected int cost;

    //Creates an item
    public Item(String itemName, String abilityName, int level, int damage) {
        this.itemName = itemName;
        this.abilityName = abilityName;
        this.level = level;
        this.damage = damage;
        this.cost = 0;
    }



    //MODIFIES: this
    //EFFECTS: adds a level
    public void addLevel() {
        level += 1;
    }

    // getters
    public int getDamage() {
        return damage;
    }

    public int getLevel() {
        return level;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public String getItemName() {
        return itemName;
    }

    public int getCost() {
        return cost;
    }





    //setters
    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    //EFFECTS: returns items stats as a json object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", itemName);
        json.put("level", level);
        json.put("ability", abilityName);
        json.put("damage", damage);
        return json;

    }
}

