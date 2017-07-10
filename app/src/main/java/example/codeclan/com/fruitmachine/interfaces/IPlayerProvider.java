package example.codeclan.com.fruitmachine.interfaces;

import java.util.ArrayList;

import example.codeclan.com.fruitmachine.models.Player;

/**
 * Created by user on 09/07/2017.
 */

public interface IPlayerProvider
{
    int addPlayer(Player player);
    Player getPlayer(int id);
    ArrayList<Player> getAllPlayers();
    void updatePlayer(int id, String firstName, String lastName, String email, int bank);
    int deletePlayer(Player player);
}
