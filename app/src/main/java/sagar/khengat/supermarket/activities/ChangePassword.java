package sagar.khengat.supermarket.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import sagar.khengat.supermarket.Adapters.SpinnerAreaAdapter;
import sagar.khengat.supermarket.Constants.Config;
import sagar.khengat.supermarket.LoginActivity;
import sagar.khengat.supermarket.R;
import sagar.khengat.supermarket.Register;
import sagar.khengat.supermarket.model.Customer;
import sagar.khengat.supermarket.model.Gender;
import sagar.khengat.supermarket.util.DatabaseHandler;
import sagar.khengat.supermarket.util.InputValidation;


public class ChangePassword extends AppCompatActivity implements View.OnClickListener {
    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutContactNo;

    private TextInputLayout textInputLayoutAddress;


    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextContactNo;

    private TextInputEditText textInputEditTextAddress;


    private AppCompatButton appCompatButtonRegister;


    private InputValidation inputValidation;


    Customer customer;
    Gson gson;

    String genderString = " ";
    private final AppCompatActivity activity = ChangePassword.this;
    private DatabaseHandler databaseHelper;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle("Change Profile");

        initViews();
        initListeners();
        initObjects();

        gson = new Gson();

        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(Config.USER, "");

        customer = gson.fromJson(json,Customer.class);
        textInputEditTextAddress.setText(customer.getAddress());
        textInputEditTextName.setText(customer.getName());
        textInputEditTextContactNo.setText(customer.getContactNo());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutContactNo = (TextInputLayout) findViewById(R.id.textInputLayoutContact);

        textInputLayoutAddress= (TextInputLayout) findViewById(R.id.textInputLayoutCustomerAddress);


        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextContactNo = (TextInputEditText) findViewById(R.id.textInputEditTextContact);

        textInputEditTextAddress= (TextInputEditText) findViewById(R.id.textInputEditTextCustomerAddress);


        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);



    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(ChangePassword.this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHandler(activity);
        customer = new Customer();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonRegister:

                postDataToSQLiteCustomer();
                break;

            case R.id.appCompatTextViewLoginLink:
                startActivity(new Intent(ChangePassword.this,LoginActivity.class));
                finish();
                break;
        }
    }

    private void postDataToSQLiteCustomer() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
            return;
        }

        if (!inputValidation.isInputEditTextFilled(textInputEditTextContactNo, textInputLayoutContactNo, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextContactNo, textInputLayoutContactNo, "Enter valid mobile Number")) {
            return;
        }


                customer.setName(textInputEditTextName.getText().toString().trim());

                customer.setContactNo(textInputEditTextContactNo.getText().toString().trim());

                customer.setAddress(textInputEditTextAddress.getText().toString().trim());

                databaseHelper.updateCustomer(customer);

                // Snack Bar to show success message that record saved successfully
                emptyInputEditText();
//                startActivity(new Intent(ChangePassword.this, LoginActivity.class));
                finish();




    }

    private void emptyInputEditText() {
        textInputEditTextName.setText(null);
        textInputEditTextContactNo.setText(null);
        textInputEditTextAddress.setText(null);
    }

}