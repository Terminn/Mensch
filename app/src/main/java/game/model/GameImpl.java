package game.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class GameImpl implements Game {
    private List<Player> players;

    public GameImpl(int numberPlayers){
        players = new ArrayList<>(numberPlayers);
    }

    @Override
    public void saveField(String filename) throws FileNotFoundException {


            try{

                OutputStream os = new FileOutputStream(filename);//File wird hergestellt
                DataOutputStream dos = new DataOutputStream(os);


                for(int i = 0; i< 2; i++){


                    dos.writeBytes(players.get(i).getColor().name());


                }

                //Farben wurden gespeichert

                //********************************************************************************************************

                for(int i=0; i<2; i++){
                    for(int j=0; j<4; j++){


                        dos.writeBoolean(players.get(i).getFinish()[j]); //Speichert Ob die spiel Figuren schon richtig platzier worden sind oder nicht



                    }

                }

                //getFinish gespeichert

                //*******************************************************************************************

                for(int i=0; i<2; i++){
                    for(int j=0; j<4; j++){

                        dos.writeInt(players.get(i).getPawns().getIndexAtIndex(j));
                    }


                }
                // Die index spiel figuren wurde gespeichert
                //*******************************************************************************************

                for(int i = 0; i <2; i++){

                    dos.writeBoolean(players.get(i).isThrowable());

                }

                //Es wurde gespeichert wer am Zug ist

                //*******************************************************************************************




                dos.close();



            } catch (IOException e) {
                e.printStackTrace();
            }



    }

    @Override
    public void loadField(String filename) throws FileNotFoundException {
        try {
            InputStream is = null;
            DataInputStream dis = null;


            try {
                is = new FileInputStream(filename);//file von is gelesen
                dis = new DataInputStream(is);//und in DataIntputStream gespeichert

            } catch (FileNotFoundException x) {
                throw new FileNotFoundException();
            }

            String strDis=null;

            for(int i = 0; i< 2; i++){

                strDis = dis.readUTF();
                 players.get(i).setColor(PawnColor.valueOf(strDis));

            }


            //*****************************************************************************
            boolean boolDis;
            for(int i =0; i <2;i++){
                for(int j=0; j<4;j++){

                    boolDis= dis.readBoolean();
                    players.get(i).setFinish(j, boolDis);

                }
            }

            //*****************************************************************************

            int intDis;
            for(int i =0; i <2;i++){
                for(int j=0; j<4;j++){

                    intDis= dis.readInt();
                    players.get(i).getPawns().setIndexAtIndex(j,intDis);

                }
            }

            //*****************************************************************************

            for(int i = 0; i< 2; i++){

                boolDis=dis.readBoolean();
                players.get(i).setisThroable(boolDis);

            }






        } catch (FileNotFoundException x) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            e.printStackTrace();
        }


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
