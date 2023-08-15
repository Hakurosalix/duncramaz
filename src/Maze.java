/*
 * Maze.java
 * A maze class for use with the DunCraMaz program
 * 
 * @author Jack Manges
 */

import java.util.*;

public class Maze
{
    // Singleton
    private static Maze dungeon = new Maze();
    private Player player;

    private int[][] keyMaze = { {7,0,0,1,0,0,1,1,1,0,0,0,0},
                                {0,1,0,0,0,1,0,0,0,0,1,1,0},
                                {1,1,1,1,0,1,0,1,0,1,0,1,1},
                                {0,0,0,1,0,0,0,1,0,1,0,0,0},
                                {0,1,0,1,1,1,1,0,0,0,1,1,0},
                                {0,1,0,0,0,0,0,0,1,0,0,0,0},
                                {0,1,1,1,1,1,1,1,1,1,1,1,1},
                                {0,0,0,0,0,0,0,0,0,0,0,0,0}, };

    private String[][] fogMaze = { {"7","0","*","*","*","*","*","*","*","*","*","*","*",},
                                   {"0","1","*","*","*","*","*","*","*","*","*","*","*",},
                                   {"*","*","*","*","*","*","*","*","*","*","*","*","*",},
                                   {"*","*","*","*","*","*","*","*","*","*","*","*","*",},
                                   {"*","*","*","*","*","*","*","*","*","*","*","*","*",},
                                   {"*","*","*","*","*","*","*","*","*","*","*","*","*",},
                                   {"*","*","*","*","*","*","*","*","*","*","*","*","*",},
                                   {"*","*","*","*","*","*","*","*","*","*","*","*","*",}, };
    
    private Enemy[] enemies = new Enemy[8];
    private Enemy combatEnemy;

    private Maze()
    {   
        this.player = Player.getInstance();
        GenerateEnemies();
    }

    // Method for randomly populating maze with enemies
    public void GenerateEnemies()
    {          
        Random randGen = new Random();
        int i = 0;                  

        //Loop through each position in keyMaze
        for (int row = 0; row < keyMaze.length; row++) {            
            for (int col = 0; col < keyMaze[row].length; col++) {

                first:
                //Check if position in keyMaze is an open space, omit top left corner to avoid instant enemy encounter
                if (keyMaze[row][col] == 0 && (row >= 2 || col >= 3) && (row > 0 || col > 10)) {    

                    //Generate random number in desired range (tweak as necessary) to use as a chance check to create an enemy
                    int testNum = randGen.nextInt(3);            

                    if (row == 7 && col > 8){testNum = 1;}     // Ensures that final enemy is generated if bad RNG
                    
                    if (testNum == 1) {           //Check chance

                        if (i >= 0 && i < 3) {
                            enemies[i] = new Orc("Orc", row, col);                      
                            //keyMaze[row][col] = 3;
                            i++;                                                             
                            break first;                                                            
                        }
                        else if (i > 2 && i < 5 && row > 1) {
                            enemies[i] = new Troll("Troll", row, col);
                            //keyMaze[row][col] = 4;
                            i++; 
                            break first;
                        }
                        else if (i > 4 && i < 7 && row > 3) {
                            enemies[i] = new HighElf("High Elf", row, col);
                            //keyMaze[row][col] = 5;
                            i++;
                            break first;
                        }
                        else if (i > 6 && i < 8 && row > 6) {
                            enemies[i] = new Balrog("Balrog", row, col);
                            //keyMaze[row][col] = 6;
                            i++;
                            break first;
                        }
                    }
                }
            }
        }
    }

