package kozakiewicz.szymon.androidgame.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import kozakiewicz.szymon.androidgame.R;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }
}