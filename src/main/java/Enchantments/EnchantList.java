package Enchantments;
/*
* EnchantList.java
* An enchantlist class for use with the Launcher.DunCraMaz program
*
* @author Jack Manges
*/

import java.util.ArrayList;
import java.util.Random;

public class EnchantList {
	// Singleton
	private static final EnchantList enchantList = new EnchantList();
	private final Random randGen = new Random();
	private final ArrayList<Enchantment> enchants = new ArrayList<>();
	private final Enchantment FLAMING_ENCHANTMENT = new Enchantment("Flaming", 2);
	private final Enchantment ELECTRIC_ENCHANTMENT = new Enchantment("Electric", 4);
	private final Enchantment FREEZING_ENCHANTMENT = new Enchantment("Freezing", 2);
	private final Enchantment TIME_SHIFTING_ENCHANTMENT = new Enchantment("Time-shifting", 6);

	private EnchantList() {
		// Set possible enchants
		enchants.add(FLAMING_ENCHANTMENT);
		enchants.add(ELECTRIC_ENCHANTMENT);
		enchants.add(FREEZING_ENCHANTMENT);
		enchants.add(TIME_SHIFTING_ENCHANTMENT);
	}

	public static EnchantList getInstance() {
		return enchantList;
	}

	public Enchantment getEnchantment(String name) {
		int index = 0;
		for (int i = 0; i < enchants.size(); i++) {
			if (name.equalsIgnoreCase(enchants.get(i).getName())) {
				index = i;
			}
		}
		return enchants.get(index);
	}

	public Enchantment getRandomEnchantment() {
		int n = randGen.nextInt(enchants.size());
		return enchants.get(n);
	}
}