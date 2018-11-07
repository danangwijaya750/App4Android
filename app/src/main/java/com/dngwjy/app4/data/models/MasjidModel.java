package com.dngwjy.app4.data.models;

import com.google.gson.annotations.SerializedName;

public class MasjidModel {
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
}
