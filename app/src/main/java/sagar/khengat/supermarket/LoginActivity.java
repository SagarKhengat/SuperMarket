package sagar.khengat.supermarket;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import sagar.khengat.supermarket.Constants.Config;
import sagar.khengat.supermarket.activities.ChangePassword;
import sagar.khengat.supermarket.activities.MainActivity;
import sagar.khengat.supermarket.model.Customer;
import sagar.khengat.supermarket.util.DatabaseHandler;
import sagar.khengat.supermarket.util.InputValidation;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private final AppCompatActivity activity = LoginActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;

    private AppCompatTextView textViewLinkRegister;
    private AppCompatTextView textViewLinkForgotPassword;

    private InputValidation inputValidation;
    private DatabaseHandler databaseHelper;

    private Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        gson = new Gson();
        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);

        textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);
        textViewLinkForgotPassword = (AppCompatTextView) findViewById(R.id.textViewLinkForgotPassword);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
        textViewLinkForgotPassword.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new DatabaseHandler(activity);
        inputValidation = new InputValidation(activity);

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonLogin:


                break;
            case R.id.textViewLinkRegister:
                // Navigate to RegisterActivity

                break;
            case R.id.textViewLinkForgotPassword:
                Intent intentRegister = new Intent(activity, ChangePassword.class);
                startActivity(intentRegister);
                finish();
        }
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void verifyFromSQLiteCustomer() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }

        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))) {
            return;
        }

        if (databaseHelper.checkCustomer(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())) {
            //Creating a shared preference
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                      //Creating editor to store values to shared preferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
            Customer customer = databaseHelper.getCustomer(textInputEditTextEmail.getText().toString().trim()
                    , textInputEditTextPassword.getText().toString().trim());
            String json = gson.toJson(customer);
            editor.putString(Config.USER,json);
                        //Adding values to editor
                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
//                        editor.putString(Config.NAME, userFirstName);
//
//                        editor.putInt(Config.USERTOKEN,userToken);

                        //Saving values to editor
                        editor.apply();
            Intent accountsIntent = new Intent(activity, MainActivity.class);
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);
            finish();
            Toast.makeText(activity, "Login Success", Toast.LENGTH_SHORT).show();


        } else {
            // Snack Bar to show success message that record is wrong
            if (databaseHelper.checkCustomer((textInputEditTextEmail.getText().toString().trim()))) {
                Toast.makeText(activity, "wrong password..please try again..", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(activity, "No username found, please create account", Toast.LENGTH_SHORT).show();
            }
        }
    }



    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }
}


