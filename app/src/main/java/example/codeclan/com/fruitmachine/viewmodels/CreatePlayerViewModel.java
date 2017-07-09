package example.codeclan.com.fruitmachine.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import example.codeclan.com.fruitmachine.interfaces.IPlayerProvider;
import example.codeclan.com.fruitmachine.models.Player;

/**
 * Created by user on 09/07/2017.
 */

public class CreatePlayerViewModel extends BaseObservable
{
    //By using an interface I'm decoupling the DB data provider from a concrete implementation - better for testing!
    private final IPlayerProvider playerProvider;
    public Player player;

    public CreatePlayerViewModel(IPlayerProvider playerProvider)
    {
        this.playerProvider = playerProvider;
        this.player = new Player();
    }

    public void createPlayer(View view)
    {
        player.setBank(0);
        playerProvider.addPlayer(player);
    }
}
