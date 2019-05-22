package game.model;

import java.util.ArrayList;
import java.util.List;

import game.gameExceptions.PawnCannotMoveException;

public class PlayerImpl implements Player {
    private boolean[] finish = new boolean[4];
    private PawnColor color;
    private Pawn pawns;
    private boolean throwable;
    private int dice;

    public PlayerImpl(PawnColor color) {
        this.color = color;
        pawns = new PawnImpl(color);
        throwable = false;
        dice = 0;
    }

    public boolean isThrowable() {
        return throwable;
    }

    @Override
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
    public List<Integer> choosePawn() throws PawnCannotMoveException {
        dice = throwDice();
        boolean[] canMove = pawns.canMove(dice);
        List<Integer> list = new ArrayList<>();
        if(canMove.equals(new boolean[4])){
            throw new PawnCannotMoveException();
        } else {
            for(int i=0;i<4;i++){
                if(canMove[i]){
                    list.add(i);
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
       Player p = new PlayerImpl(PawnColor.Red);
       for(boolean b : p.getFinish()) {
           System.out.println(b);
       }
    }
}
