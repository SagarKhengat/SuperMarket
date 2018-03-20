package sagar.khengat.supermarket.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;


public class Cart implements Parcelable {
    @DatabaseField(canBeNull = true)

    private String productName;

    @DatabaseField(canBeNull = true)

    private String productUnit;


    @DatabaseField(canBeNull = true,generatedId = true)

    private int productId;


    @DatabaseField(canBeNull = true)

    private String productSize;


    @DatabaseField(canBeNull = true)

    private String productBrand;

    @DatabaseField(canBeNull = true)


    private double productPrice;
    @DatabaseField(canBeNull = true)

    private int productQuantity;


    @DatabaseField(canBeNull = true)


    private double productTotalPrice;

    @DatabaseField(canBeNull = true)

    private Integer productCartId;


    @DatabaseField(foreign=true, foreignAutoRefresh=true, canBeNull=true,
            maxForeignAutoRefreshLevel=3)

    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getProductCartId() {
        return productCartId;
    }

    public void setProductCartId(int productCartId) {
        this.productCartId = productCartId;
    }


    public double getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(double productToatalPrice) {
        this.productTotalPrice = productToatalPrice;
    }


    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public void setProductCartId(Integer productCartId) {
        this.productCartId = productCartId;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productGstPrice) {
        this.productPrice = productGstPrice;
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




    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
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
        dest.writeInt(productId);
        dest.writeInt(productQuantity);
        dest.writeString(productSize);
        dest.writeInt(productCartId);

        dest.writeDouble(productPrice);
        dest.writeDouble(productTotalPrice);
        dest.writeString(productName);


        dest.writeValue(customer);

        dest.writeString(productUnit);


    }
    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel source) {
            return new Cart(source);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };
    public Cart(Parcel in) {
        productId = in.readInt();
        productName = in.readString();
        productCartId = in.readInt();





        customer = (Customer) in.readValue(Product.class.getClassLoader());
        productQuantity = in.readInt();
        productSize = in.readString();
        productUnit = in.readString();

        productTotalPrice = in.readDouble();
        productPrice = in.readDouble();
    }

    public Cart() {
    }

    @Override
    public String toString() {
        return "Cart{" +
                "productName='" + productName + '\'' +
                ", productUnit='" + productUnit + '\'' +
                ", productCartId='" + productCartId + '\'' +
                ", productId=" + productId +
                ", productSize='" + productSize + '\'' +
                ", productPrice=" + productPrice +
                ", productQuantity=" + productQuantity +
                ", productTotalPrice=" + productTotalPrice +
                ", customer=" + customer +
                '}';
    }
}
