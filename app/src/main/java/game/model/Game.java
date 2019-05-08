package game.model;

import java.io.FileNotFoundException;

import game.gameExceptions.ColorAlreadyChosenException;
import game.gameExceptions.InvalidNumberPlayersException;
import game.model.PawnColor;
import game.model.Player;

public interface Game {
    public static final int BOARD_LENGTH = 41;
    public void saveField(String filename) throws FileNotFoundException;
    public void loadField(String filename) throws FileNotFoundException;
    void eat(Player p, int index);
    Player getPlayer(PawnColor color);
}
