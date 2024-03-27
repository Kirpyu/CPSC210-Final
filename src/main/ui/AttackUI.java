package ui;

import model.Inventory;
import model.Player;
import model.enemy.*;

import java.util.Random;

// Creates a class that manages all attacking mechanics and enemies
public class AttackUI {
    private final EnemyList enemyList;
    private final Player player;
    private final Inventory inventory;
    private final Dialogue dialogue;
    private final TerminalGame terminalGame;

    // initiates attack screen
    public AttackUI(Player player, EnemyList enemyList, Inventory inventory, Dialogue dialogue,
                    TerminalGame terminalGame) {
        this.player = player;
        this.enemyList = enemyList;
        this.inventory = inventory;
        this.dialogue = dialogue;
        this.terminalGame = terminalGame;
    }

    //MODIFIES: terminalGame
    //EFFECTS: exit if cursor hovers exit, otherwise attack the enemy hovered and show dialogue
    public void executeAttack(int option) {
        if (option == enemyList.getCurrentEnemies().size()) {
            terminalGame.swapScreen("Options");
        } else {
            attackEnemy(enemyList.getCurrentEnemy(option));
            terminalGame.swapScreen("Dialogue");

        }
    }

    //REQUIRES: targetEnemy != null
    //MODIFIES: terminalGame, targetEnemy
    //EFFECTS: player attacks the enemy depending on their damage, then creates dialogue depending on
    // whether the enemy dies. If the enemylist is empty after attacking, then the next waves ensues, otherwise
    // the remaining enemies will attack the player.
    public void attackEnemy(Enemy targetEnemy) {
        dialogue.resetDialogue();
        player.damageEnemy(targetEnemy, player.getDamage());

        dialogue.addDialogue("You damaged " + targetEnemy.getName() + " for " + player.getDamage());

        if (targetEnemy.dead()) {
            dialogue.addDialogue(targetEnemy.deathLine());
            enemyList.removeEnemy(targetEnemy);
            inventory.addGold(targetEnemy.getGoldDropped());
            inventory.addInventory(targetEnemy.getItemDropped());
            dialogue.addDialogue("You gained " + targetEnemy.getGoldDropped() + "G");
            dialogue.addDialogue("You gained " + targetEnemy.getItemDropped().getItemName());
        } else {
            dialogue.addDialogue(targetEnemy.getName() + " has " + targetEnemy.getHealth() + " health left");
        }

        if (enemyList.getCurrentEnemies().isEmpty()) {
            dialogue.addDialogue("You have killed all enemies! Shop has refreshed");
            dialogue.addDialogue("Next enemies have arrived!");
            createEnemies(2);
        } else {
            attackPlayer(player);
        }
    }

    //REQUIRES: player != null
    //MODIFIES: terminalGame, player
    //EFFECTS: enemy attacks the players, then writes dialogue on the screen depending on if
    // the player is dead
    public void attackPlayer(Player player) {
        for (Enemy e: enemyList.getCurrentEnemies()) {
            player.damagePlayer(e.getAttack());
            dialogue.addDialogue(e.attackLine());
        }

        if (player.dead()) {
            terminalGame.drawEndScreen();
        } else {
            dialogue.addDialogue("You have " + player.getCurrentHealth() + "HP left");
        }
    }

    //REQUIRES: amount > 0
    //MODIFIES: this
    //EFFECTS: instantiates a certain amount of random enemies
    public void createEnemies(int amount) {
        for (int i = amount; i > 0; i--) {
            Random random = new Random();
            String randomEnemy = enemyList.getListOfEnemies().get(random.nextInt(5));

            enemyList.addEnemy(randomEnemy);
        }
    }
}
