package example.codeclan.com.fruitmachine.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import example.codeclan.com.fruitmachine.BR;
import example.codeclan.com.fruitmachine.R;
import example.codeclan.com.fruitmachine.adapters.ReelStripViewModelAdapter;
import example.codeclan.com.fruitmachine.interfaces.IPlayerProvider;
import example.codeclan.com.fruitmachine.interfaces.ISymbolProvider;
import example.codeclan.com.fruitmachine.models.FruitMachine;
import example.codeclan.com.fruitmachine.models.Player;
import example.codeclan.com.fruitmachine.models.Reel;
import example.codeclan.com.fruitmachine.models.Symbol;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * Created by user on 07/07/2017.
 */

public class PlayFruitMachineViewModel extends ViewModel
{
    //By using an interface I'm decoupling the DB data provider from a concrete implementation - better for testing!
    private final IPlayerProvider playerProvider;
    private final ISymbolProvider symbolProvider;
    public PlayerItemViewModel player;

    public ObservableList<ReelStripViewModel> reelStrips = new ObservableArrayList<ReelStripViewModel>();
    public ItemBinding<ReelStripViewModel> reelStripBinding = ItemBinding.of(BR.reelStrip, R.layout.item_reel_strip);

    public PlayFruitMachineViewModel(IPlayerProvider playerProvider, ISymbolProvider symbolProvider)
    {
        this.playerProvider = playerProvider;
        this.symbolProvider = symbolProvider;

        buildFruitMachine();
    }

    private void buildFruitMachine()
    {
        ArrayList<Symbol> symbols = symbolProvider.getAllSymbolsFromSet("classic");

        for(int i = 0; i < 5; i++)
        {
            Reel reel = new Reel(symbols);
            reelStrips.add(new ReelStripViewModel(reel));
        }
    }

    public final ReelStripViewModelAdapter<ReelStripViewModel> adapter = new ReelStripViewModelAdapter<>();
}
