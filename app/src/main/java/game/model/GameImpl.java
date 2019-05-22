package game.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class GameImpl implements Game {
    private List<Player> players = new ArrayList<>(2);

    @Override
    public void saveField(String filename) throws FileNotFoundException {

    }

    @Override
    public void loadField(String filename) throws FileNotFoundException {

    }

    @Override
    public void eat(int playerIndex, int pawnIndex) throws IllegalArgumentException{
        if((playerIndex>1 || playerIndex<0) || (pawnIndex>4 || pawnIndex<0)){
            throw new IllegalArgumentException();
        }
        boolean res = false;
        int cmt=0;
        while(!res && cmt<4){
            if(players.get(playerIndex).getPawns().getIndexAtIndex(pawnIndex) == getIndexComparedToOtherPlayer(1-playerIndex,cmt)){
                res = true;
            } else {
                cmt++;
            }
        }
        players.get(1-playerIndex).getPawns().getsEaten(cmt);
    }

    @Override
    public int getIndexComparedToOtherPlayer(int playerIndex, int pawnIndex) throws IllegalArgumentException {
        int newPawnIndex;
        if((playerIndex>1 || playerIndex<0) || (pawnIndex>4 || pawnIndex<0)) {
            if (players.get(playerIndex).getPawns().getIndexAtIndex(pawnIndex) + 20 > (BOARD_LENGTH-1)) {
                newPawnIndex = players.get(playerIndex).getPawns().getIndexAtIndex(pawnIndex) + 20 - (BOARD_LENGTH-1);
            } else {
                newPawnIndex = players.get(playerIndex).getPawns().getIndexAtIndex(pawnIndex) + 20;
            }
        } else {
            throw new IllegalArgumentException();
        }
        return newPawnIndex;
    }
}
