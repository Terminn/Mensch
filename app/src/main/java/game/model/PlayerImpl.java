package game.model;

import java.util.ArrayList;
import java.util.List;

import game.gameExceptions.PawnCannotMoveException;

public class PlayerImpl implements Player {
    private boolean[] finish = new boolean[4];
    private PawnColor color;
    private Pawn pawns;
    private boolean throwable;

    public PlayerImpl(PawnColor color) {
        this.color = color;
        pawns = new PawnImpl(color);
        throwable = false;
    }

    @Override
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
    public List<Integer> choosePawn(int dice) throws IllegalArgumentException {
        List<Integer> list = new ArrayList<>();
        if(dice <= 6 && dice >= 1) {
            boolean[] canMove = pawns.canMove(dice);
            if(!canMove.equals(new boolean[4])){
                for(int i=0;i<4;i++){
                    if(canMove[i]){
                        list.add(i);
                    }
                }
            }
        } else {
            throw new IllegalArgumentException();
        }

        return list;
    }
}
