package kozakiewicz.szymon.androidgame;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

public class Data implements Serializable {

    String nickname;
    int score;
    Date dateTime;
    int targetNameId;
    int hunterNameId;
    int resultId;
    String sound;

    public Data(String nickname, int score, Date dateTime, int targetName, int hunterName, int result, String sound) {
        this.nickname = nickname;
        this.score = score;
        this.dateTime = dateTime;
        this.targetNameId = targetName;
        this.hunterNameId = hunterName;
        this.resultId = result;
        this.sound = sound;
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

    public int getTargetName() {
        return targetNameId;
    }

    public void setTargetName(int targetName) {
        this.targetNameId = targetName;
    }

    public int getHunterName() {
        return hunterNameId;
    }

    public int getHunterId()
    {
        return R.drawable.alicja_hunter;
    }

    public void setHunterName(int hunterName) {
        this.hunterNameId = hunterName;
    }

    public int getResult() {
        return resultId;
    }

    public void setResult(int result) {
        this.resultId = result;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }
}
