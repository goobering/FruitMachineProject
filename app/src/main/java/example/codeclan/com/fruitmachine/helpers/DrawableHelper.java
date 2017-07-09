package example.codeclan.com.fruitmachine.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import junit.framework.Assert;

import example.codeclan.com.fruitmachine.R;

/**
 * Created by user on 07/07/2017.
 */

public class DrawableHelper
{
    public static int getDrawable(Context context, String name)
    {
        Assert.assertNotNull(context);
        Assert.assertNotNull(name);

        return context.getResources().getIdentifier(name,
                "drawable", context.getPackageName());
    }

    public static Bitmap drawableToBitmap(Context context, int myDrawable)
    {
        return BitmapFactory.decodeResource(context.getResources(), myDrawable);
    }
}
