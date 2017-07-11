package example.codeclan.com.fruitmachine.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import example.codeclan.com.fruitmachine.enums.SymbolDb;
import example.codeclan.com.fruitmachine.interfaces.ISymbolProvider;
import example.codeclan.com.fruitmachine.models.Symbol;

/**
 * Created by user on 07/07/2017.
 */

public class SymbolHandler extends DatabaseHandler implements ISymbolProvider
{

    public SymbolHandler(Context context)
    {
        super(context);
    }

    public int addSymbol(Symbol symbol)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SymbolDb.COL_IMG_SET_NAME.toString(), symbol.getImageSetName());
        values.put(SymbolDb.COL_IMG_NAME.toString(), symbol.getImageName());
        values.put(SymbolDb.COL_IMG_LOCATION.toString(), symbol.getImageLocation());
        values.put(SymbolDb.COL_SCORE.toString(), symbol.getScore());

        // db.insert returns the id of last inserted row
        int id = (int) db.insert(SymbolDb.TABLE_SYMBOLS.toString(), null, values);
        db.close();
        return id;
    }

    public Symbol getSymbol(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Symbol symbol = null;

        Cursor cursor = db.query(SymbolDb.TABLE_SYMBOLS.toString(),
                new String[]
                        {
                                SymbolDb.COL_ID.toString(),
                                SymbolDb.COL_IMG_SET_NAME.toString(),
                                SymbolDb.COL_IMG_NAME.toString(),
                                SymbolDb.COL_IMG_LOCATION.toString(),
                                SymbolDb.COL_SCORE.toString()
                        },
                SymbolDb.COL_ID.toString() + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
        {
            cursor.moveToFirst();
            symbol = new Symbol(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), Integer.parseInt(cursor.getString(4)));
        }
        return symbol;
    }

    public Symbol getSymbolByImageName(String imageName)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Symbol symbol = null;

        Cursor cursor = db.query(SymbolDb.TABLE_SYMBOLS.toString(),
                new String[]
                        {
                                SymbolDb.COL_ID.toString(),
                                SymbolDb.COL_IMG_SET_NAME.toString(),
                                SymbolDb.COL_IMG_NAME.toString(),
                                SymbolDb.COL_IMG_LOCATION.toString(),
                                SymbolDb.COL_SCORE.toString()
                        },
                SymbolDb.COL_IMG_NAME.toString() + "=?",
                new String[]{imageName}, null, null, null, null);

        if (cursor != null)
        {
            cursor.moveToFirst();
            symbol = new Symbol(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), Integer.parseInt(cursor.getString(4)));
        }
        return symbol;
    }

    public ArrayList<Symbol> getAllSymbolsFromSet(String imageSetName)
    {
        ArrayList<Symbol> symbolList = new ArrayList<Symbol>();

        String sql = String.format("SELECT * FROM %s WHERE %s = '%s'", SymbolDb.TABLE_SYMBOLS, SymbolDb.COL_IMG_SET_NAME, imageSetName);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        Symbol symbol = null;
        if (cursor.moveToFirst())
        {
            do
            {
                symbol = new Symbol();
                symbol.setId(Integer.parseInt(cursor.getString(0)));
                symbol.setImageName(cursor.getString(1));
                symbol.setImageSetName(cursor.getString(2));
                symbol.setScore(Integer.parseInt(cursor.getString(3)));
                symbol.setImageLocation(cursor.getString(4));
                symbolList.add(symbol);
            } while (cursor.moveToNext());
        }
        return symbolList;
    }

    public void updateSymbolLocation(int id, String location)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SymbolDb.COL_IMG_LOCATION.getName(), location); //These Fields should be your String values of actual column names

        SQLiteDatabase db = this.getReadableDatabase();
        db.update(SymbolDb.TABLE_SYMBOLS.toString(), contentValues, "id=" + id, null);
    }
}
