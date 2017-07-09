package example.codeclan.com.fruitmachine.enums;

/**
 * Created by user on 07/07/2017.
 */

public enum PlayerDb
{
    TABLE_PLAYERS("players"),
    COL_ID("id"),
    COL_FIRST_NAME("first_name"),
    COL_LAST_NAME("last_name"),
    COL_EMAIL("email"),
    COL_BANK("bank");

    private String text;

    PlayerDb(String text)
    {
        this.text = text;
    }

    public String toString()
    {
        return text;
    }

    public String getName()
    {
        return name();
    }
}
