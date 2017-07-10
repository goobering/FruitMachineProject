package example.codeclan.com.fruitmachine.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import example.codeclan.com.fruitmachine.enums.PlayerDb;
import example.codeclan.com.fruitmachine.interfaces.IPlayerProvider;
import example.codeclan.com.fruitmachine.models.Player;

/**
 * Created by user on 09/07/2017.
 */

public class PlayerHandler extends DatabaseHandler implements IPlayerProvider
{
    public PlayerHandler(Context context)
    {
        super(context);
    }

    public int addPlayer(Player player)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PlayerDb.COL_FIRST_NAME.toString(), player.getFirstName());
        values.put(PlayerDb.COL_LAST_NAME.toString(), player.getLastName());
        values.put(PlayerDb.COL_EMAIL.toString(), player.getEmail());
        values.put(PlayerDb.COL_BANK.toString(), player.getBank());

        int id = -1;

        try
        {
            // db.insertOrThrow returns the id of last inserted row, or throws exception if something went wrong
            id = (int) db.insertOrThrow(PlayerDb.TABLE_PLAYERS.toString(), null, values);
        }
        catch(SQLException ex)
        {
            Log.e("PlayerHandler", "exception", ex);
            throw ex;
        }
        finally
        {
            if(db.isOpen())
            {
                db.close();
            }
        }

        return id;
    }

    public Player getPlayer(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Player player = null;

        Cursor cursor = db.query(PlayerDb.TABLE_PLAYERS.toString(),
                new String[]
                        {
                                PlayerDb.COL_ID.toString(),
                                PlayerDb.COL_FIRST_NAME.toString(),
                                PlayerDb.COL_LAST_NAME.toString(),
                                PlayerDb.COL_EMAIL.toString(),
                                PlayerDb.COL_BANK.toString()
                        },
                PlayerDb.COL_ID.toString() + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
        {
            cursor.moveToFirst();
            player = new Player(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), Integer.parseInt(cursor.getString(4)));
        }

        return player;
    }

    public ArrayList<Player> getAllPlayers()
    {
        ArrayList<Player> playerList = new ArrayList<Player>();

        String sql = String.format("SELECT * FROM %s", PlayerDb.TABLE_PLAYERS.toString());
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        Player player = null;
        if (cursor.moveToFirst())
        {
            do
            {
                player = new Player();
                player.setId(Integer.parseInt(cursor.getString(0)));
                player.setFirstName(cursor.getString(1));
                player.setLastName(cursor.getString(2));
                player.setEmail(cursor.getString(3));
                player.setBank(Integer.parseInt(cursor.getString(4)));
                playerList.add(player);
            } while (cursor.moveToNext());
        }
        return playerList;
    }

    public void updatePlayer(int id, String firstName, String lastName, String email, int bank)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PlayerDb.COL_FIRST_NAME.toString(), firstName);
        contentValues.put(PlayerDb.COL_LAST_NAME.toString(), lastName);
        contentValues.put(PlayerDb.COL_EMAIL.toString(), email);
        contentValues.put(PlayerDb.COL_BANK.toString(), bank);

        SQLiteDatabase db = this.getReadableDatabase();
        db.update(PlayerDb.TABLE_PLAYERS.getName(), contentValues, "id=" + id, null);
    }

    public int deletePlayer(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();
        int numRowsAffected = db.delete(PlayerDb.TABLE_PLAYERS.toString(), PlayerDb.COL_ID.toString() + " = ?",
                new String[] { String.valueOf(player.getId()) });
        db.close();
        return numRowsAffected;
    }
}
