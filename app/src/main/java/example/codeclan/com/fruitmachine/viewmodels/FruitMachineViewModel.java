package example.codeclan.com.fruitmachine.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import example.codeclan.com.fruitmachine.BR;
import example.codeclan.com.fruitmachine.R;
import example.codeclan.com.fruitmachine.interfaces.IPlayerProvider;
import example.codeclan.com.fruitmachine.interfaces.ISymbolProvider;
import example.codeclan.com.fruitmachine.models.Reel;
import example.codeclan.com.fruitmachine.models.Symbol;
import example.codeclan.com.fruitmachine.services.FruitMachineService;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

import static example.codeclan.com.fruitmachine.helpers.NumberHelpers.setObservableBooleanIfIntOverZero;
import static example.codeclan.com.fruitmachine.helpers.NumberHelpers.setObservableIntToRandom;

/**
 * Created by user on 07/07/2017.
 */

public class FruitMachineViewModel extends ViewModel
{
    //By using an interface I'm decoupling the DB data provider from a concrete implementation - better for testing!
    private final IPlayerProvider playerProvider;
    private final ISymbolProvider symbolProvider;

    public final ObservableField<PlayerItemViewModel> player = new ObservableField<PlayerItemViewModel>();
    public final ObservableInt playerBank = new ObservableInt();
    public final ObservableBoolean isPlayerBroke = new ObservableBoolean();

    public final ObservableList<ReelStripViewModel> reelStrips = new ObservableArrayList<ReelStripViewModel>();
    public ItemBinding<ReelStripViewModel> reelStripBinding = ItemBinding.of(BR.reelStrip, R.layout.item_reel_strip);
    public final ObservableInt lastScore = new ObservableInt();
    public final ObservableInt nudges = new ObservableInt();
    public final ObservableInt holds = new ObservableInt();
    public final ObservableInt numHeldReels = new ObservableInt();
    public final ObservableBoolean hasNudges = new ObservableBoolean(false);
    public final ObservableBoolean hasHolds = new ObservableBoolean(false);
    public final ObservableBoolean hasWon = new ObservableBoolean(false);
    public final ObservableBoolean hasLost = new ObservableBoolean(false);
    public final ObservableBoolean canSpin = new ObservableBoolean(true);
    public final ObservableBoolean isFirstSpin = new ObservableBoolean(true);

    public FruitMachineViewModel(IPlayerProvider playerProvider, ISymbolProvider symbolProvider, int playerId)
    {
        this.playerProvider = playerProvider;
        this.symbolProvider = symbolProvider;

        grabPlayer(playerId);

        nudges.set(0);
        holds.set(0);

        buildFruitMachine();
    }

    private void grabPlayer(int id)
    {
        player.set(new PlayerItemViewModel(playerProvider.getPlayer(id)));
        playerBank.set(player.get().getBank());
    }

    private void buildFruitMachine()
    {
        ArrayList<Symbol> symbols = symbolProvider.getAllSymbolsFromSet("classic");

        for (int i = 0; i < 5; i++)
        {
            Reel reel = new Reel(symbols);
            reelStrips.add(new ReelStripViewModel(reel, this));
        }
    }

    //Spin button has been hit
    public void startTurn(View view)
    {
        //No playing if you're broke
        if ((player.get().getBank() == 0) && (isFirstSpin.get()))
        {
            canSpin.set(false);
            isPlayerBroke.set(true);
            return;
        }

        //Disable spin button while reels spinning
        canSpin.set(false);
        //Clear these on new spin
        hasWon.set(false);
        hasLost.set(false);

        //Player gets 2 spins per turn - only charge on 1
        if (isFirstSpin.get())
        {
            removeCreditFromPlayer();
        }

        spinReels();
        //Outcome is based on reel spin outcome - need to do rest of logic in/after spinReels / countdowntimer / onfinish
    }

    private void removeCreditFromPlayer()
    {
        player.get().setBank(player.get().getBank() - 1);
        playerBank.set(player.get().getBank());
        playerProvider.updatePlayer(player.get().getId(), player.get().getFirstName(), player.get().getLastName(), player.get().getEmail(), player.get().getBank());
    }

    //Used as a counter in the spinReels method
    private static class IntHolder
    {
        public int value;
    }

