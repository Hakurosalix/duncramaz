/*
 * DunCraMaz.java
 *  A program for a terminal-based Maze Navigator/Dungeon Crawler game.  
 * @author Jack Manges
 */



import java.io.FileNotFoundException;

public class DunCraMaz
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Game game = new Game();
        game.run();
    }
}
