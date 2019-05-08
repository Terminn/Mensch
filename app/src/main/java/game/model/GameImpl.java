package game.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

class GameImpl implements Game {
    private List<Player> players;

    public GameImpl(int numberPlayers){
        players = new ArrayList<>(numberPlayers);
    }

    @Override
    public void saveField(String filename) throws FileNotFoundException {

    }

    @Override
    public void loadField(String filename) throws FileNotFoundException {

    }

    @Override
    public void eat(Player p, int index) {
        boolean res=false;
        PawnColor pawnColor = null;
        List<Player> playerList = players;
        playerList.remove(p);
        int cmt=0,cmt2=0;
        while(!res && cmt<4){
            while(!res && cmt2<playerList.size()){
                if(p.getPawns().getIndexAtIndex(index)==playerList.get(cmt2).getPawns().getIndexAtIndex(cmt)){
                    res = true;
                    pawnColor = playerList.get(cmt2).getColor();
                }
                cmt2++;
            }
            cmt++;
        }
        getPlayer(pawnColor).getPawns().getsEaten(cmt-1);
    }

    @Override
    public Player getPlayer(PawnColor color) {
        boolean res = true;
        Player p = null;
        int cmt=0;
        while(res && cmt<players.size()){
            if(players.get(cmt).getColor()==color){
                res = false;
                p = players.get(cmt);
            }
            cmt++;
        }
        return p;
    }
}
