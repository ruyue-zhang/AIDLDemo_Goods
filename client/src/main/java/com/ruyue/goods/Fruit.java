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

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
