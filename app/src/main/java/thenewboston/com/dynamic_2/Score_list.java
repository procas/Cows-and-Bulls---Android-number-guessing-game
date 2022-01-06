package thenewboston.com.dynamic_2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class Score_list extends AppCompatActivity {

    DBHandler dbHandler;
    ListView listView;
    ScoreListAdapter listAdapter;
    Cursor cursor;
    SQLiteDatabase sqLiteDatabase;
    int score;
    String date;
    float stars;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_list);
        listView=(ListView)findViewById(R.id.listView);
        listAdapter=new ScoreListAdapter(getApplicationContext(), R.layout.score_layout);
        listView.setAdapter(listAdapter);
        dbHandler=new DBHandler(getApplicationContext());
        sqLiteDatabase=dbHandler.getReadableDatabase();
        cursor=dbHandler.getInformation(sqLiteDatabase);

        if(cursor.moveToNext())
        {
            do {
                {

                    score=Integer.parseInt(cursor.getString(cursor.getColumnIndex(UserContract.NewUserInfo.COLUMN_SCORE)));
                    date=cursor.getString(cursor.getColumnIndex(UserContract.NewUserInfo.COLUMN_DATE));
                    try {
                        stars = Float.parseFloat(cursor.getString(cursor.getColumnIndexOrThrow(UserContract.NewUserInfo.COLUMN_STARS)));
                    }
                    catch (NullPointerException e)
                    {
                        e.printStackTrace();
                    }
                    Scores object=new Scores(score, date, stars);
                    listAdapter.add(object);
                    listAdapter.notifyDataSetChanged();
                }
            }while(cursor.moveToNext());
        }

    }

    public void deleteAll(View v)
    {
        sqLiteDatabase=dbHandler.getWritableDatabase();
        sqLiteDatabase.delete(UserContract.NewUserInfo.TABLE_NAME,null,null);

        listAdapter.notifyDataSetChanged();

        Toast.makeText(this, "Please Restart for Effect", Toast.LENGTH_LONG).show();
}
}
