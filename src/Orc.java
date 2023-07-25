/*
 * Orc.java
 * An orc class for use with the DunCraMaz program
 * 
 * @author Jack Manges
 */

 public class Orc extends Enemy
 {
    public Orc(String name, int row, int col)
    {
        super(name, row, col);
        this.attackName = " hits you with a heavy club!";
        this.health = 10;
        this.damage = 4;
        this.weaponLoot = new Sword("Excalibur", true);
    }
}
