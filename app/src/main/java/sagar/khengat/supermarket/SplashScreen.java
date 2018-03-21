package sagar.khengat.supermarket;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import sagar.khengat.supermarket.Constants.Config;
import sagar.khengat.supermarket.activities.CaptchaActivity;
import sagar.khengat.supermarket.activities.MainActivity;


public class SplashScreen extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    private boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Animation animation = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.blink);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setDuration(2000);
        final ImageView splash = (ImageView) findViewById(R.id.image);
        splash.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {





                SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                //Fetching the boolean value form sharedpreferences
                loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);


                Log.d( "Test", "Logged In Splash screen: " +loggedIn );

                //If we will get true
                if(loggedIn){
                    //We will start the Profile Activity
                    Intent i = new Intent(SplashScreen.this, CaptchaActivity.class);
                    startActivity(i);

                    // close this activity
                    finish();
                } else {
                    Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(i);

                    // close this activity
                    finish();
                }



            }
        }, SPLASH_TIME_OUT);


    }




}