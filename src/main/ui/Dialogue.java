package ui;

import model.Player;
import model.enemy.Enemy;
import model.enemy.EnemyList;

import java.util.ArrayList;

public class Dialogue {
    EnemyList enemyList;
    Player player;
    ArrayList<String> displayedDialogue; // holds dialogue in the screen
    ArrayList<String> currentDialogue; // holds accumulated dialogue that's backlogged

    public Dialogue(Player player, EnemyList enemyList) {
        this.player = player;
        this.enemyList = enemyList;
        currentDialogue = new ArrayList<>();
        displayedDialogue = new ArrayList<>();
    }

    // EFFECTS: displays the dialogue
    public ArrayList<String> displayDialogue() {
        displayedDialogue = currentDialogue;
        return displayedDialogue;
    }

    // MODIFIES: this
    // EFFECTS: creates the starting dialogue of the enemies arriving
    public void start() {
        StringBuilder tempString = new StringBuilder();
        int start = 0;
        for (Enemy e: enemyList.getCurrentEnemies()) {
            if (start == 0) {
                tempString = new StringBuilder(e.getName());
                start += 1;
            } else if (start == enemyList.getCurrentEnemies().size() - 1) {
                tempString.append(" and ").append(e.getName());
            } else {
                tempString.append(", ").append(e.getName());
                start += 1;
            }
        }
        currentDialogue.add(tempString + " has arrived!");
    }

    //MODIFIES: this
    //EFFECTS: adds string to current dialogue
    public void addDialogue(String dialogue) {
        currentDialogue.add(dialogue);
    }

    //MODIFIES: this
    //EFFECTS: removes all strings from current dialogue
    public void resetDialogue() {
        currentDialogue.removeAll(currentDialogue);
    }

}
