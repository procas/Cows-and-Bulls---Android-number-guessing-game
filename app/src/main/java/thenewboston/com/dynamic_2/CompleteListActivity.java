package thenewboston.com.dynamic_2;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class CompleteListActivity extends AppCompatActivity implements OnClickListener {
    //CompleteListAdapter completeListAdapter=new CompleteListAdapter()
    public static int rand;
    private ListView mCompleteListView;
    private ListView listView;
    public ImageView back;
    private static Button mAddItemToList;
    private EditText enter;
    private TextView cow;
    private TextView bull;
   // AlertDialog alertDialog;
    AlertDialog alertDialog2;
    AlertDialog alertDialog3;
    Animation animation1;

    protected static boolean toBeAdded;
    protected static boolean rep;
    private ImageView powerButton;
    public static boolean buttonClicked;
    public static boolean revealed;
    private List<String> mItems;
    private List<String> mCow;
    private List<String> mBull;
   private CompleteListAdapter mListAdapter;
    private CompleteListAdapter cowAdapter;
    private CompleteListAdapter bullAdapter;
    private ImageView sound;
   // public static MediaPlayer player;

    private static final int MIN = 0, MAX = 10000;
    public static int k=0;
    public AlertDialog alertDialog=null;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  listView=(ListView)findViewById(R.id.completeList);


        // listView.getBackground().setAlpha(51);

        setContentView(R.layout.activity_complete_list);
        initViews();
        mItems = new ArrayList<String>();
        mListAdapter = new CompleteListAdapter(this, mItems);
        mCompleteListView.setAdapter(mListAdapter);
        if(mListAdapter.finished)
            mAddItemToList.setVisibility(View.INVISIBLE);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        powerButton = (ImageView)findViewById(R.id.powerButton);
         powerButton.setOnClickListener(new View.OnClickListener()
        {
        public void onClick(View v) {
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory(Intent.CATEGORY_HOME);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
        });


        // build the alert dialog box


    }




    public void power(View view) {

        TextView ask=new TextView(this);
        ask.setText("DO YOU REALLY WISH TO EXIT?");

        AlertDialog.Builder fileDialog = new AlertDialog.Builder(this);
        //        content.setText(result);
        fileDialog.setView(ask);
        fileDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int which)
            {
                Intent homeIntent=new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory(Intent.CATEGORY_HOME);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);

            }

        });
        fileDialog.setNegativeButton("NO", null);
        fileDialog.show();

    }

    public void revealAnswer(View view)
    {

              revealed=true;

        AlertDialog.Builder builder2 = new AlertDialog.Builder(CompleteListActivity.this);


        // builder1.setMessage("CONGRATULATIONS! YOU'VE GUESSED IT CORRECTLY IN "+c+" ATTEMPT(S)!");
        builder2.setCancelable(true);

        builder2.setPositiveButton(
                "YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        AlertDialog.Builder builder3 = new AlertDialog.Builder(CompleteListActivity.this);
                        alertDialog3=builder3.create();
                        alertDialog3.setMessage("THE NUMBER IS "+mListAdapter.random);
                        alertDialog3.show();

                        mAddItemToList.setEnabled(false);
                        enter.setEnabled(false);
                        enter.setClickable(false);
                    }
                });

        builder2.setNegativeButton(
                "NO", null
        );

        builder2.setTitle("HINT");
        alertDialog2 = builder2.create();

        alertDialog2.setMessage("Are you sure you want the word revealed?");
        alertDialog2.show();



    }

    public void again(View view)
    {
        Intent home=new Intent(this, CompleteListActivity.class);
        startActivity(home);
    }

    public void showHelp(View view)
    {
        Intent helpIntent=new Intent(this, ScrollInstruct.class);
        startActivity(helpIntent);
    }














    private void initViews() {
        back=(ImageView)findViewById(R.id.back);
        mCompleteListView = (ListView) findViewById(R.id.completeList);
        mAddItemToList=(Button)findViewById(R.id.addItemToList);
        back.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.hail));
        back.getBackground().setAlpha(45);
      animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        animation1.setRepeatCount(Animation.INFINITE);


        back.startAnimation(animation1);



        mAddItemToList = (Button) findViewById(R.id.addItemToList);
        enter = (EditText) findViewById(R.id.enter);
        cow=(TextView)findViewById(R.id.cow);
        bull=(TextView)findViewById(R.id.bull);
        mAddItemToList.setOnClickListener(this);
    }


    public static boolean isRepeated(int n)
    {
        rep=false;
        String num=Integer.toString(n);
        char c[]=num.toCharArray();
        for(int i=0;i<c.length;i++)
        {
            for(int j=0;j<c.length;j++)
            {
                if(i!=j)
                {
                    if(c[i]==c[j])
                        rep= true;
                }
            }
        }
        return rep;
    }












    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addItemToList:
                //enter=(EditText)findViewById(R.id.enter);

                 buttonClicked=true;



                  String curr = "";
                curr = enter.getText().toString();

                if(enter.getText()!=null) {
                    if (Integer.parseInt(enter.getText().toString()) > 1000 && Integer.parseInt(enter.getText().toString()) > 1000 && !isRepeated(Integer.parseInt(enter.getText().toString())))
                        toBeAdded = true;
                    else
                        toBeAdded = false;
                }
                else

                    Toast.makeText(this, "PLEASE ENTER VALID NUMBERS!", Toast.LENGTH_LONG).show();






                    if(toBeAdded)
                    mItems.add(String.valueOf(curr));
                    else
                    Toast.makeText(this, "PLEASE ENTER VALID NUMBERS!", Toast.LENGTH_LONG).show();




                k=mItems.indexOf(String.valueOf(curr))+1;





                mListAdapter.notifyDataSetChanged();
                enter.setText("");

                break;
        }
    }

  /*  @Override
    protected void onRestart() {
        super.onRestart();

        Intent moveIntent=new Intent(this, WELCOME.class);
        startActivity(moveIntent);

    }
    */



    @Override
    public void onStart() {
        super.onStart();



        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CompleteList Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://thenewboston.com.dynamic_2/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }


    public void toOptions(View view)
    {
        Intent optionIntent=new Intent(this, OPTIONS.class);
        startActivity(optionIntent);
    }




       @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CompleteList Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://thenewboston.com.dynamic_2/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


}
