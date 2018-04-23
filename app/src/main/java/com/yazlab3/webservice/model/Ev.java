package com.yazlab3.webservice.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Ev implements Parcelable{

    private int id;
    private String il;
    private String emlakTipi;
    private int alan;
    private int odaSayisi;
    private int binaYasi;
    private int bulKat;
    private int fiyat;
    private String aciklama;
    public List<Resim> resimler;

    public Ev(){

    }

    public Ev(int id, String il, String emlakTipi, int alan, int odaSayisi, int binaYasi, int bulKat, int fiyat, String aciklama) {
        this.id = id;
        this.il = il;
        this.emlakTipi = emlakTipi;
        this.alan = alan;
        this.odaSayisi = odaSayisi;
        this.binaYasi = binaYasi;
        this.bulKat = bulKat;
        this.fiyat = fiyat;
        this.aciklama = aciklama;
    }

    protected Ev(Parcel in) {
        id = in.readInt();
        il = in.readString();
        emlakTipi = in.readString();
        alan = in.readInt();
        odaSayisi = in.readInt();
        binaYasi = in.readInt();
        bulKat = in.readInt();
        fiyat = in.readInt();
        aciklama = in.readString();
    }

    public static final Creator<Ev> CREATOR = new Creator<Ev>() {
        @Override
        public Ev createFromParcel(Parcel in) {
            return new Ev(in);
        }

        @Override
        public Ev[] newArray(int size) {
            return new Ev[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIl() {
        return il;
    }

    public void setIl(String il) {
        this.il = il;
    }

    public String getEmlakTipi() {
        return emlakTipi;
    }

    public void setEmlakTipi(String emlakTipi) {
        this.emlakTipi = emlakTipi;
    }

    public int getAlan() {
        return alan;
    }

    public void setAlan(int alan) {
        this.alan = alan;
    }

    public int getOdaSayisi() {
        return odaSayisi;
    }

    public void setOdaSayisi(int odaSayisi) {
        this.odaSayisi = odaSayisi;
    }

    public int getBulKat() {
        return bulKat;
    }

    public void setBulKat(int bulKat) {
        this.bulKat = bulKat;
    }

    public int getFiyat() {
        return fiyat;
    }

    public void setFiyat(int fiyat) {
        this.fiyat = fiyat;
    }

    public int getBinaYasi() {
        return binaYasi;
    }

    public void setBinaYasi(int binaYasi) {
        this.binaYasi = binaYasi;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public List<Resim> getResimler() {
        return resimler;
    }

    public void addResim(Resim resim) {
        resimler.add(resim);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(il);
        dest.writeString(emlakTipi);
        dest.writeInt(alan);
        dest.writeInt(odaSayisi);
        dest.writeInt(binaYasi);
        dest.writeInt(bulKat);
        dest.writeInt(fiyat);
        dest.writeString(aciklama);
    }
}
