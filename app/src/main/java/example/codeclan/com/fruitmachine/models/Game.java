package example.codeclan.com.fruitmachine.models;

/**
 * Created by user on 07/07/2017.
 */

public abstract class Game
{
    private String name;
    private int playerId;

    public Game()
    {

    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getPlayerId()
    {
        return playerId;
    }

    public void setPlayerId(int playerId)
    {
        this.playerId = playerId;
    }
}
