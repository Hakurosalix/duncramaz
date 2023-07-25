/*
 * Enemy.java
 * An enemy class for use with the DunCraMaz program
 * 
 * @author Jack Manges
 */
 
 import java.util.Random;
 
 public abstract class Enemy
 {
    protected String name = "";
    protected String attackName = "";
    protected int health;
    protected int damage;
    protected int enemyRow;
    protected int enemyCol;
    protected Weapon weaponLoot;

    public Enemy(String name, int row, int col)
    {
        this.name = name;
        this.enemyRow = row;
        this.enemyCol = col;
    }

    public int damageRoll()
    {
        Random randGen = new Random();
        int n = randGen.nextInt(damage) + 1;
        return n;
    }

    public int getRow(){return enemyRow;}
    public int getCol(){return enemyCol;}
    public int getHealth(){return health;}
    public String getName(){return name;}
    public int getDamage(){return damage;}
    public Weapon getWeapon(){return weaponLoot;}
    public String getAttackName(){return attackName;}
    public void setRow(int i){this.enemyRow = i;}
    public void setCol(int i){this.enemyCol = i;}
    public void changeHealth(int i){health += i;}

    public String toString()
    {
        return name + "\t" + damage;
    }
}