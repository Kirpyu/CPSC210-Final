package ui;

import model.Player;
import model.enemy.Enemy;
import model.enemy.EnemyList;

import java.util.ArrayList;

public class Dialogue {
    EnemyList enemyList;
    Player player;
    ArrayList<String> displayedDialogue;
    ArrayList<String> currentDialogue;

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
        String tempString = "";
        int start = 0;
        for (Enemy e: enemyList.getEnemyList()) {
            if (start == 0) {
                tempString = e.getName();
                start += 1;
            } else if (start == enemyList.getEnemyList().size() - 1) {
                tempString = tempString + " and " + e.getName();
            } else {
                tempString = tempString + ", " + e.getName();
                start += 1;
            }
        }
        currentDialogue.add(tempString + " has arrived!");
    }

    public void addDialogue(String dialogue) {
        currentDialogue.add(dialogue);
    }

    public void resetDialogue() {
        currentDialogue.removeAll(currentDialogue);
    }

}
