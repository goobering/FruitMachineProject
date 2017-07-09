package example.codeclan.com.fruitmachine.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import example.codeclan.com.fruitmachine.enums.PlayerDb;
import example.codeclan.com.fruitmachine.enums.SymbolDb;

/**
 * Created by user on 07/07/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper
{
    //Database Version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "gameDB";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Create Tables
    public void onCreate(SQLiteDatabase db){
        createSymbolTable(db);
        createPlayerTable(db);
    }

    public void createSymbolTable(SQLiteDatabase db)
    {
        String sql = "CREATE TABLE " + SymbolDb.TABLE_SYMBOLS +
                "(" +
                SymbolDb.COL_ID + " INTEGER PRIMARY KEY, " +
                SymbolDb.COL_IMG_NAME + " TEXT UNIQUE, " +
                SymbolDb.COL_IMG_SET_NAME + " TEXT, " +
                SymbolDb.COL_SCORE + " INTEGER, " +
                SymbolDb.COL_IMG_LOCATION + " TEXT UNIQUE" +
                ")";
        db.execSQL(sql);
    }

    public void createPlayerTable(SQLiteDatabase db)
    {
        String sql = "CREATE TABLE " + PlayerDb.TABLE_PLAYERS +
                "(" +
                PlayerDb.COL_ID + " INTEGER PRIMARY KEY, " +
                PlayerDb.COL_FIRST_NAME + " TEXT, " +
                PlayerDb.COL_LAST_NAME + " TEXT, " +
                PlayerDb.COL_EMAIL + " TEXT, " +
                PlayerDb.COL_BANK + " INTEGER" +
                ")";
        db.execSQL(sql);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + SymbolDb.TABLE_SYMBOLS);
        db.execSQL("DROP TABLE IF EXISTS " + PlayerDb.TABLE_PLAYERS);
        onCreate(db);
    }
}
