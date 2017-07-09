package example.codeclan.com.fruitmachine.viewmodels;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.view.View;

import java.util.ArrayList;

import example.codeclan.com.fruitmachine.activities.CreatePlayerActivity;
import example.codeclan.com.fruitmachine.activities.MainActivity;
import example.codeclan.com.fruitmachine.database.PlayerHandler;
import example.codeclan.com.fruitmachine.interfaces.IPlayerProvider;
import example.codeclan.com.fruitmachine.models.Player;

/**
 * Created by user on 07/07/2017.
 */

public class HomeViewModel extends BaseObservable
{
    //By using an interface I'm decoupling the DB data provider from a concrete implementation - better for testing!
    private final IPlayerProvider playerProvider;
    @Bindable
    public ObservableArrayList<Player> players;

    public HomeViewModel(IPlayerProvider playerProvider)
    {
        this.playerProvider = playerProvider;
        players = new ObservableArrayList<Player>();

        ArrayList<Player> playerList = playerProvider.getAllPlayers();
        if(playerList != null && playerList.size() > 0)
        {
            players.addAll(playerList);
        }
    }

    public void showCreatePlayer(View view)
    {
        Context context = view.getContext();
        Intent intent = new Intent(context, CreatePlayerActivity.class);
        context.startActivity(intent);
    }
}
