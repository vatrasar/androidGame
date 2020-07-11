package kozakiewicz.szymon.androidgame.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
        Spinner spinHunter=(Spinner) findViewById(R.id.spinHunter);
        if(txtNickName.getText().toString().equals(""))
        {
            Toast.makeText(this,"Nickname have to have at least one character",Toast.LENGTH_LONG).show();
            return;

        }
        Intent returnIntent = new Intent();
        Bundle bundle = new Bundle();
        Data data = new Data(txtNickName.getText().toString(),2,Calendar.getInstance().getTime(),-1,resolveImageId(spinHunter.getSelectedItem().toString()),-1,"");
        bundle.putSerializable("data", data);
        returnIntent.putExtras(bundle);


        setResult(Activity.RESULT_OK,returnIntent);
        finish();


    }
    public int resolveImageId(String name)
    {
        switch (name)
        {
            case "girl":
                return R.drawable.cel;

        }
        return -1;
    }
}