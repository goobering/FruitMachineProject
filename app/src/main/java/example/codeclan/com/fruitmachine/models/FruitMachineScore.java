package example.codeclan.com.fruitmachine.models;

/**
 * Created by user on 07/07/2017.
 */

public class FruitMachineScore extends GameScore
{
    private int score;

    public FruitMachineScore(int score)
    {
        this.score = score;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }
}
