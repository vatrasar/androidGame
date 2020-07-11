package kozakiewicz.szymon.androidgame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import kozakiewicz.szymon.androidgame.objects.ObjectOnScreen;

public class Utils {

    public static double computeDistance(ObjectOnScreen node1, ObjectOnScreen node2)
    {
        double distance=Math.sqrt(Math.pow(node1.getX()-node2.getX(),2)+Math.pow(node1.getY()-node2.getY(),2));
        return distance;
    }

    public static String getDateFormatString(Date date)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(

                "dd-MM-yyyy", Locale.getDefault());




        return dateFormat.format(date);
    }


//    public static Bitmap convertBlobForBitmap(Blob blob)
//    {
//
//    }

}
