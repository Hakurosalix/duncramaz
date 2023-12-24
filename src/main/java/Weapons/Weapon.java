package Weapons;
/*
* Weapon.java
* A weapon class for use with the Launcher.DunCraMaz program
*
* @author Jack Manges
*/

import java.util.Random;

import Enchantments.EnchantList;
import Enchantments.Enchantment;

public abstract class Weapon {
	static protected EnchantList enchantList;
	protected String name;
	protected int damage;
	protected Enchantment enchantment;
	protected Boolean enchanted;

	public Weapon(String name, Boolean enchant) {
		this.name = name;
		enchantList = EnchantList.getInstance();

		if (enchant) {
			enchanted = true;
			enchantment = enchantList.getRandomEnchantment();
			this.name = enchantment.getName() + " " + this.name;
		} else {
			enchanted = false;
		}
	}

	public int damageRoll() {
		Random randGen = new Random();
		int n = randGen.nextInt(damage) + 1;
		if (enchanted) {
			n += enchantment.damageRoll();
		}
		return n;
	}

	public void addEnchantment(Enchantment enchantment) {
		this.enchantment = enchantment;
		this.name = enchantment.getName() + " " + this.name;
		enchanted = true;
	}

	public String getName() {
		return name;
	}

	public int getDamage() {
		return damage;
	}

	public Enchantment getEnchantment() {
		return enchantment;
	}

	@Override
	public String toString() {
		if (this.enchanted) {
			return this.name + "\tDamage: " + (this.damage + this.enchantment.getDamage());
		} else {
			return this.name + "\tDamage: " + this.damage;
		}
	}
}