package sagar.khengat.supermarket.activities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;


import java.io.File;

import sagar.khengat.supermarket.Constants.Config;
import sagar.khengat.supermarket.R;
import sagar.khengat.supermarket.model.Product;


public class ProductDescription extends AppCompatActivity {

    public ImageView imageView;
    public TextView textViewName;
    public TextView textViewSize;
    public TextView textViewDescription;
    public TextView textActualPrice;
    public TextView textSellingPrice;
    public TextView textBrand;
    Product product;
    Gson gson;
    final Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);
        imageView = (ImageView) findViewById(R.id.product_image);
        textViewName = (TextView) findViewById(R.id.product_name);
        textSellingPrice= (TextView) findViewById(R.id.tv_selling_price);
        textViewSize = (TextView) findViewById(R.id.tv_product_size);
        textBrand = (TextView) findViewById(R.id.product_brand);
        product = new Product();
        gson = new Gson();
        final SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String strArea = sharedPreferences.getString("product", "");
        product = gson.fromJson(strArea,Product.class);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);





        setTitle(product.getProductName());


        textViewName.setText(product.getProductName());
        textBrand.setText(product.getProductBrand());


        textSellingPrice.setText("Rs. "+ Double.toString(product.getProductPrice()));
        textViewSize.setText(product.getProductSize());



        Glide.with(activity).load(getImage(product.getProductBrand())).into(imageView);

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
    public int getImage(String imageName) {

        int drawableResourceId = getResources().getIdentifier(imageName, "drawable",getPackageName());

        return drawableResourceId;
    }
}
