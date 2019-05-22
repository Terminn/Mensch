package game.model;

import java.io.FileNotFoundException;

import game.gameExceptions.ColorAlreadyChosenException;
import game.gameExceptions.InvalidNumberPlayersException;
import game.model.PawnColor;
import game.model.Player;

public interface Game {
    int BOARD_LENGTH = 41;
    void saveField(String filename) throws FileNotFoundException;
    void loadField(String filename) throws FileNotFoundException;
    void eat(int playerIndex, int pawnIndex) throws IllegalArgumentException;
    int getIndexComparedToOtherPlayer(int playerIndex, int pawnIndex) throws IllegalArgumentException;
}
