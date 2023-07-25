/*
 * Player.java
 * A player class for use with the MazeDQ program
 * 
 * @author Jack Manges
 */

 import java.util.*;
 
 public class Player{

    private int health;
    private int mana;
    private int playerRow;
    private int playerCol;
    private Weapon playerWeapon;

    public Player(){

        playerWeapon = new Dagger("Knifey", false);
        playerRow = 0;
        playerCol = 0;
        health = 100;
        mana = 100;
    }

    public void Heal(){

        if (mana >= 8){
            Random randGen = new Random();
            int healAmount = randGen.nextInt(15) + 15;
            health += healAmount;
            if (health > 100){health = 100;}
            mana -= 8;
            System.out.println("\nYou cast a healing spell for 8 mana. Healed for " + healAmount);
        }
        else{System.out.println("Not enough mana!");}
    }

    public Weapon getWeapon(){return playerWeapon;}
    public void setWeapon(Weapon w){playerWeapon = w;}
    public int damageRoll(){return playerWeapon.damageRoll();}
    public int getHealth(){return health;}
    public int getMana(){return mana;}
    public int getRow(){return playerRow;}
    public int getCol(){return playerCol;}
    public void setRow(int i){playerRow = i;}
    public void setCol(int i){playerCol = i;}
    public void changeHealth(int i){health += i;}
    public void addRow(){playerRow++;}
    public void subtractRow(){playerRow--;}
    public void addCol(){playerCol++;}
    public void subtractCol(){playerCol--;}
 }