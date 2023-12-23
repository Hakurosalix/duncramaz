package GameEngine;

/*
* Game.java
* A game class for use in the Launcher.DunCraMaz program
* @author Jack Manges
*/

//=====================================================================================================
// TODO:
//      Rework this entire file so that it isn't the most bulky/nested/unintelligible
//          code in the world!!
//
//=====================================================================================================

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Enemies.Enemy;
import Maze.Maze;
import Player.Player;
import Weapons.Weapon;

public class GameEngine {
	// Singleton
	private static GameEngine gameEngine = new GameEngine();
	private Player player;
	private Scanner scan;
	private Maze dungeon;

	private GameEngine() {
		player = Player.getInstance();
		dungeon = Maze.getInstance();
		scan = new Scanner(System.in);
	}

	public static GameEngine getInstance() {
		return gameEngine;
	}

	public void run() throws FileNotFoundException {
		printIntro();
		primaryGameLoop();
		scan.close();
	}

	private void primaryGameLoop() {
		dungeon.printFogMaze();
		// Game transpires within this do loop
		do {
			PlayerMove();
			// random enemy encounter check
			if (dungeon.combatCheck()) {
				combatLoop();
			}
		} while (!dungeon.endCheck());
	}

	private void combatLoop() {
		Enemy combatEnemy = dungeon.getCombatEnemy();
		String move;

		System.out.println("Enemy " + combatEnemy.getName() + " encountered! You must defeat"
				+ " this foe to continue! Type \"ROLL\" to attack with your currently \nequipped weapon, or \"HEAL\" to cast"
				+ " a healing spell on yourself. Either action consumes a turn!");

		while (true) { // Combat loop

			System.out.println("\nPlayer.Player HP: " + player.getHealth() + "\tMana: " + player.getMana());
			System.out.print("Player.Player Move: ");
			move = scan.next();
			move = move.toLowerCase();
			printLineBreak();
			Boolean validInput = true;

			switch (move) {

			case "roll": {
				playerAttackTurn(combatEnemy);
				// Enemy dead?
				if (combatEnemy.getHealth() <= 0) {
					combatEnemy.setRow(-1);
					combatEnemy.setCol(-1); // removes enemy from play
					System.out.println("\nEnemy " + combatEnemy.getName() + " defeated!");
					postCombatLootQuery(combatEnemy.getWeapon());
					dungeon.printFogMaze();
					return;
				}

				break;
			}

			case "heal": {
				player.Heal();
				break;
			}

			default: {
				System.out.println("\nInvalid input. Try again.");
				validInput = false;
				break;
			}
			}

			// Enemy attacks after player turn
			if (validInput) {
				enemyTurn(combatEnemy);
				gameOverTest();
			}
		}

	}

	private void postCombatLootQuery(Weapon loot) {
		System.out.println("\nYou loot a new weapon: " + loot.toString());
		System.out.println("Your current weapon: " + player.getWeapon().toString());

		while (true) { // Prompt user to equip new weapon or not

			System.out.print("\nEquip " + loot.getName() + " instead? (Type YES or NO): ");
			String response = scan.next();
			response = response.toLowerCase();

			switch (response) {

			case "yes": {
				player.setWeapon(loot);
				printLineBreak();
				System.out.println("\n" + loot.getName() + " equipped!");
				return;
			}
			case "no": {
				printLineBreak();
				return;
			}
			default: {
				System.out.println("\nInvalid input. Try again");
				break;
			}
			}
		}

	}

	private void enemyTurn(Enemy combatEnemy) {
		int enemyAttackRoll = combatEnemy.damageRoll();
		System.out.println("\nEnemy " + combatEnemy.getName() + combatEnemy.getAttackName());
		System.out.println("You take " + enemyAttackRoll + " damage.");
		player.changeHealth(-1 * enemyAttackRoll);

	}

	private void playerAttackTurn(Enemy combatEnemy) {
		int playerAttack = player.damageRoll();
		combatEnemy.changeHealth(-1 * playerAttack);
		System.out.println("\nYou attack with " + player.getWeapon().getName() + " for " + playerAttack + " damage.");

	}

	private void PlayerMove() {
		System.out.print("Player.Player Move: ");
		String move = scan.next();
		dungeon.executePlayerMove(move);
		dungeon.printFogMaze();

	}

	private void printIntro() throws FileNotFoundException {
		File artFile = new File("src/main/resources/Art/wizardArt.txt");
		Scanner fileScan = new Scanner(artFile);
		while (fileScan.hasNextLine()) {
			System.out.println(fileScan.nextLine());
		}
		fileScan.close();

		printLineBreak();
		System.out.println("\nYou've woken up in a dungeon full of monsters and you must escape! \n\nINSTRUCTIONS:\n\n"
				+ "Player.Player starts at the top-left corner of the grid. 7 represents the player. \n1s represent walls,"
				+ " while 0s represent open paths. Asterisks (*) represent unexplored spaces. \nType the W, A, S,"
				+ " and D keys to move up, left, down, and right, respectively, and then press enter."
				+ "\nBe wary of monsters! Enemies are randomly placed, with combat transpiring in a turn-based fashion."
				+ "\nThe player starts with a simple dagger and a healing spell to use both in and out of combat."
				+ "\nBetter weapons may be looted off of enemies!"
				+ "\n\nReach the bottom-right corner of the dungeon to escape!");

	}

	private void printLineBreak() {
		System.out.println("\n--------------------------" + "-----------------------------------------------"
				+ "-------------------|");
	}

	private void gameOverTest() {
		if (player.getHealth() <= 0) {
			System.out.println("\nYou died. GAME OVER\n");
			System.exit(0);
		}
	}

}
