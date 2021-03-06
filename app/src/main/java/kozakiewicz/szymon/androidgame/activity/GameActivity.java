package kozakiewicz.szymon.androidgame.activity;

import androidx.annotation.Dimension;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import kozakiewicz.szymon.androidgame.Data;
import kozakiewicz.szymon.androidgame.MyCanvas;
import kozakiewicz.szymon.androidgame.R;
import kozakiewicz.szymon.androidgame.Utils;
import kozakiewicz.szymon.androidgame.objects.ObjectOnScreen;
import kozakiewicz.szymon.androidgame.objects.ObjectType;

import static android.hardware.Sensor.TYPE_ACCELEROMETER;

public class GameActivity extends AppCompatActivity implements SensorEventListener {

    Data settings;
    ObjectOnScreen hunter;
    ObjectOnScreen target;
    List<ObjectOnScreen> results;
    Random random;
    MyCanvas myCanvas;
    int imageHeight;
    int imageWidth;
    Sensor accelerometer;
    int score;
    ObjectOnScreen lastHitedResult;
    Date lastHitTime=Calendar.getInstance().getTime();



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);
        Bundle bundle=this.getIntent().getExtras();
        settings=(Data)bundle.getSerializable("settings");
        myCanvas = findViewById(R.id.myCanvas);
        myCanvas.setHighScore(settings.getScore());
        myCanvas.setNickname(settings.getNickname());
        myCanvas.setResults(results);
        myCanvas.setGameActivity(this);
        random=new Random(System.currentTimeMillis());
        imageHeight=60;
        imageWidth=60;
        registSensor();
        
//        initGame();
//        rePaint();

    }

    private void registSensor() {
        SensorManager sm=(SensorManager) this.getSystemService(SENSOR_SERVICE);
        accelerometer=sm.getDefaultSensor(TYPE_ACCELEROMETER);
        sm.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void rePaint() {
        myCanvas.setHunter(hunter);
        myCanvas.setTarget(target);
        myCanvas.setResults(results);
        myCanvas.invalidate();
    }

    public void initGame() {
        int width = myCanvas.getWidth();
        int height = myCanvas.getHeight();

        hunter=new ObjectOnScreen(random,width,height,resizeImage(settings.getHunterId()), ObjectType.HUNTER,imageWidth,imageHeight);
        target=new ObjectOnScreen(random,width,height,resizeImage(settings.getTargetId()), ObjectType.TARGET,imageWidth,imageHeight);
        results=new ArrayList<>();
        rePaint();

    }
    Bitmap resizeImage(int id)
    {

        return Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),id),imageWidth,imageHeight,false);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(!myCanvas.isFirstRun()) {
            float[] values = sensorEvent.values;
            hunter.push(-values[0], values[1],myCanvas.getWidth(),myCanvas.getHeight(),imageWidth);
            checkColisions();
            rePaint();
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    void checkColisions()
    {

        if(isColision(target,hunter))
        {


            playSound(settings.getSound());
            int width = myCanvas.getWidth();
            int height = myCanvas.getHeight();

            target.setImage(resizeImage(settings.getResultId()));
            target.setObjectType(ObjectType.Result);
            results.add(target);
            lastHitedResult=target;
            lastHitTime=Calendar.getInstance().getTime();

            //set new target
            while (true) {
                target = new ObjectOnScreen(random, width, height, resizeImage(settings.getTargetId()), ObjectType.TARGET, imageWidth, imageHeight);
                //check whether is colision between new target and any result

                boolean isColisionBetweenTargetAndResult=false;
                for (ObjectOnScreen result : results) {
                    if (isColision(result, target))
                    {
                        isColisionBetweenTargetAndResult=true;
                    }
                }
                if(isColisionBetweenTargetAndResult)
                    continue;
                else
                    break;
            }

            score++;
            myCanvas.setCurrentScore(score);

        }

        for(ObjectOnScreen result:results)
        {
            if(isColision(result,hunter))
            {


                if(result!=lastHitedResult || (lastHitTime.getTime()+3000L<Calendar.getInstance().getTimeInMillis()))
                {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("score",score);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("settings",settings);
                    returnIntent.putExtras(bundle);
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();
                }


            }
        }
    }

    private boolean isColision(ObjectOnScreen node1, ObjectOnScreen node2) {
        if(Utils.computeDistance(node1,node2)<imageHeight)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    private void playSound(String soundName)
    {
        switch (soundName)
        {
            case "sound1":
                ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
                toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP,150);
                break;
            case "sound2":
                ToneGenerator toneGen2 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
                toneGen2.startTone(ToneGenerator.TONE_CDMA_HIGH_PBX_L,150);
//                toneGen2.startTone(ToneGenerator.TONE_CDMA_PIP,150);
                break;
            case "sound3":
                ToneGenerator toneGen3 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
                toneGen3.startTone(ToneGenerator.TONE_DTMF_0,150);
//                toneGen2.startTone(ToneGenerator.TONE_CDMA_PIP,150);
                break;
        }
    }

}

