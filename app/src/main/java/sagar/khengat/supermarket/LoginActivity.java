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
import sagar.khengat.supermarket.model.Gender;
import sagar.khengat.supermarket.model.Product;
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


        Gender category3 = new Gender();
        category3.setGenderName("Select Gender");
        if(!databaseHelper.checkGender(category3)) {
            databaseHelper.addGender(category3);
        }
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
                verifyFromSQLiteCustomer();

                break;
            case R.id.textViewLinkRegister:
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(activity, Register.class);
                startActivity(intentRegister);
                finish();
                break;
            case R.id.textViewLinkForgotPassword:
                Intent intentpass = new Intent(activity, ChangePassword.class);
                startActivity(intentpass);
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


            Product p1 = new Product();
            p1.setProductName("lux");
            p1.setProductSize("100");
            p1.setProductBrand("Dabour");
            p1.setProductUnit("gm");
            p1.setProductPrice(12);
            if(!databaseHelper.checkProduct(p1.getProductName())) {
                databaseHelper.addProduct(p1);
            }

            Product p2 = new Product();
            p2.setProductName("asd");
            p2.setProductSize("100");
            p2.setProductBrand("Dabour");
            p2.setProductUnit("gm");
            p2.setProductPrice(12);
            if(!databaseHelper.checkProduct(p2.getProductName())) {
                databaseHelper.addProduct(p2);
            }

            Product p3 = new Product();
            p3.setProductName("dd");
            p3.setProductSize("100");
            p3.setProductBrand("Dabour");
            p3.setProductUnit("gm");
            p3.setProductPrice(12);
            if(!databaseHelper.checkProduct(p3.getProductName())) {
                databaseHelper.addProduct(p3);
            }
            Product p4 = new Product();
            p4.setProductName("gg");
            p4.setProductSize("100");
            p4.setProductBrand("Dabour");
            p4.setProductUnit("gm");
            p4.setProductPrice(12);
            if(!databaseHelper.checkProduct(p4.getProductName())) {
                databaseHelper.addProduct(p4);
            }
            Product p5 = new Product();
            p5.setProductName("lutrx");
            p5.setProductSize("100");
            p5.setProductBrand("Dabour");
            p5.setProductUnit("gm");
            p5.setProductPrice(12);
            if(!databaseHelper.checkProduct(p5.getProductName())) {
                databaseHelper.addProduct(p5);
            }
            Product p6 = new Product();
            p6.setProductName("ghgh");
            p6.setProductSize("100");
            p6.setProductBrand("Dabour");
            p6.setProductUnit("gm");
            p6.setProductPrice(12);
            if(!databaseHelper.checkProduct(p6.getProductName())) {
                databaseHelper.addProduct(p6);
            }
            Product p7 = new Product();
            p7.setProductName("jjh");
            p7.setProductSize("100");
            p7.setProductBrand("Dabour");
            p7.setProductUnit("gm");
            p7.setProductPrice(12);
            if(!databaseHelper.checkProduct(p7.getProductName())) {
                databaseHelper.addProduct(p7);
            }
            Product p8 = new Product();
            p8.setProductName("yuyu");
            p8.setProductSize("100");
            p8.setProductBrand("Dabour");
            p8.setProductUnit("gm");
            p8.setProductPrice(12);
            if(!databaseHelper.checkProduct(p8.getProductName())) {
                databaseHelper.addProduct(p8);
            }
            Product p9 = new Product();
            p9.setProductName("yty");
            p9.setProductSize("100");
            p9.setProductBrand("Dabour");
            p9.setProductUnit("gm");
            p9.setProductPrice(12);
            if(!databaseHelper.checkProduct(p9.getProductName())) {
                databaseHelper.addProduct(p9);
            }
            Product p11 = new Product();
            p11.setProductName("tyt");
            p11.setProductSize("100");
            p11.setProductBrand("Dabour");
            p11.setProductUnit("gm");
            p11.setProductPrice(12);
            if(!databaseHelper.checkProduct(p11.getProductName())) {
                databaseHelper.addProduct(p11);
            }
            Product p12 = new Product();
            p12.setProductName("ltytux");
            p12.setProductSize("100");
            p12.setProductBrand("Dabour");
            p12.setProductUnit("gm");
            p12.setProductPrice(12);
            if(!databaseHelper.checkProduct(p12.getProductName())) {
                databaseHelper.addProduct(p12);
            }
            Product p13 = new Product();
            p13.setProductName("lutytyux");
            p13.setProductSize("100");
            p13.setProductBrand("Dabour");
            p13.setProductUnit("gm");
            p13.setProductPrice(12);
            if(!databaseHelper.checkProduct(p13.getProductName())) {
                databaseHelper.addProduct(p13);
            }
            Product p14 = new Product();
            p14.setProductName("kjk");
            p14.setProductSize("100");
            p14.setProductBrand("Dabour");
            p14.setProductUnit("gm");
            p14.setProductPrice(12);
            if(!databaseHelper.checkProduct(p14.getProductName())) {
                databaseHelper.addProduct(p14);
            }
            Product p15 = new Product();
            p15.setProductName("luxkjkj");
            p15.setProductSize("100");
            p15.setProductBrand("Dabour");
            p15.setProductUnit("gm");
            p15.setProductPrice(12);
            if(!databaseHelper.checkProduct(p15.getProductName())) {
                databaseHelper.addProduct(p15);
            }
            Product p16 = new Product();
            p16.setProductName("lughghtx");
            p16.setProductSize("100");
            p16.setProductBrand("Dabour");
            p16.setProductUnit("gm");
            p16.setProductPrice(12);
            if(!databaseHelper.checkProduct(p16.getProductName())) {
                databaseHelper.addProduct(p16);
            }


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
                Toast.makeText(activity, "No username or UserId found, please create account", Toast.LENGTH_SHORT).show();
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


