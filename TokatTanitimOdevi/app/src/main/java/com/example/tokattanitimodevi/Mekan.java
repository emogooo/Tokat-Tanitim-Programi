package com.example.tokattanitimodevi;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Mekan implements Parcelable {
    Bitmap resim;
    String isim;
    String tarih;
    String aciklama;
    String kisaaciklama;

    public Mekan(Bitmap resim, String isim, String tarih, String aciklama, String kisaaciklama) {
        this.resim = resim;
        this.isim = isim;
        this.tarih = tarih;
        this.aciklama = aciklama;
        this.kisaaciklama = kisaaciklama;
    }

    protected Mekan(Parcel in) {
        resim = in.readParcelable(Bitmap.class.getClassLoader());
        isim = in.readString();
        tarih = in.readString();
        aciklama = in.readString();
        kisaaciklama = in.readString();
    }

    public static final Creator<Mekan> CREATOR = new Creator<Mekan>() {
        @Override
        public Mekan createFromParcel(Parcel in) {
            return new Mekan(in);
        }

        @Override
        public Mekan[] newArray(int size) {
            return new Mekan[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(resim, flags);
        dest.writeString(isim);
        dest.writeString(tarih);
        dest.writeString(aciklama);
        dest.writeString(kisaaciklama);
    }
}
