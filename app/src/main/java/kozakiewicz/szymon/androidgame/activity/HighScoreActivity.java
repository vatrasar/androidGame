package kozakiewicz.szymon.androidgame.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import kozakiewicz.szymon.androidgame.MyDataBaseHelper;
import kozakiewicz.szymon.androidgame.R;
import kozakiewicz.szymon.androidgame.Score;
import kozakiewicz.szymon.androidgame.Utils;

public class HighScoreActivity extends AppCompatActivity {
    private ScoreAdapter adapter ;
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
        List<Score>socresList=databaseHelper.getResultsRows();
        socresList.sort(new Comparator<Score>() {
            @Override
            public int compare(Score y, Score x) {
                if(x.getScore()>y.getScore())
                {
                    return 1;
                }else if(x.getScore()==y.getScore())
                    return 0;
                else
                    return -1;

            }
        });
        adapter = new ScoreAdapter(this,socresList);

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
        TextView txtDate=(TextView)convertView.findViewById(R.id.txtDate);
        ImageView image=(ImageView)convertView.findViewById(R.id.hunterImage);
        Score score=(Score)getItem(position);

        tvNickname.setText(score.getNickname());
        txtScore.setText(score.getScore()+"");
        txtDate.setText(Utils.getDateFormatString(score.getDateTime()));

        image.setImageBitmap(score.getBlob());

        return convertView;
    }



}