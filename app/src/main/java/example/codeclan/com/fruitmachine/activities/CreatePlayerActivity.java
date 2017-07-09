package example.codeclan.com.fruitmachine.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import example.codeclan.com.fruitmachine.R;
import example.codeclan.com.fruitmachine.database.PlayerHandler;
import example.codeclan.com.fruitmachine.databinding.ActivityCreatePlayerBinding;
import example.codeclan.com.fruitmachine.viewmodels.CreatePlayerViewModel;

public class CreatePlayerActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_player);

        ActivityCreatePlayerBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_create_player);
        binding.setViewModel(new CreatePlayerViewModel(new PlayerHandler(this)));
    }
}
