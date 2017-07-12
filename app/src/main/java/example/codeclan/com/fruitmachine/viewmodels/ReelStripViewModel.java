package example.codeclan.com.fruitmachine.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.view.View;

import java.util.ArrayList;

import example.codeclan.com.fruitmachine.BR;
import example.codeclan.com.fruitmachine.R;
import example.codeclan.com.fruitmachine.models.Reel;
import example.codeclan.com.fruitmachine.models.Symbol;
import example.codeclan.com.fruitmachine.services.FruitMachineService;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * Created by user on 10/07/2017.
 */

public class ReelStripViewModel extends BaseObservable
{
    private Reel reel;

    @Bindable
    private SymbolItemViewModel symbolItem;

    public final ObservableInt itemPosition = new ObservableInt();
    public final ObservableBoolean canNudge = new ObservableBoolean(false);
    public final ObservableBoolean canHold = new ObservableBoolean(false);
    public final ObservableBoolean markedForHold = new ObservableBoolean(false);
    public final ObservableArrayList<SymbolItemViewModel> symbols = new ObservableArrayList<SymbolItemViewModel>();
    public final ObservableBoolean holdsEnabled = new ObservableBoolean(false);
    public final ObservableBoolean nudgesEnabled = new ObservableBoolean(false);
    public int nudgeCount = 0;
    public int holdCount = 0;

    public ItemBinding<SymbolItemViewModel> symbolItemBinding = ItemBinding.of(BR.symbol, R.layout.item_symbol);

    public ReelStripViewModel(Reel reel)
    {
        this.reel = reel;
        cloneSymbols();
    }

    private void cloneSymbols()
    {
        ArrayList<SymbolItemViewModel> symbolItems = new ArrayList<SymbolItemViewModel>();
        for(Symbol symbol : reel.getSymbols())
        {
            symbols.add(new SymbolItemViewModel(symbol));
        }
    }

    public ArrayList<SymbolItemViewModel> getSymbols()
    {
        return symbols;
    }

    public void nudgeReel(View view)
    {
        nudgeCount++;
        canNudge.set(false);
        FruitMachineService.nudgeReel(this);
    }

    public void setHold(View view)
    {
        holdCount++;
        markedForHold.set(!markedForHold.get());
        canHold.set(false);
    }
}
