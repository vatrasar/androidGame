package kozakiewicz.szymon.androidgame.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import kozakiewicz.szymon.androidgame.Data;
import kozakiewicz.szymon.androidgame.MyDataBaseHelper;
import kozakiewicz.szymon.androidgame.R;
import kozakiewicz.szymon.androidgame.Score;

public class HomeActivity extends AppCompatActivity {

    MyDataBaseHelper databaseHelper;
    SQLiteDatabase database;
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
        startActivityForResult(intent,2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle result=data.getExtras();
        TextView textInfo=(TextView) findViewById(R.id.txtInfo);
        Data score=(Data) result.getSerializable("data");
        textInfo.setText(score.getNickname());
    }


    public void onPlay(View view) {
        Intent intent=new Intent(this,GameActivity.class);
        startActivity(intent);


    }
}