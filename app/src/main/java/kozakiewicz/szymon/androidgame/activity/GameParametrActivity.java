package kozakiewicz.szymon.androidgame.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import kozakiewicz.szymon.androidgame.Data;
import kozakiewicz.szymon.androidgame.R;
import kozakiewicz.szymon.androidgame.Score;

public class GameParametrActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_parametr);
        setTitle("Game Parameters");
    }

    public void onValidate(View view) {
        EditText txtNickName=(EditText) findViewById(R.id.txtNickName);

        Intent returnIntent = new Intent();
        Bundle bundle = new Bundle();
        Data data = new Data(txtNickName.getText().toString(),2,Calendar.getInstance().getTime(),"","","","");
        bundle.putSerializable("data", data);
        returnIntent.putExtras(bundle);


        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}