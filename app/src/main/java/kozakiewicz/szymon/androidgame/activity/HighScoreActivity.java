package kozakiewicz.szymon.androidgame.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import kozakiewicz.szymon.androidgame.R;

public class HighScoreActivity extends AppCompatActivity {
    private ArrayAdapter<Score> adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        setTitle(R.string.score_view_title);
        ListView listView=findViewById(R.id.scoreListView);
        ArrayList<Score>scoresList=new ArrayList<>();
        scoresList.add(new Score("lipa",2, Calendar.getInstance().getTime(),null));
        adapter = new ScoreAdapter(this,  scoresList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }
}

class Score
{
    String nickname;
    int score;
    Date dateTime;
    Blob blob;

    public Score(String nickname, int score, Date dateTime, Blob blob) {
        this.nickname = nickname;
        this.score = score;
        this.dateTime = dateTime;
        this.blob = blob;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Blob getBlob() {
        return blob;
    }

    public void setBlob(Blob blob) {
        this.blob = blob;
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
        Score score=(Score)getItem(position);

        tvNickname.setText(score.getNickname());
        return tvNickname;
    }
}