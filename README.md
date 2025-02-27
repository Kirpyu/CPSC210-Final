# My Personal Project

## Strategy Game
A game that revolves around a villain powering up while defending against waves
of heroes. The game is made for people interested in playing strategy games related
to building up power from scratch, similar to popular games such as *Super Auto Pets* or *Slay the Spire*.
The project serves as a quick introduction to game development for me and will give me insight on
whether I intend to pursue game development/ similar projects.

## **User Stories:**
- As a user, I want to be able to add any amount of items to my inventory through a shop or by killing enemies any amount of times
  (each duplicate will counts towards the items level)
- As a user, I want to be able to open my inventory to see all acquired items.
- As a user, I want to be able to equip an item through the inventory
- As a user, I want to be able to kill enemies to gain gold and items.
- As a user, I want to be able to fight random enemies
- As a user, I want to be able to save my games progress (i.e. items, enemies)
- As a user, I want to be able to load the progress that I have saved

## **Instructions for Grader:**
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by either killing an enemy (through Attack) or purchasing a weapon through the shop, which adds an item to your inventory.
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by opening the inventory and clicking on an item. This should equip the item as seen through the stats page.
- You can locate my visual component by the amount of characters above the main menu, with the characters indicating the amount of enemies
- You can save the state of my application by clicking the save option in the main menu
- You can reload the state of my application by clicking the load option in the main menu

Side Note: You are able to navigate the game either throw arrow keys and space bar or clicking on the options

## **Phase 4: Task 2**
Shop -> Purchase Axe -> Shop -> Purchase Dagger -> Inventory -> Equip Axe -> Equip Dagger

Console Log:

- Sun Apr 07 15:27:43 PDT 2024
- Added Axe to inventory.
- Sun Apr 07 15:27:45 PDT 2024
- Added Dagger to inventory.
- Sun Apr 07 15:27:46 PDT 2024
- Player equipped Axe
- Sun Apr 07 15:27:46 PDT 2024
- Player equipped Dagger

## **Phase 4: Task 3**

One change I noticed I would make is creating an Entity abstract class for both the Player and Enemy. This would allow
me to go less back and forth between the common properties between Player and Enemy in my code, such as taking damage, 
attacking people, taking their attack damage, etc. These two classes are almost the same with only a few exceptions, 
which is how an abstract class would definitely come in handy. 

Another change I would make is dividing the TerminalGame class or making it shorter. The class
is quite long and hard to navigate, which may be a bit bothersome in the future. Some ways I may approach this may be
creating more classes or creating new methods for commonly repeated code. 


## **JSON**
Referenced from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
