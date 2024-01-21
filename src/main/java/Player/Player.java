package Player;
/*
* Player.Player.java
* A player class for use with the Launcher.DunCraMaz program
*
* @author Jack Manges
*/

import java.util.Random;

import Weapons.Dagger;
import Weapons.Weapon;

public class Player {
	private static final int MAX_MANA = 100;
	private static final int MAX_HEALTH = 100;
	private static final int HEAL_MANA_COST = 8;
	private static final int FIXED_HEAL_AMOUNT = 15;
	private int health;
	private int mana;
	private int playerRow;
	private int playerCol;
	private Weapon playerWeapon;

	public Player() {
		playerWeapon = new Dagger("Knifey", false);
		playerRow = 0;
		playerCol = 0;
		health = MAX_HEALTH;
		mana = MAX_MANA;
	}

	public void Heal() {
		if (mana >= HEAL_MANA_COST) {
			Random randGen = new Random();
			int healAmount = randGen.nextInt(15) + FIXED_HEAL_AMOUNT;
			health += healAmount;
			if (health > MAX_HEALTH) {
				health = MAX_HEALTH;
			}
			mana -= HEAL_MANA_COST;
			System.out.println("\nYou cast a healing spell for 8 mana. Healed for " + healAmount);
		} else {
			System.out.println("Not enough mana!");
		}
	}

	public Weapon getWeapon() {
		return playerWeapon;
	}

	public void setWeapon(Weapon w) {
		playerWeapon = w;
	}

	public int damageRoll() {
		return playerWeapon.damageRoll();
	}

	public int getHealth() {
		return health;
	}

	public int getMana() {
		return mana;
	}

	public int getRow() {
		return playerRow;
	}

	public void setRow(int i) {
		playerRow = i;
	}

	public int getCol() {
		return playerCol;
	}

	public void setCol(int i) {
		playerCol = i;
	}

	public void changeHealth(int i) {
		health += i;
	}

	public void addRow() {
		playerRow++;
	}

	public void subtractRow() {
		playerRow--;
	}

	public void addCol() {
		playerCol++;
	}

	public void subtractCol() {
		playerCol--;
	}
}