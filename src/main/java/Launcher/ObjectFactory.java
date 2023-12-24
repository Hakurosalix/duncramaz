package Launcher;

import GameEngine.GameEngine;
import Maze.Maze;
import Player.Player;

public abstract class ObjectFactory {

	private static final Player defaultPlayer = new Player();
	private static final Maze defaultMaze = new Maze(defaultPlayer);

	private static final GameEngine defaultGameEngine = new GameEngine(defaultMaze, defaultPlayer);

	private ObjectFactory() {
	}

	public static Player getDefaultPlayer() {
		return defaultPlayer;
	}

	public static Maze getDefaultMaze() {
		return defaultMaze;
	}

	public static GameEngine getDefaultGameEngine() {
		return defaultGameEngine;
	}

}
