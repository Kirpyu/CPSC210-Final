package model.items;

public class Item {
    protected String itemName;
    protected String abilityName;
    protected int damage;
    protected int level;
    protected int cost;

    //getters
    public Item(String itemName, String abilityName, int level, int damage) {
        this.itemName = itemName;
        this.abilityName = abilityName;
        this.level = level;
        this.damage = damage;
        this.cost = 0;
    }

    public int getDamage() {
        return damage;
    }

    public void addLevel() {
        level += 1;
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

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    //setters
    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }


    public void setDamage(int damage) {
        this.damage = damage;
    }
}

