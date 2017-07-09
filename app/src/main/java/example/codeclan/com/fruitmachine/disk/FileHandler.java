package example.codeclan.com.fruitmachine.disk;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import example.codeclan.com.fruitmachine.R;

/**
 * Created by user on 09/07/2017.
 */

public class FileHandler
{
    //Code sourced from: https://stackoverflow.com/a/34026527
    public static String saveBitmapToFile(File dir, String fileName, Bitmap bm,
                                   Bitmap.CompressFormat format, int quality)
    {
        File imageFile = new File(dir, fileName);

        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(imageFile, false);

            bm.compress(format, quality, fos);

            fos.close();

            return imageFile.getAbsolutePath();
        }
        catch (IOException e)
        {
            Log.e("app", e.getMessage());
            if (fos != null)
            {
                try
                {
                    fos.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        }

        return null;
    }
}
