package kozakiewicz.szymon.androidgame.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import kozakiewicz.szymon.androidgame.MyDataBaseHelper;
import kozakiewicz.szymon.androidgame.R;
import kozakiewicz.szymon.androidgame.Score;

public class HighScoreActivity extends AppCompatActivity {
    private ArrayAdapter<Score> adapter ;
    MyDataBaseHelper databaseHelper;
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createDatabase();
        setContentView(R.layout.activity_high_score);
        setTitle(R.string.score_view_title);
        ListView listView=findViewById(R.id.scoreListView);
//        ArrayList<Score>scoresList=new ArrayList<>();
//        scoresList.add(new Score("lipa",2, Calendar.getInstance().getTime(),null));
        adapter = new ScoreAdapter(this,databaseHelper.getResultsRows());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    public void createDatabase() {

        databaseHelper=new MyDataBaseHelper(this);
        database=databaseHelper.getWritableDatabase();
//        Toast.makeText(this,"baza utworzona",Toast.LENGTH_SHORT).show();

    }
}


class ScoreAdapter extends ArrayAdapter<Score>
{


    public ScoreAdapter(@NonNull Context context, @NonNull List<Score> objects) {
        super(context, 0,objects);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView==null)
        {
            convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.score_list_view_item, parent, false);
        }

        TextView tvNickname=(TextView)convertView.findViewById(R.id.txtNickname);
        TextView txtScore=(TextView)convertView.findViewById(R.id.txtScore);
        Score score=(Score)getItem(position);

        tvNickname.setText(score.getNickname());
        txtScore.setText(score.getScore()+"");
        return convertView;
    }



}