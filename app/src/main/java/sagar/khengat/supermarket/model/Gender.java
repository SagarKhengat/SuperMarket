package sagar.khengat.supermarket.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Sagar Khengat on 18/03/2018.
 */

public class Gender implements Parcelable {
    @DatabaseField(canBeNull = true)
    private String genderName;

    @DatabaseField(canBeNull = true,generatedId = true)
    private Integer genderId;


    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public Integer getGenderId() {
        return genderId;
    }

    public void setGenderId(Integer genderId) {
        this.genderId = genderId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(genderId);
        dest.writeString(genderName);

    }
    public static final Parcelable.Creator<Gender> CREATOR = new Parcelable.Creator<Gender>() {
        @Override
        public Gender createFromParcel(Parcel source) {
            return new Gender(source);
        }

        @Override
        public Gender[] newArray(int size) {
            return new Gender[size];
        }
    };

    public Gender(Parcel in) {
        genderId = in.readInt();
        genderName = in.readString();
    }
    @Override
    public String toString() {
        return "Gender{" +
                "genderName='" + genderName + '\'' +
                ", genderId='" + genderId + '\'' +
                '}';
    }

    public Gender() {
    }
}
