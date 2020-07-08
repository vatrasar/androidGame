package kozakiewicz.szymon.androidgame.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Date;

import kozakiewicz.szymon.androidgame.Data;
import kozakiewicz.szymon.androidgame.MyCanvas;
import kozakiewicz.szymon.androidgame.R;

public class GameActivity extends AppCompatActivity {

    Data settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Bundle bundle=this.getIntent().getExtras();
        settings=(Data)bundle.getSerializable("settings");
        MyCanvas myCanvas = findViewById(R.id.myCanvas);
        myCanvas.setHighScore(settings.getScore());
        myCanvas.setNickname(settings.getNickname());
    }
}

