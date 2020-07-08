package kozakiewicz.szymon.androidgame;

import java.sql.Blob;
import java.util.Date;

public class Score
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
