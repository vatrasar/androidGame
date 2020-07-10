package kozakiewicz.szymon.androidgame;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
        cv.put(DATE, getDateTime(Calendar.getInstance().getTime()));
        database.insert(TABLE_NAME,null,cv);
        database.close();
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    private String getDateTime(Date date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(

                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());



        return dateFormat.format(date);

    }



}
