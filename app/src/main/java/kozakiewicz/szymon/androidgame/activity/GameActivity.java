package kozakiewicz.szymon.androidgame.activity;

import androidx.annotation.Dimension;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import java.util.ArrayList;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);
        Bundle bundle=this.getIntent().getExtras();
        settings=(Data)bundle.getSerializable("settings");
        myCanvas = findViewById(R.id.myCanvas);
        myCanvas.setHighScore(settings.getScore());
        myCanvas.setNickname(settings.getNickname());
        myCanvas.setResults(results);
        random=new Random(System.currentTimeMillis());
        imageHeight=60;
        imageWidth=60;
        registSensor();

        initGame();
        rePaint();

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

    private void initGame() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        hunter=new ObjectOnScreen(random,width,height,resizeImage(R.drawable.cel), ObjectType.HUNTER,imageWidth,imageHeight);
        target=new ObjectOnScreen(random,width,height,resizeImage(R.drawable.kotek), ObjectType.TARGET,imageWidth,imageHeight);
        results=new ArrayList<>();

    }
    Bitmap resizeImage(int id)
    {

        return Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),id),imageWidth,imageHeight,false);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float[]values=sensorEvent.values;
        hunter.push(-values[0],values[1]);
        checkColisions();
        rePaint();

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    void checkColisions()
    {

        if(isColision(target,hunter))
        {
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            int height = size.y;

            target.setImage(resizeImage(R.drawable.blood));
            results.add(target);
            target=new ObjectOnScreen(random,width,height,resizeImage(R.drawable.kotek), ObjectType.TARGET,imageWidth,imageHeight);
            score++;
            myCanvas.setCurrentScore(score);
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
}

