package Weapons;/*
				* Sword.java
				* A Sword class for use with the Launcher.DunCraMaz program
				* 
				* @author Jack Manges
				*/

public class Sword extends Weapon {
	public Sword(String name, Boolean enchant) {
		super(name, enchant);
		this.damage = 5;
	}
}
