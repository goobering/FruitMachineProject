package example.codeclan.com.fruitmachine.models;

import java.util.Date;

/**
 * Created by user on 07/07/2017.
 */

public class GameScore
{
    private int id, playerId;
    private String name;
    private Date playedAt;

    public GameScore()
    {

    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getPlayerId()
    {
        return playerId;
    }

    public void setPlayerId(int playerId)
    {
        this.playerId = playerId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Date getPlayedAt()
    {
        return playedAt;
    }

    public void setPlayedAt(Date playedAt)
    {
        this.playedAt = playedAt;
    }
}
