package example.codeclan.com.fruitmachine.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.lang.reflect.Field;
import java.util.ArrayList;

import example.codeclan.com.fruitmachine.R;
import example.codeclan.com.fruitmachine.database.DatabaseHandler;
import example.codeclan.com.fruitmachine.database.PlayerHandler;
import example.codeclan.com.fruitmachine.database.SymbolHandler;
import example.codeclan.com.fruitmachine.databinding.ActivityMainBinding;
import example.codeclan.com.fruitmachine.disk.FileHandler;
import example.codeclan.com.fruitmachine.helpers.DrawableHelper;
import example.codeclan.com.fruitmachine.models.Symbol;
import example.codeclan.com.fruitmachine.viewmodels.HomeViewModel;

public class HomeActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(new HomeViewModel(new PlayerHandler(this)));



        //Everything south of here really should go somewhere else - need something that runs once after install
        String[] defaultImages = new String[]{
                "classic_bar",
                "classic_bell",
                "classic_chip",
                "classic_diamond",
                "classic_lemon",
                "classic_melon",
                "classic_orange",
                "classic_plum",
                "classic_seven",
                "classic_strawberry"
        };

        //Move each image held in drawables to internal storage
        SymbolHandler symbolHandler = new SymbolHandler(this);
        int i = 1;

        for (String imgName : defaultImages)
        {
            int imgId = DrawableHelper.getDrawable(this, imgName);
            Bitmap convertedDrawable = DrawableHelper.drawableToBitmap(this, imgId);

            String imgPath = FileHandler.saveBitmapToFile(getFilesDir(), imgName + ".png", convertedDrawable, Bitmap.CompressFormat.PNG, 100);

            Symbol newSymbol = new Symbol("classic", imgName, imgPath, i);
            i++;
            symbolHandler.addSymbol(newSymbol);
        }
    }
}
