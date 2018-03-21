package sagar.khengat.supermarket.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.LayerDrawable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import sagar.khengat.supermarket.Constants.Config;
import sagar.khengat.supermarket.LoginActivity;
import sagar.khengat.supermarket.R;
import sagar.khengat.supermarket.SplashScreen;
import sagar.khengat.supermarket.util.InputValidation;

public class CaptchaActivity extends AppCompatActivity {
    ImageView im;
    private AppCompatButton appCompatButtonSubmit;
    private AppCompatButton appCompatButtonReset;
    private TextInputLayout textInputLayoutCaptcha;
    private InputValidation inputValidation;

    Captcha c;
    private TextInputEditText textInputEditTextCaptcha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captcha);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        im = (ImageView)findViewById(R.id.imageView1);
        inputValidation = new InputValidation(CaptchaActivity.this);
        appCompatButtonSubmit = (AppCompatButton) findViewById(R.id.appCompatButtonsubmit);
        appCompatButtonReset = (AppCompatButton) findViewById(R.id.appCompatButtonReset);
        textInputLayoutCaptcha = (TextInputLayout) findViewById(R.id.textInputLayoutcaptcha);
        textInputEditTextCaptcha = (TextInputEditText) findViewById(R.id.textInputEditTextcaptcha);
        c = new MathCaptcha(300, 100, MathCaptcha.MathOptions.PLUS_MINUS_MULTIPLY);
        //Captcha c = new TextCaptcha(300, 100, 5, TextOptions.NUMBERS_AND_LETTERS);
        im.setImageBitmap(c.image);
        im.setLayoutParams(new LinearLayout.LayoutParams(c.width *2, c.height *2));
//        textInputEditTextCaptcha.setText(c.answer);
        appCompatButtonSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!inputValidation.isInputEditTextFilled(textInputEditTextCaptcha, textInputLayoutCaptcha, "Please Enter Answer..")) {
                    return;
                }
                if(textInputEditTextCaptcha.getText().toString().trim().equals(c.answer))
                {
                    Intent i = new Intent(CaptchaActivity.this, MainActivity.class);
                    startActivity(i);

                    // close this activity
                    finish();
                }
                else
                {
                        textInputLayoutCaptcha.setError("Incorrect answer, try again..");
                }
            }
        });

        appCompatButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = new MathCaptcha(300, 100, MathCaptcha.MathOptions.PLUS_MINUS_MULTIPLY);
                //Captcha c = new TextCaptcha(300, 100, 5, TextOptions.NUMBERS_AND_LETTERS);
                im.setImageBitmap(c.image);
                im.setLayoutParams(new LinearLayout.LayoutParams(c.width *2, c.height *2));
//        textInputEditTextCaptcha.setText(c.answer);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.store_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){

            case R.id.updatepass:
                startActivity(new Intent(CaptchaActivity.this, ChangePassword.class));
                return true;
            case R.id.logout:
                logout();
                return true;

            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void logout() {
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(R.string.logout_title_msg);
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //Getting out sharedpreferences
                        SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
                        editor.putString(Config.WHO, "");

                        //putting blank value to usertoken
                        editor.putString(Config.USER, "");
                        editor.putString(Config.NAME,"");

                        //Saving the sharedpreferences
                        editor.commit();

                        //Starting login activity
                        Intent intent = new Intent(CaptchaActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

}
