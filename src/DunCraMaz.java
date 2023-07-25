/*
 * DunCraMaz.java
 *  A program for a terminal-based Maze Navigator/Dungeon Crawler game. 
 *  See the included README text file included in project folder for guide to specific lines where
 *      the project requirements were fulfilled. 
 * @author Jack Manges
 */

import java.util.*;

//=====================================================================================================
// TODO:
//      Rework this entire file so that it isn't the most bulky/nested/unintelligible
//          code in the world!!
//
//=====================================================================================================

public class DunCraMaz{
    public static void main(String[] args){
        
        System.out.println("\n--------------------------------------------------------------------------------------------|"+
                        "\nYou've woken up in a dungeon full of monsters and you must escape! \n\nINSTRUCTIONS:\n\n"+
                        "Player starts at the top-left corner of the grid. 7 represents the player. \n1s represent walls," + 
                        " while 0s represent open paths. Asterisks (*) represent unexplored spaces. \nType the W, A, S,"+
                        " and D keys to move up, left, down, and right, respectively, and then press enter."+
                        "\nBe wary of monsters! Enemies are randomly placed, with combat transpiring in a turn-based fashion."+
                        "\nThe player starts with a simple dagger and a healing spell to use both in and out of combat."+
                        "\nBetter weapons may be looted off of enemies!"+
                        "\n\nReach the bottom-right corner of the dungeon to escape!");

        Maze dungeon = new Maze();
        dungeon.printFogMaze();
        Scanner scan = new Scanner(System.in);

        do {        // Game transpires within this do loop


            System.out.print("Player Move: ");
            String move = scan.next();
    
            dungeon.playerMove(move);
            dungeon.printFogMaze();
            
            if (dungeon.combatCheck() == true){          // random enemy encounter check

                Enemy combatEnemy = dungeon.getCombatEnemy();
                Player player = dungeon.getPlayer();

                System.out.println("Enemy " + combatEnemy.getName() + " encountered! You must defeat" +
                " this foe to continue! Type \"ROLL\" to attack with your currently \nequipped weapon, or \"HEAL\" to cast" +
                " a healing spell on yourself. Either action consumes a turn!");
                Boolean combatTester = true;

                while (combatTester == true){       // Combat loop 
                    
                    System.out.println("\nPlayer HP: " + player.getHealth() + "\tMana: " + player.getMana());
                    System.out.print("Player Move: ");
                    move = scan.next();
                    System.out.println("\n--------------------------------------------------------------------------------------------|");
                    move = move.toLowerCase();
                    Boolean validInputCheck = true;

                    switch (move){
                        
                        case "roll":{

                            int playerAttack = player.damageRoll();
                            combatEnemy.changeHealth(-1 * playerAttack);
                            System.out.println("\nYou attack with " + player.getWeapon().getName() + " for " + playerAttack + " damage.");

                            if (combatEnemy.getHealth() <= 0){

                                combatEnemy.setRow(-1); combatEnemy.setCol(-1);                         // removes enemy from play
                                System.out.println("\nEnemy " + combatEnemy.getName() + " defeated!");
                                Weapon loot = combatEnemy.getWeapon();
                                System.out.println("\nYou loot a new weapon: " + loot.toString());
                                System.out.println("Your current weapon: " + player.getWeapon().toString()); 

                                Boolean tester = true;
                                while (tester == true){           // Prompt user to equip new weapon or not

                                    System.out.print("\nEquip " + loot.getName() + " instead? (Type YES or NO): ");
                                    String response = scan.next();
                                    response = response.toLowerCase();

                                    switch (response){

                                        case "yes":{
                                            player.setWeapon(loot);
                                            System.out.println("\n--------------------------------------------------------------------------------------------|");
                                            System.out.println("\n" + loot.getName() + " equipped!");
                                            tester = false;
                                            break;
                                        }
                                        case "no":{
                                            tester = false;
                                            System.out.println("\n--------------------------------------------------------------------------------------------|");
                                            break;
                                        }
                                        default: {
                                            if (tester) System.out.println("\nInvalid input. Try again");
                                            break;
                                        }
                                    }
                                }
                                dungeon.printFogMaze();
                                validInputCheck = false;
                                combatTester = false;
                                break;
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
                    if (validInputCheck){

                        int enemyAttackRoll = combatEnemy.damageRoll();
                        System.out.println("\nEnemy " + combatEnemy.getName() + combatEnemy.getAttackName());
                        System.out.println("You take " + enemyAttackRoll + " damage.");
                        player.changeHealth(-1 * enemyAttackRoll);
                        if (player.getHealth() <= 0){
                            System.out.println("\nYou died. GAME OVER\n");
                            System.exit(0);
                        }
                    }
                }
            }
        }
        while (dungeon.endCheck() == false);

        scan.close();
    }

}