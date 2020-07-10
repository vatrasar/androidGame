package kozakiewicz.szymon.androidgame.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

import kozakiewicz.szymon.androidgame.Data;
import kozakiewicz.szymon.androidgame.MyDataBaseHelper;
import kozakiewicz.szymon.androidgame.R;
import kozakiewicz.szymon.androidgame.Score;

public class HomeActivity extends AppCompatActivity {

    MyDataBaseHelper databaseHelper;
    SQLiteDatabase database;
    Data settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activity);
        createDatabase();

    }
    public void createDatabase() {
        databaseHelper=new MyDataBaseHelper(this);
        database=databaseHelper.getWritableDatabase();
//        Toast.makeText(this,"baza utworzona",Toast.LENGTH_SHORT).show();

    }

    public void onHighScore(View view) {
        Intent intent=new Intent(this,HighScoreActivity.class);
        startActivity(intent);
    }

    public void onParameters(View view) {
        Intent intent=new Intent(this,GameParametrActivity.class);
        startActivityForResult(intent,1);
    }

    Bitmap getImage(int id)
    {

        return BitmapFactory.decodeResource(getResources(),id);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1) {
            Bundle result = data.getExtras();
            TextView textInfo = (TextView) findViewById(R.id.txtInfo);
            settings = (Data) result.getSerializable("data");
            textInfo.setText(settings.getNickname());
        }
        else if(requestCode==2)
        {

            TextView textInfo = (TextView) findViewById(R.id.txtInfo);
          int score=data.getIntExtra("score",-1);
          databaseHelper.insertScore(settings,score,getImage(settings.getHunterId()));
        }

    }


    public void onPlay(View view) {
        Intent intent=new Intent(this,GameActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("settings",settings);
        intent.putExtras(bundle);
        startActivityForResult(intent,2);


    }
}