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

    public int getTargetId() {
        return targetNameId;
    }

    public void setTargetId(int targetName) {
        this.targetNameId = targetName;
    }



    public int getHunterId()
    {
        return hunterNameId;
    }

    public void setHunterId(int hunterName) {
        this.hunterNameId = hunterName;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int result) {
        this.resultId = result;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }
}
