package example.codeclan.com.fruitmachine.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

import example.codeclan.com.fruitmachine.models.Symbol;

/**
 * Created by user on 10/07/2017.
 */

public class SymbolItemViewModel extends BaseObservable
{
    @Bindable
    private Symbol symbol;

    public SymbolItemViewModel(Symbol symbol)
    {
        this.symbol = symbol;
    }

    public String getImageLocation()
    {
        return symbol.getImageLocation();
    }

    public int getScore()
    {
        return symbol.getScore();
    }

    @BindingAdapter({"imageLocation"})
    public static void loadImage(ImageView view, String imageUrl) {
        Uri imageUri = Uri.parse(imageUrl);
        //Uri imageUri = Uri.parse("/files/classic_bell.png");
        Picasso.with(view.getContext())
                .load(new File(imageUrl))
                .placeholder(example.codeclan.com.fruitmachine.R.drawable.placeholder)
                .into(view);
    }
}
