package example.codeclan.com.fruitmachine.models;

import java.util.ArrayList;

/**
 * Created by user on 07/07/2017.
 */

public class FruitMachine extends Game
{
    private int lastAmountWon;
    private boolean lastTurnWon;
    private ArrayList<Reel> reels;

    public FruitMachine(ArrayList<Reel> reels)
    {
        this.reels = reels;
    }

    public int getLastAmountWon()
    {
        return lastAmountWon;
    }

    public void setLastAmountWon(int lastAmountWon)
    {
        this.lastAmountWon = lastAmountWon;
    }

    public boolean isLastTurnWon()
    {
        return lastTurnWon;
    }

    public void setLastTurnWon(boolean lastTurnWon)
    {
        this.lastTurnWon = lastTurnWon;
    }

    public ArrayList<Reel> getReels()
    {
        return reels;
    }

    public void setReels(ArrayList<Reel> reels)
    {
        this.reels = reels;
    }
}
