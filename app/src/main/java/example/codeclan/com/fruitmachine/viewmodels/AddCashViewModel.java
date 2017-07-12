package example.codeclan.com.fruitmachine.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

import example.codeclan.com.fruitmachine.activities.HomeActivity;
import example.codeclan.com.fruitmachine.interfaces.IPlayerProvider;

/**
 * Created by user on 12/07/2017.
 */

public class AddCashViewModel extends ViewModel
{
    private final IPlayerProvider playerProvider;
    public final ObservableField<PlayerItemViewModel> player = new ObservableField<PlayerItemViewModel>();

    public AddCashViewModel(IPlayerProvider playerProvider, int playerId)
    {
        this.playerProvider = playerProvider;
        grabPlayer(playerId);
    }

    private void grabPlayer(int id)
    {
        player.set(new PlayerItemViewModel(playerProvider.getPlayer(id)));
    }

    public void submitAmount(View view)
    {
        playerProvider.updatePlayer(player.get().getId(), player.get().getFirstName(), player.get().getLastName(), player.get().getEmail(), player.get().getBank());
        showHome(view);
    }

    private void showHome(View view)
    {
        Context context = view.getContext();
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }
}
