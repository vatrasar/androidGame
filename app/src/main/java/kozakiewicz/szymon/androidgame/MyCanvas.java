package kozakiewicz.szymon.androidgame;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kozakiewicz.szymon.androidgame.objects.ObjectOnScreen;


public class MyCanvas extends View {

    Paint paint;
    String nickname;
    int highScore;
    int currentScore;
    ObjectOnScreen hunter;
    ObjectOnScreen target;
    List<ObjectOnScreen> results;



//    public MyCanvas(Context context) {
//        super(context);
//    }

    public MyCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.paint = new Paint();
        nickname="name";
        highScore=0;
        currentScore=0;
        hunter=null;
        target=null;
        results=new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //settings
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPaint(paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(20);

        //set stats
        canvas.drawText(nickname, 10, 20, paint);
        canvas.drawText(highScore+"", 10, 80, paint);
        canvas.drawText(currentScore+"", 10, 160, paint);

        //show objects
        if (hunter==null)
            return;
        canvas.drawBitmap(hunter.image,hunter.x,hunter.y,paint);
        canvas.drawBitmap(target.image,target.x,target.y,paint);

    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
        this.invalidate();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
        this.invalidate();
    }

    public ObjectOnScreen getHunter() {
        return hunter;
    }

    public void setHunter(ObjectOnScreen hunter) {
        this.hunter = hunter;
    }

    public ObjectOnScreen getTarget() {
        return target;
    }

    public void setTarget(ObjectOnScreen target) {
        this.target = target;
    }

    public List<ObjectOnScreen> getResults() {
        return results;
    }

    public void setResults(List<ObjectOnScreen> results) {
        this.results = results;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
        this.invalidate();
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
        this.invalidate();
    }
}