package example.codeclan.com.fruitmachine.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import example.codeclan.com.fruitmachine.R;
import example.codeclan.com.fruitmachine.database.PlayerHandler;
import example.codeclan.com.fruitmachine.database.SymbolHandler;
import example.codeclan.com.fruitmachine.databinding.ActivityFruitMachineBinding;
import example.codeclan.com.fruitmachine.viewmodels.PlayFruitMachineViewModel;

public class FruitMachineActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_machine);

        ActivityFruitMachineBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_fruit_machine);
        binding.setViewModel(new PlayFruitMachineViewModel(new PlayerHandler(this), new SymbolHandler(this)));
    }
}
