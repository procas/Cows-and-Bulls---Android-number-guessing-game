package thenewboston.com.dynamic_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScoreListAdapter extends ArrayAdapter {

    List list=new ArrayList();

    public ScoreListAdapter(Context context, int resource)
    {
        super(context, resource);
    }

    static  class LayoutHandler
    {
        TextView score;
        TextView date;
        RatingBar numstars;

    }

    @Override
    public void add(Object object) {
        super.add(object);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutHandler layoutHandler;
        if(row == null)
        {
            LayoutInflater layoutInflater=(LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.score_layout, parent, false);
            layoutHandler=new LayoutHandler();
            layoutHandler.score=(TextView) row.findViewById(R.id.score);
            layoutHandler.date=(TextView)row.findViewById(R.id.date);
            layoutHandler.numstars=(RatingBar)row.findViewById(R.id.ratingBar) ;
            //layoutHandler.date_view=(TextView) row.findViewById(R.id.date_view);
            row.setTag(layoutHandler);
        }
        else
        {
            layoutHandler=(LayoutHandler) row.getTag();


        }
        thenewboston.com.dynamic_2.Scores object=(thenewboston.com.dynamic_2.Scores) this.getItem(position);
        layoutHandler.score.setText(Integer.toString(object.get_score()));
        layoutHandler.date.setText(object.get_date());
        layoutHandler.numstars.setRating(object.get_star());
        return row;
    }
}