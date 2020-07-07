package kozakiewicz.szymon.androidgame.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import kozakiewicz.szymon.androidgame.MyDataBaseHelper;
import kozakiewicz.szymon.androidgame.R;

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
}