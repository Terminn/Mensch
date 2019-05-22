package game.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import game.gameExceptions.PawnCannotMoveException;

import static java.lang.System.exit;
import static org.junit.Assert.*;

public class PlayerImplTest {

    Player p = new PlayerImpl(PawnColor.Red);
    Pawn pawn = new PawnImpl(PawnColor.Red);
    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();

    @Test
    public void newPlayerImpl() {
        assertNotNull(new PlayerImpl(PawnColor.Red));
        //constructor test
    }

    @Test
    public void isThrowable() {
        assertEquals(false,p.isThrowable());
    }

    @Test
    public void getPawns() {
        assertEquals(pawn.getColor(),p.getPawns().getColor());
    }

    @Test
    public void getFinish() {
        for(boolean b : p.getFinish()){
            assertEquals(false,b);
        }
    }

    @Test
    public void getColor() {
        assertEquals(PawnColor.Red,p.getPawns().getColor());
    }

    @Test
    public void throwDice() {
        int dice;
        for(int i=0;i<100;i++) {
            dice = p.throwDice();
            if (dice > 6 || dice < 1) {
                fail();
            }
        }
    }

    @Test
    public void choosePawn1() {
        int dice = 1;
        assertEquals(list1,p.choosePawn(dice));
    }
    @Test
    public void choosePawn6() {
        int dice = 6;
        list2.add(0);
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list1 = p.choosePawn(dice);
        for(int i=0;i<list1.size();i++){
            assertEquals(list2.get(i),list1.get(i));
        }
    }

}