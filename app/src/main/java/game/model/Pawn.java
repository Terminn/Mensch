package game.model;

import game.gameExceptions.PawnCannotMoveException;

public interface Pawn {
    public int getIndexAtIndex(int index);
    public PawnColor getColor();
    public boolean canMoveSpecificPawn(int amount, int index);
    public boolean[] canMove(int amount);
    void getsEaten(int index);
    void movePawn(int amount, int index);
    void setIndexAtIndex(int indexes, int index);

}

