package com.yazlab3.webservice.model;


import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Resim implements Parcelable{

    private int id;
    private String dizin;
    private Bitmap bitmap;
    private int evID;

    protected Resim(Parcel in) {
        id = in.readInt();
        dizin = in.readString();
        bitmap = in.readParcelable(Bitmap.class.getClassLoader());
        evID = in.readInt();
    }
    public Resim(){

    }

    public static final Creator<Resim> CREATOR = new Creator<Resim>() {
        @Override
        public Resim createFromParcel(Parcel in) {
            return new Resim(in);
        }

        @Override
        public Resim[] newArray(int size) {
            return new Resim[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDizin() {
        return dizin;
    }

    public void setDizin(String dizin) {
        this.dizin = dizin;
    }

    public int getEvID() {
        return evID;
    }

    public void setEvID(int evID) {
        this.evID = evID;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(dizin);
        dest.writeParcelable(bitmap, flags);
        dest.writeInt(evID);
    }
}
