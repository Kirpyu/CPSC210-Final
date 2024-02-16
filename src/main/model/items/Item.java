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
}

