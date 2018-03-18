package sagar.khengat.supermarket.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;

public class Product implements Parcelable {
    @DatabaseField(canBeNull = true)

    private String productName;




    @DatabaseField(canBeNull = true)

    private String productUnit;


    @DatabaseField(canBeNull = true,id = true)

    private String productId;


    @DatabaseField(canBeNull = true)

    private String productSize;


    @DatabaseField(canBeNull = true)


    private double productOriginalPrice;
    @DatabaseField(canBeNull = true)


    private double productGstPrice;
    @DatabaseField(canBeNull = true)

    private int productQuantity;


    @DatabaseField(canBeNull = true)


    private double productTotalPrice;




    public double getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(double productToatalPrice) {
        this.productTotalPrice = productToatalPrice;
    }




    public double getProductGstPrice() {
        return productGstPrice;
    }

    public void setProductGstPrice(double productGstPrice) {
        this.productGstPrice = productGstPrice;
    }

    public double getProductOriginalPrice() {
        return productOriginalPrice;
    }

    public void setProductOriginalPrice(double productOriginalPrice) {
        this.productOriginalPrice = productOriginalPrice;
    }


    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }


    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }




    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }




    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }


    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productId);
        dest.writeInt(productQuantity);
        dest.writeString(productSize);
        dest.writeDouble(productOriginalPrice);
        dest.writeDouble(productGstPrice);
        dest.writeDouble(productTotalPrice);
        dest.writeString(productName);


        dest.writeString(productUnit);

    }
    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
    public Product(Parcel in) {
        productId = in.readString();
        productName = in.readString();

        productQuantity = in.readInt();
        productSize = in.readString();
        productUnit = in.readString();
        productOriginalPrice = in.readDouble();
        productTotalPrice = in.readDouble();
        productGstPrice = in.readDouble();
    }

    public Product() {
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", productUnit='" + productUnit + '\'' +
                ", productId=" + productId +
                ", productSize='" + productSize + '\'' +
                ", productOriginalPrice=" + productOriginalPrice +
                ", productGstPrice=" + productGstPrice +
                ", productQuantity=" + productQuantity +
                ", productTotalPrice=" + productTotalPrice +
                '}';
    }
}
