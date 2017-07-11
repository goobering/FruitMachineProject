package example.codeclan.com.fruitmachine.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by user on 11/07/2017.
 */

public class FruitMachineViewModel extends BaseObservable
{
    @Bindable
    private ReelStripViewModel reelStrip;

    public FruitMachineViewModel()
    {

    }
}
