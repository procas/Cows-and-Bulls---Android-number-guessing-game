package thenewboston.com.dynamic_2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

public class WELCOME extends AppCompatActivity {

   // static MediaPlayer player;



    private final int SPLASH_DISPLAY_LENGTH = 3000;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
      //  player = MediaPlayer.create(this, R.raw.theme);
        setContentView(R.layout.activity_welcome);


        //player.setLooping(true);



        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(WELCOME.this, OPTIONS.class);
                WELCOME.this.startActivity(mainIntent);
                WELCOME.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    @Override
    public void onStart() {
        super.onStart();
        //player.start();



        }
}
