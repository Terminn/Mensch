package game;

import game.gameExceptions.PawnCannotMoveException;

class PlayerImpl implements Player {
    private boolean[] finish = new boolean[4];
    private PawnColor color;
    private Pawn pawns;

    public PlayerImpl(PawnColor color) {
        this.color = color;
        pawns = new PawnImpl(color);
    }

    public Pawn getPawns(){
        return pawns;
    }

    @Override
    public boolean[] getFinish() {
        return finish;
    }

    @Override
    public PawnColor getColor() {
        return color;
    }

    @Override
    public int throwDice() {
        return (int) ((Math.random() * ((6 - 1) + 1)) + 1);
    }

    @Override
    public void choosePawn() throws PawnCannotMoveException {

    }

    public static void main(String[] args) {
       Player p = new PlayerImpl(PawnColor.Red);
       for(boolean b : p.getFinish()) {
           System.out.println(b);
       }
    }
}
