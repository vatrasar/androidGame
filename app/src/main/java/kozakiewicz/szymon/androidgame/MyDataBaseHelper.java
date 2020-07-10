package kozakiewicz.szymon.androidgame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyDataBaseHelper extends SQLiteOpenHelper
{
    public final static String LOG_TAG="DB log";
    public final static String DB_NAME="GameDB";
    public final static int VERSION=1;
    final static String TABLE_NAME="GameTab";
    final static String _ID="_id";
    final static String NICKNAME="nickname";
    final static String SCORE="score";
    final static String DATE="date";
    final static String IMAGE="image";
    final static String[] columns={_ID,NICKNAME,SCORE,DATE,IMAGE};
    SQLiteDatabase database;
    Context context;

    public MyDataBaseHelper(Context context) {
        super(context,DB_NAME,null,VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

//        Toast.makeText(context,"tworzenie bazy",Toast.LENGTH_LONG).show();
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+"(" +
                _ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NICKNAME+" TEXT NOT NULL, " +
                SCORE + " INTEGER NOT NULL, " +
                DATE + " DATE NOT NULL," +
                IMAGE+" BLOB NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }
    public void deleteDatabase()
    {
        context.deleteDatabase(DB_NAME);
    }

    public void insertScore(Data settings, int score,Bitmap bitmap)
    {
        database=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(NICKNAME,settings.nickname);
        cv.put(SCORE,score);
        cv.put(IMAGE,getBitmapAsByteArray(bitmap));
        cv.put(DATE, getDateTimeString(Calendar.getInstance().getTime()));
        database.insert(TABLE_NAME,null,cv);
        database.close();
    }

    public List<Score> getResultsRows() {
        database=getWritableDatabase();
        List<Score>scoreList=new ArrayList<>();
        Cursor cursor=database.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        while (cursor.moveToNext())
        {
            try {
                Score newScore=new Score(cursor.getString(1),cursor.getInt(2),dateFormat.parse(cursor.getString(3)),null);
                scoreList.add(newScore);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return scoreList;
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    private String getDateTimeString(Date date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(

                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());




        return dateFormat.format(date);

    }



}
