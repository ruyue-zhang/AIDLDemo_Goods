package com.ruyue.goods;

import android.os.Parcel;
import android.os.Parcelable;

public class Fruit implements Parcelable {
    private String imgLink;
    private String name;
    private String description;
    private int price;

    public Fruit() {
    }

    public Fruit(String imgLink, String name, String description, int price) {
        this.imgLink = imgLink;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    protected Fruit(Parcel in) {
        imgLink = in.readString();
        name = in.readString();
        description = in.readString();
        price = in.readInt();
    }

    public static final Creator<Fruit> CREATOR = new Creator<Fruit>() {
        @Override
        public Fruit createFromParcel(Parcel in) {
            return new Fruit(in);
        }

        @Override
        public Fruit[] newArray(int size) {
            return new Fruit[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imgLink);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(price);
    }
}
