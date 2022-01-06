package thenewboston.com.dynamic_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import thenewboston.com.dynamic_2.DBHandler;

public class Show_scores extends AppCompatActivity {
    DBHandler dbHandler;
    TextView show;
    TextView show_date;
    RatingBar show_rate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_list);
        dbHandler=new DBHandler(this);
        show=(TextView)findViewById(R.id.show);
        show_date=(TextView)findViewById(R.id.show_date);
        show_rate=(RatingBar)findViewById(R.id.ratingBar2);

        String dbString="", dateList="", starNum ="";
        dbString=dbHandler.dataBaseToString();
        dateList=dbHandler.showDates();
        starNum=dbHandler.showStars();
        System.out.println(dbString);
        show_date.setText(dateList);
        show_rate.setRating(Float.parseFloat(starNum));



    }


}