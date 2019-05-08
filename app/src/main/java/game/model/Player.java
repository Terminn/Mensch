package game.model;

import java.util.List;

import game.gameExceptions.PawnCannotMoveException;

public interface Player {
    public boolean[] getFinish();
    public PawnColor getColor();
    Pawn getPawns();
    public int throwDice();
    public List<Integer> choosePawn() throws PawnCannotMoveException;

}
