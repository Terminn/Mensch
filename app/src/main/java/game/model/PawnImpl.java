package game.model;

import game.gameExceptions.PawnCannotMoveException;

class PawnImpl implements Pawn {
    private PawnColor color;
    private int[] indexes = new int[4];

    public PawnImpl(PawnColor color) {
        this.color = color;
    }

    @Override
    public int getIndexAtIndex(int index) throws IndexOutOfBoundsException{
        return indexes[index];
    }

    @Override
    public PawnColor getColor() {
        return color;
    }

    @Override
    public void getsEaten(int index) {
        indexes[index] = 0;
    }

    @Override
    public void movePawn(int amount, int index) throws PawnCannotMoveException {
        indexes[index] += amount;
    }

    @Override
    public boolean canMoveSpecificPawn(int amount, int index){
        boolean res = true;
        if(amount<6 && indexes[index]==0) {
            res = false;
        } else if(amount>(Game.BOARD_LENGTH - indexes[index] + 4)) {
            res = false;
        } else {
            int cmt=0;
            while(res && cmt<4){
                if(indexes[index]+amount == indexes[cmt]) {
                    res = false;
                }
            }
        }
        return res;
    }

    @Override
    public boolean[] canMove(int amount) {
        boolean[] res = {true,true,true,true};
        for(int i=0;i<res.length;i++){
            res[i] = canMoveSpecificPawn(amount,i);
        }
        return res;
    }


}
