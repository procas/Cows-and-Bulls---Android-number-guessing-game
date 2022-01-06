package thenewboston.com.dynamic_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HP on 28-01-2017.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "scores.db";

    Object ob;


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL("DROP TABLE IF EXISTS " + UserContract.NewUserInfo.TABLE_NAME);
        onCreate(db);


    }

    public Cursor getInformation(SQLiteDatabase sqLiteDatabase)
    {
        Cursor cursor;
        String[] projections={UserContract.NewUserInfo.COLUMN_SCORE, UserContract.NewUserInfo.COLUMN_DATE, UserContract.NewUserInfo.COLUMN_STARS};
        cursor=sqLiteDatabase.query(UserContract.NewUserInfo.TABLE_NAME,projections,null,null,null,null,null);
        return cursor;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " +
                UserContract.NewUserInfo.TABLE_NAME + "(" +
                UserContract.NewUserInfo.COLUMN_SCORE + " INTEGER, " +
                UserContract.NewUserInfo.COLUMN_STARS + " FLOAT, " +
                UserContract.NewUserInfo.COLUMN_DATE+ " TEXT "+ ");";
        db.execSQL(query);
    }

    public void addScore(Scores scores) {
        ContentValues values = new ContentValues();

        values.put(UserContract.NewUserInfo.COLUMN_SCORE, scores.get_score());
        values.put(UserContract.NewUserInfo.COLUMN_DATE, scores.get_date());
        values.put(UserContract.NewUserInfo.COLUMN_STARS, scores.get_star());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(UserContract.NewUserInfo.TABLE_NAME, null, values);
        db.close();
    }












    public String dataBaseToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = " SELECT * FROM " + UserContract.NewUserInfo.TABLE_NAME;

        //POINT CURSOR

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            if (cursor.getString(cursor.getColumnIndex("highscore")) != null) {
                dbString += cursor.getString((cursor.getColumnIndex("highscore")));
                dbString += "\n";

                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();


        return dbString;
    }


    public String showStars()
    {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = " SELECT * FROM " + UserContract.NewUserInfo.TABLE_NAME;

        //POINT CURSOR

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndexOrThrow(UserContract.NewUserInfo.COLUMN_STARS)) != null) {
                dbString += c.getString((c.getColumnIndexOrThrow(UserContract.NewUserInfo.COLUMN_STARS)));
                dbString += "\n";

                c.moveToNext();

            }

           // if(c.isAfterLast())break;
        }
        c.close();
        db.close();


        return dbString;
    }












    public String showDates()
    {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = " SELECT * FROM " + UserContract.NewUserInfo.TABLE_NAME;

        //POINT CURSOR

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            if (cursor.getString(cursor.getColumnIndex("dates")) != null) {
                dbString += cursor.getString((cursor.getColumnIndex("dates")));
                dbString += "\n";

                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();


        return dbString;
    }

}
