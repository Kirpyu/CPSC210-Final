package model.items;

public class Item {
    protected String itemName;
    protected String abilityName;
    protected int damage;
    protected int level;

    //getters
    public Item() {
    }

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

    //setters
    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}

