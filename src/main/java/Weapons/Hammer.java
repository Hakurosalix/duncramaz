package Weapons;
/*
* Hammer.java
* A hammer class for use with the Launcher.DunCraMaz program
*
* @author Jack Manges
*/

public class Hammer extends Weapon {
	public Hammer(String name, Boolean enchant) {
		super(name, enchant);
		this.damage = 7;
	}
}