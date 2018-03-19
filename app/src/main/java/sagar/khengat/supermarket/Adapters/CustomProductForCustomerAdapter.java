package sagar.khengat.supermarket.Adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;


import sagar.khengat.supermarket.R;
import sagar.khengat.supermarket.model.Product;
import sagar.khengat.supermarket.util.MyAdapterListener;

/**
 * Created by sagar on 3/7/18.
 */

public class CustomProductForCustomerAdapter extends RecyclerView.Adapter<CustomProductForCustomerAdapter.ViewHolder> {


    private Context context;

    public MyAdapterListener onClickListener;
    List<Product> productList;
    Product product;


    public CustomProductForCustomerAdapter(List<Product> products, Context context, MyAdapterListener listener){
        super();
        //Getting all the superheroes
        this.productList = products;
//        this.alQuantity= q;
        this.context = context;
        this.onClickListener = listener;
    }
    public CustomProductForCustomerAdapter(Product product, Context context){
        super();
        //Getting all the superheroes
        this.product = product;
//        this.alQuantity= q;
        this.context = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_product_for_customer, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Product product =  productList.get(position);






        holder.textViewName.setText(product.getProductName());
        holder.tvSize.setText(product.getProductSize());
        holder.tvOff.setText(product.getProductBrand());

        holder.tvUnitsell.setText("/ "+product.getProductUnit());



        holder.textSellingPrice.setText(Double.toString(product.getProductPrice()));







        Glide.with(context).load(getImage(product.getProductName())).into(holder.imageView);

    }


    public int getImage(String imageName) {

        int drawableResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        return drawableResourceId;
    }



    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView textViewName;


        public TextView textSellingPrice;
        public TextView tvQuantity;

        public TextView tvUnitsell;

        public TextView tvTotalPrice;
        public TextView tvOff;
        public TextView tvSize;
        public Button add;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.product_image);
            textViewName = (TextView) itemView.findViewById(R.id.product_name);


            textSellingPrice= (TextView) itemView.findViewById(R.id.selling_price);
            tvQuantity = (TextView) itemView.findViewById(R.id.product_category);

            tvUnitsell = (TextView) itemView.findViewById(R.id.unitsell);
            tvTotalPrice = (TextView)itemView.findViewById(R.id.product_subcategory);
            tvOff = (TextView)itemView.findViewById(R.id.product_brand);
            tvSize = (TextView)itemView.findViewById(R.id.size);
            add = (Button) itemView.findViewById(R.id.add);



            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.imageViewOnClick(v, getAdapterPosition());
                }
            });
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.buttonViewOnClick(v, getAdapterPosition());
                }
            });
        }
    }
}



