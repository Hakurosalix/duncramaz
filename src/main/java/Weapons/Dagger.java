package Weapons;
/*
* Dagger.java
* A dagger class for use with the Launcher.DunCraMaz program
*
* @author Jack Manges
*/

public class Dagger extends Weapon {
	public Dagger(String name, Boolean enchant) {
		super(name, enchant);
		this.damage = 5;
	}
}