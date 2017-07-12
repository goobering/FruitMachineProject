package example.codeclan.com.fruitmachine.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import example.codeclan.com.fruitmachine.R;
import example.codeclan.com.fruitmachine.database.PlayerHandler;
import example.codeclan.com.fruitmachine.databinding.ActivityAddCashBinding;
import example.codeclan.com.fruitmachine.viewmodels.AddCashViewModel;
import example.codeclan.com.fruitmachine.viewmodels.FruitMachineViewModel;

public class AddCashActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cash);

        int playerId = getIntent().getIntExtra("PLAYER_ID", -1);

        ActivityAddCashBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_add_cash);
        binding.setAddCashViewModel(new AddCashViewModel(new PlayerHandler(this), playerId));
    }
}
