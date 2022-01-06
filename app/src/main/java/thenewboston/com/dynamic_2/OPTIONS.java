package thenewboston.com.dynamic_2;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;

public class OPTIONS extends AppCompatActivity {

    DBHandler dbHandler;
    static boolean on;
    ImageView sound;
    Button quit ;
    ImageView insButton;
    static MediaPlayer player;
    boolean flag = false;
    static String pic="_SOUND";
    static boolean entered=false;
    AlertDialog.Builder fileDialog;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    Button play;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  sound=(ImageView)findViewById(R.id.sound);




                player = MediaPlayer.create(this, R.raw.theme);

        setContentView(R.layout.activity_options);
        play=(Button)findViewById(R.id.play);
        sound=(ImageView)findViewById(R.id.sound);
        insButton = (ImageView)findViewById(R.id.insButton);
        quit = (Button)findViewById(R.id.quit);



        sound.setOnClickListener(new ImageView.OnClickListener()
            {

                public void onClick(View v) {


                    if (flag) {

                        player.stop();

                        flag= false;
                        player = null;

                        sound.setImageDrawable(getResources().getDrawable(R.drawable.no_sound));

                    }


                        else{
                            player = MediaPlayer.create(getApplicationContext(),R.raw.theme);
                            player.start();
                            flag = true;

                            sound.setImageDrawable(getResources().getDrawable(R.drawable.sound));
                          //  pic = "_SOUND";
                           // on = true;
                        }
                    }



            });


        insButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent infoIntent = new Intent(getApplicationContext(), ScrollInstruct.class);
                startActivity(infoIntent);
            }
        });

        quit.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v)
            {

                Intent homeIntent=new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory(Intent.CATEGORY_HOME);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);


            }

        });

        play.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent playIntent = new Intent(getApplicationContext(), CompleteListActivity.class);
                startActivity(playIntent);
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void openSettings(View view) {
        Intent setIntent = new Intent(this, SettingsActivity.class);
        startActivity(setIntent);
        entered=true;
    }


    public void openScores(View view) {
        Intent scoreIntent = new Intent(this, Score_list.class);
        startActivity(scoreIntent);
        entered=true;
    }

    public void start(View view) {
        Intent playIntent = new Intent(this, CompleteListActivity.class);
        startActivity(playIntent);
        entered=true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(flag) {
            player.start();
            flag = false;
        }
        else {
        player.stop();
            player.release();
            flag = true;
        }



    }


    @Override
    protected void onPause() {
        super.onPause();
        if(player!=null && flag!=false)
        player.stop();
    }
    @Override
    public void onStart() {
        super.onStart();
        if(!flag) {
            player.start();
            flag = true;
        }


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "OPTIONS Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://thenewboston.com.dynamic_2/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "OPTIONS Page", // TODO: Define a title for the content shown.
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
