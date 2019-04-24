package game;

public interface Player {
    public boolean[] getFinish();
    public PawnColor getColor();
    public int throwDice();
    public void choosePawn() throws PawnCannotMoveException;

}
