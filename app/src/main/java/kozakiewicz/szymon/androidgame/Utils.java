package kozakiewicz.szymon.androidgame;

import kozakiewicz.szymon.androidgame.objects.ObjectOnScreen;

public class Utils {

    public static double computeDistance(ObjectOnScreen node1, ObjectOnScreen node2)
    {
        double distance=Math.sqrt(Math.pow(node1.getX()-node2.getX(),2)+Math.pow(node1.getY()-node2.getY(),2));
        return distance;
    }
}
