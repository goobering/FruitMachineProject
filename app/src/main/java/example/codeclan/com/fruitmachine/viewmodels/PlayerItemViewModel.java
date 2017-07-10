package example.codeclan.com.fruitmachine.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.view.View;

import example.codeclan.com.fruitmachine.models.Player;

/**
 * Created by user on 10/07/2017.
 */

public class PlayerItemViewModel extends BaseObservable
{
    @Bindable
    private Player player;

    private final ObservableBoolean selected = new ObservableBoolean();

    public PlayerItemViewModel(Player player)
    {
        this.player = player;
    }

    public int getId()
    {
        return player.getId();
    }

    public String getFirstName()
    {
        return player.getFirstName();
    }

    public String getLastName()
    {
        return player.getLastName();
    }

    public String getEmail()
    {
        return player.getEmail();
    }

    public String getBank()
    {
        return String.valueOf(player.getBank());
    }

    public ObservableBoolean getSelected()
    {
        return selected;
    }

    public interface OnItemClickListener {
        void onItemClick(PlayerItemViewModel item);
    }

    @Bindable
    OnItemClickListener listener = new OnItemClickListener()
    {
        @Override
        public void onItemClick(PlayerItemViewModel item)
        {
            selected.set(!selected.get());
        }
    };
}
