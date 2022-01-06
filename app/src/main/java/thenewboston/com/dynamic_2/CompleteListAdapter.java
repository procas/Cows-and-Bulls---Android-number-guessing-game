package thenewboston.com.dynamic_2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
//import android.support.v7.util.ThreadUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

public class CompleteListAdapter extends BaseAdapter {
    AlertDialog alertDialog;
    AlertDialog alertDialog2;
    AlertDialog alertDialog3;
    private ListView completeList;
    Intent returnIntent;
    public static boolean finished = false;
    private Button button;
    private Button powerButton;
    private TextView enter;
    public static boolean shown = false;
    public static boolean toAdd = true;
    private Activity mContext;
    private List<String> mList;
    private ArrayList<String> cowList;
    private ArrayList<String> bullList;
    private LayoutInflater mLayoutInflater = null;
    DBHandler dbHandler;
    public int c = 0;
    private static boolean rep;
    static float ns=0;

    public CompleteListAdapter(final Activity context, List<String> list) {
        mContext = context;
        mList = list;
        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        returnIntent = new Intent(context, CompleteListActivity.class);


    }

    //generates random number

    public static int random() {

        int min = 1000;
        int max = 9999;

        int x = (int) (Math.random() * max) + min;
        if (!(x < min || x > max)) {
            if (!isRepeated(x))
                return x;
        } else

            return random();

        return random();
    }

    public static boolean isRepeated(int n) {
        rep = false;
        String num = Integer.toString(n);
        char c[] = num.toCharArray();
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c.length; j++) {
                if (i != j) {
                    if (c[i] == c[j])
                        rep = true;
                }
            }
        }
        return rep;
    }


    public int random = random();


    public int guess;


    //is the number cow or bull

    public boolean isCow(int digit, int number, int guess) {
        String num = Integer.toString(number);
        String gues = Integer.toString(guess);
        char[] n = num.toCharArray();
        char g[] = gues.toCharArray();

        boolean val = false;

        for (int dex = 0; dex < 4; dex++) {
            if (!isBull(Character.getNumericValue(g[dex]), number, guess)) {
                if (Character.getNumericValue(n[dex]) == digit)
                    val = true;
            }
        }
        return val;
    }

    public boolean isBull(int digit, int number, int guess) {
        String num = Integer.toString(number);
        String gues = Integer.toString(guess);
        boolean val = false;
        char n[] = num.toCharArray();
        char g[] = gues.toCharArray();


        for (int dex = 0; dex < 4; dex++) {
            if (n[dex] == g[dex] && Character.getNumericValue(n[dex]) == digit)
                val = true;
        }

        return val;
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int pos) {
        return mList.get(pos);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        CompleteListActivity completeListActivity = new CompleteListActivity();

        View v = convertView;
        CompleteListViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.list_layout, null);
            viewHolder = new CompleteListViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (CompleteListViewHolder) v.getTag();
        }
        guess = Integer.parseInt(mList.get(position));




        if (!isRepeated(guess) && (guess > 1000 && guess < 9999)) {
            //  completeListActivity.toBeAdded=true;


            viewHolder.mTVItem.setText(mList.get(position));


            int cowno = 0, bullno = 0;
            String num = Integer.toString(random);
            String gues = Integer.toString(guess);
            char[] n = num.toCharArray();
            char g[] = gues.toCharArray();
            RatingBar rate = new RatingBar(mContext);
            rate.setNumStars(5);

            for (int dex = 0; dex < 4; dex++) {
                if (isCow(Character.getNumericValue(g[dex]), random, guess))
                    cowno++;
                if (isBull(Character.getNumericValue(g[dex]), random, guess))
                    bullno++;
            }


            cowList = new ArrayList<String>(mList.size());
            bullList = new ArrayList<String>(mList.size());

            for (int dex = 0; dex < mList.size(); dex++) {
                cowList.add("");
                bullList.add("");
            }


            System.out.println(random);
            //  viewHolder.check.setText(Integer.toString(random));
            viewHolder.cow.setText(Integer.toString(cowno));
            viewHolder.bull.setText(Integer.toString(bullno));


            c++;


            if (viewHolder.bull.getText().toString() == "4" ) {

                enter = (TextView) mContext.findViewById(R.id.enter);
                button = (Button) mContext.findViewById(R.id.addItemToList);
                button.setEnabled(false);
                enter.setEnabled(false);
                enter.setClickable(false);






                AlertDialog.Builder builder1 = new AlertDialog.Builder(mContext);


                // builder1.setMessage("CONGRATULATIONS! YOU'VE GUESSED IT CORRECTLY IN "+c+" ATTEMPT(S)!");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "NEW GAME",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                mContext.startActivity(returnIntent);
                            }
                        });

                builder1.setNegativeButton(
                        "EXIT",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                                homeIntent.addCategory(Intent.CATEGORY_HOME);
                                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                mContext.startActivity(homeIntent);

                            }
                        });

                //alertDialog = builder1.create();


                LinearLayout ll = new LinearLayout(mContext);
                ll.setOrientation(LinearLayout.VERTICAL);
                rate.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                TextView message = new TextView(mContext);
                message.setText("CONGRATULATIONS! YOU'VE GUESSED THE CORRECT NUMBER IN " + completeListActivity.k + " ATTEMPTS!");




                //add obtained score and date to database



                ll.addView(message);




                if (c <= 15)
                    rate.setRating(5);
                else if (c <= 16 && c>20)
                    rate.setRating(4);
                else if (c <= 21 && c>30)
                    rate.setRating(3);
                else
                    rate.setRating(1.5f);


                ns=rate.getRating();

                ll.addView(rate);

                builder1.setView(ll);
                alertDialog = builder1.create();


                // alertDialog.setMessage();

              if(completeListActivity.buttonClicked) {
                  alertDialog.show();

                  completeListActivity.buttonClicked=false;

                  dbHandler=new DBHandler(mContext);
                  String formattedDate= DateFormat.getDateTimeInstance().format(new Date());
                  Scores sc=new Scores(completeListActivity.k, formattedDate,ns);
                  dbHandler.addScore(sc);

                  System.out.println("Score "+completeListActivity.k+"  added");
                  System.out.println(ns);



              }

                completeList = null;
            } else {
                //
            }
        } else {
            //
        }




        return v;
    }
}

class CompleteListViewHolder {
    public TextView mTVItem;
    public TextView cow;
    public TextView bull;
    public TextView check;
    public ListView completeList;
    public Button button;
    public CompleteListViewHolder(View base) {
        mTVItem = (TextView) base.findViewById(R.id.editText);
        cow=(TextView)base.findViewById(R.id.cow);
        bull=(TextView)base.findViewById(R.id.bull);
      //  check=(TextView)base.findViewById(R.id.check);
        completeList=(ListView)base.findViewById(R.id.completeList);
        button=(Button)base.findViewById(R.id.addItemToList);
    }
}