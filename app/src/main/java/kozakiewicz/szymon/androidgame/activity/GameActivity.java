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

import java.util.Date;
import java.util.List;
import java.util.Random;

import kozakiewicz.szymon.androidgame.Data;
import kozakiewicz.szymon.androidgame.MyCanvas;
import kozakiewicz.szymon.androidgame.R;
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


    }
    Bitmap resizeImage(int id)
    {

        return Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),id),imageWidth,imageHeight,false);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float[]values=sensorEvent.values;
        hunter.push(-values[0],values[1]);
        rePaint();

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}

