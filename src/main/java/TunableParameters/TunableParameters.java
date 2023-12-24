package TunableParameters;

public class TunableParameters {

	public static final String INTRO_ART_FILE_PATH = "src/main/resources/Art/wizardArt.txt";
	public static final String INTRO_MESSAGE = "\nYou've woken up in a dungeon full of monsters and you must escape! \n\nINSTRUCTIONS:\n\n"
			+ "Player starts at the top-left corner of the grid. 7 represents the player. \n1s represent walls,"
			+ " while 0s represent open paths. Asterisks (*) represent unexplored spaces. \nType the W, A, S,"
			+ " and D keys to move up, left, down, and right, respectively, and then press enter."
			+ "\nBe wary of monsters! Enemies are randomly placed, with combat transpiring in a turn-based fashion."
			+ "\nThe player starts with a simple dagger and a healing spell to use both in and out of combat."
			+ "\nBetter weapons may be looted off of enemies!"
			+ "\n\nReach the bottom-right corner of the dungeon to escape!";

	public static final String LINE_BREAK = "\n--------------------------"
			+ "-----------------------------------------------" + "-------------------|";

	public static final String GAME_OVER_MESSAGE = "\nYou died. GAME OVER\n";

	public static final String COMBAT_QUERY = " encountered! You must defeat"
			+ " this foe to continue! Type \"ROLL\" to attack with your currently \nequipped weapon, or \"HEAL\" to cast"
			+ " a healing spell on yourself. Either action consumes a turn!";

	public static final String ENEMY = "Enemy ";
	public static final String DEFEATED = " defeated!";
	public static final String PLAYER_MOVE_MESSAGE = "Player Move: ";

	public static final String INVALID_INPUT_MESSAGE = "\nInvalid input. Try again";

	public static final String NEW_WEAPON_MESSAGE = "\nYou loot a new weapon: ";
	public static final String CURRENT_WEAPON_MESSAGE = "Your current weapon: ";
}
