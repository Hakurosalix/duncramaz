/*
 * Game.java
 * A game class for use in the DunCraMaz program
 * @author Jack Manges
 */

//=====================================================================================================
// TODO:
//      Rework this entire file so that it isn't the most bulky/nested/unintelligible
//          code in the world!!
//
//=====================================================================================================

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Game 
{
    // Singleton
    private static Game game = new Game();
    private Player player;
    private Scanner scan;
    private Maze dungeon;

    private Game() 
    {
        player = Player.getInstance();
        dungeon = Maze.getInstance();
        scan = new Scanner(System.in);
    }

    public void run() throws FileNotFoundException
    {
        printIntro();
        primaryGameLoop();
        scan.close();
    }

    private void primaryGameLoop()
    {   
        dungeon.printFogMaze();
        // Game transpires within this do loop
        do {        
            PlayerMove();
            // random enemy encounter check
            if (dungeon.combatCheck()){          
                combatLoop();
            }
        }
        while (dungeon.endCheck() == false);

    }

    private void combatLoop()
    {
        Enemy combatEnemy = dungeon.getCombatEnemy();
        String move;

        System.out.println("Enemy " + combatEnemy.getName() + " encountered! You must defeat" +
        " this foe to continue! Type \"ROLL\" to attack with your currently \nequipped weapon, or \"HEAL\" to cast" +
        " a healing spell on yourself. Either action consumes a turn!");

        Boolean combatTester = true;
        while (combatTester == true){       // Combat loop 
                    
            System.out.println("\nPlayer HP: " + player.getHealth() + "\tMana: " + player.getMana());
            System.out.print("Player Move: ");
            move = scan.next();
            move = move.toLowerCase();
            printLineBreak();
            Boolean validInputCheck = true;

            switch (move) {  

                case "roll": {
                    playerAttackTurn(combatEnemy);
                    if (combatEnemy.getHealth() <= 0) {
                        postCombatLootQuery(combatEnemy);
                        dungeon.printFogMaze();
                        return;
                    }

                    break;
                }

                case "heal":{
                    player.Heal();
                    break;
                }

                default:{
                    System.out.println("\nInvalid input. Try again."); 
                    validInputCheck = false;
                    break;
                }
            }

            // Enemy attacks after player turn
            if (validInputCheck) {
                enemyTurn(combatEnemy);
                gameOverTest();
            }
        }
    
    }

    private void postCombatLootQuery(Enemy combatEnemy)
    {
        combatEnemy.setRow(-1); combatEnemy.setCol(-1);  // removes enemy from play
        System.out.println("\nEnemy " + combatEnemy.getName() + " defeated!");
        Weapon loot = combatEnemy.getWeapon();
        System.out.println("\nYou loot a new weapon: " + loot.toString());
        System.out.println("Your current weapon: " + player.getWeapon().toString()); 

        Boolean equipTester = true;
        while (equipTester == true) {           // Prompt user to equip new weapon or not
            System.out.print("\nEquip " + loot.getName() + " instead? (Type YES or NO): ");
            String response = scan.next();
            response = response.toLowerCase();

            switch (response){

                case "yes":{
                    player.setWeapon(loot);
                    printLineBreak();
                    System.out.println("\n" + loot.getName() + " equipped!");
                    equipTester = false;
                    break;
                }
                case "no":{
                    equipTester = false;
                    printLineBreak();
                    break;
                }
                default: {
                    if (equipTester) System.out.println("\nInvalid input. Try again");
                    break;
                }
            }
        }
    
    }

    private void enemyTurn(Enemy combatEnemy)
    {
        int enemyAttackRoll = combatEnemy.damageRoll();
        System.out.println("\nEnemy " + combatEnemy.getName() + combatEnemy.getAttackName());
        System.out.println("You take " + enemyAttackRoll + " damage.");
        player.changeHealth(-1 * enemyAttackRoll);
        
    }

    private void playerAttackTurn(Enemy combatEnemy)
    {
        int playerAttack = player.damageRoll();
        combatEnemy.changeHealth(-1 * playerAttack);
        System.out.println("\nYou attack with " + player.getWeapon().getName() + 
                           " for " + playerAttack + " damage.");
    
    }

    private void PlayerMove()
    {
        System.out.print("Player Move: ");
        String move = scan.next();
        dungeon.executePlayerMove(move);
        dungeon.printFogMaze();

    }

    private void printIntro() throws FileNotFoundException
    {
        File artFile = new File("wizardArt.txt");
        Scanner fileScan = new Scanner(artFile);
        while (fileScan.hasNextLine()) {
            System.out.println(fileScan.nextLine());
        }
        fileScan.close();

        printLineBreak();
        System.out.println(
        "\nYou've woken up in a dungeon full of monsters and you must escape! \n\nINSTRUCTIONS:\n\n"+
        "Player starts at the top-left corner of the grid. 7 represents the player. \n1s represent walls," + 
        " while 0s represent open paths. Asterisks (*) represent unexplored spaces. \nType the W, A, S,"+
        " and D keys to move up, left, down, and right, respectively, and then press enter."+
        "\nBe wary of monsters! Enemies are randomly placed, with combat transpiring in a turn-based fashion."+
        "\nThe player starts with a simple dagger and a healing spell to use both in and out of combat."+
        "\nBetter weapons may be looted off of enemies!"+
        "\n\nReach the bottom-right corner of the dungeon to escape!");
    
    }

    private void printLineBreak()
    {
        System.out.println("\n--------------------------" +
        "-----------------------------------------------" +
        "-------------------|");
    }

    private void gameOverTest()
    {
        if (player.getHealth() <= 0) {
            System.out.println("\nYou died. GAME OVER\n");
            System.exit(0);
        }
    }

    public static Game getInstance() {return game;}
            
}


