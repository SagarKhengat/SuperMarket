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
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
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



    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;

    private AppCompatTextView textViewLinkRegister;


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
        ImageView ivImg = (ImageView) findViewById(R.id.imageView);
        GlideDrawableImageViewTarget ivTarget = new GlideDrawableImageViewTarget(ivImg);
        Glide.with(this)
                .load(R.drawable.products) // The image you want to load
                .crossFade()
                .placeholder(R.drawable.ic_add_box_black_24dp) // The placeholder GIF.
                .into(ivTarget);
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {



        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);

        textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);


    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);

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
            p1.setProductName("Lux");
            p1.setProductSize("150");
            p1.setProductBrand("unilever");
            p1.setProductUnit("gm");
            p1.setProductPrice(36);
            if(!databaseHelper.checkProduct(p1.getProductName())) {
                databaseHelper.addProduct(p1);
            }

            Product p2 = new Product();
            p2.setProductName("All Out Ultra");
            p2.setProductSize("45");
            p2.setProductBrand("allout");
            p2.setProductUnit("ml");
            p2.setProductPrice(76.50);
            if(!databaseHelper.checkProduct(p2.getProductName())) {
                databaseHelper.addProduct(p2);
            }

            Product p3 = new Product();
            p3.setProductName("Ariel Washing Detergent Powder");
            p3.setProductSize("1");
            p3.setProductBrand("ariel");
            p3.setProductUnit("kg");
            p3.setProductPrice(374);
            if(!databaseHelper.checkProduct(p3.getProductName())) {
                databaseHelper.addProduct(p3);
            }
            Product p4 = new Product();
            p4.setProductName("Colgate");
            p4.setProductSize("200");
            p4.setProductBrand("colgate");
            p4.setProductUnit("gm");
            p4.setProductPrice(75);
            if(!databaseHelper.checkProduct(p4.getProductName())) {
                databaseHelper.addProduct(p4);
            }
            Product p5 = new Product();
            p5.setProductName("Patanjali Honey");
            p5.setProductSize("1");
            p5.setProductBrand("patanjali");
            p5.setProductUnit("kg");
            p5.setProductPrice(260);
            if(!databaseHelper.checkProduct(p5.getProductName())) {
                databaseHelper.addProduct(p5);
            }
            Product p6 = new Product();
            p6.setProductName("Fresho Palak");
            p6.setProductSize("1");
            p6.setProductBrand("palak");
            p6.setProductUnit("kg");
            p6.setProductPrice(50);
            if(!databaseHelper.checkProduct(p6.getProductName())) {
                databaseHelper.addProduct(p6);
            }
            Product p7 = new Product();
            p7.setProductName("Fresho Green Peas");
            p7.setProductSize("500");
            p7.setProductBrand("peas");
            p7.setProductUnit("gm");
            p7.setProductPrice(39);
            if(!databaseHelper.checkProduct(p7.getProductName())) {
                databaseHelper.addProduct(p7);
            }
            Product p8 = new Product();
            p8.setProductName("Maggi Sauce");
            p8.setProductSize("1");
            p8.setProductBrand("maggi");
            p8.setProductUnit("kg");
            p8.setProductPrice(102.51);
            if(!databaseHelper.checkProduct(p8.getProductName())) {
                databaseHelper.addProduct(p8);
            }
            Product p9 = new Product();
            p9.setProductName("Parle Gluco Biscuits");
            p9.setProductSize("800");
            p9.setProductBrand("parle");
            p9.setProductUnit("gm");
            p9.setProductPrice(58.20);
            if(!databaseHelper.checkProduct(p9.getProductName())) {
                databaseHelper.addProduct(p9);
            }
            Product p11 = new Product();
            p11.setProductName("Cadbury Dairy Milk Silk ");
            p11.setProductSize("150");
            p11.setProductBrand("cadbury");
            p11.setProductUnit("gm");
            p11.setProductPrice(150);
            if(!databaseHelper.checkProduct(p11.getProductName())) {
                databaseHelper.addProduct(p11);
            }
            Product p12 = new Product();
            p12.setProductName("Kelloggs Chocos");
            p12.setProductSize("1.2");
            p12.setProductBrand("kelloggs");
            p12.setProductUnit("kg");
            p12.setProductPrice(358);
            if(!databaseHelper.checkProduct(p12.getProductName())) {
                databaseHelper.addProduct(p12);
            }
            Product p13 = new Product();
            p13.setProductName("Teddy");
            p13.setProductSize("1");
            p13.setProductBrand("baybee");
            p13.setProductUnit("pc");
            p13.setProductPrice(340);
            if(!databaseHelper.checkProduct(p13.getProductName())) {
                databaseHelper.addProduct(p13);
            }
            Product p14 = new Product();
            p14.setProductName("Boost");
            p14.setProductSize("500");
            p14.setProductBrand("gsk");
            p14.setProductUnit("gm");
            p14.setProductPrice(217);
            if(!databaseHelper.checkProduct(p14.getProductName())) {
                databaseHelper.addProduct(p14);
            }
            Product p15 = new Product();
            p15.setProductName("Fresho Bread");
            p15.setProductSize("400");
            p15.setProductBrand("bread");
            p15.setProductUnit("gm");
            p15.setProductPrice(34.20);
            if(!databaseHelper.checkProduct(p15.getProductName())) {
                databaseHelper.addProduct(p15);
            }
            Product p16 = new Product();
            p16.setProductName("Amul Masti Buttermilk");
            p16.setProductSize("1");
            p16.setProductBrand("amul");
            p16.setProductUnit("lt");
            p16.setProductPrice(50);
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


