package kozakiewicz.szymon.androidgame;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

public class Data implements Serializable {

    String nickname;
    int score;
    Date dateTime;
    String targetName;
    String hunterName;
    String result;
    String sound;

    public Data(String nickname, int score, Date dateTime, String targetName, String hunterName, String result, String sound) {
        this.nickname = nickname;
        this.score = score;
        this.dateTime = dateTime;
        this.targetName = targetName;
        this.hunterName = hunterName;
        this.result = result;
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

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getHunterName() {
        return hunterName;
    }

    public void setHunterName(String hunterName) {
        this.hunterName = hunterName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }
}
