package Enemies;
/*
* Troll.java
* A troll class for use with the Launcher.DunCraMaz program
*
* @author Jack Manges
*/

import Weapons.Hammer;

public class Troll extends Enemy {
	public Troll(String name, int row, int col) {
		super(name, row, col);
		this.attackName = " shoots you with a piercing arrow!";
		this.health = 15;
		this.damage = 8;
		this.weaponLoot = new Hammer("Blunt-Force Trama", true);
	}
}