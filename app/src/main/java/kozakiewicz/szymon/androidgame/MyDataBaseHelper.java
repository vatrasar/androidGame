package kozakiewicz.szymon.androidgame;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

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
}
