package game.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class GameImpl implements Game {
    private List<Player> players = new ArrayList<>(2);

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
