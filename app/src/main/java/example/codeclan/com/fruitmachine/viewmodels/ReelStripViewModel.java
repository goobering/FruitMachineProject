package example.codeclan.com.fruitmachine.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;

import java.util.ArrayList;

import example.codeclan.com.fruitmachine.BR;
import example.codeclan.com.fruitmachine.R;
import example.codeclan.com.fruitmachine.models.Reel;
import example.codeclan.com.fruitmachine.models.Symbol;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * Created by user on 10/07/2017.
 */

public class ReelStripViewModel extends BaseObservable
{
    private Reel reel;

    @Bindable
    private SymbolItemViewModel symbolItem;

    public ObservableBoolean canNudge = new ObservableBoolean(false);
    public ObservableBoolean canHold = new ObservableBoolean(false);

    public ItemBinding<SymbolItemViewModel> symbolItemBinding = ItemBinding.of(BR.symbol, R.layout.item_symbol);

    public ReelStripViewModel(Reel reel)
    {
        this.reel = reel;
    }

    public ArrayList<SymbolItemViewModel> getSymbolItems()
    {
        ArrayList<SymbolItemViewModel> symbolItems = new ArrayList<SymbolItemViewModel>();
        for(Symbol symbol : reel.getSymbols())
        {
            symbolItems.add(new SymbolItemViewModel(symbol));
        }
        return symbolItems;
    }
}
