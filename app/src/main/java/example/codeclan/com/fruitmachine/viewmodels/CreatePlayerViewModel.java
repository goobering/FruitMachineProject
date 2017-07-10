package example.codeclan.com.fruitmachine.viewmodels;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.view.View;

import example.codeclan.com.fruitmachine.activities.HomeActivity;
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
        if((player.getFirstName() != null && !player.getFirstName().isEmpty())
            && (player.getLastName() != null && !player.getLastName().isEmpty())
            && ((player.getEmail() != null) && !player.getEmail().isEmpty()))
        {
            playerProvider.addPlayer(player);

            Context context = view.getContext();
            Intent intent = new Intent(context, HomeActivity.class);
            context.startActivity(intent);
        }
    }
}
