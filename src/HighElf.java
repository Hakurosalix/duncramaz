/*
 * HighElf.java
 * A HighElf class for use with the MazeDQ program
 * 
 * @author Jack Manges
 */

 public class HighElf extends Enemy{

    public HighElf(String name, int row, int col){

        super(name, row, col);
        this.attackName = " casts a firebolt at you!";
        this.health = 25;
        this.damage = 14;
        this.weaponLoot = new Halberd("World-Slayer", true);
    }
}