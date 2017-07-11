package example.codeclan.com.fruitmachine.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

import example.codeclan.com.fruitmachine.R;
import example.codeclan.com.fruitmachine.BR;
import example.codeclan.com.fruitmachine.activities.CreatePlayerActivity;
import example.codeclan.com.fruitmachine.activities.FruitMachineActivity;
import example.codeclan.com.fruitmachine.interfaces.IPlayerProvider;
import example.codeclan.com.fruitmachine.models.Player;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * Created by user on 07/07/2017.
 */

public class HomeViewModel extends ViewModel
{
    //By using an interface I'm decoupling the DB data provider from a concrete implementation - better for testing!
    private final IPlayerProvider playerProvider;

    //Stores a list of available players
    public ObservableList<PlayerItemViewModel> players = new ObservableArrayList<PlayerItemViewModel>();

    //Binds an instance of Player to its layout for showing in a ListView
    public ItemBinding<Player> playerBinding = ItemBinding.of(BR.player, R.layout.item_player);

    //Tracks the Player selected in the ListView
    public PlayerItemViewModel selectedPlayerItem = null;

    //Used to enable/disable Create Player/Confirm Player buttons
    public ObservableBoolean isPlayerSelected = new ObservableBoolean(false);

    public HomeViewModel(IPlayerProvider playerProvider)
    {
        this.playerProvider = playerProvider;
        populateList();
    }

    //Fetches available players from DB, populates list
    private void populateList()
    {
        ArrayList<Player> playerList = playerProvider.getAllPlayers();
        if (playerList != null && playerList.size() > 0)
        {
            players.clear();

            for (Player player : playerList)
            {
                PlayerItemViewModel newplayerItemViewModel = new PlayerItemViewModel(player);
                players.add(newplayerItemViewModel);
            }
        }
    }

    //Takes us to the Create Player view
    public void showCreatePlayer(View view)
    {
        Context context = view.getContext();
        Intent intent = new Intent(context, CreatePlayerActivity.class);
        context.startActivity(intent);
    }

    //Takes us to the Game view
    public void showFruitMachine(View view)
    {
        Context context = view.getContext();
        Intent intent = new Intent(context, FruitMachineActivity.class);
        context.startActivity(intent);
    }

    //Catches clicks on ListView items and sets local properties accordingly.
    //Bit smelly - ideally shouldn't be quite so tight to View
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        selectedPlayerItem = (PlayerItemViewModel) parent.getAdapter().getItem(position);
        isPlayerSelected.set(true);
    }

    //Deletes the selected Player from DB
    //If sucessful deletes from list
    public void deletePlayer(View view)
    {
        Player playerToDelete = playerProvider.getPlayer(selectedPlayerItem.getId());
        if (playerProvider.deletePlayer(playerToDelete) > 0)
        {
            players.remove(selectedPlayerItem);
        }
    }
}
