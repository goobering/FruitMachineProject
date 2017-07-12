package example.codeclan.com.fruitmachine.helpers;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;

import java.util.Random;

/**
 * Created by user on 12/07/2017.
 */

public class NumberHelpers
{
    public static void setObservableBooleanOverZero(ObservableBoolean bool, ObservableInt number)
    {
        if(number.get() > 0)
        {
            bool.set(true);
        }
        else
        {
            bool.set(false);
        }
    }

    public static void setRandomObservableInt(ObservableInt number)
    {
        Random rand = new Random();
        number.set(rand.nextInt(4));
    }
}