    public void printMaze()
    {
        System.out.println();
        for (int row = 0; row < keyMaze.length; row++) {
            for (int col = 0; col < keyMaze[row].length; col++) {
                System.out.print(keyMaze[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printFogMaze()
    {
        // Update the fogged maze with the real maze values as the player traverses
        try{fogMaze[player.getRow()-1][player.getCol()-1] = Integer.toString(keyMaze[player.getRow()-1][player.getCol()-1]);}   
            catch (ArrayIndexOutOfBoundsException e){}
        try{fogMaze[player.getRow()-1][player.getCol()] = Integer.toString(keyMaze[player.getRow()-1][player.getCol()]);}
            catch (ArrayIndexOutOfBoundsException e){}
        try{fogMaze[player.getRow()-1][player.getCol()+1] = Integer.toString(keyMaze[player.getRow()-1][player.getCol()+1]);}
            catch (ArrayIndexOutOfBoundsException e){}
        try{fogMaze[player.getRow()][player.getCol()-1] = Integer.toString(keyMaze[player.getRow()][player.getCol()-1]);}
            catch (ArrayIndexOutOfBoundsException e){}
        try{fogMaze[player.getRow()][player.getCol()] = Integer.toString(keyMaze[player.getRow()][player.getCol()]);}
            catch (ArrayIndexOutOfBoundsException e){}
        try{fogMaze[player.getRow()][player.getCol()+1] = Integer.toString(keyMaze[player.getRow()][player.getCol()+1]);}
            catch (ArrayIndexOutOfBoundsException e){}
        try{fogMaze[player.getRow()+1][player.getCol()-1] = Integer.toString(keyMaze[player.getRow()+1][player.getCol()-1]);}
            catch (ArrayIndexOutOfBoundsException e){}
        try{fogMaze[player.getRow()+1][player.getCol()] = Integer.toString(keyMaze[player.getRow()+1][player.getCol()]);}
            catch (ArrayIndexOutOfBoundsException e){}
        try{fogMaze[player.getRow()+1][player.getCol()+1] = Integer.toString(keyMaze[player.getRow()+1][player.getCol()+1]);}
            catch (ArrayIndexOutOfBoundsException e){}

        System.out.println();
        for (int row = 0; row < fogMaze.length; row++) {
            for (int col = 0; col < fogMaze[row].length; col++){
                System.out.print(fogMaze[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void executePlayerMove(String move)
    {
        move = move.toLowerCase();
        switch (move){

            case "w":
                try {
                    if (keyMaze[player.getRow()-1][player.getCol()] == 0) {
                        keyMaze[player.getRow()][player.getCol()] = 0;
                        player.subtractRow();
                        keyMaze[player.getRow()][player.getCol()] = 7;
                        break;
                    }
                    break;
                }
                catch (ArrayIndexOutOfBoundsException e){break;}
            
            case "a":
                try{
                    if (keyMaze[player.getRow()][player.getCol()-1] == 0) {
                        keyMaze[player.getRow()][player.getCol()] = 0;
                        player.subtractCol();
                        keyMaze[player.getRow()][player.getCol()] = 7;
                        break;
                    }
                    break;
                }
                catch (ArrayIndexOutOfBoundsException e){break;}
            
            case "s":
                try{
                    if (keyMaze[player.getRow()+1][player.getCol()] == 0) {
                        keyMaze[player.getRow()][player.getCol()] = 0;
                        player.addRow();
                        keyMaze[player.getRow()][player.getCol()] = 7;
                        break;
                    }
                    break;
                }
                catch (ArrayIndexOutOfBoundsException e){break;}
            
            case "d":
                try{
                    if (keyMaze[player.getRow()][player.getCol()+1] == 0) {
                        keyMaze[player.getRow()][player.getCol()] = 0;
                        player.addCol();
                        keyMaze[player.getRow()][player.getCol()] = 7;
                        break;
                    }
                    break;
                }
                catch (ArrayIndexOutOfBoundsException e){break;}
            
            case "heal": {
                player.Heal();
                System.out.println("\nPlayer HP: " + player.getHealth() + "\tMana: " + player.getMana());
                break;
            }
    
            default: {
                System.out.println("(Invalid input)"); 
                break;
            }
        }
    }
    
    public Boolean combatCheck()
    {
         for (int i = 0; i < enemies.length; i++) {
            if (enemies[i].getRow() == player.getRow() && enemies[i].getCol() == player.getCol()) {
                combatEnemy = enemies[i];
                return true;
            }
         }
         return false;
    }

    public Boolean endCheck()
    {
        //if (keyMaze[7][12] == 7){
        if (player.getRow() == 7 && player.getCol() == 12) {

            System.out.println("Dungeon escaped. You win!\n");
            return true;
        }
        else{return false;}
    }

    public static Maze getInstance() {return dungeon;}

    public Enemy getEnemy(int index){return enemies[index];}
    public Enemy getCombatEnemy(){return combatEnemy;}
 }