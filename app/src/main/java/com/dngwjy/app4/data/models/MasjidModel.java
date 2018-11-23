package com.dngwjy.app4.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MasjidModel implements Parcelable {
    @SerializedName("id")
    String id;
    @SerializedName("nama_masjid")
    String nama_masjid;
    @SerializedName("alamat")
    String alamat;
    @SerializedName("image")
    String image;
    @SerializedName("verified")
    String verified;
    @SerializedName("longitude")
    double longitude;
    @SerializedName("latitude")
    double latitude;

    protected MasjidModel(Parcel in) {
        id = in.readString();
        nama_masjid = in.readString();
        alamat = in.readString();
        image = in.readString();
        verified = in.readString();
        longitude = in.readDouble();
        latitude = in.readDouble();
    }

    public static final Creator<MasjidModel> CREATOR = new Creator<MasjidModel>() {
        @Override
        public MasjidModel createFromParcel(Parcel in) {
            return new MasjidModel(in);
        }

        @Override
        public MasjidModel[] newArray(int size) {
            return new MasjidModel[size];
        }
    };

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama_masjid() {
        return nama_masjid;
    }

    public void setNama_masjid(String nama_masjid) {
        this.nama_masjid = nama_masjid;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nama_masjid);
        dest.writeString(alamat);
        dest.writeString(image);
        dest.writeString(verified);
        dest.writeDouble(longitude);
        dest.writeDouble(latitude);
    }
}
