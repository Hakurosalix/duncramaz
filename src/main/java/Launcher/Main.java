package Launcher;
/*
* Launcher.DunCraMaz.java
*  A program for a terminal-based Maze.Maze Navigator/Dungeon Crawler game.
* @author Jack Manges
*/

import java.io.FileNotFoundException;

import GameEngine.GameEngine;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		GameEngine gameEngine = ObjectFactory.getDefaultGameEngine();
		gameEngine.run();
	}
}
