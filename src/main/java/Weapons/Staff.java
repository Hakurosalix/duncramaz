package Weapons;/*
				* Staff.java
				* A Staff class for use with the Launcher.DunCraMaz program
				* 
				* @author Jack Manges
				*/

public class Staff extends Weapon {
	public Staff(String name, Boolean enchant) {
		super(name, enchant);
		this.damage = 100;
	}
}