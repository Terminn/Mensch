package game;

import java.io.FileNotFoundException;

public interface Game {
    public int[] getField();
    public void saveField(String filename) throws FileNotFoundException;
    public Field loadField(String filename) throws FileNotFoundException;
    public int chooseNumberOfPlayers() throws InvalidNumberPlayersException;
    public Player choosePlayerColor(PawnColor pc) throws ColorAlreadyChosenException;
}
