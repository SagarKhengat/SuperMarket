package sagar.khengat.supermarket;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import sagar.khengat.supermarket.Adapters.SpinnerAreaAdapter;
import sagar.khengat.supermarket.Constants.Config;
import sagar.khengat.supermarket.model.Customer;
import sagar.khengat.supermarket.model.Gender;
import sagar.khengat.supermarket.util.DatabaseHandler;
import sagar.khengat.supermarket.util.InputValidation;


public class Register extends AppCompatActivity implements Spinner.OnItemSelectedListener, View.OnClickListener{
    private final AppCompatActivity activity = Register.this;
    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutContactNo;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;
    private TextInputLayout textInputLayoutAddress;
    private TextInputLayout textInputLayoutUserId;

    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextContactNo;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;
    private TextInputEditText textInputEditTextAddress;
    private TextInputEditText textInputEditTextUserId;

    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;

    private InputValidation inputValidation;
    private DatabaseHandler databaseHelper;
    private SpinnerAreaAdapter areaAdapter;
    Customer customer;
    private Spinner spinnerArea;
    private Gender gender;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutContactNo = (TextInputLayout) findViewById(R.id.textInputLayoutContact);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);
        textInputLayoutAddress= (TextInputLayout) findViewById(R.id.textInputLayoutCustomerAddress);
        textInputLayoutUserId= (TextInputLayout) findViewById(R.id.textInputLayoutUserId);

        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextContactNo = (TextInputEditText) findViewById(R.id.textInputEditTextContact);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);
        textInputEditTextAddress= (TextInputEditText) findViewById(R.id.textInputEditTextCustomerAddress);
        textInputEditTextUserId= (TextInputEditText) findViewById(R.id.textInputEditTextUserId);
        gender = new Gender();
        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);

        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);
        spinnerArea = (Spinner) findViewById(R.id.spinnerArea );
        spinnerArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Gender area1 = areaAdapter.getItem(position);
                gender=area1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(Register.this);
        appCompatTextViewLoginLink.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHandler(activity);
        customer = new Customer();


    }


    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonRegister:

                    postDataToSQLiteCustomer();
                break;

            case R.id.appCompatTextViewLoginLink:
                startActivity(new Intent(Register.this,LoginActivity.class));
                finish();
                break;
        }
    }

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLiteCustomer() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextContactNo, textInputLayoutContactNo, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextContactNo, textInputLayoutContactNo, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }

        if (!databaseHelper.checkCustomer(textInputEditTextName.getText().toString().trim())) {

            customer.setName(textInputEditTextName.getText().toString().trim());
            customer.setContactNo(textInputEditTextContactNo.getText().toString().trim());
            customer.setPassword(textInputEditTextPassword.getText().toString().trim());
            customer.setAddress(textInputEditTextAddress.getText().toString().trim());

            databaseHelper.addCustomer(customer);

            // Snack Bar to show success message that record saved successfully
            emptyInputEditText();
            startActivity(new Intent(Register.this,LoginActivity.class));
            finish();


        } else {
            // Snack Bar to show error message that record already exists
            Toast.makeText(activity, getString(R.string.error_email_exists), Toast.LENGTH_LONG).show();
        }


    }


    /**
     * This method is to validate the input text fields and post data to SQLite
     */


    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextName.setText(null);
        textInputEditTextContactNo.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
        textInputEditTextAddress.setText(null);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void loadSpinnerArea() {
        // database handler


        // Spinner Drop down elements
        List<Gender> allAreas = databaseHelper.fnGetAllGender();

        // Creating adapter for spinner
        areaAdapter = new SpinnerAreaAdapter(activity,
                android.R.layout.simple_spinner_item,
                allAreas);

        // Drop down layout style - list view with radio button


        // attaching data adapter to spinner
        spinnerArea.setAdapter(areaAdapter);
    }

}
