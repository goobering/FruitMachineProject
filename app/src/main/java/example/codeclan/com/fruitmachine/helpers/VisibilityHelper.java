package example.codeclan.com.fruitmachine.helpers;

import android.databinding.BindingConversion;
import android.databinding.InverseMethod;
import android.databinding.ObservableBoolean;
import android.support.annotation.IntDef;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by user on 12/07/2017.
 */

//This doesn't work! Need to find out how to pass View.VISIBLE/View.GONE back to a Button - doesn't take an int
public class VisibilityHelper
{
    @InverseMethod("toBool")
    public static String toVisibility(boolean bool) {
        if(bool)
        {
            return "visible";
        }
        else
        {
            return "gone";
        }
    }

    public static boolean toBool(String visibility) {
        if(visibility.equals("gone"))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
