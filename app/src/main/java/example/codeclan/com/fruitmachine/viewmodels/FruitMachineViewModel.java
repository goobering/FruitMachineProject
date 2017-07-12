package example.codeclan.com.fruitmachine.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.os.CountDownTimer;
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

import static example.codeclan.com.fruitmachine.helpers.NumberHelpers.setObservableBooleanOverZero;
import static example.codeclan.com.fruitmachine.helpers.NumberHelpers.setRandomObservableInt;

/**
 * Created by user on 07/07/2017.
 */

public class FruitMachineViewModel extends ViewModel
{
    //By using an interface I'm decoupling the DB data provider from a concrete implementation - better for testing!
    private final IPlayerProvider playerProvider;
    private final ISymbolProvider symbolProvider;
    public final ObservableField<PlayerItemViewModel> player = new ObservableField<PlayerItemViewModel>();

    public final ObservableList<ReelStripViewModel> reelStrips = new ObservableArrayList<ReelStripViewModel>();
    public ItemBinding<ReelStripViewModel> reelStripBinding = ItemBinding.of(BR.reelStrip, R.layout.item_reel_strip);
    public final ObservableInt nudges = new ObservableInt();
    public final ObservableInt holds = new ObservableInt();
    public final ObservableBoolean hasNudges = new ObservableBoolean(false);
    public final ObservableBoolean hasHolds = new ObservableBoolean(false);
    public final ObservableBoolean hasWon = new ObservableBoolean(false);
    public final ObservableInt lastScore = new ObservableInt();

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
    }

    private void buildFruitMachine()
    {
        ArrayList<Symbol> symbols = symbolProvider.getAllSymbolsFromSet("classic");

        for (int i = 0; i < 5; i++)
        {
            Reel reel = new Reel(symbols);
            reelStrips.add(new ReelStripViewModel(reel));
        }
    }

    public void startTurn(View view)
    {
        hasWon.set(false);

        spinReels();
        //Outcome is based on reel spin outcome - need to do rest of logic in/after countdowntimer onfinish
    }

    private boolean symbolsMatch(ObservableList<ReelStripViewModel> reelStrips)
    {
        boolean match = true;
        SymbolItemViewModel firstSymbol = reelStrips.get(0).getSymbols().get(1);

        for(ReelStripViewModel reelStrip : reelStrips)
        {
            if(reelStrip.getSymbols().get(1).getScore() != firstSymbol.getScore())
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
        playerProvider.updatePlayer(player.get().getId(), player.get().getFirstName(), player.get().getLastName(), player.get().getEmail(), player.get().getBank());
    }

    //Heh. Someone ran out of time and jammed the universe in his last method...
    private void spinReels()
    {
        Random rand = new Random();

        for (final ReelStripViewModel reelStrip : reelStrips)
        {
            if (!reelStrip.markedForHold.get())
            {
                new CountDownTimer(2000 + (rand.nextInt(10) * 100), 100)
                {
                    public void onTick(long millisUntilFinished)
                    {
                        FruitMachineService.nudgeReel(reelStrip);
                    }

                    public void onFinish()
                    {
                        hasWon.set(symbolsMatch(reelStrips));
                        if(hasWon.get())
                        {
                            awardPoints();

                            nudges.set(0);
                            holds.set(0);
                            hasNudges.set(false);
                            hasHolds.set(false);

                            for(ReelStripViewModel reelStrip : reelStrips)
                            {
                                reelStrip.canHold.set(false);
                                reelStrip.canNudge.set(false);
                                reelStrip.markedForHold.set(false);
                                reelStrip.nudgesEnabled.set(false);
                                reelStrip.holdsEnabled.set(false);
                            }

                            return;
                        }

                        setRandomObservableInt(nudges);
                        setObservableBooleanOverZero(hasNudges, nudges);

                        for(ReelStripViewModel reelStrip : reelStrips)
                        {
                            if(hasNudges.get())
                            {
                                reelStrip.nudgesEnabled.set(true);
                                reelStrip.canNudge.set(true);
                            }
                            else
                            {
                                reelStrip.nudgesEnabled.set(false);
                                reelStrip.canNudge.set(false);
                            }
                        }

                        setRandomObservableInt(holds);
                        setObservableBooleanOverZero(hasHolds, holds);
                    }
                }.start();
            }
        }
    }
}
















