package example.codeclan.com.fruitmachine.enums;

import example.codeclan.com.fruitmachine.models.Symbol;

/**
 * Created by user on 07/07/2017.
 */

public enum SymbolDb
{
    TABLE_SYMBOLS("symbols"),
    COL_ID("id"),
    COL_IMG_SET_NAME("image_set_name"),
    COL_IMG_NAME("image_name"),
    COL_IMG_LOCATION("image_location"),
    COL_SCORE("score");

    private String text;

    SymbolDb(String text)
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
