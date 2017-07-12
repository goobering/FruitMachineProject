package example.codeclan.com.fruitmachine.services;

import java.util.ArrayList;
import java.util.Collections;

import example.codeclan.com.fruitmachine.viewmodels.ReelStripViewModel;
import example.codeclan.com.fruitmachine.viewmodels.SymbolItemViewModel;

/**
 * Created by user on 11/07/2017.
 */

public class FruitMachineService
{
    public static void nudgeReel(ReelStripViewModel reelStrip)
    {
        Collections.rotate(reelStrip.symbols, 1);
    }
}
