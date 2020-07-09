package kozakiewicz.szymon.androidgame.objects;

import android.graphics.Bitmap;

import java.util.Random;

public class ObjectOnScreen {

   public float x,y;
   public Bitmap image;
   public ObjectType objectType;

    public ObjectOnScreen(Random random,int maxX,int maxY,Bitmap image,ObjectType objectType,int imageWidth,int imageHeight) {

        x=random.nextInt(maxX-imageWidth);
        y=random.nextInt(maxY-imageHeight);
        this.image=image;
        this.objectType=objectType;

    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public ObjectType getObjectType() {
        return objectType;
    }

    public void setObjectType(ObjectType objectType) {
        this.objectType = objectType;
    }


    public void push(float x_push, float y_push) {
        x=x+x_push;
        y=y+y_push;
    }
}
