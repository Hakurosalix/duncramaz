package GameEngine;

/*
* Game.java
* A game class for use in the Launcher.DunCraMaz program
* @author Jack Manges
*/

//=====================================================================================================
// TODO:
//      Wrap Java functionalities
//
//=====================================================================================================

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Enemies.Enemy;
import Maze.Maze;
import Player.Player;
import TunableParameters.TunableParameters;
import Weapons.Weapon;

public class GameEngine {
	private static final String ROLL = "roll";
	private static final String HEAL = "heal";
	private static final String YES = "yes";
	private static final String NO = "no";
	private final Player player;
	private final Scanner scan;
	private final Maze dungeon;

	public GameEngine(Maze maze, Player player) {
		this.dungeon = maze;
		this.player = player;
		this.scan = new Scanner(System.in);
	}

	public void run() throws FileNotFoundException {
		printIntro();
		primaryGameLoop();
		scan.close();
	}

	private void primaryGameLoop() {
		dungeon.printFogMaze();
		do {
			PlayerMove();
			if (dungeon.combatCheck()) {
				combatLoop();
			}
		} while (!dungeon.endCheck());
	}

	private void combatLoop() {
		Enemy combatEnemy = dungeon.getCombatEnemy();
		String move;

		System.out.println(TunableParameters.ENEMY + combatEnemy.getName() + TunableParameters.COMBAT_QUERY);

		while (true) { // Combat loop

			printPlayerStatus();
			System.out.print(TunableParameters.PLAYER_MOVE_MESSAGE);
			move = scan.next();
			move = move.toLowerCase();
			printLineBreak();
			boolean validInput = true;

			switch (move) {
			case ROLL: {
				playerAttackTurn(combatEnemy);
				// Enemy dead?
				if (combatEnemy.getHealth() <= 0) {
					combatEnemy.setRow(-1);
					combatEnemy.setCol(-1); // removes enemy from play
					System.out.println(
							'\n' + TunableParameters.ENEMY + combatEnemy.getName() + TunableParameters.DEFEATED);
					postCombatLootQuery(combatEnemy.getWeapon());
					dungeon.printFogMaze();
					return;
				}

				break;
			}

			case HEAL: {
				player.Heal();
				break;
			}

			default: {
				System.out.println(TunableParameters.INVALID_INPUT_MESSAGE);
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
		System.out.println(TunableParameters.NEW_WEAPON_MESSAGE + loot.toString());
		System.out.println(TunableParameters.CURRENT_WEAPON_MESSAGE + player.getWeapon().toString());

		while (true) { // Prompt user to equip new weapon or not

			System.out.print("\nEquip " + loot.getName() + " instead? (Type YES or NO): ");
			String response = scan.next();
			response = response.toLowerCase();

			switch (response) {

			case YES: {
				player.setWeapon(loot);
				printLineBreak();
				System.out.println("\n" + loot.getName() + " equipped!");
				return;
			}
			case NO: {
				printLineBreak();
				return;
			}
			default: {
				System.out.println(TunableParameters.INVALID_INPUT_MESSAGE);
				break;
			}
			}
		}

	}

	private void enemyTurn(Enemy combatEnemy) {
		int enemyAttackRoll = combatEnemy.damageRoll();
		System.out.println('\n' + TunableParameters.ENEMY + combatEnemy.getName() + combatEnemy.getAttackName());
		System.out.println("You take " + enemyAttackRoll + " damage.");
		player.changeHealth(-1 * enemyAttackRoll);

	}

	private void playerAttackTurn(Enemy combatEnemy) {
		int playerAttack = player.damageRoll();
		combatEnemy.changeHealth(-1 * playerAttack);
		System.out.println("\nYou attack with " + player.getWeapon().getName() + " for " + playerAttack + " damage.");

	}

	private void PlayerMove() {
		System.out.print(TunableParameters.PLAYER_MOVE_MESSAGE);
		String move = scan.next();
		dungeon.executePlayerMove(move);
		dungeon.printFogMaze();

	}

	private void gameOverTest() {
		if (player.getHealth() <= 0) {
			System.out.println(TunableParameters.GAME_OVER_MESSAGE);
			System.exit(0);
		}
	}

	private void printIntro() throws FileNotFoundException {
		File artFile = new File(TunableParameters.INTRO_ART_FILE_PATH);
		Scanner fileScan = new Scanner(artFile);
		while (fileScan.hasNextLine()) {
			System.out.println(fileScan.nextLine());
		}
		fileScan.close();

		printLineBreak();
		System.out.println(TunableParameters.INTRO_MESSAGE);

	}

	private void printLineBreak() {
		System.out.println(TunableParameters.LINE_BREAK);
	}

	private void printPlayerStatus() {
		System.out.println("\nPlayer HP: " + player.getHealth() + "\tMana: " + player.getMana());
	}

}
