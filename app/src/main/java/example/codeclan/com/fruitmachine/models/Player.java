package example.codeclan.com.fruitmachine.models;

import java.util.ArrayList;

/**
 * Created by user on 07/07/2017.
 */

public class Player
{
    private int id, bank;
    private String firstName, lastName, email;
    private ArrayList<Integer> gameScoreIds;

    public Player()
    {
        this.bank = 0;
    }

    public Player(int id, String firstName, String lastName, String email, int bank)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.bank = bank;
    }

    public Player(String firstName, String lastName, String email, int bank)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.bank = bank;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getBank()
    {
        return bank;
    }

    public void setBank(int bank)
    {
        this.bank = bank;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public ArrayList<Integer> getGameScoreIds()
    {
        return gameScoreIds;
    }

    public void setGameScoreIds(ArrayList<Integer> gameScoreIds)
    {
        this.gameScoreIds = gameScoreIds;
    }
}
