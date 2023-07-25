/* 
 * Balrog.java
 * A Balrog class for use with the DunCraMaz program
 * 
 * @author Jack Manges
 */

 public class Balrog extends Enemy
 {
    public Balrog(String name, int row, int col)
    {
        super(name, row, col);
        this.attackName = " smites you with its Hammer of Doom!";
        this.health = 50;
        this.damage = 14;
        this.weaponLoot = new Staff("Mithrandir's Staff", false);
    }
}