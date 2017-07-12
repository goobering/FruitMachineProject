package example.codeclan.com.fruitmachine.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.ArrayList;

import example.codeclan.com.fruitmachine.models.Reel;
import example.codeclan.com.fruitmachine.models.Symbol;

/**
 * Created by user on 11/07/2017.
 */

public class BadButNecessaryViewModel extends BaseObservable
{
    @Bindable
    private ReelStripViewModel reelStrip;

    public BadButNecessaryViewModel()
    {

    }
}