    private void spinReels()
    {
        final IntHolder reelsDoneSpinning = new IntHolder();
        Random rand = new Random();

        for (final ReelStripViewModel reelStrip : reelStrips)
        {
            //Don't spin the reel if it's been held
            if (!reelStrip.markedForHold.get())
            {
                //Run for 2 seconds + (100ms * random number).
                //Nudge the reel every 100ms.
                new CountDownTimer(2000 + (rand.nextInt(10) * 100), 100)
                {
                    public void onTick(long millisUntilFinished)
                    {
                        FruitMachineService.nudgeReel(reelStrip);
                    }

                    public void onFinish()
                    {
                        reelsDoneSpinning.value++;
                        Log.d("Reels done: ", Integer.toString(reelsDoneSpinning.value));

                        //If all reels finished enable spinning and check for a match
                        if (reelsDoneSpinning.value == (reelStrips.size() - numHeldReels.get()))
                        {
                            canSpin.set(true);
                            checkForWin();
                        }
                    }
                }
                        .start();
            }
        }
    }

    private void checkForWin()
    {
        hasWon.set(symbolsMatch(reelStrips));
        if (hasWon.get())
        {
            awardPoints();
            reset();
            return;
        }

        //If it's the first spin (of two spins), set up nudges and holds.
        if (isFirstSpin.get())
        {
            //Set random number of nudges
            setObservableIntToRandom(nudges);
            //If you got more than 0 nudges set hasNudges = true
            setObservableBooleanIfIntOverZero(hasNudges, nudges);

            for (ReelStripViewModel reelStrip : reelStrips)
            {
                //Enable reelStrip buttons if nudges available
                if (hasNudges.get())
                {
                    reelStrip.nudgesEnabled.set(true);
                    reelStrip.canNudge.set(true);
                } else
                {
                    reelStrip.nudgesEnabled.set(false);
                    reelStrip.canNudge.set(false);
                }
            }

            setObservableIntToRandom(holds);
            setObservableBooleanIfIntOverZero(hasHolds, holds);

            for (ReelStripViewModel reelStrip : reelStrips)
            {
                if (hasHolds.get())
                {
                    reelStrip.holdsEnabled.set(true);
                    reelStrip.canHold.set(true);
                } else
                {
                    reelStrip.holdsEnabled.set(false);
                    reelStrip.canHold.set(false);
                }
            }

            //End of first spin
            isFirstSpin.set(!isFirstSpin.get());
        }
        else
        {
            hasLost.set(true);
            reset();
        }
    }

    private boolean symbolsMatch(ObservableList<ReelStripViewModel> reelStrips)
    {
        boolean match = true;
        SymbolItemViewModel firstSymbol = reelStrips.get(0).getSymbols().get(1);

        for (ReelStripViewModel reelStrip : reelStrips)
        {
            if (reelStrip.getSymbols().get(1).getScore() != firstSymbol.getScore())
            {
                match = false;
            }
        }

        return match;
    }

    private void awardPoints()
    {
        lastScore.set(5 * reelStrips.get(0).getSymbols().get(1).getScore());

        player.get().setBank(player.get().getBank() + lastScore.get());
        playerBank.set(player.get().getBank());
        playerProvider.updatePlayer(player.get().getId(), player.get().getFirstName(), player.get().getLastName(), player.get().getEmail(), player.get().getBank());
    }

    public void setNudgesFalse()
    {
        for(ReelStripViewModel reelStrip : reelStrips)
        {
            reelStrip.canNudge.set(false);
        }
    }

    public void setHoldsFalse()
    {
        for(ReelStripViewModel reelStrip : reelStrips)
        {
            reelStrip.canHold.set(false);
        }
    }

    private void reset()
    {
        //Reset game state
        nudges.set(0);
        holds.set(0);
        hasNudges.set(false);
        hasHolds.set(false);
        isFirstSpin.set(true);
        numHeldReels.set(0);

        for (ReelStripViewModel reelStrip : reelStrips)
        {
            reelStrip.canHold.set(false);
            reelStrip.canNudge.set(false);
            reelStrip.markedForHold.set(false);
            reelStrip.nudgesEnabled.set(false);
            reelStrip.holdsEnabled.set(false);
        }
    }
}
















