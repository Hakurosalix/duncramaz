package Enchantments;
/*
* Enchantment.java
* An enchantment class for use with the Launcher.DunCraMaz program
*
* @author Jack Manges
*/

import java.util.Random;

public class Enchantment {
	private final Random randGen = new Random();
	private final int damage;
	private String name = "";

	public Enchantment(String name, int damage) {
		this.name = name;
		this.damage = damage;
	}

	public String getName() {
		return name;
	}

	public int getDamage() {
		return damage;
	}

	public int damageRoll() {
		int n = 0;
		if (damage > 0) {
			n = randGen.nextInt(damage) + 1;
		}
		return n;
	}

	@Override
	public String toString() {
		return name + "\t" + damage;
	}
}